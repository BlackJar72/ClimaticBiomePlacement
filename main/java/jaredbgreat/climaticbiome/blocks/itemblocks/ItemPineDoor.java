package jaredbgreat.climaticbiome.blocks.itemblocks;

import jaredbgreat.climaticbiome.ClimaticBiomes;
import jaredbgreat.climaticbiome.util.IHaveModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;

public class ItemPineDoor extends ItemDoor implements IHaveModel {

	public ItemPineDoor(Block block) {
		super(block);
	}
    
    
    @Override
	public int getItemBurnTime(ItemStack stack) {
		return 300;    	
    }
	

	@Override
	public void registerModel() {
		ClimaticBiomes.proxy.registerItemRender(this, 0, "inventory");
	}

}
