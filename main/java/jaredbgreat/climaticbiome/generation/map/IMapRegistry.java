package jaredbgreat.climaticbiome.generation.map;

import jaredbgreat.climaticbiome.generation.mapgenerator.TerrainType;
import net.minecraft.world.biome.Biome;

public interface IMapRegistry {

	public abstract void findSaveDir();

	public abstract int chunkToMap(int c);

	public abstract int blockToMap(int c);

	/**
	 * Return the biome for the the given x,z block coordinates.
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
	public abstract Biome getBiomeChunk(int x, int z);

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
	public abstract Biome[] getChunkBiomeGrid(int x, int z, Biome[] in);

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
	public abstract Biome[] getUnalignedBiomeGrid(int x, int z, int h, int w,
			Biome[] in);

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
	public abstract Biome[] getChunkBiomeGen(int x, int z, Biome[] in);

	public abstract void cleanCaches();
	
	public float[] getTerrainBiomeGen(int x, int z, float[] in);

    public float getBaseHeight(int x, int z);
    
    public float getHeightScale(int x, int z);
    
    public float[] getHeightData(int x, int z);

}