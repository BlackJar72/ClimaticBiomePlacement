package jaredbgreat.climaticbiome.blocks;

import jaredbgreat.climaticbiome.ClimaticBiomes;
import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.blocks.itemblocks.ItemPineLog;
import jaredbgreat.climaticbiome.util.BlockRegistrar;
import jaredbgreat.climaticbiome.util.IHaveModel;
import jaredbgreat.climaticbiome.util.ItemRegistrar;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class BlockPineGate extends BlockFenceGate implements IHaveModel {

	public BlockPineGate(String name) {
		super(BlockPlanks.EnumType.OAK);
		setRegistryName(new ResourceLocation(Info.ID, name));
		setUnlocalizedName(Info.ID + "." + name);
		setSoundType(SoundType.WOOD);
		setCreativeTab(CreativeTabs.REDSTONE);		
		Blocks.FIRE.setFireInfo(this, 5, 20);
		setHardness(2F);
		BlockRegistrar.addBlock(this);
		ItemRegistrar.addItem(new ItemPineLog(this));
	}

	
	@Override
	public void registerModel() {
		ClimaticBiomes.proxy.registerGateRenders(this);
	}

}
