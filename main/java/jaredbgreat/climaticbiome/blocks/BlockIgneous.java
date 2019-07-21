package jaredbgreat.climaticbiome.blocks;

import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.util.BlockRegistrar;
import jaredbgreat.climaticbiome.util.ItemRegistrar;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class BlockIgneous extends ModBlockBase {
	private  String name;

	public BlockIgneous(String name) {
		super(Material.ROCK);
		this.name = name;
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setRegistryName(Info.ID, name);
        setUnlocalizedName(Info.ID + "." + name);
        setHardness(1.5f);
        setHarvestLevel("pickaxe", 0);
        setResistance(15f);
		BlockRegistrar.addBlock(this);
		ItemRegistrar.addItem(new ItemBlock(this).setRegistryName(getRegistryName()));
	}
	
}
