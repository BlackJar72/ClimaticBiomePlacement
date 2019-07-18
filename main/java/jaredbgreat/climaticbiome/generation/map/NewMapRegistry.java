package jaredbgreat.climaticbiome.generation.map;

import static jaredbgreat.climaticbiome.util.ModMath.modRight;
import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.biomes.SubBiomeRegistry;
import jaredbgreat.climaticbiome.generation.cache.Cache;
import jaredbgreat.climaticbiome.generation.cache.Coords;
import jaredbgreat.climaticbiome.generation.generator.BiomeBasin;
import jaredbgreat.climaticbiome.generation.generator.MapMaker;
import jaredbgreat.climaticbiome.util.SpatialNoise;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.DimensionManager;

public class NewMapRegistry implements IMapRegistry {
	private final Cache<NewRegionMap> data;
	private final SubBiomeRegistry subbiomes;
	
    private final SpatialNoise chunkNoise;
    private final SpatialNoise regionNoise;
    private final SpatialNoise biomeNoise;
    
    public final int dataSize;
    public final int cWidth;
    public final int bWidth;
    public final int cOffset;
    public final int bOffset;    
    
    private final MapMaker maker;
    private World world;
    private File savedir =  null;
    private boolean cansave = true;
    
    private boolean noFakes;

	public NewMapRegistry(long seed, World w) {
		cWidth = MapMaker.RSIZE * ConfigHandler.regionSize.whole;
		bWidth = cWidth * 16;
		dataSize = cWidth * cWidth;
		cOffset = cWidth / 2;
		bOffset = bWidth / 2;
		data = new Cache<>();
		subbiomes = SubBiomeRegistry.getSubBiomeRegistry();
        Random random = new Random(seed);
        chunkNoise = new SpatialNoise(random.nextLong(), random.nextLong());
        regionNoise = new SpatialNoise(random.nextLong(), random.nextLong());
        biomeNoise = new SpatialNoise(random.nextLong(), random.nextLong());
        maker = new MapMaker(chunkNoise, regionNoise, biomeNoise);
        noFakes = areFakesInvalid();
        world = w;
	}
	
	
	private final boolean areFakesInvalid() {
		return net.minecraftforge.fml.common.Loader.isModLoaded("lostcities") 
        		&& ConfigHandler.chunkProvider.equalsIgnoreCase("lostcities");
	}
	
	
	public void findSaveDir() {
		if(world == null || world.getMinecraftServer() == null) {
			cansave = false;
			return;
		}
		if(world.getMinecraftServer().isDedicatedServer()) {
			savedir = world.getMinecraftServer().getFile("world" + File.separator + "ClimaticMaps" 
								   + File.separator + "Dim" + world.provider.getDimension());
		} else {
			savedir = new File(DimensionManager.getCurrentSaveRootDirectory().toString() 
						+ File.separator + "ClimaticMaps" 
						+ File.separator + "Dim" 
						+ world.provider.getDimension());
		}
		cansave = savedir != null;
		if(cansave && (!savedir.exists())) {
			savedir.mkdirs();
			cansave = savedir.exists() && savedir.isDirectory();
		}
	}
	
	
	private File getSaveFile(int x, int z) {
		if(savedir == null) {
			findSaveDir();
		}
		if(savedir == null) {
			cansave = false;
			return null;
		}
		return new File(savedir.toString() + File.separator + "X" + x + "Z" + z + ".cbmap");
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
	private NewRegionMap getMap(int x, int z) {
		//System.out.println("{" + x + ", " + z + "}");
		NewRegionMap out = data.get(x, z);
		if(out == null) {
			out = new NewRegionMap(x, z, cWidth);
			readMap(out);
			data.add(out);
		}
		//System.out.println("Accessing Map: " + x + ", " + z);
		return out;
	}
	
	
	/**
	 * Returns the map for block coordinates of x,z -- 
	 * no y coordinate is used at the entire column of
	 * blocks will be in the same map.
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
	private NewRegionMap getMapFromBlockCoord(int x, int z) {
		return getMap((x + bOffset) / bWidth, 
				      (z + bOffset) / bWidth);
	}
	
	
	/**
	 * Gets the map for the chunk coordinates x,z.
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
	private NewRegionMap getMapFromChunkCoord(int x, int z) {
		return getMap((x + cOffset) / cWidth, 
				      (z + cOffset) / cWidth);
	}
	
	
	public int chunkToMap(int c) {
		return (c + cOffset) / cWidth;
	}
	
	
	public int blockToMap(int c) {
		return (c + bOffset) / cWidth;
	}
	
	/**
	 * Creates a new map.  Currently using a stand in, 
	 * but this will ultimately pass the map to the generator, 
	 * which will then generate it.
	 * 
	 * @param map
	 */
	private void initializeMap(NewRegionMap map) {		
		maker.generate(map, world);
		if(cansave) writeMap(map);
	}
	
	
	private void readMap(NewRegionMap map) {		
		Coords coords = map.getCoords();
		int x = coords.getX();
		int z = coords.getZ();
		if(!cansave) {
			return;
		}
		File file = getSaveFile(x, z);
		long[] data = map.getData();
		if(file != null && file.exists()) {
			if(file.length() < (dataSize * 4)) {
				convertMap(map, file);
			} else try {				
				FileInputStream fs = new FileInputStream(file);
				for(int i = 0; i < dataSize; i++) {
						data[i] = (short)fs.read();
						data[i] |= (fs.read() << 8);
						data[i] |= (fs.read() << 16);
						data[i] |= (fs.read() << 24);
						data[i] |= (fs.read() << 32);
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
	
	
	private void convertMap(NewRegionMap map, File file) {
		Coords coords = map.getCoords();
		int x = coords.getX();
		int z = coords.getZ();
		long[] data = map.getData();
		// Load old format map with modifications
		try {				
			FileInputStream fs = new FileInputStream(file);
			for(int i = 0; i < dataSize; i++) {
					data[i] = (short)fs.read();
					data[i] |= (fs.read() << 32);
				}
			fs.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Re-save in new format
		try {
			FileOutputStream fs = new FileOutputStream(file);
			for(int i = 0; i < dataSize; i++) {
					fs.write((int)(data[i]  & 0xffL));
					fs.write((int)((data[i] & 0xff00L) >> 8));
					fs.write((int)((data[i] & 0xff0000L) >> 16));
					fs.write((int)((data[i] & 0xff000000L) >> 24));
					fs.write((int)((data[i] & 0xff00000000L) >> 32));
				}
			//System.out.println("Wrote Data: " + hasher.getHash());
			fs.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void writeMap(NewRegionMap map) {
		Coords coords = map.getCoords();
		int x = coords.getX();
		int z = coords.getZ();
		File file = getSaveFile(x, z);
		if(file != null && !file.exists()) { 
			long[] data = map.getData();
			//System.out.println("Writing Data: " + NewRegionMap.otherHash(data));
			//Hasher hasher = new Hasher();
			try {
				FileOutputStream fs = new FileOutputStream(file);
				for(int i = 0; i < dataSize; i++) {
						fs.write((int)(data[i]  & 0xffL));
						fs.write((int)((data[i] & 0xff00L) >> 8));
						fs.write((int)((data[i] & 0xff0000L) >> 16));
						fs.write((int)((data[i] & 0xff000000L) >> 24));
						fs.write((int)((data[i] & 0xff00000000L) >> 32));
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
	 * A class for hashing data that is streamed through, 
	 * purely for debugging.
	 */
	private class Hasher {
    	int hash = 0;
    	int count = 0;
    	public void next(int b) {
	    	hash ^= (b & 0xff) << (8 * count);
	    	hash ^= hash << 13;
	    	hash ^= hash >> 5;
	    	hash ^= hash << 17;
	    	count = (++count) % 4;
		}
    	public int getHash() {
    		return hash;
    	}
	}
		
	
	/**
	 * Return the biome ID as an int for the the given x,z chunk coordinates.
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
	private long getBiomeIDChunk(int x, int z) {
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
	private long getGenIDChunk(int x, int z) {
		return getMapFromChunkCoord(x, z)
				.getFullBiome(modRight(x + cOffset, cWidth), 
						        modRight(z + cOffset, cWidth));
	}
		
	
	/**
	 * Return the biome for the the given x,z block coordinates.
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
	public Biome getBiomeChunk(int x, int z) {
		return Biome.getBiome((int)getMapFromChunkCoord(x, z)
				.getBiome(modRight(x + cOffset, cWidth), 
						  modRight(z + cOffset, cWidth)));
	}
    
	
/*--------------------------------------------------------------------------------------*
 *                   PRACTICAL METHODS FOR GETTING THE BIOME ARRAYS                     *
 *--------------------------------------------------------------------------------------*/
    
    
	/**
	 * Returns a biome array for the chunk at chunk 
	 * coordinates x,z.
	 * 
	 * @param x
	 * @param z
	 * @param h
	 * @param w
	 * @return
	 */
    public Biome[] getChunkBiomeGrid(int x, int z, Biome[] in) {
    	long[] tiles = new long[9];
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
    				i, 1.0 + chunkNoise.doubleFor(x2, z2, 12));    				
    	}
    	for(int i = 0; i < 16; i++)
    		for(int j = 0; j < 16; j++) {
    			in[(j * 16) + i] = Biome.getBiome((int)tiles[BiomeBasin
    			                        .summateEffect(basins, 16 + i, 16 + j)],
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
    	
    	long[] tiles = new long[numc];
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
    				i, 1.0 + chunkNoise.doubleFor(x2, z2, 12));    				
    	}
    	for(int i = 0; i < w; i++)
    		for(int j = 0; j < h; j++) {
    			out[(j * w) + i] = Biome.getBiome((int)tiles[BiomeBasin
    						.summateEffect(basins, 16 + i, 16 + j)],
    					Biomes.DEFAULT);
    		}
    	return out;
    }
    
    
    /**
     * This will return a biome array for an area starting at 
     * an arbitrary block coordinates of x,z (y not being 
     * important).  This is the preferred method when not generating 
     * the initial array for a new chunk, as it does not assume 
     * anything that could skew the results in either dimension. 
     * 
     * For generating the array for a new chunk getChunkBiomeArray() 
     * should be used as it is more optimized.
     * 
     * @param x
     * @param z
     * @param h
     * @param w
     * @return
     */
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
    
	
/*--------------------------------------------------------------------------------------*
 *                 METHODS FOR GETTING THE BIOME ARRAYS FOR GENERATION                  *
 *--------------------------------------------------------------------------------------*/
	
	
	private Biome getFullBiome(long id) {
		Biome out;
		if(id < 0xffffffffL) {
			return Biome.getBiome((int)id, Biomes.DEFAULT);
		} else {
			out = subbiomes.get(id);
			if(noFakes || (out == null)) {
				out = Biome.getBiome((int)id, Biomes.DEFAULT);
			}
			return out;
		}
	}
    
    
	/**
	 * Returns a biome array for the chunk at chunk 
	 * coordinates x,z.
	 * 
	 * @param x
	 * @param z
	 * @param h
	 * @param w
	 * @return
	 */
    public Biome[] getChunkBiomeGen(int x, int z, Biome[] in) {
    	long[] tiles = new long[9];
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
    				i, 1.0 + chunkNoise.doubleFor(x2, z2, 12));    				
    	}
    	for(int i = 0; i < 16; i++)
    		for(int j = 0; j < 16; j++) {
    			in[(j * 16) + i] = getFullBiome(tiles[BiomeBasin
    					.summateEffect(basins, 16 + i, 16 + j)]);
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
    	
    	long[] tiles = new long[numc];
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
    				i, 1.0 + chunkNoise.doubleFor(x2, z2, 12));    				
    	}
    	for(int i = 0; i < w; i++)
    		for(int j = 0; j < h; j++) {
    			out[(j * w) + i] = getFullBiome(tiles[BiomeBasin
    						.summateEffect(basins, 16 + i, 16 + j)]);
    		}
    	return out;
    }


	public void cleanCaches() {
		data.cleanup();		
	}

}
