package jaredbgreat.climaticbiome.blocks;

import jaredbgreat.climaticbiome.ClimaticBiomes;
import jaredbgreat.climaticbiome.util.IHaveModel;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public abstract class BlockSlabBase extends BlockSlab implements IHaveModel {
    protected final boolean beDouble;

	public BlockSlabBase(Material materialIn, boolean beDouble) {
		super(materialIn);
		this.beDouble = beDouble;
	}

	
	public BlockSlabBase(Material material, MapColor color, boolean beDouble) {
		super(material, color);
		this.beDouble = beDouble;
	}

	
	@Override
	public void registerModel() {
		if(!beDouble) {
			ClimaticBiomes.proxy.registerItemRender(Item
					.getItemFromBlock(this), 0, "inventory");
		}
	}

}
