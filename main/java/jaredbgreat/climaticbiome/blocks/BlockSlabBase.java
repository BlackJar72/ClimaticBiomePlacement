package jaredbgreat.climaticbiome.blocks;

import jaredbgreat.climaticbiome.ClimaticBiomes;
import jaredbgreat.climaticbiome.util.IHaveModel;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public abstract class BlockSlabBase extends BlockSlab implements IHaveModel {

	public BlockSlabBase(Material materialIn) {
		super(materialIn);
	}

	
	public BlockSlabBase(Material material, MapColor color) {
		super(material, color);
	}

	
	@Override
	public void registerModel() {
		ClimaticBiomes.proxy.registerItemRender(Item
				.getItemFromBlock(this), 0, "inventory");
	}

}
