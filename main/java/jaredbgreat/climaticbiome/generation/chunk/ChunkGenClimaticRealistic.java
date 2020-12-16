package jaredbgreat.climaticbiome.generation.chunk;

import jaredbgreat.climaticbiome.generation.ClimaticBiomeProvider;
import jaredbgreat.climaticbiome.util.SpatialHash;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkGeneratorOverworld;

public class ChunkGenClimaticRealistic extends ChunkGeneratorOverworld {
	static final IBlockState WATER = Blocks.WATER.getDefaultState();
	private final SpatialHash sprandom;
	private final World world;
	private final IBlockSetter setter;
	

	public ChunkGenClimaticRealistic(World worldIn, long seed,
			boolean mapFeaturesEnabledIn, String generatorOptions) {
		super(worldIn, seed, mapFeaturesEnabledIn, generatorOptions);
		sprandom = new SpatialHash(seed);
		world = worldIn;
		setter = new SimpleBlockSetter(world, sprandom);
	}
	


    public void setBlocksInChunk(int x, int z, ChunkPrimer primer) {
    	setter.setBlocksInChunk(x, z, primer);
    }
    
    
    /*
     * A method to stash experimental world gen so I can edit 
     * it without altering the real method.  Instead the real 
     * world gen can be commented out and a call the this used.
     */
    private void altTest(int x, int z, ChunkPrimer primer) {}

}
