package jaredbgreat.climaticbiome.blocks.items;

import jaredbgreat.climaticbiome.blocks.BlockLeaf;
import net.minecraft.item.ItemLeaves;
import net.minecraft.item.ItemStack;

public class ItemLeaf extends ItemLeaves {
	private final String uname;

	public ItemLeaf(BlockLeaf block) {
		super(block);
        setMaxDamage(0);
        setUnlocalizedName(block.getUnlocalizedName());
        setRegistryName(block.getRegistryName());
        uname = "tile." + block.getName();
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
