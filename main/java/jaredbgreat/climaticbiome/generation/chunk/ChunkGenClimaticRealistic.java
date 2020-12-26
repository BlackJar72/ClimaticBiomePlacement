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
		//setter = new BorderTestBlockSetter(world, sprandom);
	}
	
	
	/*
	 * I would like to have a system that included both "realistic" (smooth) 
	 * and "Minecrafty" terrain, parameterized on the large scale much as 
	 * Climatic-Realistic does with height and scale -- probably 1/2 "realistic"
	 * (noise == 0), 1/4 Minecrafty (noise == 1), and 1/4 intermediary 
	 * (0 < noise < 1).  This could be done by generating two noise maps to create
	 * the two height maps, where...
	 * 
	 *  heighmap1 = noisemap11, 
	 *  and 
	 *  heightmap2 = (heighmap2 * m) + (heightmap1 * (1 - m))
	 *  
	 *  ...where m is the value from a larger scale noisemap representing 
	 *  "Minecraftiness," generated ranging -2 to 2 then clamped between 
	 *  0 and 1 (see above).  A 3D noise would then select between them. 
	 *  Unfortunately 3D noise to the scale of blocks is expensive to 
	 *  generate and store and I've failed to create an optimized version 
	 *  that creates a noise for larger sections and extrapolates (as I know 
	 *  Cuberite does and suspect vanilla does) -- all attempt have crashed 
	 *  and led only to confusion and frustration to the point of being 
	 *  unhealthy.  For now (forever? probably) only the simple terrain 
	 *  option will exist (making it not an option but just what there is).
	 */
	
	

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
