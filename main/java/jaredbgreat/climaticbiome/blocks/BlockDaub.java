package jaredbgreat.climaticbiome.blocks;

import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.blocks.itemblocks.ItemBlockDaub;
import jaredbgreat.climaticbiome.util.BlockRegistrar;
import jaredbgreat.climaticbiome.util.ItemRegistrar;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockDaub extends ModBlockBase {
	private final Item item;

	public BlockDaub() {
		super(Material.GROUND);
		setRegistryName(Info.ID, "block_daub");
		setUnlocalizedName(Info.ID + "." + "block_daub");
		setHardness(5.0f);
		setResistance(2.0f);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		item = new ItemBlockDaub(this).setRegistryName(getRegistryName());
		BlockRegistrar.addBlock(this);
		ItemRegistrar.addItem(item);
	}
	
	
	public static enum EnumType implements IStringSerializable {
		FULL(0, "stucco"), BRICK(1, "brick"), SMALL(2, "small_brick");
		private static final BlockDaub.EnumType[] LOOKUP 
				= new BlockDaub.EnumType[values().length];
		private final int meta;
		private final String name;
		private final String unlocalizedName;
		private EnumType(int metaIn, String nameIn) {
			this(metaIn, nameIn, nameIn);
		}
		static {
			for (BlockDaub.EnumType type : values()) {
				LOOKUP[type.getMetadata()] = type;
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
		public static BlockDaub.EnumType byMetadata(int meta) {
			return LOOKUP[meta % 3];
		}
		public String getName() {
			return name;
		}
		public String getUnlocalizedName() {
			return this.unlocalizedName;
		}
	}
	
	
	public static final PropertyEnum<BlockDaub.EnumType> VARIANT 
		= PropertyEnum.<BlockDaub.EnumType>create("variant", 
				BlockDaub.EnumType.class);


	public int damageDropped(IBlockState state) {
		return ((BlockDaub.EnumType) state.getValue(VARIANT)).getMetadata();
	}
	

	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		for (BlockDaub.EnumType type : BlockDaub.EnumType.values()) {
			list.add(new ItemStack(Item.getItemFromBlock(this), 1, type.getMetadata()));
		}
	}

	
	public IBlockState getStateFromMeta(int meta) {
		return  getDefaultState().withProperty(VARIANT, 
				BlockDaub.EnumType.byMetadata(meta));
	}
	

	public int getMetaFromState(IBlockState state) {
		return ((BlockDaub.EnumType) state.getValue(VARIANT)).getMetadata();
	}
	

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}
	
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world,
			BlockPos pos, IBlockState state, int fortune) {
		drops.add(new ItemStack(item, 1, getMetaFromState(state)));
	}

}
