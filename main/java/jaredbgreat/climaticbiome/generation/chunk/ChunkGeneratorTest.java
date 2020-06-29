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
		int c1 = ((x % 16) - 8);
		int c2 = ((z % 16) - 8);
		c1 *= c1;
		c2 *= c2;
		int c3 = (int)Math.sqrt(c1 + c2);
		
    	int d1 = 0, d2 = 0, d3 =0;
    	for(int i = 0; i < 16; i++) 
			for(int k = 0; k < 16; k++) {				 
				//d1 = (i - 8);
				//d2 = (k - 8);
				//d1 *= d1;
				//d2 *= d2;
				//d3 = (int)Math.sqrt(d1 + d2) - c3;
				for(int j = 0; j < 256; j++) {
					if ((j) < 64) {
				        primer.setBlockState(i, j, k, WATER);
				    } else if (j < 72) {
				        primer.setBlockState(i, j, k, STONE);
				    }
    			}
			}
    }

}
