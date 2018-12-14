package jaredbgreat.climaticbiome.util;

import jaredbgreat.climaticbiome.ClimaticBiomes;
import jaredbgreat.climaticbiome.Info;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber
public class ItemRegistrar {	
	private static final List<Item> ITEMS = new ArrayList<>();
	
	public static void initItems() {
		// LASTLY, after any items get reated
		registerItems();
	}
	

	public static void registerItems() {
		IForgeRegistry<Item> regs = GameRegistry.findRegistry(Item.class);
		for(Item item : ITEMS) {
			regs.register(item);
		}
	}
	
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		for(Item item : ITEMS) {
			if(item instanceof IHaveModel) {
				((IHaveModel)item).registerModel();
			} else {
				ClimaticBiomes.proxy.registerItemRender(item, 0);
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
	
	
	public static void oreDict() {
		OreDictionary.registerOre("logWood", BlockRegistrar.blockPineLog);
		OreDictionary.registerOre("plankWood", BlockRegistrar.blockPinePlanks);
		OreDictionary.registerOre("slabWood", BlockRegistrar.pineHalfSlab);
		OreDictionary.registerOre("treeLeaves", BlockRegistrar.blockPineNeedles);
		OreDictionary.registerOre("treeSapling", BlockRegistrar.blockPineSappling);		
	}
	
	
	/**
	 * For adding recipes -- should only be used for smelting, as crafting is being 
	 * done in JSON.
	 */
	public static void addRecipes() {
		GameRegistry.addSmelting(ForgeRegistries.ITEMS
				.getValue(new ResourceLocation(Info.ID + ":pine_log")), 
				new ItemStack(Items.COAL, 1, 1), 0.15f);
	}

}
