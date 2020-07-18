package jaredbgreat.climaticbiome.generation.chunk;

import jaredbgreat.climaticbiome.util.HeightNoiseMap;
import jaredbgreat.climaticbiome.util.SpatialHash;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkGeneratorOverworld;

public class ChunkGeneratorTest extends ChunkGeneratorOverworld {
	private static final IBlockState WATER = Blocks.WATER.getDefaultState();
	private final SpatialHash sprandom;
	

	public ChunkGeneratorTest(World worldIn, long seed,
			boolean mapFeaturesEnabledIn, String generatorOptions) {
		super(worldIn, seed, mapFeaturesEnabledIn, generatorOptions);
		sprandom = new SpatialHash(seed);
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
    	HeightNoiseMap noise = new HeightNoiseMap(16, 16, 16, 16);
    	float[][] temp = noise.process(sprandom, x * 15, z * 15);
    	for(int i = 0; i < 16; i++) 
    		for(int j = 0; j < 16; j++) {
    			out[(i * 16) + j] = (int)(temp[i][j] + 64);
    		}
    	return out;
    }

}
