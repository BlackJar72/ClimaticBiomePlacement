package jaredbgreat.climaticbiome.util;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class BlockRegistrar {/*	
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
		if(ConfigHandler.includeVolcano) {
			blockBasalt = new BlockIgneous("basalt");
			blockBasaltPolished = new BlockIgneous("basalt_polished");
			blockAsh = new ModBlockFalling("volcanic_ash", SoundType.SAND);
		}
		if(ConfigHandler.includeSwamps) {
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

*/}
