package jaredbgreat.climaticbiome.generation.map;

import jaredbgreat.climaticbiome.generation.cache.Cache;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;


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
	private Cache<RegionMap> data;
	
	
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
		RegionMap out = data.get(x, z);
		if(out == null) {
			out = new RegionMap(x, z);
			initializeMap(out);
			data.add(out);
		}
		return out;
	}
	
	
	/**
	 * Return the map contain the given BlockPos.
	 * 
	 * @param pos
	 * @return
	 */
	public RegionMap getMap(BlockPos pos) {
		return getMap((pos.getX() - 2048) / 4096, (pos.getZ() - 2048) / 4096);
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
		return getMap((x - 2048) / 4096, (z - 2048) / 4096);
	}
	
	
	/**
	 * Gets the map for the chunk coordinates x,z.
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
	public RegionMap getMapFromChunkCoord(int x, int z) {
		return getMap((x - 128) / 256, (z - 128) / 256);
	}
	
	/**
	 * Creates a new map.  Currently using a stand in, 
	 * but this will ultimately pass the map to the generator, 
	 * which will then generate it.
	 * 
	 * @param map
	 */
	private void initializeMap(RegionMap map) {
		// FIXME / TODO: A temporary stand in; attach to 
		//               real generator.
		for(int i = 0; i < 4096; i++)
			for(int j = 0; j < 4096; j++) {
				map.setBiomeExpress(1, i, j); // One is a stand in (I think plains).
			}
	}
	
	
	/**
	 * Return the biome ID as an int for the the given x,z block coordinates.
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
	public int getBiomeIDBlock(int x, int z) {
		return getMapFromBlockCoord(x, z)
				.getBiome((x - 2048) % 4096, (z - 2048) % 4096);
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
				.getBiome((x - 128) % 256, (z - 128) % 256);
	}
	
	
	/**
	 * Return the biome for the the given x,z block coordinates.
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
	public Biome getBiomeBlock(int x, int z) {
		return Biome.getBiome(getMapFromBlockCoord(x, z)
				.getBiome((x - 2048) % 4096, (z - 2048) % 4096));
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
				.getBiome((x - 128) % 256, (z - 128) % 256));
	}
	
	
	/**
	 * Return the biome for world generation at the the given x,z 
	 * block coordinates.  This is too allow the use of pseudo-
	 * biomes or similar tricks to represent special features inside 
	 * the real biome and slight variations that should not be a 
	 * separate biome.
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
	public Biome getGenBiomeBlock(int x, int z) {
		int id = getMapFromBlockCoord(x, z)
				.getPseudoBiome((x - 2048) % 4096, (z - 2048) % 4096);
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
				.getPseudoBiome((x - 128) % 256, (z - 128) % 256);
		if(id < 256) {
			return Biome.getBiome(id);
		}
		// TODO: Add a registry (an ArrayList, NOT a Forge registry) for pseudo-biomes.
		return Biome.getBiome(id & 0xff); // Yes, this is totally wrong! 
	}
	
	

    
    /*
    //
    // Part of the old system, here to reference and see what I need.
    //
     *
    public Biome[] getChunkGrid(ChunkTile in[]) {
    	Biome[] out = new Biome[256];
    	ChunkTile[] tiles = new ChunkTile[9];
    	BiomeBasin[][] basins = new BiomeBasin[3][3];
    	for(int i = 0; i < tiles.length; i++) {
    		int x1 = (i / 3) + 2;
    		int z1 = (i % 3) + 2;
    		tiles[i] = in[(z1 * GENSIZE) + x1];
    		basins[i / 3][i % 3] = new BiomeBasin(
    				(x1 * 16) + (chunkNoise.intFor(tiles[i].x, tiles[i].z, 10) % 16),
    				(z1 * 16) + (chunkNoise.intFor(tiles[i].x, tiles[i].z, 11) % 16),
    				tiles[i].biome, 1.0 + chunkNoise.doubleFor(tiles[i].x, tiles[i].z, 12));    				
    	}
    	for(int i = 0; i < 16; i++)
    		for(int j = 0; j < 16; j++) {
    			out[(j * 16) + i] = Biome.getBiome(BiomeBasin.summateEffect(basins, 48 + i, 48 + j),
    					Biomes.DEFAULT);
    		}
    	return out;
    }
    
    
    public Biome[] getChunkGrid(int x, int z, int h, int w) {
    	int ch = (h / 16) + 3;
    	int cw = (w / 16) + 3;
    	int numc = ch * cw;
    	Biome[] out = new Biome[h * w];
    	ChunkTile[] tiles = new ChunkTile[numc];
    	BiomeBasin[][] basins = new BiomeBasin[ch][cw];
    	for(int i = 0; i < tiles.length; i++) {
    		int x1 = (i / ch);
    		int z1 = (i % cw);    		
    		tiles[i] = makeChunk(x + x1, z + z1)[24];
    		basins[i / ch][i % cw] = new BiomeBasin(
    				(x1 * 16) + (chunkNoise.intFor(tiles[i].x, tiles[i].z, 10) % 16),
    				(z1 * 16) + (chunkNoise.intFor(tiles[i].x, tiles[i].z, 11) % 16),
    				tiles[i].biome, 1.0 + chunkNoise.doubleFor(tiles[i].x, tiles[i].z, 12));    				
    	}
    	for(int i = 0; i < h; i++)
    		for(int j = 0; j < w; j++) {
    			out[(j * w) + i] = Biome.getBiome(BiomeBasin.summateEffect(basins, 48 + i, 48 + j),
    					Biomes.DEFAULT);
    		}
    	return out;
    }
	*/
	
	

}
