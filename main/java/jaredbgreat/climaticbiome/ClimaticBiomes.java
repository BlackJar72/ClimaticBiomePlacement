package jaredbgreat.climaticbiome;

import jaredbgreat.climaticbiome.generation.ClimaticWorldType;
import jaredbgreat.climaticbiome.proxy.IProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;


@Mod(modid=Info.ID, name=Info.NAME, version=Info.VERSION,
acceptableRemoteVersions=Info.VERSION, dependencies="after:biomesoplenty")
public class ClimaticBiomes {
	public static ClimaticBiomes instance;
	public static ClimaticWorldType worldType;
	public static final boolean gotBoP    = bopLoaded();

	@SidedProxy(clientSide = "jaredbgreat.climaticbiome.proxy.ClientProxy",
			    serverSide = "jaredbgreat.climaticbiome.proxy.ServerProxy")
	public static IProxy proxy;
	
	
	


	
    private static boolean bopLoaded() {
		return net.minecraftforge.fml.common.Loader.isModLoaded("biomesoplenty");
	}

}
