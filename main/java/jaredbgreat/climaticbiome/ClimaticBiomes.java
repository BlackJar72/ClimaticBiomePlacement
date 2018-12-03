package jaredbgreat.climaticbiome;

import jaredbgreat.climaticbiome.biomes.basic.ModBiomes;
import jaredbgreat.climaticbiome.generation.ClimaticWorldType;
import jaredbgreat.climaticbiome.proxy.IProxy;
import jaredbgreat.climaticbiome.util.BlockRegistrar;
import jaredbgreat.climaticbiome.util.ItemRegistrar;

import java.io.File;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(modid=Info.ID, name=Info.NAME, version=Info.VERSION,
acceptableRemoteVersions=Info.VERSION, dependencies=Info.DEPSTR)
public class ClimaticBiomes {
	@Instance
	public static ClimaticBiomes instance;
	public static ClimaticWorldType worldType;
	public ConfigHandler configHandler;

	@SidedProxy(clientSide = "jaredbgreat.climaticbiome.proxy.ClientProxy",
			    serverSide = "jaredbgreat.climaticbiome.proxy.ServerProxy")
	public static IProxy proxy;
	



    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	//instance = this;
    	configHandler = new ConfigHandler(event.getModConfigurationDirectory().toPath()
    			+ File.separator + Info.DIR);
    	configHandler.load();
    	BlockRegistrar.initBlocks();
    	ItemRegistrar.initItems();
    	worldType = new ClimaticWorldType();
    	ModBiomes.createBiomes();
    }


    @EventHandler
    public void init(FMLInitializationEvent event) {}


    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	//configHandler.findCustomBiomes();
    }

}
