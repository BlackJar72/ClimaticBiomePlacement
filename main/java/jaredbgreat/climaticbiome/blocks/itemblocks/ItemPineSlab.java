package jaredbgreat.climaticbiome.blocks.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

public class ItemPineSlab extends ItemSlab {

	public ItemPineSlab(Block block, BlockSlab singleSlab, BlockSlab doubleSlab) {
		super(block, singleSlab, doubleSlab);
        setUnlocalizedName(block.getUnlocalizedName());
        setRegistryName(block.getRegistryName());
	}
    
    
    @Override
	public int getItemBurnTime(ItemStack stack) {
		return 200;    	
    }

}
