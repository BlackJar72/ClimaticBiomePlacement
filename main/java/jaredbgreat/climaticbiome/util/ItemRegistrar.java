package jaredbgreat.climaticbiome.util;

import jaredbgreat.climaticbiome.ClimaticBiomes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber
public class ItemRegistrar {	
	private static final List<Item> ITEMS = new ArrayList<>();
	
	public static void initItems() {}
	
	
	@SubscribeEvent
	public static void regesterItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> regs = event.getRegistry();
		for(Item item : ITEMS) {
			regs.register(item);
		}
	}
	
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		for(Item item : ITEMS) {
			if(item instanceof IHaveModel) {
				((IHaveModel)item).registerModel();
			}
		}
		for(Block block : BlockRegistrar.getBlocks()) {
			if(block instanceof IHaveModel) {
				((IHaveModel)block).registerModel();
			}
		}
		ClimaticBiomes.proxy.fixRenders(BlockRegistrar.blockPineNeedles);
	}
	
	
	public static void addItem(Item in) {
		ITEMS.add(in);
	}

}
