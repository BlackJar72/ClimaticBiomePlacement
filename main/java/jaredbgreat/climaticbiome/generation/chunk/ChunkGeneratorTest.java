package jaredbgreat.climaticbiome.generation.chunk;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkGeneratorOverworld;

public class ChunkGeneratorTest extends ChunkGeneratorOverworld {
	private static final IBlockState WATER = Blocks.WATER.getDefaultState();
	

	public ChunkGeneratorTest(World worldIn, long seed,
			boolean mapFeaturesEnabledIn, String generatorOptions) {
		super(worldIn, seed, mapFeaturesEnabledIn, generatorOptions);
	}
	


    public void setBlocksInChunk(int x, int z, ChunkPrimer primer) {
    	int[] heightmap = getHeihtmapForChunk(x, z);
    	for(int i = 0; i < 16; i++) 
			for(int k = 0; k < 16; k++) {
				int h = heightmap[(i * 16) + k];
				for(int j = 0; j < 256; j++) {
					if (j < h) {
				        primer.setBlockState(i, j, k, STONE);
				    } else if (j < 64) {
				        primer.setBlockState(i, j, k, WATER);
				    }
    			}
			}
    }
    
    
    // FIXME? Should this return and int[] or should I use a byte[]?
    private int[] getHeihtmapForChunk(int x, int z) {
    	// TODO: Everything, mostly elsewhere; this is a stand-in!
    	int[] out = new int[256];
    	for(int i = 0; i < 16; i++) {
    			out[i * 16]      = 63;
    			out[i * 16 +  2] = 63;
    			out[i * 16 +  4] = 63;
    			out[i * 16 +  6] = 63;
    			out[i * 16 +  8] = 63;
    			out[i * 16 + 10] = 63;
    			out[i * 16 + 12] = 63;
    			out[i * 16 + 14] = 63;
    			out[i * 16 +  1] = 65;
    			out[i * 16 +  3] = 65;
    			out[i * 16 +  5] = 65;
    			out[i * 16 +  7] = 65;
    			out[i * 16 +  9] = 65;
    			out[i * 16 + 11] = 65;
    			out[i * 16 + 13] = 65;
    			out[i * 16 + 15] = 65;
    		}
    	return out;
    }

}
