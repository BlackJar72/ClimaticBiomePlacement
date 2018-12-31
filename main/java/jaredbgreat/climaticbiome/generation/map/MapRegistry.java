package jaredbgreat.climaticbiome.generation.map;

import static jaredbgreat.climaticbiome.util.ModMath.modRight;
import jaredbgreat.climaticbiome.ClimaticBiomes;
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
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.DimensionManager;


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
public class MapRegistry {
	private final Cache<RegionMap> data;
	private final SubBiomeRegistry subbiomes;
	
    private final SpatialNoise chunkNoise;
    private final SpatialNoise regionNoise;
    private final SpatialNoise biomeNoise;
    
    private final MapMaker maker;
    private World world;
    private File savedir =  null;
    private boolean cansave = true;
	
	
	public MapRegistry(long seed, World w) {
		data = new Cache<>();
		subbiomes = SubBiomeRegistry.getSubBiomeRegistry();
        Random random = new Random(seed);
        chunkNoise = new SpatialNoise(random.nextLong(), random.nextLong());
        regionNoise = new SpatialNoise(random.nextLong(), random.nextLong());
        biomeNoise = new SpatialNoise(random.nextLong(), random.nextLong());
        maker = new MapMaker(chunkNoise, regionNoise, biomeNoise);
        world = w;
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
	public RegionMap getMap(int x, int z) {
		//System.out.println("{" + x + ", " + z + "}");
		RegionMap out = data.get(x, z);
		if(out == null) {
			out = new RegionMap(x, z);
			readMap(out);
			data.add(out);
		}
		//System.out.println("Accessing Map: " + x + ", " + z);
		return out;
	}
	
	
	/**
	 * Return the map contain the given BlockPos.
	 * 
	 * @param pos
	 * @return
	 */
	public RegionMap getMap(BlockPos pos) {
		return getMap((pos.getX() + 2048) / 4096, (pos.getZ() + 2048) / 4096);
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
	public RegionMap getMapFromBlockCoord(int x, int z) {
		return getMap((x + 2048) / 4096, (z + 2048) / 4096);
	}
	
	
	/**
	 * Gets the map for the chunk coordinates x,z.
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
	public RegionMap getMapFromChunkCoord(int x, int z) {
		return getMap((x + 128) / 256, (z + 128) / 256);
	}
	
	
	public int chunkToMap(int c) {
		return (c + 128) / 256;
	}
	
	
	public int blockToMap(int c) {
		return (c + 2048) / 4096;
	}
	
	/**
	 * Creates a new map.  Currently using a stand in, 
	 * but this will ultimately pass the map to the generator, 
	 * which will then generate it.
	 * 
	 * @param map
	 */
	private void initializeMap(RegionMap map) {		
		maker.generate(map);
		//System.out.println("Map Hash: " + map.otherHash());
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
		short[] data = map.getData();
		if(file != null && file.exists()) {
			try {				
				FileInputStream fs = new FileInputStream(file);
				for(int i = 0; i < 65536; i++) {
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
			short[] data = map.getData();
			//System.out.println("Writing Data: " + RegionMap.otherHash(data));
			//Hasher hasher = new Hasher();
			try {
				FileOutputStream fs = new FileOutputStream(file);
				for(int i = 0; i < 65536; i++) {
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
	 * Return the biome ID as an int for the the given x,z block coordinates.  
	 * Note that this is not the true biome, of the exact block 
	 * but actually the dominant biome for the chunk.
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
	public int getBiomeIDBlock(int x, int z) {
		x /= 16;	z /= 16;
		return getMapFromChunkCoord(x, z)
				.getBiome(x - (256 * chunkToMap(x)), 
						  z - (256 * chunkToMap(z)));
	}
		
	
	/**
	 * Return the biome ID as an int for the the given x,z chunk coordinates.
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
	public int getBiomeIDChunk(int x, int z) {
		return getMapFromChunkCoord(x, z)
				.getBiome(modRight(x + 128, 256), 
						  modRight(z + 128, 256));
	}
	
	
	/**
	 * Return the biome ID as an int for the the given x,z chunk coordinates.
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
	public int getGenIDChunk(int x, int z) {
		return getMapFromChunkCoord(x, z)
				.getFullBiome(modRight(x + 128, 256), 
						        modRight(z + 128, 256));
	}

	
	
	/**
	 * Return the biome for the the given x,z block coordinates.  
	 * Note that this is not the true biome, of the exact block 
	 * but actually the dominant biome for the chunk.
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
	public Biome getBiomeBlock(int x, int z) {
		x /= 16;	z /= 16;
		return Biome.getBiome(getMapFromChunkCoord(x, z)
				.getBiome(modRight(x + 128, 256), 
						  modRight(z + 128, 256)));
	}
		
	
	/**
	 * Return the biome for the the given x,z block coordinates.
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
	public Biome getBiomeChunk(int x, int z) {
		return Biome.getBiome(getMapFromChunkCoord(x, z)
				.getBiome(modRight(x + 128, 256), 
						  modRight(z + 128, 256)));
	}
	
	
	/**
	 * Return the biome for world generation at the the given x,z 
	 * block coordinates.  This is too allow the use of pseudo-
	 * biomes or similar tricks to represent special features inside 
	 * the real biome and slight variations that should not be a 
	 * separate biome.
	 *   
	 * Note that this is not the true biome, of the exact block 
	 * but actually the dominant biome for the chunk.
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
	public Biome getGenBiomeBlock(int x, int z) {
		int id = getMapFromBlockCoord(x, z)
				.getSubBiomeId((x - 2048) % 4096, (z - 2048) % 4096);
		if(id < 256) {
			return Biome.getBiome(id);
		}
		// TODO: Add a registry (an ArrayList, NOT a Forge registry) for pseudo-biomes.
		return Biome.getBiome(id & 0xff); // Yes, this is totally wrong! 
	}
		
	
	/**
	 * Return the biome for world generation at the the given 
	 * x,z block coordinates.  This is too allow the use of pseudo-
	 * biomes or similar tricks to represent special features inside 
	 * the real biome and slight variations that should not be a 
	 * separate biome.
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
	public Biome getGenBiomeChunk(int x, int z) {
		int id = getMapFromBlockCoord(x, z)
				.getSubBiomeId((x - 128) % 256, (z - 128) % 256);
		if(id < 256) {
			return Biome.getBiome(id);
		}
		// TODO: Add a registry (an ArrayList, NOT a Forge registry) for pseudo-biomes.
		return Biome.getBiome(id & 0xff); // Yes, this is totally wrong! 
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
    public Biome[] getChunkBiomeGrid(int x, int z) {
    	Biome[] out = new Biome[256];
    	int[] tiles = new int[9];
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
    			out[(j * 16) + i] = Biome.getBiome(BiomeBasin.summateEffect(basins, 16 + i, 16 + j),
    					Biomes.DEFAULT);
    		}
    	return out;
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
    public Biome[] getBiomeGrid(int x, int z, int h, int w) {
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
    public Biome[] getUnalignedBiomeGrid(int x, int z, int h, int w) {
    	int hOff = modRight(x, 16);;
    	int wOff = modRight(z, 16);;
    	int h1 = h + hOff;
    	int w1 = w + wOff;
    	int h2 = h1 + (16 - h1 % 16);
    	int w2 = w1 + (16 - w1 % 16);
    	Biome[] tmp = getBiomeGrid(x / 16, z /  16, h2, w2);
    	Biome[] out = new Biome[h * w];
    	for(int i = 0; i < h; i++) 
    		for(int j = 0; j < w; j++) {
    			out[(j * w) + i] = tmp[((j + hOff) * w2) + wOff + i];
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
	
	// TODO: Methods that can be used to gain pseudo-biome arrays 
    //       and/or accurate block pseudo-biome.
    
	
/*--------------------------------------------------------------------------------------*
 *                 METHODS FOR GETTING THE BIOME ARRAYS FOR GENERATION                  *
 *--------------------------------------------------------------------------------------*/
	
	
	public Biome getFullBiome(int id) {
		//System.err.println("Biome ID: " + id);
		Biome out;
		if(id < 256) {
			return Biome.getBiome(id, Biomes.DEFAULT);
		} else {
			out = subbiomes.get(id);
			if(out == null) {
				out = Biome.getBiome(id & 0xff, Biomes.DEFAULT);
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
    public Biome[] getChunkBiomeGen(int x, int z) {
    	Biome[] out = new Biome[16];
    	int[] tiles = new int[9];
    	BiomeBasin[][] basins = new BiomeBasin[3][3];
    	for(int i = 0; i < tiles.length; i++) {
    		int x1 = (i / 3);
    		int z1 = (i % 3);   
    		int x2 = x + x1;
    		int z2 = z + z1;    		 		
    		tiles[i] = getGenIDChunk(x2, z2);
    		basins[i / 3][i % 3] = new BiomeBasin(
    				(x1 * 4) + (chunkNoise.intFor(x2, z2, 10) % 4),
    				(z1 * 4) + (chunkNoise.intFor(x2, z2, 11) % 4),
    				tiles[i], 1.0 + chunkNoise.doubleFor(x2, z2, 12));    				
    	}
    	for(int i = 0; i < 4; i++)
    		for(int j = 0; j < 4; j++) {
    			out[(j * 4) + i] = getFullBiome(BiomeBasin.summateEffect(basins, 4 + i, 4 + j));
    		}
    	return out;
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
    public Biome[] getBiomeGenGrid(int x, int z, int h, int w) {
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
    public Biome[] getUnalignedBiomeGen(int x, int z, int h, int w) {
    	int hOff = modRight(x, 16);;
    	int wOff = modRight(z, 16);;
    	int h1 = h + hOff;
    	int w1 = w + wOff;
    	int h2 = h1 + (16 - h1 % 16);
    	int w2 = w1 + (16 - w1 % 16);
    	Biome[] tmp = getBiomeGenGrid(x / 16, z /  16, h2, w2);
    	Biome[] out = new Biome[h * w];
    	for(int i = 0; i < h; i++) 
    		for(int j = 0; j < w; j++) {
    			out[(j * w) + i] = tmp[((j + hOff) * w2) + wOff + i];
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
    public Biome[] getUnalignedBiomeGen(int x, int z, int h, int w, Biome[] in) {
    	int hOff = modRight(x, 16);
    	int wOff = modRight(z, 16);
    	int h1 = h + hOff;
    	int w1 = w + wOff;
    	int h2 = h1 + (16 - h1 % 16);
    	int w2 = w1 + (16 - w1 % 16);
    	Biome[] tmp = getBiomeGenGrid(x / 16, z /  16, h2, w2);
    	for(int i = 0; i < h; i++) 
    		for(int j = 0; j < w; j++) {
    			in[(j * w) + i] 
    					= tmp[((j + hOff) * w2) + wOff + i];
    		}    	
    	return in;
    }


	public void cleanCaches() {
		data.cleanup();		
	}
	

}
