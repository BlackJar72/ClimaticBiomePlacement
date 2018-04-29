package jaredbgreat.climaticbiome.blocks.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;

public abstract class RegisteringItemBlock extends ItemBlock {
	

	public RegisteringItemBlock(Block block) {
		super(block);
	}
	

	public RegisteringItemBlock registerModels() {
		ModelResourceLocation recloc 
				= new ModelResourceLocation(block.getRegistryName(), 
						block.getUnlocalizedName()); 
    	ModelLoader.setCustomModelResourceLocation(this, 0, recloc);
    	return this;
    }

}
