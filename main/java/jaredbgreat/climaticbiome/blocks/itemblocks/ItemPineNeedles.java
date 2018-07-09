package jaredbgreat.climaticbiome.blocks.itemblocks;

import jaredbgreat.climaticbiome.blocks.BlockLeafBase;
import net.minecraft.item.ItemLeaves;
import net.minecraft.item.ItemStack;

public class ItemPineNeedles extends ItemLeaves {

	public ItemPineNeedles(BlockLeafBase block) {
		super(block);
        setMaxDamage(0);
        setUnlocalizedName(block.getUnlocalizedName());
        setRegistryName(block.getRegistryName());
	}
	

    public int getMetadata(int damage) {
        return damage;
    }
    
    
    @Override
	public int getItemBurnTime(ItemStack stack) {
		return 100;    	
    }
    
    
    @Override
    public String getUnlocalizedName(ItemStack stack) {
    	return uname;
    }

}
