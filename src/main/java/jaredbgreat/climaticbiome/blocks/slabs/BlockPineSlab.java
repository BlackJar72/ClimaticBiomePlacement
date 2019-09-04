package jaredbgreat.climaticbiome.blocks.slabs;

public class BlockPineSlab /*extends BlockSlabBase*/ {/*

	public BlockPineSlab(String name) {
		super(Material.WOOD, false);
		setUnlocalizedName(Info.ID + "." + name);
		setRegistryName(Info.ID, name);
		setSoundType(SoundType.WOOD);
		setHardness(1.8f);
		setHarvestLevel("axe", 0);
		setResistance(1.0f);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        useNeighborBrightness = true;
        IBlockState iblockstate = this.blockState.getBaseState();
        iblockstate = iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
        setDefaultState(iblockstate);
		Blocks.FIRE.setFireInfo(this, 5, 20);
		BlockRegistrar.addBlock(this);
    }

	
	@Override
	public int damageDropped(IBlockState state) {
		return this.getMetaFromState(state.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM));
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState iblockstate = this.getDefaultState();
		if (!this.isDouble())
			iblockstate = iblockstate.withProperty(HALF,
					(meta) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);

		return iblockstate;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(HALF) == EnumBlockHalf.BOTTOM ? 0 : 1;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, HALF);
	}

	@Override
	public boolean isDouble() {
		return false;
	}

	@Override
	public IProperty getVariantProperty() {
		return HALF;
	}

	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		return 0;
	}
    

    @SideOnly(Side.CLIENT)
    protected static boolean isHalfSlab(IBlockState state) {
        //return !state.isFullBlock();
    	return true;
    }

*/}
