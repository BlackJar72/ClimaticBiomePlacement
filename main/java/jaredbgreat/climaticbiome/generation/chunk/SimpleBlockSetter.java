package jaredbgreat.climaticbiome.generation.chunk;

import static jaredbgreat.climaticbiome.generation.chunk.ChunkGenClimaticRealistic.WATER;

import jaredbgreat.climaticbiome.generation.ClimaticBiomeProvider;
import jaredbgreat.climaticbiome.util.SpatialHash;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class SimpleBlockSetter implements IBlockSetter {
	static final IBlockState STONE = Blocks.STONE.getDefaultState();
	private final HeightMapManager heightMapManager;
	private final SpatialHash sprandom;
	private final World world;	
	
	
	public SimpleBlockSetter(World world, SpatialHash sprandom) {
		this.world = world;
		this.sprandom = sprandom;
		heightMapManager = new HeightMapManager();
	}
	
	
    public void setBlocksInChunk(int x, int z, ChunkPrimer primer) {
    	int[][] heightmap = getHeihtmapForChunk(x, z, sprandom);
    	for(int i = 0; i < 16; i++) 
			for(int k = 0; k < 16; k++) {
				int h = heightmap[0][(i * 16) + k];
				// I've put too much time, stress, and self-destruction 
				// into trying to figure out cliffs/overhangs, and am 
				// still stumped.  This should work for the "Minecraft 
				// terrain is too unrealistic" crowd, and is fast.
				for(int j = 0; j < 256; j++) {
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

}
