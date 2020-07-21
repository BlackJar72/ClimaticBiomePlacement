package jaredbgreat.climaticbiome.generation.map;

import static jaredbgreat.climaticbiome.util.ModMath.modRight;
import jaredbgreat.climaticbiome.biomes.SubBiomeRegistry;
import jaredbgreat.climaticbiome.generation.biomeprovider.BiomeBasin;
import jaredbgreat.climaticbiome.generation.biomeprovider.MapMaker;
import jaredbgreat.climaticbiome.generation.biomeprovider.TerrainType;
import jaredbgreat.climaticbiome.generation.cache.Cache;
import jaredbgreat.climaticbiome.generation.cache.Coords;
import jaredbgreat.climaticbiome.generation.chunk.BasinNode;
import jaredbgreat.climaticbiome.util.SpatialHash;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import net.minecraft.init.Biomes;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;


/**
 * This is a class for storing and handling biome maps.  It 
 * has nothing to do with Forge registries, its just the 
 * best name I came up with.  Essentially, its acts more as 
 * a manager that handle a a cache of maps in use while 
 * coordinating the generation of new maps and the loading of 
 * stored maps. 
 * 
 * @author JaredBGreat
 */
public class MapRegistry extends AbstractMapRegistry implements IMapRegistry {
	private static final String SETTINGS = "settings";
	private final Cache<RegionMap> data;
	private final SubBiomeRegistry subbiomes;
	
    private final SpatialHash chunkNoise;
    private final SpatialHash regionNoise;
    private final SpatialHash biomeNoise;
    
    private int dataSize;
    private int cWidth;
    private int bWidth;
    private int cOffset;
    private int bOffset;    
    
    private final MapMaker maker;
	
	
	public MapRegistry(long seed, World w) {
		super(w);
		cWidth = MapMaker.RSIZE * settings.regionSize.whole;
		bWidth = cWidth * 16;
		dataSize = cWidth * cWidth;
		cOffset = cWidth / 2;
		bOffset = bWidth / 2;
		data = new Cache<>();
		subbiomes = SubBiomeRegistry.getSubBiomeRegistry();
        Random random = new Random(seed);
        chunkNoise = new SpatialHash(random.nextLong(), random.nextLong());
        regionNoise = new SpatialHash(random.nextLong(), random.nextLong());
        biomeNoise = new SpatialHash(random.nextLong(), random.nextLong());
        maker = new MapMaker(chunkNoise, regionNoise, biomeNoise, settings);
	}
	
	
	/**
	 * Return the map at the given coordinates x and z 
	 * where x and z are on the scale of whole maps -- 
	 * this is, it will return the map for at that coordinate 
	 * in the grid.
	 * 
	 * Note that the grid of maps is shifted by half a map to 
	 * allow the world origin (block 0,y,0) to be in the center 
	 * of map 0,0. 
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
	private RegionMap getMap(int x, int z) {
		//System.out.println("{" + x + ", " + z + "}");
		RegionMap out = data.get(x, z);
		if(out == null) {
			out = new RegionMap(x, z, cWidth);
			readMap(out);
			data.add(out);
		}
		//System.out.println("Accessing Map: " + x + ", " + z);
		return out;
	}
	
	
	/**
	 * Gets the map for the chunk coordinates x,z.
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
	private RegionMap getMapFromChunkCoord(int x, int z) {
		return getMap(Math.floorDiv(x + cOffset, cWidth), 
				      Math.floorDiv(z + cOffset, cWidth));
	}
	
	
	/* (non-Javadoc)
	 * @see jaredbgreat.climaticbiome.generation.map.IMapRegistry#chunkToMap(int)
	 */
	@Override
	public int chunkToMap(int c) {
		return (c + cOffset) / cWidth;
	}
	
	
	/* (non-Javadoc)
	 * @see jaredbgreat.climaticbiome.generation.map.IMapRegistry#blockToMap(int)
	 */
	@Override
	public int blockToMap(int c) {
		return (c + bOffset) / bWidth;
	}
	
	/**
	 * Creates a new map.  Currently using a stand in, 
	 * but this will ultimately pass the map to the generator, 
	 * which will then generate it.
	 * 
	 * @param map
	 */
	private void initializeMap(RegionMap map) {		
		maker.generate(map, world);
		if(cansave) writeMap(map);
	}
	
	
	private void readMap(RegionMap map) {
		Coords coords = map.getCoords();
		int x = coords.getX();
		int z = coords.getZ();
		if(!cansave) {
			return;
		}
		File file = getSaveFile(x, z);
		int[] data = map.getData();
		if(file != null && file.exists()) {
			try {				
				FileInputStream fs = new FileInputStream(file);
				for(int i = 0; i < dataSize; i++) {
						data[i] = (short)fs.read();
						data[i] |= (fs.read() << 8);
					}
				fs.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		} else {
			initializeMap(map);
		}
	}
	
	
	private void writeMap(RegionMap map) {
		Coords coords = map.getCoords();
		int x = coords.getX();
		int z = coords.getZ();
		File file = getSaveFile(x, z);
		if(file != null && !file.exists()) { 
			int[] data = map.getData();
			//System.out.println("Writing Data: " + RegionMap.otherHash(data));
			//Hasher hasher = new Hasher();
			try {
				FileOutputStream fs = new FileOutputStream(file);
				for(int i = 0; i < dataSize; i++) {
						fs.write(data[i] & 0xff);
						//hasher.next(data[i] & 0xff);
						fs.write((data[i] & 0xff00) >> 8);
						//hasher.next((data[i] & 0xff00) >> 8);
					}
				//System.out.println("Wrote Data: " + hasher.getHash());
				fs.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
		
	
	/**
	 * Return the biome ID as an int for the the given x,z chunk coordinates.
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
	private int getBiomeIDChunk(int x, int z) {
		return getMapFromChunkCoord(x, z)
				.getBiome(modRight(x + cOffset, cWidth), 
						  modRight(z + cOffset, cWidth));
	}
	
	
	/**
	 * Return the biome ID as an int for the the given x,z chunk coordinates.
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
	private int getGenIDChunk(int x, int z) {
		return getMapFromChunkCoord(x, z)
				.getFullBiome(modRight(x + cOffset, cWidth), 
						        modRight(z + cOffset, cWidth));
	}
		
	
	/* (non-Javadoc)
	 * @see jaredbgreat.climaticbiome.generation.map.IMapRegistry#getBiomeChunk(int, int)
	 */
	@Override
	public Biome getBiomeChunk(int x, int z) {
		return getFullBiome(getMapFromChunkCoord(x, z)
				.getBiome(modRight(x + cOffset, cWidth), 
						  modRight(z + cOffset, cWidth) & 0xff));
	}
    
	
/*--------------------------------------------------------------------------------------*
 *                   PRACTICAL METHODS FOR GETTING THE BIOME ARRAYS                     *
 *--------------------------------------------------------------------------------------*/
    
    
	/* (non-Javadoc)
	 * @see jaredbgreat.climaticbiome.generation.map.IMapRegistry#getChunkBiomeGrid(int, int, net.minecraft.world.biome.Biome[])
	 */
    @Override
	public Biome[] getChunkBiomeGrid(int x, int z, Biome[] in) {
    	int[] tiles = new int[9];
    	//System.out.println("[" + x + ", " + z + "]");
    	BiomeBasin[][] basins = new BiomeBasin[3][3];
    	for(int i = 0; i < tiles.length; i++) {
    		int x1 = (i / 3);
    		int z1 = (i % 3);   
    		int x2 = x + x1;
    		int z2 = z + z1;  
    		tiles[i] = getBiomeIDChunk(x2, z2);
    		basins[i / 3][i % 3] = new BiomeBasin(
    				(x1 * 16) + (chunkNoise.intFor(x2, z2, 10) % 16),
    				(z1 * 16) + (chunkNoise.intFor(x2, z2, 11) % 16),
    				tiles[i], 1.0 + chunkNoise.doubleFor(x2, z2, 12));    				
    	}
    	for(int i = 0; i < 16; i++)
    		for(int j = 0; j < 16; j++) {
    			in[(j * 16) + i] = Biome.getBiome(BiomeBasin.summateEffect(basins, 16 + i, 16 + j),
    					Biomes.DEFAULT);
    		}
    	return in;
    }
    
    
	/**
	 * Returns a biome array starting at chunk coordinates x,z, 
	 * or more precisely block coordinates (x*16),(z*16), and 
	 * extending for h by w blocks.  For good results h and w 
	 * should be multiples of 16, allowing for nice, resulting 
	 * in arrays the fit and fill a set of chunks exactions.  
	 * What is essential is that the area represented by the 
	 * biome array will always begin on chunk boundaries.
	 * 
	 * @param x
	 * @param z
	 * @param h
	 * @param w
	 * @return
	 */
    private Biome[] getBiomeGrid(int x, int z, int h, int w) {
    	int ch = ((h - 1) / 16) + 3;
    	int cw = ((w - 1) / 16) + 3;
    	int numc = ch * cw;
    	Biome[] out = new Biome[h * w];
    	
    	int[] tiles = new int[numc];
    	BiomeBasin[][] basins = new BiomeBasin[ch][cw];
    	for(int i = 0; i < tiles.length; i++) {
    		int x1 = (i / cw);
    		int z1 = (i % cw); 
    		int x2 = x + x1; 
    		int z2 = z + z1; 
    		tiles[i] = getBiomeIDChunk(x2, z2);
    		basins[x1][z1] = new BiomeBasin(
    				(x1 * 16) + (chunkNoise.intFor(x2, z2, 10) % 16),
    				(z1 * 16) + (chunkNoise.intFor(x2, z2, 11) % 16),
    				tiles[i], 1.0 + chunkNoise.doubleFor(x2, z2, 12));    				
    	}
    	for(int i = 0; i < w; i++)
    		for(int j = 0; j < h; j++) {
    			out[(j * w) + i] = Biome.getBiome(BiomeBasin
    						.summateEffect(basins, 16 + i, 16 + j),
    					Biomes.DEFAULT);
    		}
    	return out;
    }
    
    
    /* (non-Javadoc)
	 * @see jaredbgreat.climaticbiome.generation.map.IMapRegistry#getUnalignedBiomeGrid(int, int, int, int, net.minecraft.world.biome.Biome[])
	 */
    @Override
	public Biome[] getUnalignedBiomeGrid(int x, int z, int h, int w, Biome[] in) {
    	int hOff = modRight(x, 16);
    	int wOff = modRight(z, 16);
    	int h1 = h + hOff;
    	int w1 = w + wOff;
    	int h2 = h1 + (16 - h1 % 16);
    	int w2 = w1 + (16 - w1 % 16);
    	Biome[] tmp = getBiomeGrid(x / 16, z /  16, h2, w2);
    	for(int i = 0; i < h; i++) 
    		for(int j = 0; j < w; j++) {
    			in[(j * w) + i] 
    					= tmp[((j + hOff) * w2) + wOff + i];
    		}    	
    	return in;
    }
	
	// TODO: Methods that can be used to gain pseudo-biome arrays 
    //       and/or accurate block pseudo-biome.
    
	
/*--------------------------------------------------------------------------------------*
 *                 METHODS FOR GETTING THE BIOME ARRAYS FOR GENERATION                  *
 *--------------------------------------------------------------------------------------*/
	
	
	private Biome getFullBiome(int id) {
		Biome out;
		if(id < 256) {
			return Biome.getBiome(id, Biomes.DEFAULT);
		} else {
			out = subbiomes.get(id);
			if(noFakes || (out == null)) {
				out = Biome.getBiome(id & 0xff, Biomes.DEFAULT);
			}
			return out;
		}
	}
    
    
	/* (non-Javadoc)
	 * @see jaredbgreat.climaticbiome.generation.map.IMapRegistry#getChunkBiomeGen(int, int, net.minecraft.world.biome.Biome[])
	 */
    @Override
	public Biome[] getChunkBiomeGen(int x, int z, Biome[] in) {
    	int[] tiles = new int[9];
    	BiomeBasin[][] basins = new BiomeBasin[3][3];
    	for(int i = 0; i < tiles.length; i++) {
    		int x1 = (i / 3);
    		int z1 = (i % 3);   
    		int x2 = x + x1;
    		int z2 = z + z1;    		 		
    		tiles[i] = getGenIDChunk(x2, z2);
    		basins[i / 3][i % 3] = new BiomeBasin(
    				(x1 * 16) + (chunkNoise.intFor(x2, z2, 10) % 16),
    				(z1 * 16) + (chunkNoise.intFor(x2, z2, 11) % 16),
    				tiles[i], 1.0 + chunkNoise.doubleFor(x2, z2, 12));    				
    	}
    	for(int i = 0; i < 16; i++)
    		for(int j = 0; j < 16; j++) {
    			in[(j * 16) + i] = getFullBiome(BiomeBasin.summateEffect(basins, 16 + i, 16 + j));
    		}
    	return in;
    }
    
    
	/**
	 * Returns a biome array starting at chunk coordinates x,z, 
	 * or more precisely block coordinates (x*16),(z*16), and 
	 * extending for h by w blocks.  For good results h and w 
	 * should be multiples of 16, allowing for nice, resulting 
	 * in arrays the fit and fill a set of chunks exactions.  
	 * What is essential is that the area represented by the 
	 * biome array will always begin on chunk boundaries.
	 * 
	 * @param x
	 * @param z
	 * @param h
	 * @param w
	 * @return
	 */
    private Biome[] getBiomeGenGrid(int x, int z, int h, int w) {
    	int ch = ((h - 1) / 16) + 3;
    	int cw = ((w - 1) / 16) + 3;
    	int numc = ch * cw;
    	Biome[] out = new Biome[h * w];
    	
    	int[] tiles = new int[numc];
    	BiomeBasin[][] basins = new BiomeBasin[ch][cw];
    	for(int i = 0; i < tiles.length; i++) {
    		int x1 = (i / cw);
    		int z1 = (i % cw); 
    		int x2 = x + x1; 
    		int z2 = z + z1; 
    		tiles[i] = getGenIDChunk(x2, z2);
    		basins[x1][z1] = new BiomeBasin(
    				(x1 * 16) + (chunkNoise.intFor(x2, z2, 10) % 16),
    				(z1 * 16) + (chunkNoise.intFor(x2, z2, 11) % 16),
    				tiles[i], 1.0 + chunkNoise.doubleFor(x2, z2, 12));    				
    	}
    	for(int i = 0; i < w; i++)
    		for(int j = 0; j < h; j++) {
    			out[(j * w) + i] = getFullBiome(BiomeBasin
    						.summateEffect(basins, 16 + i, 16 + j));
    		}
    	return out;
    }
    
    
    @Override
	public float[] getTerrainBiomeGen(int x, int z, float[] in) {
    	//Biome[] tiles = new Biome[49];
    	float[][] tiles = new float[49][];
    	BasinNode[] heights = new BasinNode[49];
    	BasinNode[] scales  = new BasinNode[49];
    	//System.out.println();
    	for(int i = 0; i < tiles.length; i++) {
    		int x1 = (i / 7);
    		int z1 = (i % 7);   
    		int x2 = x + x1;
    		int z2 = z + z1;
        	//System.out.println(x2 + ", " + z2);
    		//tiles[i] = makeTerainFloatuple(x2, z2);//getBiomeChunk(x2, z2);
    		tiles[i] = makeTerainFloatuple(x2, z2);
    		//if(tiles[i][0] > 0) System.out.println(tiles[i]);
    		heights[i] = new BasinNode(
    				(x1 * 16) + (chunkNoise.intFor(x2, z2, 10) % 16),
    				(z1 * 16) + (chunkNoise.intFor(x2, z2, 11) % 16),
    				tiles[i][0]/*tiles[i].getBaseHeight()*/, 
    				(1.0 + chunkNoise.doubleFor(x2, z2, 12)));
    		scales[i] = new BasinNode(
    				(x1 * 16) + (chunkNoise.intFor(x2, z2, 10) % 16),
    				(z1 * 16) + (chunkNoise.intFor(x2, z2, 11) % 16),
    				tiles[i][1]/*tiles[i].getHeightVariation()*/, 
    				(1.0 + chunkNoise.doubleFor(x2, z2, 12)));    				
    	}
    	for(int i = 0; i < 16; i++)
    		for(int j = 0; j < 16; j++) {
    			in[(i * 16) + j] = 
    					(float)BasinNode.summateEffect(heights, 16 + i, 16 + j);
    			in[(i * 16) + j + 256] =  
    					(float)BasinNode.summateEffect(scales, 16 + i, 16 + j);
    		}
    	return in;
    }
    
    /**
     * This is a stand-in to test chunk averaging. 
     * 
     * @param x
     * @param z
     * @return
     */
    private float[] makeTerainFloatuple(int x, int z) {
    	if(isSteep(x, z)) {
    		return getHeightData(x, z);
    	}
    	float[] out = new float[2];
		out[0]  = (getBaseHeight(x - 1, z - 1)
				+ getBaseHeight(x - 1, z)
				+ getBaseHeight(x - 1, z + 1)
				+ getBaseHeight(x, z - 1)
				+ getBaseHeight(x - 1, z + 1)
				+ getBaseHeight(x + 1, z - 1)
				+ getBaseHeight(x + 1, z)
				+ getBaseHeight(x - 1, z + 1)) / 8.0f;
		out[1]  = (getHeightScale(x - 1, z - 1)
				+ getHeightScale(x - 1, z)
				+ getHeightScale(x - 1, z + 1)
				+ getHeightScale(x, z - 1)
				+ getHeightScale(x - 1, z + 1)
				+ getHeightScale(x + 1, z - 1)
				+ getHeightScale(x + 1, z)
				+ getHeightScale(x - 1, z + 1)
				+ getHeightScale(x, z)) / 9.0f;
    	out[0] = (out[0] + getBiomeChunk(x, z).getBaseHeight()) / 2.0f;
    	return out;
    }
    
    
    /**
     * Returns height data if any.  If there is none 
     * it will return -1.
     * 
     * @param x relative chunk x within region
     * @param z relative chunk x within region
     * @return The biome id as in int
     */
    public float getBaseHeight(int x, int z) {
		return getMapFromChunkCoord(x, z)
				.getBaseHeight(modRight(x + cOffset, cWidth), 
						       modRight(z + cOffset, cWidth));
	}
    
    
    /**
     * Returns scale data if any.  If there is none 
     * it will return -1.
     * 
     * @param x relative chunk x within region
     * @param z relative chunk x within region
     * @return The biome id as in int
     */
    public float getHeightScale(int x, int z) {
		return getMapFromChunkCoord(x, z)
				.getHeightScale(modRight(x + cOffset, cWidth), 
						        modRight(z + cOffset, cWidth));
	}
    
    
    /**
     * Returns the terrain type.  If there is none 
     * it will return STEEP (vanilla but don't average).
     * 
     * @param x relative chunk x within region
     * @param z relative chunk x within region
     * @return The biome id as in int
     */
    public TerrainType getTerrainType(int x, int z) {
		return getMapFromChunkCoord(x, z)
				.getTerrainType(modRight(x + cOffset, cWidth), 
						        modRight(z + cOffset, cWidth));
	}
    
    
    /**
     * Returns true if the terrain type is STEEP.  
     * If there is none TRUE.
     * 
     * @param x relative chunk x within region
     * @param z relative chunk x within region
     * @return The biome id as in int
     */
    public boolean isSteep(int x, int z) {
		return getMapFromChunkCoord(x, z)
				.isSteep(modRight(x + cOffset, cWidth), 
						 modRight(z + cOffset, cWidth));
	}
    
    
    public float[] getHeightData(int x, int z) {
		return getMapFromChunkCoord(x, z)
				.getHeightData(modRight(x + cOffset, cWidth), 
						       modRight(z + cOffset, cWidth));
	}
    


	/* (non-Javadoc)
	 * @see jaredbgreat.climaticbiome.generation.map.IMapRegistry#cleanCaches()
	 */
	@Override
	public void cleanCaches() {
		data.cleanup();		
	}
	

}
