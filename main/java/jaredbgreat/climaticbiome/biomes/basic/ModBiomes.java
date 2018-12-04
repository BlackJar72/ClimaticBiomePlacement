package jaredbgreat.climaticbiome.biomes.basic;

import jaredbgreat.climaticbiome.ClimaticBiomes;
import jaredbgreat.climaticbiome.biomes.pseudo.PseudoBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
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
				= new TropicalForest(new Biome.BiomeProperties("Subtropical Forest Hills")
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
		// Lastly
		PseudoBiomes.createBiomes();
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
		setupBiomeTypes();
		BiomeDictionary.addTypes(warmForest, Type.FOREST, Type.CONIFEROUS);
		BiomeDictionary.addTypes(tropicalForest, Type.FOREST, Type.JUNGLE, Type.HOT);
		BiomeDictionary.addTypes(pineWoods, Type.FOREST, Type.WET, Type.HOT, Type.CONIFEROUS, Type.SWAMP);
		BiomeDictionary.addTypes(warmForestHills, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
		BiomeDictionary.addTypes(tropicalForestHills, Type.FOREST, Type.JUNGLE, Type.HOT, Type.HILLS);
		BiomeDictionary.addTypes(denseScrub, Type.SPARSE, Type.HOT, Type.DRY);
		BiomeDictionary.addTypes(dryScrub, Type.SPARSE, Type.HOT, Type.DRY, Type.SANDY);
		BiomeDictionary.addTypes(denseScrubHills, Type.SPARSE, Type.HOT, Type.DRY, Type.HILLS);
		BiomeDictionary.addTypes(dryScrubHills, Type.SPARSE, Type.HOT, Type.DRY, Type.HILLS, Type.SANDY);
		// Lastly
		PseudoBiomes.registerBiomes();
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
}
