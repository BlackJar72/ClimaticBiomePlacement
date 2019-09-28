package jaredbgreat.climaticbiome.util;

import jaredbgreat.climaticbiome.biomes.feature.GenPine;
import jaredbgreat.climaticbiome.blocks.BlockIgneous;
import jaredbgreat.climaticbiome.blocks.BlockPeat;
import jaredbgreat.climaticbiome.blocks.BlockPineDoor;
import jaredbgreat.climaticbiome.blocks.BlockPineFence;
import jaredbgreat.climaticbiome.blocks.BlockPineGate;
import jaredbgreat.climaticbiome.blocks.BlockPineLog;
import jaredbgreat.climaticbiome.blocks.BlockPineNeedles;
import jaredbgreat.climaticbiome.blocks.BlockPinePlanks;
import jaredbgreat.climaticbiome.blocks.BlockPineSapling;
import jaredbgreat.climaticbiome.blocks.BlockPineStairs;
import jaredbgreat.climaticbiome.blocks.ModBlockFalling;
import jaredbgreat.climaticbiome.blocks.itemblocks.ItemPineSlab;
import jaredbgreat.climaticbiome.blocks.slabs.BlockPineDoubleSlab;
import jaredbgreat.climaticbiome.blocks.slabs.BlockPineSlab;
import jaredbgreat.climaticbiome.configuration.ConfigHandler;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.registry.GameRegistry;
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
	public static BlockPineStairs blockPineStairs;
	public static BlockPineFence blockPineFence;
	public static BlockPineGate blockPineGate;
	public static BlockPineDoor blockPineDoor;
	public static BlockIgneous blockBasalt;
	public static BlockIgneous blockBasaltPolished;
	public static ModBlockFalling blockAsh;
	public static BlockPeat blockPeat;
	
	
	public static void initBlocks() {
    	if(ConfigHandler.pineBlocks) {
    		blockPineLog = new BlockPineLog();
    		blockPineNeedles = new BlockPineNeedles();
    		blockPineSappling = new BlockPineSapling("pine_sapling", new GenPine());
    		pineHalfSlab = new BlockPineSlab("pine_slab");
    		pineDoubleSlab = new BlockPineDoubleSlab("pine_doubleslab", pineHalfSlab);
    		ItemRegistrar.addItem(new ItemPineSlab(pineHalfSlab, pineDoubleSlab, pineDoubleSlab));
    		blockPinePlanks = new BlockPinePlanks("pine_planks");
    		blockPineStairs = new BlockPineStairs(blockPinePlanks.getDefaultState(), "pine_stairs");
    		blockPineFence = new BlockPineFence("pine_fence");
    		blockPineGate = new BlockPineGate("pine_gate");
    		blockPineDoor = new BlockPineDoor("pine_door");
    	}
		if(ConfigHandler.includeVolcano) {
			blockBasalt = new BlockIgneous("basalt");
			blockBasaltPolished = new BlockIgneous("basalt_polished");
			blockAsh = new ModBlockFalling("volcanic_ash", SoundType.SAND);
		}
		if(ConfigHandler.includeSwamps && ConfigHandler.peatBlocks) {
			blockPeat = new BlockPeat();
		}
		// LASTLY
		registerBlocks();
	}
	
	
	public static void registerBlocks() {
		IForgeRegistry<Block> regs = GameRegistry.findRegistry(Block.class);
		for(Block block : BLOCKS) {
			regs.register(block);
		}
	}
	
	
	public static void addBlock(Block in) {
		BLOCKS.add(in);
	}
	
	
	public static List<Block> getBlocks() {
		return BLOCKS;
	}

}
