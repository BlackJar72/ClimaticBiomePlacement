package jaredbgreat.climaticbiome.blocks;

public class BlockPineSapling /*extends BlockBushBase implements IGrowable*/ {/*
	private final WorldGenAbstractTree tree;
	
	
	public BlockPineSapling(String name, WorldGenAbstractTree generator) {
		setUnlocalizedName(Info.ID + "." + name);
		setRegistryName(Info.ID, name);
		setSoundType(SoundType.PLANT);
		tree = generator;
		BlockRegistrar.addBlock(this);
		ItemRegistrar.addItem(new ItemPineSappling(this));
	}
	

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state,
			boolean isClient) {
		return true;
	}
    
    
    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
    	super.updateTick(world, pos, state, rand);;
    	if((world.getLight(pos) > 8) && (rand.nextInt(9) == 0)) {
    		grow(world, rand, pos, state);
    	}
    }
	

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos,
			IBlockState state) {
		return true;
	}
	

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		tree.generate(world, rand, pos);
	}
	
	
	@Override
	public boolean isWood(IBlockAccess world, BlockPos pos) {
		return true;
	}

*/}
