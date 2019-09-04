package jaredbgreat.climaticbiome.blocks;

public class BlockPineDoor /*extends BlockDoor implements IHaveModel*/ {/*
	private final Item item;

	public BlockPineDoor(String name) {
		super(Material.WOOD);
		setRegistryName(new ResourceLocation(Info.ID, name));
		setUnlocalizedName(Info.ID + "." + name);
		setCreativeTab(CreativeTabs.REDSTONE);
		setHardness(3F);
		setSoundType(SoundType.WOOD);
		setDefaultState(this.blockState.getBaseState()
				.withProperty(FACING, EnumFacing.NORTH)
				.withProperty(OPEN, Boolean.valueOf(false))
				.withProperty(HINGE, BlockDoor.EnumHingePosition.LEFT)
				.withProperty(POWERED, Boolean.valueOf(false))
				.withProperty(HALF, BlockDoor.EnumDoorHalf.LOWER));
		item = new ItemDoor(this);
		item.setRegistryName(this.getRegistryName());
		item.setUnlocalizedName(this.getUnlocalizedName());
		BlockRegistrar.addBlock(this);
		ItemRegistrar.addItem(item);		
	}
	

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER 
				? Items.AIR : item;
	}

	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(item);
	}

	
	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.getMaterial().getMaterialMapColor();
	}
	

	@Override
	public void registerModel() {
		ClimaticBiomes.proxy.registerDoorRenders(this);
	}

*/}
