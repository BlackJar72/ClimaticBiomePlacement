package jaredbgreat.climaticbiome.blocks;

import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.util.BlockRegistrar;
import jaredbgreat.climaticbiome.util.ItemRegistrar;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class BlockDaub extends ModBlockBase {

	public BlockDaub() {
		super(Material.GROUND);
		setRegistryName(Info.ID, "block_daub");
		setUnlocalizedName(Info.ID + "." + "block_daub");
		setHardness(5.0f);
		setResistance(2.0f);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		BlockRegistrar.addBlock(this);
		ItemRegistrar.addItem(new ItemBlock(this)
				.setRegistryName(getRegistryName()));
	}

}
