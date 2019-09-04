package jaredbgreat.climaticbiome.blocks;

public class BlockPinePlanks /*extends ModBlockBase*/ {/*

	
	public BlockPinePlanks(String name) {
		super(Material.WOOD);
		setSoundType(SoundType.WOOD);
		setUnlocalizedName(Info.ID + "." + name);
		setRegistryName(Info.ID, name);
		setHardness(1.0f);
		setHarvestLevel("axe", 0);
		setResistance(1.0f);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		Blocks.FIRE.setFireInfo(this, 5, 20);
		BlockRegistrar.addBlock(this);
		ItemRegistrar.addItem(new ItemPinePlanks(this));	
	}

// The idea of bringing back the old pine plank texture as a "long" sub
// sub-block is tempting, but for now lets just keep it simple.

/*
	public static final PropertyEnum<BlockPinePlanks.EnumType> LENGTH 
			= PropertyEnum.<BlockPinePlanks.EnumType>create("variant", 
					BlockPinePlanks.EnumType.class);
	

	public static enum EnumType implements IStringSerializable {
		SHORT(0, "short"), LONG(1, "long");
		private static final BlockPinePlanks.EnumType[] LOOKUP 
				= new BlockPinePlanks.EnumType[values().length];
		private final int meta;
		private final String name;
		private final String unlocalizedName;
		private EnumType(int metaIn, String nameIn) {
			this(metaIn, nameIn, nameIn);
		}
		static {
			for (BlockPinePlanks.EnumType blockplanks$enumtype : values()) {
				LOOKUP[blockplanks$enumtype.getMetadata()] = blockplanks$enumtype;
			}
		}
		private EnumType(int metaIn, String nameIn, String unlocalizedNameIn) {
			this.meta = metaIn;
			this.name = nameIn;
			this.unlocalizedName = unlocalizedNameIn;
		}
		public int getMetadata() {
			return meta;
		}
		public String toString() {
			return name;
		}
		public static BlockPinePlanks.EnumType byMetadata(int meta) {
			return LOOKUP[meta & 1];
		}
		public String getName() {
			return name;
		}
		public String getUnlocalizedName() {
			return this.unlocalizedName;
		}
	}
	

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, 
			EnumFacing facing, float hitX, float hitY, float hitZ, int i, 
			EntityLivingBase player, EnumHand hand) {
		return getStateFromMeta(i);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		for (int i = 0; i < EnumType.values().length; i++) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), i, 
					new ModelResourceLocation(getRegistryName() + "_" 
							+ EnumType.byMetadata(i).getName(), "inventory"));
		}
	}
	

	public int damageDropped(IBlockState state) {
		return ((BlockPinePlanks.EnumType) state.getValue(LENGTH)).getMetadata();
	}
	

	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		for (BlockPinePlanks.EnumType blockplanks$enumtype : BlockPinePlanks.EnumType.values()) {
			list.add(new ItemStack(Item.getItemFromBlock(this), 1, blockplanks$enumtype.getMetadata()));
		}
	}

	
	public IBlockState getStateFromMeta(int meta) {
		return  getDefaultState().withProperty(LENGTH, 
				BlockPinePlanks.EnumType.byMetadata(meta));
	}
	

	public int getMetaFromState(IBlockState state) {
		return ((BlockPinePlanks.EnumType) state.getValue(LENGTH)).getMetadata();
	}
	

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { LENGTH });
	}

*/}

