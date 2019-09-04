package jaredbgreat.climaticbiome.blocks.slabs;

public class BlockPineDoubleSlab /*extends BlockSlabBase*/ {/*
	private Block slab;

	public BlockPineDoubleSlab(String name, BlockPineSlab single) {
		super(Material.WOOD, true);
		setUnlocalizedName(Info.ID + "." + name);
		setRegistryName(Info.ID, name);
		setSoundType(SoundType.WOOD);
		setHardness(1.8f);
		setHarvestLevel("axe", 0);
		setResistance(1.0f);
		slab = single;
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        useNeighborBrightness = true;
        IBlockState iblockstate = this.blockState.getBaseState();
        iblockstate = iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
        setDefaultState(iblockstate);
		Blocks.FIRE.setFireInfo(this, 5, 20);
		BlockRegistrar.addBlock(this);
    }
 
	
	public void setSlab(Block slab) {
		this.slab = slab;
	}

	
	@Override
	public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		drops.add(new ItemStack(Item.getItemFromBlock(this.slab), 1));
		drops.add(new ItemStack(Item.getItemFromBlock(this.slab), 1));
		return drops;
	}

	
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0,
				new ModelResourceLocation(getRegistryName().toString(), "inventory"));
	}

	
	@Override
	public boolean isDouble() {
		return true;
	}

	
	@SideOnly(Side.CLIENT)
	protected static boolean isHalfSlab(IBlockState state) {
		return true;
	}

	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this.slab));
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
	public IProperty<?> getVariantProperty() {
		return HALF;
	}

	
	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		return 0;
	}


*/}
