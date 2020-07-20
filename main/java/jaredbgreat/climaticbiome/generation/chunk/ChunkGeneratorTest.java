package jaredbgreat.climaticbiome.generation.chunk;

import jaredbgreat.climaticbiome.generation.ClimaticBiomeProvider;
import jaredbgreat.climaticbiome.util.SpatialHash;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkGeneratorOverworld;

public class ChunkGeneratorTest extends ChunkGeneratorOverworld {
	private static final IBlockState WATER = Blocks.WATER.getDefaultState();
	private final SpatialHash sprandom;
	private final SpatialHash sprandom2;
	private final SpatialHash sprandom3;
	private final SpatialHash sprandom4;
	private final HeightMapManager heightMapManager;
	private final World world;
	

	public ChunkGeneratorTest(World worldIn, long seed,
			boolean mapFeaturesEnabledIn, String generatorOptions) {
		super(worldIn, seed, mapFeaturesEnabledIn, generatorOptions);
		sprandom = new SpatialHash(seed);
		sprandom2 = new SpatialHash(sprandom.longFor(1, 4, 1024));
		sprandom3 = new SpatialHash(sprandom.longFor(2, 5, 2048));
		sprandom4 = new SpatialHash(sprandom.longFor(3, 6, 4096));
		heightMapManager = new HeightMapManager();
		world = worldIn;
	}
	


    public void setBlocksInChunk(int x, int z, ChunkPrimer primer) {
    	int[][] heightmap = getHeihtmapForChunk(x, z, sprandom, sprandom2, sprandom3, sprandom4);
    	for(int i = 0; i < 16; i++) 
			for(int k = 0; k < 16; k++) {
				int h = heightmap[0][(i * 16) + k];
				int mn = heightmap[1][(i * 16) + k];
				int mx = heightmap[2][(i * 16) + k];
				int h2 = heightmap[3][(i * 16) + k];
				for(int j = 0; j < 256; j++) {
					/*if(true) { // How to thin
						if((j < h) && !((j > mn) && (j < mx) && ((mx - mn) > 1)) 
								&& !((mx > mn) && ((h - mx) < 3) && (j > mn))) {
							primer.setBlockState(i, j, k, STONE);
					    } else if (j < 63) {
					        primer.setBlockState(i, j, k, WATER);
					    }
					} else*/ { // I spent too much time trying to figure it out
						if((j < h)) {
					        primer.setBlockState(i, j, k, STONE);
					    } else if (j < 63) {
					        primer.setBlockState(i, j, k, WATER);
					    }						
					}
    			}
			}
    }
    
    
    // FIXME? Should this return and int[] or should I use a byte[]?
    private int[][] getHeihtmapForChunk(int x, int z, SpatialHash rand,SpatialHash rand2, 
    		SpatialHash rand3, SpatialHash rand4) {
    	ClimaticBiomeProvider provider = (ClimaticBiomeProvider)world.getBiomeProvider();
    	return heightMapManager.getChunkHieghts(x, z, rand, rand2, rand3, rand4, provider.getTerrainBiomeGen(x, z));
    }

}
