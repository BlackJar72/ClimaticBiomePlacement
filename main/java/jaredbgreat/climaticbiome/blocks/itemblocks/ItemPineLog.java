package jaredbgreat.climaticbiome.blocks.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemPineLog extends ItemBlock {

	public ItemPineLog(Block block) {
		super(block);
        setMaxDamage(0);
        setUnlocalizedName(block.getUnlocalizedName());
        setRegistryName(block.getRegistryName());
	}
	
	
	@Override
    public int getMetadata(int damage) { 
        return damage;
    }
    
    
    @Override
	public int getItemBurnTime(ItemStack stack) {
		return 300;    	
    }

}