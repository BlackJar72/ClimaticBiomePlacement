package jaredbgreat.climaticbiome.blocks;

import jaredbgreat.climaticbiome.ClimaticBiomes;
import jaredbgreat.climaticbiome.util.IHaveModel;
import net.minecraft.block.BlockLog;
import net.minecraft.item.Item;

public abstract class BlockLogClimatic extends BlockLog implements IHaveModel {
	
	@Override
	public void registerModel() {
		ClimaticBiomes.proxy.registerItemRender(Item
				.getItemFromBlock(this), 0, "inventory");
	}

}
