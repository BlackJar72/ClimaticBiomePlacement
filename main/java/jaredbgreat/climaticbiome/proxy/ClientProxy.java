package jaredbgreat.climaticbiome.proxy;

import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class ClientProxy implements IProxy {

	/*
	 * I give the *FUCK* up because this is way too
	 * super-duper-uber-ultra hard beyond all hardness 
	 * of ultimate impossibility.  I Don't know what 
	 * kind of magic spell other cast to make models 
	 * work, but this is random and erratic, with 
	 * no rhyme, reason, or control.  Plus, its 
	 * absurdly stresdful -- doing something that 
	 * causes extreme missery for "fun" makes no 
	 * sense.  So I give up; something that is 
	 * often the best thing you can do.
	 * 
	 * FUCK THIS!
	 */
	
	@Override
	public void registerItemRenders() {
		ModBlocks.pineLogItem.registerModels();
		ModBlocks.pinePlanksItem.registerModels();
		ModBlocks.pineNeedleItem.registerModels();	
		ModBlocks.pineSaplingItem.registerModels();
	}

	@SideOnly(Side.CLIENT)
	public static void initClient(ItemModelMesher mesher, Block block) {		
		Item item = Item.getItemFromBlock(block);
		ModelResourceLocation model = new ModelResourceLocation(Info.ID + ":" 
					+ block.getUnlocalizedName(), "inventory");
		ModelLoader.registerItemVariants(item, model);
		mesher.register(item, 0, model);
	}
	
}
