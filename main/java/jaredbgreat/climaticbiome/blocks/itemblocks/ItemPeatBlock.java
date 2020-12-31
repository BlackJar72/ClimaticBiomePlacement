package jaredbgreat.climaticbiome.blocks.itemblocks;

import jaredbgreat.climaticbiome.configuration.ConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemPeatBlock extends ItemBlock {

	public ItemPeatBlock(Block block) {
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
    	// The value, on burning the whole block; still poor compared 
    	// to a full coal block, but better than coal or charcoal. 
    	// Makes sense to me!
		return ConfigHandler.peatSmelts * 200;    	
    }

}
