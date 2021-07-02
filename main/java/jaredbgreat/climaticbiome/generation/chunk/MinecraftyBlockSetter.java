package jaredbgreat.climaticbiome.generation.chunk;

import static jaredbgreat.climaticbiome.generation.chunk.ChunkGenClimaticRealistic.WATER;

import jaredbgreat.climaticbiome.generation.ClimaticBiomeProvider;
import jaredbgreat.climaticbiome.util.SpatialHash;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;


/**
 * FIXME:  This is very broken, doesn't look right at all (but step in that directions?)
 * 
 * @author jared
 *
 */
public class MinecraftyBlockSetter implements IBlockSetter {
	static final IBlockState STONE = Blocks.STONE.getDefaultState();
	private final HeightMapManager heightMapManager;
	private final VolumnMapManager volMapManager;
	private final SpatialHash sprandom;
	private final World world;	
	
	
	public MinecraftyBlockSetter(World world, SpatialHash sprandom) {
		this.world = world;
		this.sprandom = sprandom;
		heightMapManager = new HeightMapManager();
		volMapManager = new VolumnMapManager();
	}
	
	
    public void setBlocksInChunk(int x, int z, ChunkPrimer primer) {
    	float[] volumns = getVolumnsForChunk(x, z, sprandom);
    	int[][] heightmap = getHeihtmapForChunk(x, z, sprandom, volumns);
    	for(int i = 0; i < 16; i++) 
			for(int k = 0; k < 16; k++) {
				int h1 = heightmap[0][(i * 16) + k];
				int h2 = heightmap[1][(i * 16) + k];
				int h;
				// I've put too much time, stress, and self-destruction 
				// into trying to figure out cliffs/overhangs, and am 
				// still stumped.  This should work for the "Minecraft 
				// terrain is too unrealistic" crowd, and is fast.
				for(int j = 0; j < 256; j++) {
					if((0 < volumns[(((i * 16) + k) * 256) + j])) {
						h = h1;
					} else {
						h = h2;
					}
					if((j < h)) {
				        primer.setBlockState(i, j, k, STONE);
				    } else if (j < 63) {
				        primer.setBlockState(i, j, k, WATER);
					}
    			}
		}
    }
    
    
    // FIXME? Should this return and int[] or should I use a byte[]?
    private int[][] getHeihtmapForChunk(int x, int z, SpatialHash rand) {
    	ClimaticBiomeProvider provider = (ClimaticBiomeProvider)world.getBiomeProvider();
    	return heightMapManager.getChunkHieghts(x, z, rand, provider.getTerrainBiomeGen(x, z));
    }
    
    
    // FIXME? Should this return and int[] or should I use a byte[]?
    private int[][] getHeihtmapForChunk(int x, int z, SpatialHash rand, float[] vol) {
    	ClimaticBiomeProvider provider = (ClimaticBiomeProvider)world.getBiomeProvider();
    	return heightMapManager.getChunkHieghts(x, z, rand, provider.getTerrainBiomeGen(x, z), vol);
    }
    
    // FIXME? Should this return and int[] or should I use a byte[]?
    private float[] getVolumnsForChunk(int x, int z, SpatialHash rand) {
    	ClimaticBiomeProvider provider = (ClimaticBiomeProvider)world.getBiomeProvider();
    	return volMapManager.getChunkNoise(x, z, rand, provider.getTerrainBiomeGen(x, z));
    }

}
