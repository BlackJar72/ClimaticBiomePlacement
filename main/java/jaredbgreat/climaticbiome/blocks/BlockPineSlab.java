package jaredbgreat.climaticbiome.blocks;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public abstract class BlockPineSlab extends BlockSlab {

	public BlockPineSlab() {
		super(Material.WOOD);
        IBlockState iblockstate = this.blockState.getBaseState();
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        if (!this.isDouble()) {
            iblockstate = iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
        }
    }

	
	@Override
	public String getUnlocalizedName(int meta) {
		return super.getUnlocalizedName();
	}
	

	@Override
	public IProperty<?> getVariantProperty() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		return BlockPlanks.EnumType.byMetadata(stack.getMetadata());
	}

}
