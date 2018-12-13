package jaredbgreat.climaticbiome.blocks;

import jaredbgreat.climaticbiome.ClimaticBiomes;
import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.blocks.itemblocks.ItemPineLog;
import jaredbgreat.climaticbiome.util.BlockRegistrar;
import jaredbgreat.climaticbiome.util.IHaveModel;
import jaredbgreat.climaticbiome.util.ItemRegistrar;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockPineStairs extends BlockStairs implements IHaveModel {

	public BlockPineStairs(IBlockState modelState, String name) {
		super(modelState);
		this.setLightOpacity(0);
		setRegistryName(name);
		setUnlocalizedName(Info.ID + "." + name);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		setHardness(1.0f);
		setHarvestLevel("axe", 0);
		setResistance(1.0f);
		Blocks.FIRE.setFireInfo(this, 5, 20);
		BlockRegistrar.addBlock(this);
		ItemRegistrar.addItem(new ItemPineLog(this));		
	}

	
	@Override
	public void registerModel() {
		ClimaticBiomes.proxy.registerItemRender(Item
				.getItemFromBlock(this), 0, "inventory");
	}

}
