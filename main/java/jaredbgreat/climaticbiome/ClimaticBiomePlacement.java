package jaredbgreat.climaticbiome;

import jaredbgreat.climaticbiome.biomes.ModBiomes;
import jaredbgreat.climaticbiome.biomes.Pinewoods;
import jaredbgreat.climaticbiome.biomes.Scrub;
import jaredbgreat.climaticbiome.biomes.TropicalForest;
import jaredbgreat.climaticbiome.biomes.WarmForest;
import jaredbgreat.climaticbiome.blocks.ModBlocks;
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
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	instance = this;
    	configHandler = new ConfigHandler(event.getModConfigurationDirectory().toPath() 
    			+ File.separator + Info.DIR);
    	configHandler.load();
    	worldType = new BetterWorldType();
    	ModBlocks.createBlocks();
    	ModBiomes.createBiomes();
    }
    
    
    @EventHandler 
    public void init(FMLInitializationEvent event) {
    	proxy.registerItemRenders();
    	Recipes.register();
    }
    
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {    	
    	configHandler.findCustomBiomes();
    }

}
