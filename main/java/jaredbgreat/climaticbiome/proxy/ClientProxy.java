package jaredbgreat.climaticbiome.proxy;

import jaredbgreat.climaticbiome.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class ClientProxy implements IProxy {
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerItemRenders() {
		ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		initClient(mesher, ModBlocks.pineLog, ModBlocks.pineLog.getBasicState());
		initClient(mesher, ModBlocks.pinePlanks);
		initClient(mesher, ModBlocks.pineNeedle, ModBlocks.pineNeedle.getDefaultState());
		initClient(mesher, ModBlocks.pineSapling);
		initClient(mesher, ModBlocks.pineHalfSlab, ModBlocks.pineHalfSlab.getDefaultState());
	}
	

	@SideOnly(Side.CLIENT)
	public static void initClient(ItemModelMesher mesher, Block block) {
		Item item = Item.getItemFromBlock(block);
		ModelResourceLocation model = 
				new ModelResourceLocation(block.getRegistryName(),"normal");
		mesher.register(item, 0, model);
	}
	

	@SideOnly(Side.CLIENT)
	public static void initClient(ItemModelMesher mesher, Block block, IBlockState type) {
		Item item = Item.getItemFromBlock(block);
		String s = new DefaultStateMapper().getPropertyString(type.getProperties());
		ModelResourceLocation model = 
				new ModelResourceLocation(block.getRegistryName(), s);
		mesher.register(item, 0, model);
	}
	
}
