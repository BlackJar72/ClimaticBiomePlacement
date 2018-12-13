package jaredbgreat.climaticbiome.blocks;

import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.blocks.itemblocks.ItemPineLog;
import jaredbgreat.climaticbiome.util.BlockRegistrar;
import jaredbgreat.climaticbiome.util.ItemRegistrar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;

// This may become a multi-block *IF* I add more wood types
public class BlockPinePlanks extends ModBlockBase {

	public BlockPinePlanks(String name) {
		super(Material.WOOD);
		setSoundType(SoundType.WOOD);
		setUnlocalizedName(Info.ID + "." + name);
		setRegistryName(Info.ID, name);
		setHardness(1.0f);
		setHarvestLevel("axe", 0);
		setResistance(1.0f);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		Blocks.FIRE.setFireInfo(this, 5, 20);
		BlockRegistrar.addBlock(this);
		ItemRegistrar.addItem(new ItemPineLog(this));	
	}

}
