package jaredbgreat.climaticbiome.blocks;

import jaredbgreat.climaticbiome.ClimaticBiomes;
import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.blocks.itemblocks.ItemPineLog;
import jaredbgreat.climaticbiome.util.BlockRegistrar;
import jaredbgreat.climaticbiome.util.IHaveModel;
import jaredbgreat.climaticbiome.util.ItemRegistrar;
import net.minecraft.block.BlockFence;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class BlockPineFence extends BlockFence implements IHaveModel {

	public BlockPineFence(String name) {
		super(Material.WOOD, Material.WOOD.getMaterialMapColor());
		setRegistryName(new ResourceLocation(Info.ID, name));
		setUnlocalizedName(Info.ID + "." + name);
		setCreativeTab(CreativeTabs.DECORATIONS);
		setHardness(2F);
		setSoundType(SoundType.WOOD);		
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
