package jaredbgreat.climaticbiome.blocks.items;

import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemLeaves;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

public class ItemLeaf extends ItemLeaves {

	public ItemLeaf(BlockLeaves block) {
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

}
