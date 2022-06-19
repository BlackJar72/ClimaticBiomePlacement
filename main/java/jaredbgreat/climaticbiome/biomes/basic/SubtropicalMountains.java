package jaredbgreat.climaticbiome.biomes.basic;

import java.util.Random;

import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.biome.BiomeHills;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBirchTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;

public class SubtropicalMountains extends BiomeHills {
	private static final WorldGenTaiga2 SPRUCE = new WorldGenTaiga2(false);
	protected static final WorldGenBirchTree BIRCH = new WorldGenBirchTree(false, false);

	public SubtropicalMountains(Type p_i46710_1_, BiomeProperties properties) {
		super(p_i46710_1_, properties);
	}


	@Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
    	int kind = rand.nextInt(10);
		switch(kind) {
			case 0: return BIG_TREE_FEATURE;
			case 1: 
			case 2: return BIRCH;
			case 3: return SPRUCE;
			default: return TREE_FEATURE;
		}
    }

}
