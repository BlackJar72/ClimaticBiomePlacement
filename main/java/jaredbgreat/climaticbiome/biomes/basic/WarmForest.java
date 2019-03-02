package jaredbgreat.climaticbiome.biomes.basic;

import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.biomes.basic.Pinewoods.IPineFinder;
import jaredbgreat.climaticbiome.biomes.basic.Pinewoods.PineFinder;
import jaredbgreat.climaticbiome.biomes.basic.Pinewoods.SpruceFinder;
import jaredbgreat.climaticbiome.biomes.feature.GenPine;

import java.util.Random;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

/**
 * We don't have birches here in the humid subtropical zone, they prefer cooler 
 * weather -- but we do have plenty of pines.
 * 
 * @author Jared Blackburn
 */
public class WarmForest extends BiomeForest {
	private final IPineFinder PINE;

	public WarmForest() {
		super(BiomeForest.Type.NORMAL, 
				new Biome.BiomeProperties("Subtropical Forest")
					.setTemperature(0.8f)
					.setRainfall(0.85f));
        if(ConfigHandler.addPines) {
        	PINE = new PineFinder();
        } else {
        	PINE = new SpruceFinder();
        }
	}

	
	public WarmForest(Biome.BiomeProperties prop) {
		super(BiomeForest.Type.NORMAL, prop);
        if(ConfigHandler.addPines) {
        	PINE = new PineFinder();
        } else {
        	PINE = new SpruceFinder();
        }
	}

	
	@Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
    	int t = rand.nextInt(5);
    	switch(t) {
    		case 0:
    			return BIG_TREE_FEATURE;
    		case 1:
    			return PINE.getTree(rand);
    		default:
    			return TREE_FEATURE;
    	}
    }

}
