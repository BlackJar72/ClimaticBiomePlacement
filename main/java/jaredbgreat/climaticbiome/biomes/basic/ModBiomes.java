package jaredbgreat.climaticbiome.biomes.basic;

import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.biomes.pseudo.PseudoBiomes;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetRiver;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeHills;
import net.minecraft.world.biome.BiomeRiver;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber
public class ModBiomes {
	
	public static WarmForest     warmForest;
	public static TropicalForest tropicalForest;
	public static WarmForest     warmForestHills;
	public static TropicalForest tropicalForestHills;
	public static Pinewoods      pineWoods;
	public static Scrub          denseScrub;
	public static Scrub          dryScrub;
	public static Scrub          denseScrubHills;
	public static Scrub          dryScrubHills;
	
	// Extra Mountains
	public static SubtropicalMountains warmMountain;
	public static SubtropicalMountains warmMountainTrees;
	public static TropicalMountains    hotMountain;
	public static TropicalMountains    hotMountainTrees;
	
	// Advanced Rivers
	public static BiomeRiver coolRiver;
	public static BiomeRiver river;
	public static BiomeRiver warmRiver;
	public static BiomeRiver hotRiver;
    
    
    public static void createBiomes() {
		warmForest = new WarmForest();
		makeMoreUsable(warmForest);
		tropicalForest = new TropicalForest();
		makeMoreUsable(tropicalForest);
		pineWoods = new Pinewoods();
		makeMoreUsable(pineWoods);
		warmForestHills 
				= new WarmForest(new Biome.BiomeProperties("Subtropical Forest Hills")
					.setTemperature(0.8F)
					.setRainfall(0.85F)
					.setBaseHeight(0.45F)
					.setHeightVariation(0.3F));
		makeMoreUsable(warmForestHills);
		tropicalForestHills 
				= new TropicalForest(new Biome.BiomeProperties("Tropical Forest Hills")
					.setTemperature(0.9F)
					.setRainfall(0.7F)
					.setBaseHeight(0.45F)
					.setHeightVariation(0.3F));
		makeMoreUsable(tropicalForestHills);
		denseScrub = new Scrub(Scrub.Type.DENSE, 
				new Biome.BiomeProperties("Dense Scrub")
					.setTemperature(1.0F)
					.setRainfall(0.1F)
					.setBaseHeight(0.125F)
					.setHeightVariation(0.05F));
		makeMoreUsable(denseScrub, true);
		dryScrub = new Scrub(Scrub.Type.DRY, 
				new Biome.BiomeProperties("Dry Scrub")
					.setTemperature(1.25F)
					.setRainfall(0.05F)
					.setRainDisabled()
					.setBaseHeight(0.125F)
					.setHeightVariation(0.05F));
		makeMoreUsable(dryScrub);
		denseScrubHills = new Scrub(Scrub.Type.DENSE, 
				new Biome.BiomeProperties("Dense Scrub Hills")
					.setTemperature(1.0F)
					.setRainfall(0.1F)
					.setBaseHeight(0.45F)
					.setHeightVariation(0.3F));
		makeMoreUsable(denseScrubHills);
		dryScrubHills = new Scrub(Scrub.Type.DRY, 
				new Biome.BiomeProperties("Dry Scrub Hills")
					.setTemperature(1.25F)
					.setRainfall(0.0F)
					.setRainDisabled()
					.setBaseHeight(0.45F)
					.setHeightVariation(0.3F));
		makeMoreUsable(dryScrubHills);
		warmMountain  
				= new SubtropicalMountains(BiomeHills.Type.NORMAL,
						new Biome.BiomeProperties("Warm Extreme Hills")
					.setTemperature(0.6F)
					.setRainfall(0.3F)
					.setBaseHeight(1.0F)
					.setHeightVariation(0.5F));
		makeMoreUsable(warmMountain);
		warmMountainTrees
				= new SubtropicalMountains(BiomeHills.Type.EXTRA_TREES,
						new Biome.BiomeProperties("Warm Forested Extreme Hills")
					.setTemperature(0.6F)
					.setRainfall(0.65F)
					.setBaseHeight(1.0F)
					.setHeightVariation(0.5F));
		makeMoreUsable(warmMountainTrees);
		hotMountain  
				= new TropicalMountains(BiomeHills.Type.NORMAL,
						new Biome.BiomeProperties("Tropical Extreme Hills")
					.setTemperature(0.8F)
					.setRainfall(0.3F)
					.setBaseHeight(1.0F)
					.setHeightVariation(0.5F));
		makeMoreUsable(warmMountain);
		hotMountainTrees
				= new TropicalMountains(BiomeHills.Type.EXTRA_TREES,
						new Biome.BiomeProperties("Tropical Forested Extreme Hills")
					.setTemperature(0.8F)
					.setRainfall(0.8F)
					.setBaseHeight(1.0F)
					.setHeightVariation(0.5F));
		makeMoreUsable(hotMountainTrees);
		// Lastly
		PseudoBiomes.createBiomes();
		if(ConfigHandler.rivers) {
			makeAdvancedRivers();
		}
    }
    

	@SubscribeEvent
	public static void registerBiomes(RegistryEvent.Register<Biome> event) {
		event.getRegistry().register(warmForest
				.setRegistryName("subtropical_forest"));
		event.getRegistry().register(tropicalForest
				.setRegistryName("tropical_forest"));
		event.getRegistry().register(pineWoods
				.setRegistryName("pine_swamp"));
		event.getRegistry().register(warmForestHills
				.setRegistryName("subtropical_forest_hills"));
		event.getRegistry().register(tropicalForestHills
				.setRegistryName("tropical_forest_hills"));
		event.getRegistry().register(denseScrub
				.setRegistryName("dense_scrub"));
		event.getRegistry().register(dryScrub
				.setRegistryName("dry_scrub"));
		event.getRegistry().register(denseScrubHills
				.setRegistryName("dense_scrub_hills"));
		event.getRegistry().register(dryScrubHills
				.setRegistryName("dry_scrub_hills"));
		event.getRegistry().register(warmMountain
				.setRegistryName("warm_mountain"));
		event.getRegistry().register(warmMountainTrees
				.setRegistryName("warm_mountain_trees"));
		event.getRegistry().register(hotMountain
				.setRegistryName("hot_mountain"));
		event.getRegistry().register(hotMountainTrees
				.setRegistryName("hot_mountain_trees"));
		setupBiomeTypes();
		BiomeDictionary.addTypes(warmForest, Type.FOREST, Type.CONIFEROUS);
		BiomeDictionary.addTypes(tropicalForest, Type.FOREST, Type.JUNGLE, Type.HOT);
		BiomeDictionary.addTypes(pineWoods, Type.FOREST, Type.WET, Type.HOT, Type.CONIFEROUS, Type.SWAMP, Type.SAVANNA);
		BiomeDictionary.addTypes(warmForestHills, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
		BiomeDictionary.addTypes(tropicalForestHills, Type.FOREST, Type.JUNGLE, Type.HOT, Type.HILLS);
		BiomeDictionary.addTypes(denseScrub, Type.SPARSE, Type.HOT, Type.DRY);
		BiomeDictionary.addTypes(dryScrub, Type.SPARSE, Type.HOT, Type.DRY, Type.SANDY);
		BiomeDictionary.addTypes(denseScrubHills, Type.SPARSE, Type.HOT, Type.DRY, Type.HILLS);
		BiomeDictionary.addTypes(dryScrubHills, Type.SPARSE, Type.HOT, Type.DRY, Type.HILLS, Type.SANDY);
		BiomeDictionary.addTypes(warmMountain, Type.HILLS, Type.MOUNTAIN);
		BiomeDictionary.addTypes(warmMountainTrees, Type.HILLS, Type.MOUNTAIN, Type.FOREST);
		BiomeDictionary.addTypes(hotMountain, Type.HILLS, Type.MOUNTAIN, Type.HOT);
		BiomeDictionary.addTypes(hotMountainTrees, Type.HILLS, Type.MOUNTAIN, Type.FOREST, Type.HOT);
		// Lastly
		PseudoBiomes.registerBiomes();
		if(ConfigHandler.rivers) {
			registerAdvancedRivers(event);
		}
	}
	
	
	private static void setupBiomeTypes() {/*TODO*/}
	
	
	private static void setupBopTypes() {/*TODO*/}
	
	
	private static void makeMoreUsable(Biome biome) {
		BiomeManager.addSpawnBiome(biome);
		BiomeManager.addStrongholdBiome(biome);
	}
	
	
	private static void makeMoreUsable(Biome biome, boolean villages) {
		BiomeManager.addSpawnBiome(biome);
		BiomeManager.addStrongholdBiome(biome);
		BiomeManager.addVillageBiome(biome, villages);
	}
	
	
	private static void makeAdvancedRivers() {
		river = new BiomeRiver(new Biome.BiomeProperties("River").setBaseHeight(-0.8f)
											            .setHeightVariation(0.0f)
												        .setTemperature(0.7F)
												        .setRainfall(0.5F)
												        .setSnowEnabled());
		warmRiver = new BiomeRiver(new Biome.BiomeProperties("River").setBaseHeight(-0.8f)
											            .setHeightVariation(0.0f)
												        .setTemperature(0.8F)
												        .setRainfall(0.5F)
												        .setSnowEnabled());
		hotRiver = new BiomeRiver(new Biome.BiomeProperties("River").setBaseHeight(-0.8f)
											            .setHeightVariation(0.0f)
												        .setTemperature(1.0F)
												        .setRainfall(0.5F)
												        .setSnowEnabled());
	}
	
	
	private static void registerAdvancedRivers(RegistryEvent.Register<Biome> event) {
		event.getRegistry().register(river
				.setRegistryName("temperate_river"));
		event.getRegistry().register(warmRiver
				.setRegistryName("subtropical_river"));
		event.getRegistry().register(hotRiver
				.setRegistryName("tropical_river"));
		BiomeDictionary.addTypes(river, Type.RIVER, Type.WATER);
		BiomeDictionary.addTypes(warmRiver, Type.RIVER, Type.WATER);
		BiomeDictionary.addTypes(hotRiver, Type.RIVER, Type.WATER, Type.HOT);		
		GetRiver.initAdvanced();
	}
	
	
	public static void addToVanilla() {
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(warmForest, 5));
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(tropicalForest, 5));
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(warmForestHills, 5));
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(tropicalForestHills, 5));
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(pineWoods, 5));

		BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(denseScrub, 5));
		BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(denseScrubHills, 5));
		BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(dryScrub, 5));
		BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(dryScrubHills, 5));
		
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(warmMountain, 5));
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(warmMountainTrees, 5));
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(hotMountain, 5));
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(hotMountainTrees, 5));		
	}
}
