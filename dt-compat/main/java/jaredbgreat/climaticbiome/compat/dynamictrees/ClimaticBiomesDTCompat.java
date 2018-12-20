package jaredbgreat.climaticbiome.compat.dynamictrees;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(modid=Info.ID, name=Info.NAME, version=Info.VERSION,
acceptableRemoteVersions=Info.VERSION, dependencies=Info.DEPSTR)
public class ClimaticBiomesDTCompat {
	@Instance
	public static ClimaticBiomesDTCompat instance;

	@SidedProxy(clientSide = "jaredbgreat.climaticbiome.compat.dynamictrees.ClientProxy",
			    serverSide = "jaredbgreat.climaticbiome.compat.dynamictrees.ServerProxy")
	public static IProxy proxy;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	DynamicTreeHelper.preInit();
    	proxy.preInit();
    }


    @EventHandler
    public void init(FMLInitializationEvent event) {
    	DynamicTreeHelper.init();
    	proxy.init();
    }

}
