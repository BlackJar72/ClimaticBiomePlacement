package jaredbgreat.climaticbiome.blocks.itemblocks;

import jaredbgreat.climaticbiome.blocks.BlockDaub;
import jaredbgreat.climaticbiome.util.IHaveModel;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

public class ItemBlockDaub extends ItemBlock implements IHaveModel {

	public ItemBlockDaub(Block block) {
		super(block);
	}	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		IBlockState state = Block.getBlockFromItem(stack.getItem())
				.getStateFromMeta(stack.getMetadata());
		return getUnlocalizedName() + "_" + state.getValue(BlockDaub.VARIANT);
	}
	

	@Override
	public int getMetadata(int damage) {
		return damage;
	}


	@Override
	public void registerModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, 
				new ModelResourceLocation(getRegistryName(), ""));
		ModelLoader.setCustomModelResourceLocation(this, 1, 
				new ModelResourceLocation(getRegistryName() + "_brick", ""));
		ModelLoader.setCustomModelResourceLocation(this, 2, 
				new ModelResourceLocation(getRegistryName() + "_small_brick", ""));		
	}


}
