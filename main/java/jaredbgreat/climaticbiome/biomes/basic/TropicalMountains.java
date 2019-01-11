package jaredbgreat.climaticbiome.biomes.basic;

import jaredbgreat.climaticbiome.biomes.feature.GenPine;

import java.util.Random;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.biome.BiomeHills;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class TropicalMountains extends BiomeHills {
	private final GenPine PinGenerator;

	public TropicalMountains(Type p_i46710_1_, BiomeProperties properties) {
		super(p_i46710_1_, properties);
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
