package jaredbgreat.climaticbiome.proxy;

import java.io.File;

import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.DimensionManager;

public class ClientProxy implements IProxy {

	@Override
	public void registerItemRender(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, 
				new ModelResourceLocation(item.getRegistryName(), id));
	}

	@Override
	public void fixRenders(BlockLeaves in) {
		in.setGraphicsLevel(Minecraft.getMinecraft().isFancyGraphicsEnabled());
	}

	
	@Override
	public void output(Exception e) {
		System.err.println();
		System.err.println("*********************");
		System.err.println("*  I AM THE CLIENT  *");
		System.err.println("*********************");
		System.err.println();
		if(e != null) {
			e.printStackTrace();
		}		
	}

}
