package jaredbgreat.climaticbiome.blocks.items;

import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemLeaves;
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
    
    
    public ItemLeaf registerModels() {
    	ModelLoader.setCustomModelResourceLocation(this, 0, 
    			new ModelResourceLocation(block.getRegistryName(), block.getUnlocalizedName()));
    	return this;
    }

}
