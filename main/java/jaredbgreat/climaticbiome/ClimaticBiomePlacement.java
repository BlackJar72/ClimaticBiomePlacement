package jaredbgreat.climaticbiome;

import jaredbgreat.climaticbiome.biomes.Pinewoods;
import jaredbgreat.climaticbiome.biomes.Scrub;
import jaredbgreat.climaticbiome.biomes.TropicalForest;
import jaredbgreat.climaticbiome.biomes.WarmForest;
import jaredbgreat.climaticbiome.blocks.Recipes;
import jaredbgreat.climaticbiome.config.ConfigHandler;
import jaredbgreat.climaticbiome.generation.BetterWorldType;
import jaredbgreat.climaticbiome.generation.chunk.biomes.GetChaparral;
import jaredbgreat.climaticbiome.generation.chunk.biomes.GetSubtropicalForest;
import jaredbgreat.climaticbiome.generation.chunk.biomes.GetTropicalForest;
import jaredbgreat.climaticbiome.proxy.IProxy;

import java.io.File;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=Info.ID, name=Info.NAME, version=Info.VERSION, acceptableRemoteVersions=Info.VERSION) 
public class ClimaticBiomePlacement {
	public static ClimaticBiomePlacement instance;
	public static BetterWorldType worldType;
	public static ConfigHandler configHandler; 
	//public static EventMonitor handler;
    
	@SidedProxy(clientSide = "jaredbgreat.climaticbiome.proxy.ClientProxy", 
				serverSide = "jaredbgreat.climaticbiome.proxy.ServerProxy")
	public static IProxy proxy;
	
	static WarmForest     warmForest;
	static TropicalForest tropicalForest;
	static WarmForest     warmForestHills;
	static TropicalForest tropicalForestHills;
	static Pinewoods      pineWoods;
	static Scrub          denseScrub;
	static Scrub          dryScrub;
	static Scrub          denseScrubHills;
	static Scrub          dryScrubHills;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	instance = this;
    	configHandler = new ConfigHandler(event.getModConfigurationDirectory().toPath() 
    			+ File.separator + Info.DIR);
    	configHandler.load();
    	worldType = new BetterWorldType();
    	registerBiomes();
    }
    
    
    @EventHandler 
    public void init(FMLInitializationEvent event) {}
    
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {    	
    	configHandler.findCustomBiomes();
    }
    
    
    private void registerBiomes() {
		Biome.registerBiome(configHandler.getBiomeID("warm forest"), 
				"warm_forest", warmForest = new WarmForest());
		Biome.registerBiome(configHandler.getBiomeID("hot forest"), 
				"hot_forest",  tropicalForest = new TropicalForest());	
		Biome.registerBiome(configHandler.getBiomeID("pinewoods"), 
				"pine_woods",  pineWoods = new Pinewoods());
		Biome.registerBiome(configHandler.getBiomeID("warm forest hills"), 
				"warm_forest_hills", warmForestHills 
				= new WarmForest(new Biome.BiomeProperties("Subtropical Forest Hills")
					.setTemperature(0.8F)
					.setRainfall(0.85F)
					.setBaseHeight(0.45F)
					.setHeightVariation(0.3F)));
		Biome.registerBiome(configHandler.getBiomeID("hot forest hills"), 
				"hot_forest_hills",  tropicalForestHills 
				= new TropicalForest(new Biome.BiomeProperties("Subtropical Forest Hills")
					.setTemperature(0.9F)
					.setRainfall(0.7F)
					.setBaseHeight(0.45F)
					.setHeightVariation(0.3F)));
		Biome.registerBiome(configHandler.getBiomeID("dense scrub"), 
				"dense_scrub",  denseScrub = new Scrub(Scrub.Type.DENSE, 
				new Biome.BiomeProperties("Dense Scrub")
					.setTemperature(1.0F)
					.setRainfall(0.1F)
					.setBaseHeight(0.125F)
					.setHeightVariation(0.05F)));
		Biome.registerBiome(configHandler.getBiomeID("dry scrub"), 
				"dry_scrub",  denseScrub = new Scrub(Scrub.Type.DRY, 
				new Biome.BiomeProperties("Dry Scrub")
					.setTemperature(1.25F)
					.setRainfall(0.05F)
					.setRainDisabled()
					.setBaseHeight(0.125F)
					.setHeightVariation(0.05F)));
		Biome.registerBiome(configHandler.getBiomeID("dense scrub hills"), 
				"dense_scrub_hills",  denseScrub = new Scrub(Scrub.Type.DENSE, 
				new Biome.BiomeProperties("Dense Scrub Hills")
					.setTemperature(1.0F)
					.setRainfall(0.1F)
					.setBaseHeight(0.45F)
					.setHeightVariation(0.3F)));
		Biome.registerBiome(configHandler.getBiomeID("warm forest"), 
				"dry scrub hills",  denseScrub = new Scrub(Scrub.Type.DRY, 
				new Biome.BiomeProperties("Dry Scrub Hills")
					.setTemperature(1.25F)
					.setRainfall(0.0F)
					.setRainDisabled()
					.setBaseHeight(0.45F)
					.setHeightVariation(0.3F)));
		Recipes.register();
		GetChaparral.init();
		GetSubtropicalForest.init();
		GetTropicalForest.init();
	}

}
