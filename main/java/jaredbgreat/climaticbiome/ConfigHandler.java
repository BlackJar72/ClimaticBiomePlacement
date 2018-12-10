package jaredbgreat.climaticbiome;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {
	
	public static boolean useBoP = true;
	public static boolean useBoPTable = false;
	public static boolean useCfg = true;
	public static boolean writeList = true;
	public static boolean rivers = true;

	private static File dir;
	private static File file;
	
	
	public ConfigHandler(String configDir) {
		file = new File(findConfigDir(configDir).getPath() 
				+ File.separatorChar + Info.DIR + ".cfg");
		
	}
	
	
	public void load() {
		Configuration config = new Configuration(file);
		config.load();	
		
		useBoP = config.getBoolean("UseBoPBiomes", "Compat", true, 
						"If true it will use Biomes O'Plenty biomes in its world type")
				 && net.minecraftforge.fml.common.Loader.isModLoaded("biomesoplenty");	
		
		useBoPTable = config.getBoolean("UseBoPClimateTable", "Compat", false, 
						"If true the climate table for Biomes O'Plenty will be used even if /n"
						+ "BoP is not install or used; you will need to provide biomes for /n"
						+ "cool forest and cool plain in the config list or Minecraft will crash!");
				
		writeList = config.getBoolean("WriteBiomelist", "Compat", true, 
						"If true a list of all biome resource locations will be saved to a file");		
		
		useCfg = config.getBoolean("UseCustomConfigs", "Compat", true, 
						"If true it read will files from the biomes folder to extends its worldgen");		
		
		rivers = config.getBoolean("AdvancedRivers", "General", true, 
						"If true there will be temperature specific rivers, which are good with \n"
						+ "seasons and weather related mods, but will use a few more ids.  \n"
						+ "set this to false if you are running out of biome ids");
		
		config.save();	// Saving it all
	}
	
	
	/**
	 * This looks for the mods config directory, and attempts to 
	 * create it if it does not exist.  It will them set this as 
	 * the config directory and return it as a File.
	 * 
	 * @param fd
	 * @return the config directory / folder
	 */
	public static File findConfigDir(String fd) {
		File out = new File(fd);
		if(!out.exists()) out.mkdir();
		
		if(!out.exists()) {
			System.err.println("[CLIMATIC BIOMES] ERROR: Could not create config directory");
		} else if(!out.isDirectory()) {
			System.err.println("[CLIMATIC BIOMES] ERROR: Config directory is not a directory!");
		} else {
			dir = out;
		}
		return out;
	}
	
	


}
