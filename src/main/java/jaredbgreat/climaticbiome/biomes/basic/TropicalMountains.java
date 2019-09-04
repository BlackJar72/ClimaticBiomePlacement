package jaredbgreat.climaticbiome.biomes.basic;

public class TropicalMountains /*xtends BiomeHills*/ {/*
    private static final IBlockState JUNGLE_LOG = Blocks.LOG.getDefaultState()
    		.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
    private static final IBlockState JUNGLE_LEAF = Blocks.LEAVES.getDefaultState()
    		.withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE)
    		.withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
	private final IPineFinder PINE;

	public TropicalMountains(Type p_i46710_1_, BiomeProperties properties) {
		super(p_i46710_1_, properties);
        if(ConfigHandler.addPines) {
        	PINE = new PineFinder();
        } else {
        	PINE = new SpruceFinder();
        }
	}

	
	@Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
    	int t = rand.nextInt(10);
    	switch(t) {
    		case 0:
    		case 1:
    			return BIG_TREE_FEATURE;
    		case 2:
    		case 3:
    			return PINE.getTree(rand);
    		case 4:
    			return new WorldGenTrees(false, 4 + rand.nextInt(7), JUNGLE_LOG, JUNGLE_LEAF, true);
    		default:
    			return TREE_FEATURE;
    	}
    }

*/}
