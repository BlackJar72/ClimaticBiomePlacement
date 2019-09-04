package jaredbgreat.climaticbiome.biomes.basic;

public class TropicalForest /*extends BiomeForest*/ {/*
	private static final WorldGenSavannaTree SAVANNA_TREE = new WorldGenSavannaTree(false);
    private static final IBlockState JUNGLE_LOG = Blocks.LOG.getDefaultState()
    		.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
    private static final IBlockState JUNGLE_LEAF = Blocks.LEAVES.getDefaultState()
    		.withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE)
    		.withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));

	public TropicalForest() {
		super(BiomeForest.Type.NORMAL, 
				new Biome.BiomeProperties("Tropical Forest")
					.setTemperature(1.0f)
					.setRainfall(0.7f));
	}
	

	public TropicalForest(Biome.BiomeProperties prop) {
		super(BiomeForest.Type.NORMAL, prop);
	}

	
	@Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
    	int t = rand.nextInt(5);
    	switch(t) {
    		case 0:
    			return BIG_TREE_FEATURE;
    		case 1:
    			return new WorldGenTrees(false, 4 + rand.nextInt(7), JUNGLE_LOG, JUNGLE_LEAF, true);
    		default:
    			return SAVANNA_TREE;
    	}
    }
    
*/}