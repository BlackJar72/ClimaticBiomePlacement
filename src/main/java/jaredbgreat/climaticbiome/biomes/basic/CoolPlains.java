package jaredbgreat.climaticbiome.biomes.basic;

public class CoolPlains /*extends BiomePlains*/ {/*
	private static final IBlockState SPRUCE_LOG = Blocks.LOG.getDefaultState()
			.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);    
	private static final IBlockState SPRUCE_LEAF = Blocks.LEAVES.getDefaultState()
			.withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE);
	private final IPineFinder SPRUCE;
	private final boolean cold;

	public CoolPlains(boolean p_i46699_1_, boolean cold, BiomeProperties properties) {
		super(p_i46699_1_, properties);
		SPRUCE = new SpruceFinder();
		this.cold = cold;
	}
	
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		if(cold) {
			return new WorldGenShrub(SPRUCE_LOG, SPRUCE_LEAF);
		} else if(rand.nextBoolean()) {
			return SPRUCE.getTree(rand);
		} else {
			return super.getRandomTreeFeature(rand);
		}
	}
	

*/}
