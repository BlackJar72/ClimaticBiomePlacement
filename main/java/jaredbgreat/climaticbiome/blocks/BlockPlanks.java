package jaredbgreat.climaticbiome.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

// This may become a multi-block *IF* I add more wood types
public class BlockPlanks extends Block {

	public BlockPlanks(String name) {
		super(Material.WOOD);
		setSoundType(SoundType.WOOD);
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(1.0f);
		setHarvestLevel("axe", 0);
		setResistance(1.0f);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}

}
