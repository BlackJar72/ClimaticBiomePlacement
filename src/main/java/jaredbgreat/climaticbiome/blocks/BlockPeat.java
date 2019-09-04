package jaredbgreat.climaticbiome.blocks;

import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.blocks.itemblocks.ItemPeatBlock;
import jaredbgreat.climaticbiome.util.BlockRegistrar;
import jaredbgreat.climaticbiome.util.ItemRegistrar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPeat extends ModBlockBase {
	private static final String NAME = "peat";

	public BlockPeat() {
		super(Material.GROUND);
		setSoundType(SoundType.GROUND);
		setUnlocalizedName(Info.ID + "." + NAME);
		setRegistryName(Info.ID, NAME);
		setHardness(0.75f);
		setHarvestLevel("shovel", 0);
		setResistance(8.0f); // spongy
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		Blocks.FIRE.setFireInfo(this, 5, 10);
		BlockRegistrar.addBlock(this);
		ItemRegistrar.addItem(new ItemPeatBlock(this));	
	}

}
