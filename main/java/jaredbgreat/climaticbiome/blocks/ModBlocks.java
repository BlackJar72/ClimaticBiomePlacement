package jaredbgreat.climaticbiome.blocks;

import jaredbgreat.climaticbiome.blocks.items.ItemLeaf;
import jaredbgreat.climaticbiome.blocks.items.ItemLog;
import jaredbgreat.climaticbiome.blocks.items.ItemSapling;
import jaredbgreat.climaticbiome.generation.feature.GenPine;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
	
	
@Mod.EventBusSubscriber
public class ModBlocks {
	
	public static BlockLog pineLog = new BlockLog("pine_log");
	public static BlockPlanks pinePlanks = new BlockPlanks("pine_planks");
	public static BlockLeaf pineNeedle = new BlockLeaf("pine_leaves");
	public static Sapling pineSapling = new Sapling("pine_sapling", new GenPine());
	
	
	@SubscribeEvent
	public static void register(RegistryEvent.Register<Block> event) {
		registerBlock(pineLog, event, new ItemLog(pineLog).registerModels());
		registerBlock(pinePlanks, event, new ItemLog(pinePlanks).registerModels());
		registerBlock(pineNeedle, event, new ItemLeaf(pineNeedle).registerModels());
		registerBlock(pineSapling, event, new ItemSapling(pineSapling).registerModels());
	}


	public static void registerBlock(Block block, RegistryEvent.Register<Block> event, ItemBlock item) {
		event.getRegistry().register(block);
		GameRegistry.register(item);
		System.out.println("Registered block : " + block.getUnlocalizedName());
	}

}
