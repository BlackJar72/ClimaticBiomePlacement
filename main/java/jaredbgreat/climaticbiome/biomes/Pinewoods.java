package jaredbgreat.climaticbiome.biomes;

import jaredbgreat.climaticbiome.generation.feature.GenPine;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

/**
 * A warm, subtropical biome based loosely on parts of northern Florida, 
 * where I lived as a child.  (Basically a hot, semi-swampy forest of 
 * southern pine and live oak (which is ironically a strangely dead looking 
 * tree in real life.)
 * 
 * @author Jared Blackburn
 */
public class Pinewoods extends BiomeForest {
	private static final GenPine PINE_GENERATOR = new GenPine();
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
	}

	
	@Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
    	if((rand.nextInt(8) % 8) == 0) {
    		return SWAMP_FEATURE;
    	} else {
    		return PINE_GENERATOR;
    	}
    }

}
