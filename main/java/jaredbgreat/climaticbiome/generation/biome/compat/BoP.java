package jaredbgreat.climaticbiome.generation.biome.compat;

import jaredbgreat.climaticbiome.biomes.basic.ModBiomes;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.biome.NoiseDoubleBiome;
import jaredbgreat.climaticbiome.generation.biome.SeedDoubleBiome;
import jaredbgreat.climaticbiome.generation.biome.TempDoubleBiome;
import jaredbgreat.climaticbiome.generation.biome.WetDoubleBiome;
import net.minecraft.init.Biomes;
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
		forest.addItem(new NoiseDoubleBiome(BOPBiomes.meadow.get(), 
											4, BOPBiomes.coniferous_forest.get()));
		forest.addItem(new LeafBiome(BOPBiomes.seasonal_forest.get()), 2);
		forest.addItem(new LeafBiome(BOPBiomes.dead_forest.get()));
		forest.addItem(new LeafBiome(BOPBiomes.ominous_woods.get()));
		forest.addItem(new LeafBiome(BOPBiomes.shield.get()), 2);
		forest.addItem(new NoiseDoubleBiome(BOPBiomes.meadow.get(), 
											4, BOPBiomes.shield.get()));
	}
	
	
	public static void addCoolPark(BiomeList parks) {
		parks.addItem(new LeafBiome(BOPBiomes.meadow.get()));
	}
	
	
	public static void addCoolPlains(BiomeList plains) {
		plains.addItem(new LeafBiome(BOPBiomes.moor.get()));
		plains.addItem(new LeafBiome(BOPBiomes.grassland.get()), 3);
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
	
	
	public static void addForest(BiomeList forests) {
		forests.addItem(new LeafBiome(BOPBiomes.woodland.get()));
		forests.addItem(new WetDoubleBiome(BOPBiomes.orchard.get(), 
										   7, BOPBiomes.land_of_lakes.get()));
		forests.addItem(new LeafBiome(BOPBiomes.land_of_lakes.get()));
		forests.addItem(new LeafBiome(BOPBiomes.temperate_rainforest.get()));
		forests.addItem(new WetDoubleBiome(BOPBiomes.woodland.get(), 
										   7, BOPBiomes.temperate_rainforest.get()));
	}
	
	
	public static void addIslands(BiomeList frozen, BiomeList cold, BiomeList cool, 
								  BiomeList warm, BiomeList hot, BiomeList desert) {
		cool.addItem(new NoiseDoubleBiome(BOPBiomes.origin_beach.get(), 
									      3, BOPBiomes.origin_island.get()));
		warm.addItem(new LeafBiome(BOPBiomes.flower_island.get()));
		hot.addItem(new LeafBiome(BOPBiomes.tropical_island.get()));		
	}
	
	
	public static void addHotForest(BiomeList forests) {}
	
	
	public static void addJungle(BiomeList jungle) {
		jungle.addItem(new LeafBiome(BOPBiomes.rainforest.get()), 2);
		jungle.addItem(new LeafBiome(BOPBiomes.tropical_rainforest.get()), 2);
		jungle.addItem(new LeafBiome(BOPBiomes.overgrown_cliffs.get()));
	}
	
	
	public static void addOceans(BiomeList frozen, BiomeList cold, BiomeList cool, 
			 					 BiomeList warm, BiomeList hot, BiomeList dfrozen, 
			 					 BiomeList dcold, BiomeList dcool, BiomeList dwarm, 
			 					 BiomeList dhot) {
		cool.addItem(new LeafBiome(BOPBiomes.kelp_forest.get()));
		warm.addItem(new LeafBiome(BOPBiomes.coral_reef.get()));
		hot.addItem(new LeafBiome(BOPBiomes.coral_reef.get()));		
	}
	
	
	public static void addParks(BiomeList parks) {
		parks.addItem(new LeafBiome(BOPBiomes.grove.get()), 3);
		parks.addItem(new LeafBiome(BOPBiomes.orchard.get()), 2);
		parks.addItem(new LeafBiome(BOPBiomes.cherry_blossom_grove.get()));
	}
	
	
	public static void addPlains(BiomeList plains) {
		plains.addItem(new LeafBiome(BOPBiomes.prairie.get()), 3);
		plains.addItem(new LeafBiome(BOPBiomes.flower_field.get()));
		plains.addItem(new LeafBiome(BOPBiomes.lavender_fields.get()));
		plains.addItem(new LeafBiome(BOPBiomes.pasture.get()));
	}
	
	
	public static void addSavanna(BiomeList savannas) {}
	
	
	public static void addSwamps(BiomeList cold, BiomeList cool, BiomeList warm, BiomeList hot) {
		cold.addItem(new LeafBiome(BOPBiomes.bog.get()), 3);
		cold.addItem(new LeafBiome(BOPBiomes.fen.get()), 3);
		cold.addItem(new LeafBiome(BOPBiomes.dead_swamp.get()));
		cold.addItem(new LeafBiome(BOPBiomes.quagmire.get()));
		cool.addItem(new LeafBiome(BOPBiomes.wetland.get()));
		cool.addItem(new LeafBiome(BOPBiomes.lush_swamp.get()));
		cool.addItem(new LeafBiome(BOPBiomes.marsh.get()));
		warm.addItem(new LeafBiome(BOPBiomes.bayou.get()), 2);
		warm.addItem(new LeafBiome(ModBiomes.pineWoods), 2);
		warm.addItem(new LeafBiome(BOPBiomes.mangrove.get()));
		hot.addItem(new LeafBiome(BOPBiomes.mangrove.get()), 2);
	}
	
	
	public static void addTaiga(int tbound, BiomeList forest) {
		forest.addItem(new TempDoubleBiome(BOPBiomes.snowy_coniferous_forest.get(),  
								           tbound, BOPBiomes.boreal_forest.get()),  4);
		forest.addItem(new TempDoubleBiome(BOPBiomes.snowy_coniferous_forest.get(),  
										   tbound, BOPBiomes.maple_woods.get()));
		forest.addItem(new TempDoubleBiome(BOPBiomes.snowy_forest.get(),  
				   						   tbound, BOPBiomes.maple_woods.get()));
	}
	
	
	public static void addTundra(BiomeList tundra) {
		tundra.addItem(new TempDoubleBiome(Biomes.ICE_PLAINS, 2, BOPBiomes.tundra.get()), 4);
		tundra.addItem(new TempDoubleBiome(BOPBiomes.glacier.get(), 2, BOPBiomes.tundra.get()), 2);
	}
	
	
	public static void addWarmForest(BiomeList forests) {
		forests.addItem(new LeafBiome(BOPBiomes.bamboo_forest.get()), 3);
		forests.addItem(new LeafBiome(BOPBiomes.mystic_grove.get()));
		forests.addItem(new LeafBiome(BOPBiomes.sacred_springs.get()));		
	}
	
	

}
