package jaredbgreat.climaticbiome.blocks.itemblocks;

import jaredbgreat.climaticbiome.blocks.BlockPinePlanks;
import jaredbgreat.climaticbiome.util.IHaveModel;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

public class ItemPinePlanks extends ItemPineLog /*implements IHaveModel*/ {

	public ItemPinePlanks(Block block) {
		super(block);
	}

// The idea of bringing back the old pine plank texture as a "long" sub
// sub-block is tempting, but for now lets just keep it simple.
	
/*	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		IBlockState state = Block.getBlockFromItem(stack.getItem())
				.getStateFromMeta(stack.getMetadata());
		return getUnlocalizedName() + "_" + state.getValue(BlockPinePlanks.LENGTH);
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
				new ModelResourceLocation(getRegistryName() + "_long", ""));		
	}
*/

}
