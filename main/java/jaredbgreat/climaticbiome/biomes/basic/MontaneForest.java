package jaredbgreat.climaticbiome.biomes.basic;

import jaredbgreat.climaticbiome.biomes.basic.Pinewoods.IPineFinder;
import jaredbgreat.climaticbiome.biomes.basic.Pinewoods.PineFinder;
import jaredbgreat.climaticbiome.biomes.basic.Pinewoods.SpruceFinder;
import jaredbgreat.climaticbiome.biomes.decorators.MediterranianAlpineDecorator;
import jaredbgreat.climaticbiome.biomes.feature.GenNoTree;
import jaredbgreat.climaticbiome.configuration.ConfigHandler;

import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraft.world.biome.BiomeHills;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBirchTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class MontaneForest extends Biome {
	private static final WorldGenSavannaTree SAVANNA_TREE = new WorldGenSavannaTree(false);
	private static final GenNoTree NOTHING = new GenNoTree();
	protected static final WorldGenBirchTree BIRCH = new WorldGenBirchTree(false, false);
    private static final IBlockState JUNGLE_LOG = Blocks.LOG.getDefaultState()
    		.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
    private static final IBlockState JUNGLE_LEAF = Blocks.LEAVES.getDefaultState()
    		.withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE)
    		.withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
    private static final IBlockState OAK_LEAF = Blocks.LEAVES.getDefaultState()
    		.withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK)
    		.withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
	private final IPineFinder PINE;
	private final IPineFinder SPRUCE;
	public Type type;
	
	public static enum Type {
		COOL,
		WARM,
		HOT,
		MEDITERRANIAN;
	}
	
		

	public MontaneForest(Type type, BiomeProperties properties) {
		super(properties);
		this.type = type;
		if(type == Type.MEDITERRANIAN) {
			decorator = new MediterranianAlpineDecorator();
		}
        if(ConfigHandler.addPines) {
        	PINE = new PineFinder();
        } else {
        	PINE = new SpruceFinder();
        }
        SPRUCE = new SpruceFinder();
        if(type == Type.HOT) {
        	decorator.treesPerChunk = 20;
        } else if(type == Type.MEDITERRANIAN) {
        	// Trees are handled differently here
        	decorator.treesPerChunk = -999;        	
        } else {
	        decorator.treesPerChunk = 7;
	        decorator.grassPerChunk = 2;
        }
	}

	
	@Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		switch(type) {
		case COOL:
			return getCoolTrees(rand);
		case HOT:
			return getHotTrees(rand);
		case MEDITERRANIAN:
			return getMediterranianTrees(rand);
		case WARM:
			return getWarmTrees(rand);
		default:
			return getWarmTrees(rand);		
		}
	}
	
	
	private WorldGenAbstractTree getWarmTrees(Random rand) {
    	int kind = rand.nextInt(5);
		switch(kind) {
			case 0: return BIG_TREE_FEATURE;
			case 1: return PINE.getTree(rand); 
			case 2: return BIRCH;
			case 3: return SPRUCE.getTree(rand);
			default: return TREE_FEATURE;
		}
		
	}
	
	
	private WorldGenAbstractTree getCoolTrees(Random rand) {
    	int kind = rand.nextInt(6);
		switch(kind) {
			case 0: return BIG_TREE_FEATURE;
			case 1: return SPRUCE.getTree(rand); 
			case 2: return BIRCH;
			case 3: return SPRUCE.getTree(rand);
			case 4: return SPRUCE.getTree(rand);
			default: return BIRCH;
		}
		
	}
	
	
	private WorldGenAbstractTree getHotTrees(Random rand) {
    	int kind = rand.nextInt(10);
		switch(kind) {
			case 0: return BIG_TREE_FEATURE;
			case 1: return PINE.getTree(rand); 
			case 2: return new WorldGenTrees(false, 4 + rand.nextInt(7), 
						JUNGLE_LOG, JUNGLE_LEAF, true); 
			case 3: return new WorldGenTrees(false, 4 + rand.nextInt(7), 
						JUNGLE_LOG, JUNGLE_LEAF, true);
			case 4: return new WorldGenTrees(false, 4 + rand.nextInt(7), 
						JUNGLE_LOG, JUNGLE_LEAF, true);
			case 5: return SAVANNA_TREE;
			default: return new WorldGenShrub(JUNGLE_LOG, OAK_LEAF);
		}
	}
		
		
	public WorldGenAbstractTree getMediterranianTrees(Random rand) {
    	int kind = rand.nextInt(6);
		switch(kind) {
			case 0: return BIG_TREE_FEATURE;
			case 1: return PINE.getTree(rand);
			case 2: return PINE.getTree(rand);
			case 3: return PINE.getTree(rand);
			case 4: return BIRCH;
			default: return TREE_FEATURE;
		}
	
	}

}
