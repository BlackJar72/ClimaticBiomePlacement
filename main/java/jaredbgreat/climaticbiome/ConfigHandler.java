package jaredbgreat.climaticbiome;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {
	
	public static boolean useBoP = false;
	public static boolean useBoPTable = false;
	public static boolean useCfg = true;
	public static boolean writeList = true;
	public static boolean rivers = true;
	public static boolean cleanSlate = false;
	public static boolean rockyScrub = true;
	public static boolean useDT = false;

	private static File dir;
	private static File file;
	
	
	public ConfigHandler(String configDir) {
		file = new File(findConfigDir(configDir).getPath() 
				+ File.separatorChar + Info.DIR + ".cfg");
		
	}
	
	
	public void load() {
		useDT = net.minecraftforge.fml.common.Loader.isModLoaded("dynamictrees");	
		Configuration config = new Configuration(file);
		config.load();	
		
		useBoP = config.getBoolean("UseBoPBiomes", "Compat", true, 
						"If true it will use Biomes O'Plenty biomes in its world type.  If this \n"
						+ "is true you must have all BoP biomes enabled or Minecraft will crash.  \n"
						+ "to use only some BoP biomes you must make a custom config.  (If you do \n"
						+ "not have BoP installed this does nothing.)")
				 && net.minecraftforge.fml.common.Loader.isModLoaded("biomesoplenty");	
		
		useBoPTable = config.getBoolean("UseBoPClimateTable", "Compat", false, 
						"If true the climate table for Biomes O'Plenty will be used even if \n"
						+ "BoP is not install or used; you will need to provide biomes for \n"
						+ "cool forest and cool plain in the config list or Minecraft will crash!");
				
		writeList = config.getBoolean("WriteBiomelist", "Compat", true, 
						"If true a list of all biome resource locations will be saved to a file.");
		
		useCfg = config.getBoolean("UseCustomConfigs", "Compat", true, 
						"If true it read will files from the biomes folder to extends its worldgen.");		
		
		cleanSlate = config.getBoolean("NoDefaultBiomes", "Compat", false, 
						"DANGER: If true there will be *NO* default biomes; if this is set to true \n"
						+ "you *MUST* supply biomes to all list or Minecraft will crash!!!");		
		
		rivers = config.getBoolean("AdvancedRivers", "General", true, 
						"If true there will be temperature specific rivers, which are good with \n"
						+ "seasons and weather related mods, but will use a few more ids.  \n"
						+ "set this to false if you are running out of biome ids.");
		
		rockyScrub = config.getBoolean("RockyScrub", "General", true, 
						"If true scrub biomes will have cobble bolders.");
		
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
