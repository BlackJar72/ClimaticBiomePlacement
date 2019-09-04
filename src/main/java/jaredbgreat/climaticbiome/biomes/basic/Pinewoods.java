package jaredbgreat.climaticbiome.biomes.basic;

import jaredbgreat.climaticbiome.biomes.feature.GenPine;
import jaredbgreat.climaticbiome.configuration.ConfigHandler;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;

/**
 * A warm, subtropical biome based loosely on parts of northern Florida, 
 * where I lived as a child.  (Basically a hot, semi-swampy forest of 
 * southern pine and live oak (which is ironically a strangely dead looking 
 * tree in real life.)
 * 
 * @author Jared Blackburn
 */
public class Pinewoods extends BiomeForest {
	private final IPineFinder PINE;
	private static final IBlockState WATER_LILY = Blocks.WATERLILY.getDefaultState();

	public Pinewoods() {
		super(BiomeForest.Type.NORMAL, 
				new Biome.BiomeProperties("Pine Woods")
					.setBaseHeight(-0.1f)
					.setHeightVariation(0.1f)
					.setTemperature(0.85f)
					.setRainfall(1.0F));
	    decorator.grassPerChunk = 10;
        decorator.clayPerChunk = 1;
        decorator.waterlilyPerChunk = 2;
        if(ConfigHandler.addPines) {
        	PINE = new PineFinder();
        } else {
        	PINE = new SpruceFinder();
        }
	}

	
	@Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
    	if((rand.nextInt(8) % 8) == 0) {
    		return SWAMP_FEATURE;
    	} else {
    		return PINE.getTree(rand);
    	}
    }
	
	
	public static interface IPineFinder {
		WorldGenAbstractTree getTree(Random rand);
	}
	
	
	static class PineFinder implements IPineFinder {
		final GenPine PINE_GENERATOR = new GenPine();
		@Override
		public WorldGenAbstractTree getTree(Random rand) {
			return PINE_GENERATOR;
		}		
	}
	
	
	static class SpruceFinder implements IPineFinder {    
		final WorldGenTaiga1 PINE_GENERATOR = new WorldGenTaiga1();
		final WorldGenTaiga2 SPRUCE_GENERATOR = new WorldGenTaiga2(false);
		@Override
		public WorldGenAbstractTree getTree(Random rand) {
			return rand.nextBoolean() ? PINE_GENERATOR : SPRUCE_GENERATOR;
		}		
	}

}
