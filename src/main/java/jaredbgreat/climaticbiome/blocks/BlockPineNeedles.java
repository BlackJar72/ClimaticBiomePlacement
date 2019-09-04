package jaredbgreat.climaticbiome.blocks;

public class BlockPineNeedles /*extends BlockLeafBase*/ {/*

	public BlockPineNeedles() {
		super();
		setUnlocalizedName(Info.ID + ".pine_leaves");
		setRegistryName(Info.ID, "pine_leaves");
		Blocks.FIRE.setFireInfo(this, 5, 50);
		BlockRegistrar.addBlock(this);
		ItemRegistrar.addItem(new ItemPineNeedles(this));
	}
	

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world,
			BlockPos pos, int fortune) {
		return Arrays.asList(new ItemStack(this, 1, 0));
	}

	
	@Override
	public EnumType getWoodType(int meta) {
		return BlockPlanks.EnumType.byMetadata(0);
	}
	
	
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(BlockRegistrar.blockPineSappling);
    }

	
    public int getMetaFromState(IBlockState state) {
        int out = 0;
        if (!((Boolean)state.getValue(DECAYABLE)).booleanValue()) {
            out |= 4;
        }
        if (((Boolean)state.getValue(CHECK_DECAY)).booleanValue()) {
            out |= 8;
        }
        return out;
    }

    
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState()
        		.withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0))
        		.withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
    }
    

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {CHECK_DECAY, DECAYABLE});
    }

    
    @Override
    public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
    	return true;
    }
    
*/}

