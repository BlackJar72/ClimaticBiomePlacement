package jaredbgreat.climaticbiome;

import jaredbgreat.climaticbiome.biomes.basic.ModBiomes;
import jaredbgreat.climaticbiome.generation.ClimaticWorldType;
import jaredbgreat.climaticbiome.generation.biome.compat.dynamictrees.DynamicTreeHelper;
import jaredbgreat.climaticbiome.generation.biome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.proxy.IProxy;
import jaredbgreat.climaticbiome.util.BlockRegistrar;
import jaredbgreat.climaticbiome.util.Externalizer;
import jaredbgreat.climaticbiome.util.ItemRegistrar;

import java.io.File;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;


@Mod(modid=Info.ID, name=Info.NAME, version=Info.VERSION,
acceptableRemoteVersions=Info.VERSION, dependencies=Info.DEPSTR)
public class ClimaticBiomes {
	@Instance
	public static ClimaticBiomes instance;
	public static ClimaticWorldType worldType;
	public ConfigHandler configHandler;
	File confdir;

	@SidedProxy(clientSide = "jaredbgreat.climaticbiome.proxy.ClientProxy",
			    serverSide = "jaredbgreat.climaticbiome.proxy.ServerProxy")
	public static IProxy proxy;
	



    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	confdir = new File(event.getModConfigurationDirectory().toPath()
    			+ File.separator + Info.DIR);
    	configHandler = new ConfigHandler(confdir.toString());
    	configHandler.load();
    	BlockRegistrar.initBlocks();
    	ItemRegistrar.initItems();
    	worldType = new ClimaticWorldType();
    	ModBiomes.createBiomes();
    	if(ConfigHandler.useDT) {
    		DynamicTreeHelper.preInit();
    	}
    	proxy.preInit();
    }


    @EventHandler
    public void init(FMLInitializationEvent event) {  	
    	if(ConfigHandler.useDT) {
    		DynamicTreeHelper.init();
    	}
    	proxy.init();
    	ItemRegistrar.addRecipes();
    }


    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	if(ConfigHandler.writeList) {
    		DefReader.writeList(confdir);
    	}
    	DefReader.init(ForgeRegistries.BIOMES, confdir);
    	Externalizer extern = new Externalizer();
    	extern.copyOut(confdir);
    	ItemRegistrar.oreDict();
    }

}
