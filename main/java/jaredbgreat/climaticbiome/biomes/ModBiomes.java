package jaredbgreat.climaticbiome.biomes;

import jaredbgreat.climaticbiome.ClimaticBiomePlacement;
import jaredbgreat.climaticbiome.generation.chunk.biomes.GetChaparral;
import jaredbgreat.climaticbiome.generation.chunk.biomes.GetSubtropicalForest;
import jaredbgreat.climaticbiome.generation.chunk.biomes.GetTropicalForest;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPAlpine;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPChaparral;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPColdPlains;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPCoolForest;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPCoolPlains;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPDesert;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPIsland;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPJungle;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPOcean;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPPark;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPPlains;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPSubtropicalForest;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPSwamp;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPTaiga;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPTemporateForest;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPTundra;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPWarmPlains;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPWetForest;
import net.minecraft.world.biome.Biome;
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
		//BiomeDictionary.registerBiomeType(warmForest, Type.FOREST, Type.CONIFEROUS);
		makeMoreUsable(warmForest);
		tropicalForest = new TropicalForest();
		//BiomeDictionary.registerBiomeType(tropicalForest, Type.FOREST, Type.JUNGLE, Type.HOT);
		makeMoreUsable(tropicalForest);
		pineWoods = new Pinewoods();
		//BiomeDictionary.registerBiomeType(pineWoods, Type.FOREST, Type.WET, Type.HOT, Type.CONIFEROUS, Type.SWAMP);
		makeMoreUsable(pineWoods);
		warmForestHills 
				= new WarmForest(new Biome.BiomeProperties("Subtropical Forest Hills")
					.setTemperature(0.8F)
					.setRainfall(0.85F)
					.setBaseHeight(0.45F)
					.setHeightVariation(0.3F));
		//BiomeDictionary.registerBiomeType(warmForestHills, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
		makeMoreUsable(warmForestHills);
		tropicalForestHills 
				= new TropicalForest(new Biome.BiomeProperties("Subtropical Forest Hills")
					.setTemperature(0.9F)
					.setRainfall(0.7F)
					.setBaseHeight(0.45F)
					.setHeightVariation(0.3F));
		//BiomeDictionary.registerBiomeType(tropicalForestHills, Type.FOREST, Type.JUNGLE, Type.HOT, Type.HILLS);
		makeMoreUsable(tropicalForestHills);
		denseScrub = new Scrub(Scrub.Type.DENSE, 
				new Biome.BiomeProperties("Dense Scrub")
					.setTemperature(1.0F)
					.setRainfall(0.1F)
					.setBaseHeight(0.125F)
					.setHeightVariation(0.05F));
		//BiomeDictionary.registerBiomeType(denseScrub, Type.SPARSE, Type.HOT, Type.DRY);
		makeMoreUsable(denseScrub, true);
		dryScrub = new Scrub(Scrub.Type.DRY, 
				new Biome.BiomeProperties("Dry Scrub")
					.setTemperature(1.25F)
					.setRainfall(0.05F)
					.setRainDisabled()
					.setBaseHeight(0.125F)
					.setHeightVariation(0.05F));
		//BiomeDictionary.registerBiomeType(dryScrub, Type.SPARSE, Type.HOT, Type.DRY);
		makeMoreUsable(dryScrub);
		denseScrubHills = new Scrub(Scrub.Type.DENSE, 
				new Biome.BiomeProperties("Dense Scrub Hills")
					.setTemperature(1.0F)
					.setRainfall(0.1F)
					.setBaseHeight(0.45F)
					.setHeightVariation(0.3F));
		//BiomeDictionary.registerBiomeType(denseScrubHills, Type.SPARSE, Type.HOT, Type.DRY, Type.HILLS);
		makeMoreUsable(denseScrubHills);
		dryScrubHills = new Scrub(Scrub.Type.DRY, 
				new Biome.BiomeProperties("Dry Scrub Hills")
					.setTemperature(1.25F)
					.setRainfall(0.0F)
					.setRainDisabled()
					.setBaseHeight(0.45F)
					.setHeightVariation(0.3F));
		//BiomeDictionary.registerBiomeType(dryScrubHills, Type.SPARSE, Type.HOT, Type.DRY, Type.HILLS);
		makeMoreUsable(dryScrubHills);
    }
    

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Biome> event) {
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
		GetChaparral.init();
		GetSubtropicalForest.init();
		GetTropicalForest.init();
		if(ClimaticBiomePlacement.gotBoP) {
			setupBopTypes();
		}
	}
	
	
	private static void setupBopTypes() {
		GetBoPAlpine.init();
		GetBoPChaparral.init();
		GetBoPColdPlains.init();
		GetBoPCoolForest.init();
		GetBoPCoolPlains.init();
		GetBoPDesert.init();
		GetBoPIsland.init();
		GetBoPJungle.init();
		GetBoPOcean.init();
		GetBoPPark.init();
		GetBoPPlains.init();
		GetBoPSubtropicalForest.init();
		GetBoPSwamp.init();
		GetBoPTaiga.init();
		GetBoPTemporateForest.init();
		GetBoPTundra.init();
		GetBoPWarmPlains.init();
		GetBoPWetForest.init();		
	}
	
	
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
