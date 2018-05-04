package jaredbgreat.climaticbiome.blocks;

import jaredbgreat.climaticbiome.blocks.items.ItemLeaf;
import jaredbgreat.climaticbiome.blocks.items.ItemLog;
import jaredbgreat.climaticbiome.blocks.items.ItemPlanks;
import jaredbgreat.climaticbiome.blocks.items.ItemSapling;
import jaredbgreat.climaticbiome.generation.feature.GenPine;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
	
	
@Mod.EventBusSubscriber
public class ModBlocks {
	
	public static BlockLog pineLog;
	public static BlockPlanks pinePlanks;
	public static BlockLeaf pineNeedle;
	public static Sapling pineSapling;
	
	public static ItemLog pineLogItem;
	public static ItemPlanks pinePlanksItem;
	public static ItemLeaf pineNeedleItem;
	public static ItemSapling pineSaplingItem;
	
	
	public static void createBlocks() {		
		pineLog = new BlockLog("pine_log");
		pinePlanks = new BlockPlanks("pine_planks");
		pineNeedle = new BlockLeaf("pine_leaves");
		pineSapling = new Sapling("pine_sapling", new GenPine());
		
		pineLogItem = new ItemLog(pineLog);
		pinePlanksItem = new ItemPlanks(pinePlanks);
		pineNeedleItem = new ItemLeaf(pineNeedle);
		pineSaplingItem = new ItemSapling(pineSapling);
	}
	
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		registerBlock(pineLog, event);
		registerBlock(pinePlanks, event);
		registerBlock(pineNeedle, event);
		registerBlock(pineSapling, event);
	}
	
	
	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
		registerItem(pineLogItem, event);
		registerItem(pinePlanksItem, event);
		registerItem(pineNeedleItem, event);
		registerItem(pineSaplingItem, event);
	}


	public static void registerBlock(Block block, RegistryEvent.Register<Block> event) {
		event.getRegistry().register(block);
		System.out.println("Registered block : " + block.getUnlocalizedName());
	}


	public static void registerItem(Item item, RegistryEvent.Register<Item> event) {
		event.getRegistry().register(item);
		System.out.println("Registered block Item: " + item.getUnlocalizedName());
	}

}
