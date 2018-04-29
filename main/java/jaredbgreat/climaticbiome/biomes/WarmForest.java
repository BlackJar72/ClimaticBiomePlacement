package jaredbgreat.climaticbiome.biomes;

import jaredbgreat.climaticbiome.generation.feature.GenPine;

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
	private final GenPine PinGenerator;

	public WarmForest() {
		super(BiomeForest.Type.NORMAL, 
				new Biome.BiomeProperties("Subtropical Forest")
					.setTemperature(0.8f)
					.setRainfall(0.85f));
		PinGenerator = new GenPine();
	}

	public WarmForest(Biome.BiomeProperties prop) {
		super(BiomeForest.Type.NORMAL, prop);
		PinGenerator = new GenPine();
	}

	
	@Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
    	int t = rand.nextInt(5);
    	switch(t) {
    		case 0:
    			return BIG_TREE_FEATURE;
    		case 1:
    			return PinGenerator;
    		default:
    			return TREE_FEATURE;
    	}
    }

}
