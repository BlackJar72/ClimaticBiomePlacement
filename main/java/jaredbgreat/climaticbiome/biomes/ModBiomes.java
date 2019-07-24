package jaredbgreat.climaticbiome.biomes;

import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.biomes.basic.ActiveVolcano;
import jaredbgreat.climaticbiome.biomes.basic.CoolPlains;
import jaredbgreat.climaticbiome.biomes.basic.MontaneForest;
import jaredbgreat.climaticbiome.biomes.basic.Pinewoods;
import jaredbgreat.climaticbiome.biomes.basic.Scrub;
import jaredbgreat.climaticbiome.biomes.basic.SubtropicalMountains;
import jaredbgreat.climaticbiome.biomes.basic.TropicalForest;
import jaredbgreat.climaticbiome.biomes.basic.TropicalMountains;
import jaredbgreat.climaticbiome.biomes.basic.WarmForest;
import jaredbgreat.climaticbiome.biomes.basic.Wetland;
import jaredbgreat.climaticbiome.biomes.basic.WindsweptPlains;
import jaredbgreat.climaticbiome.biomes.pseudo.PseudoBiomes;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetRiver;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeHills;
import net.minecraft.world.biome.BiomePlains;
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
	
	public static WarmForest      warmForest;
	public static TropicalForest  tropicalForest;
	public static WarmForest      warmForestHills;
	public static TropicalForest  tropicalForestHills;
	public static Pinewoods       pineWoods;
	public static Scrub           denseScrub;
	public static Scrub           dryScrub;
	public static Scrub           denseScrubHills;
	public static Scrub           dryScrubHills;
	public static BiomePlains     coolPlains;
	public static WindsweptPlains windswept;
	public static WindsweptPlains coolWindswept;
	public static CoolPlains      coldPlains;
	public static Wetland         bog;
	public static Wetland         carr;
	public static Wetland         marsh;
	
	// Extra Mountains
	public static SubtropicalMountains warmMountain;
	public static SubtropicalMountains warmMountainTrees;
	public static TropicalMountains    hotMountain;
	public static TropicalMountains    hotMountainTrees;
	public static ActiveVolcano        activeVolcano;
	public static MontaneForest        coolMontaneForest;
	public static MontaneForest        warmMontaneForest;
	public static MontaneForest        mediMontaneForest;
	public static MontaneForest        montaneJungle;
	
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
		makeMoreUsable(pineWoods);
		activeVolcano 
				= new ActiveVolcano(new Biome.BiomeProperties("Active Volcano")
					.setTemperature(3.0F)
					.setRainfall(0.5F)
					.setBaseHeight(1.5F)
					.setHeightVariation(0.5F));
		coolMontaneForest
				= new MontaneForest(MontaneForest.Type.COOL,
						new Biome.BiomeProperties("Warm Montane Forest")
					.setTemperature(0.2F)
					.setRainfall(0.7F)
					.setBaseHeight(1.25F)
					.setHeightVariation(0.5F));
		makeMoreUsable(coolMontaneForest);
		warmMontaneForest
				= new MontaneForest(MontaneForest.Type.WARM,
						new Biome.BiomeProperties("Warm Montane Forest")
					.setTemperature(0.6F)
					.setRainfall(0.7F)
					.setBaseHeight(1.25F)
					.setHeightVariation(0.5F));
		makeMoreUsable(warmMontaneForest);
		montaneJungle
				= new MontaneForest(MontaneForest.Type.HOT,
						new Biome.BiomeProperties("Montane Jungle")
					.setTemperature(0.9F)
					.setRainfall(1.0F)
					.setBaseHeight(1.25F)
					.setHeightVariation(0.5F));
		makeMoreUsable(montaneJungle);
		mediMontaneForest
				= new MontaneForest(MontaneForest.Type.MEDITERRANIAN,
						new Biome.BiomeProperties("Dry Montane Forest")
					.setTemperature(0.7F)
					.setRainfall(0.5F)
					.setBaseHeight(1.25F)
					.setHeightVariation(0.5F));
		makeMoreUsable(mediMontaneForest);
		coolPlains = new CoolPlains(false, false,
				new Biome.BiomeProperties("Cool Plains")
					.setTemperature(0.5F)
					.setRainfall(0.5F)
					.setBaseHeight(0.125F)
					.setHeightVariation(0.05f));
		makeMoreUsable(coolPlains);
		BiomeManager.addVillageBiome(coolPlains, true);
		windswept = new WindsweptPlains( 
				new Biome.BiomeProperties("Windswept Plains")
					.setTemperature(0.8F)
					.setRainfall(0.3F)
					.setBaseHeight(0.125F)
					.setHeightVariation(0.0f));
		makeMoreUsable(windswept);
		BiomeManager.addVillageBiome(windswept, true);
		coolWindswept = new WindsweptPlains( 
				new Biome.BiomeProperties("Cool Windswept Plains")
					.setTemperature(0.5F)
					.setRainfall(0.3F)
					.setBaseHeight(0.125F)
					.setHeightVariation(0.0f));
		makeMoreUsable(coolWindswept);
		BiomeManager.addVillageBiome(coolWindswept, true);
		coldPlains = new CoolPlains(false, true,
				new Biome.BiomeProperties("Cold Plains")
					.setTemperature(0.2F)
					.setRainfall(0.3F)
					.setBaseHeight(0.125F)
					.setHeightVariation(0.05f));
		makeMoreUsable(coldPlains);
		BiomeManager.addVillageBiome(coldPlains, true);
		bog = new Wetland(Wetland.Type.BOG,
				new Biome.BiomeProperties("Bog")
					.setTemperature(0.4F)
					.setRainfall(0.9F)
					.setBaseHeight(-0.2F)
					.setHeightVariation(0.1f));
		makeMoreUsable(bog);
		carr = new Wetland(Wetland.Type.CARR,
				new Biome.BiomeProperties("Carr")
					.setTemperature(0.6F)
					.setRainfall(0.9F)
					.setBaseHeight(-0.2F)
					.setHeightVariation(0.1f));
		makeMoreUsable(carr);
		marsh = new Wetland(Wetland.Type.MARSH,
				new Biome.BiomeProperties("Marsh")
					.setTemperature(0.6F)
					.setRainfall(0.9F)
					.setBaseHeight(-0.2F)
					.setHeightVariation(0.1f));
		makeMoreUsable(marsh);
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
		event.getRegistry().register(activeVolcano
				.setRegistryName("active_volcano"));
		event.getRegistry().register(coolMontaneForest
				.setRegistryName("cool_montane_forest"));
		event.getRegistry().register(warmMontaneForest
				.setRegistryName("warm_montane_forest"));
		event.getRegistry().register(montaneJungle
				.setRegistryName("montane_jungle"));
		event.getRegistry().register(mediMontaneForest
				.setRegistryName("dry_montane_forest"));
		event.getRegistry().register(coolPlains
				.setRegistryName("cool_plains"));
		event.getRegistry().register(windswept
				.setRegistryName("windswept_plains"));
		event.getRegistry().register(coolWindswept
				.setRegistryName("cool_windswept_plains"));
		event.getRegistry().register(coldPlains
				.setRegistryName("cold_plains"));
		event.getRegistry().register(bog
				.setRegistryName("bog"));
		event.getRegistry().register(carr
				.setRegistryName("carr"));
		event.getRegistry().register(marsh
				.setRegistryName("marsh"));
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
		BiomeDictionary.addTypes(activeVolcano, Type.HILLS, Type.MOUNTAIN, Type.HOT);
		BiomeDictionary.addTypes(coolMontaneForest, Type.HILLS, Type.MOUNTAIN, Type.COLD, Type.FOREST);
		BiomeDictionary.addTypes(warmMontaneForest, Type.HILLS, Type.MOUNTAIN, Type.FOREST);
		BiomeDictionary.addTypes(montaneJungle, Type.HILLS, Type.MOUNTAIN, Type.JUNGLE, Type.HOT, Type.WET);
		BiomeDictionary.addTypes(mediMontaneForest, Type.HILLS, Type.MOUNTAIN, Type.FOREST, Type.HOT, Type.DRY);
		BiomeDictionary.addTypes(coolPlains, Type.PLAINS);
		BiomeDictionary.addTypes(windswept, Type.PLAINS);
		BiomeDictionary.addTypes(coolWindswept, Type.PLAINS);
		BiomeDictionary.addTypes(coldPlains, Type.PLAINS, Type.COLD);
		BiomeDictionary.addTypes(bog, Type.SWAMP, Type.COLD, Type.WET);
		BiomeDictionary.addTypes(carr, Type.SWAMP, Type.FOREST, Type.WET);
		BiomeDictionary.addTypes(marsh, Type.SWAMP, Type.PLAINS, Type.WET);
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
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(marsh, 5));
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(windswept, 5));
		
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(warmMountain, 5));
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(warmMountainTrees, 5));
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(hotMountain, 5));
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(hotMountainTrees, 5));
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(warmMontaneForest, 5));
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(montaneJungle, 5));

		BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(denseScrub, 5));
		BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(denseScrubHills, 5));
		BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(dryScrub, 5));
		BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(dryScrubHills, 5));
		BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(mediMontaneForest, 5));
		
		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(coolMontaneForest, 10));
		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(bog, 5));
		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(carr, 10));
		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(coolPlains, 5));
		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(coldPlains, 5));
		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(coolWindswept, 5));
		
	}
}
