package jaredbgreat.climaticbiome.util;

import jaredbgreat.climaticbiome.biomes.feature.GenPine;
import jaredbgreat.climaticbiome.blocks.BlockPineLog;
import jaredbgreat.climaticbiome.blocks.BlockPineNeedles;
import jaredbgreat.climaticbiome.blocks.BlockPinePlanks;
import jaredbgreat.climaticbiome.blocks.BlockPineSapling;
import jaredbgreat.climaticbiome.blocks.itemblocks.ItemPineSlab;
import jaredbgreat.climaticbiome.blocks.slabs.BlockPineDoubleSlab;
import jaredbgreat.climaticbiome.blocks.slabs.BlockPineSlab;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber
public class BlockRegistrar {	
	private static final List<Block> BLOCKS = new ArrayList<>();
	
	//public static Block blockDaub;
	public static BlockLog blockPineLog;
	public static BlockPineNeedles blockPineNeedles;
	public static Block blockPineSappling;
	public static BlockPineSlab pineHalfSlab;
	public static BlockPineDoubleSlab pineDoubleSlab;
	public static Block blockPinePlanks;
	
	
	public static void initBlocks() {
		//blockDaub = new BlockDaub();
		blockPineLog = new BlockPineLog();
		blockPineNeedles = new BlockPineNeedles();
		blockPineSappling = new BlockPineSapling("pine_sapling", new GenPine());
		pineHalfSlab = new BlockPineSlab("pine_slab");
		pineDoubleSlab = new BlockPineDoubleSlab("pine_doubleslab", pineHalfSlab);
		ItemRegistrar.addItem(new ItemPineSlab(pineHalfSlab, pineDoubleSlab, pineDoubleSlab));
		blockPinePlanks = new BlockPinePlanks("pine_planks");
	}
	
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> regs = event.getRegistry();
		for(Block block : BLOCKS) {
			regs.register(block);
		}
	}
	
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		for(Block block : BLOCKS) {
			
		}
	}
	
	
	public static void addBlock(Block in) {
		BLOCKS.add(in);
	}
	
	
	public static List<Block> getBlocks() {
		return BLOCKS;
	}

}
