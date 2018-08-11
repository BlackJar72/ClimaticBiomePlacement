package jaredbgreat.climaticbiome.blocks.special;

import jaredbgreat.climaticbiome.ClimaticBiomes;
import jaredbgreat.climaticbiome.util.IHaveModel;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public abstract class BlockDoorBase extends BlockDoor implements IHaveModel {

	
	protected BlockDoorBase(Material materialIn) {
		super(materialIn);}

	
	@Override
	public void registerModel() {
		ClimaticBiomes.proxy.registerItemRender(Item
				.getItemFromBlock(this), 0, "inventory");
	}

}
