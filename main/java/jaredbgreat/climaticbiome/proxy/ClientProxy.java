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
