package jaredbgreat.climaticbiome.blocks.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

public class ItemPlanks extends ItemBlock {

	public ItemPlanks(Block block) {
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
		return 300;    	
    }

}
