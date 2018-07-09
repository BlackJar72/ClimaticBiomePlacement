package jaredbgreat.climaticbiome.blocks;

import jaredbgreat.climaticbiome.ClimaticBiomes;
import jaredbgreat.climaticbiome.util.IHaveModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public abstract class ModBlockBase extends Block implements IHaveModel {
	
	public ModBlockBase(Material materialIn) {
		super(materialIn);
	}

	
	@Override
	public void registerModel() {
		ClimaticBiomes.proxy.registerItemRender(Item
				.getItemFromBlock(this), 0, "inventory");
	}

}
