package jaredbgreat.climaticbiome.generation.biome.compat;

import net.minecraft.init.Biomes;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.biome.NoiseDoubleBiome;
import jaredbgreat.climaticbiome.generation.biome.SeedDoubleBiome;
import jaredbgreat.climaticbiome.generation.biome.TempDoubleBiome;
import biomesoplenty.api.biome.BOPBiomes;

public class BoP {
	
	public static void addAlpine(BiomeList wet, BiomeList dry) {
		wet.addItem(new LeafBiome(BOPBiomes.highland.get()));
		wet.addItem(new LeafBiome(BOPBiomes.mountain.get()));
		wet.addItem(new SeedDoubleBiome(BOPBiomes.highland.get(), 3, BOPBiomes.highland.get()));
		dry.addItem(new LeafBiome(BOPBiomes.highland.get()));
		dry.addItem(new LeafBiome(BOPBiomes.mountain.get()));
		dry.addItem(new SeedDoubleBiome(BOPBiomes.highland.get(), 3, BOPBiomes.highland.get()));		
	}
	
	
	public static void addChaparral(BiomeList scrub) {
		scrub.addItem(new LeafBiome(BOPBiomes.chaparral.get()));
		scrub.addItem(new TempDoubleBiome(BOPBiomes.shrubland.get(), 
										  16, BOPBiomes.steppe.get()));
		scrub.addItem(new TempDoubleBiome(BOPBiomes.shrubland.get(), 
										  16, BOPBiomes.brushland.get()));
	}
	
	
	public static void addColdPlains(BiomeList plains) {
		plains.addItem(new LeafBiome(BOPBiomes.tundra.get()));
	}
	
	
	public static void addCoolForest(BiomeList forest) {
		forest.addItem(new LeafBiome(BOPBiomes.coniferous_forest.get()), 2);
		forest.addItem(new LeafBiome(BOPBiomes.seasonal_forest.get()), 2);
		forest.addItem(new LeafBiome(BOPBiomes.dead_forest.get()));
		forest.addItem(new LeafBiome(BOPBiomes.ominous_woods.get()));
		forest.addItem(new LeafBiome(BOPBiomes.shield.get()), 2);
	}
	
	
	public static void addCoolPark(BiomeList parks) {
		parks.addItem(new LeafBiome(BOPBiomes.meadow.get()));
	}
	
	
	public static void addCoolPlains(BiomeList plains) {
		plains.addItem(new LeafBiome(BOPBiomes.meadow.get()));
		plains.addItem(new LeafBiome(BOPBiomes.moor.get()));
	}
	
	
	public static void addDesert(BiomeList deserts) {
		deserts.addItem(new LeafBiome(BOPBiomes.lush_desert.get()));
		deserts.addItem(new NoiseDoubleBiome(Biomes.MESA, 5, BOPBiomes.lush_desert.get()));	
		deserts.addItem(new LeafBiome(BOPBiomes.outback.get()), 4);
		deserts.addItem(new LeafBiome(BOPBiomes.xeric_shrubland.get()), 6);
		deserts.addItem(new SeedDoubleBiome(BOPBiomes.wasteland.get(), 
											5, BOPBiomes.lush_desert.get()));
		deserts.addItem(new NoiseDoubleBiome(BOPBiomes.oasis.get(), 3, Biomes.DESERT));
	}

}
