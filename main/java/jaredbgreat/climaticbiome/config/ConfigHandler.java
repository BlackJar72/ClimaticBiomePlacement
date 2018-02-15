package jaredbgreat.climaticbiome.config;

import jaredbgreat.climaticbiome.Info;

import java.io.File;
import java.util.HashMap;

import net.minecraftforge.common.config.Configuration;

public final class ConfigHandler {
	private static File dir;
	private static File file;
	private final HashMap<String, Integer> idMap;
	
	BiomeConfigurator customBiomes;
	
	// Defaults
	private static final int WARM_FOREST_ID = 66;
	private static final int HOT_FOREST_ID = 67;
	private static final int FLORIDA_ID = 68;
	private static final int WARM_FOREST_HILLS_ID = 69;
	private static final int HOT_FOREST_HILLS_ID = 70;
	private static final int DENSE_SCRUB_ID = 71;
	private static final int DRY_SCRUB_ID = 72;
	private static final int DENSE_SCRUB_HILLS_ID = 73;
	private static final int DRY_SCRUB_HILLS_ID = 74;
	
	
	public ConfigHandler(String configDir) {
		file = new File(findConfigDir(configDir).getPath() 
				+ File.separatorChar + Info.DIR + ".cfg");
		idMap = new HashMap<>();
		
	}
	
	
	public void load() {
		Configuration config = new Configuration(file);
		config.load();
		
		idMap.put("warm forest", config.getInt("Subtropical Forest", 
				"Biome IDs", WARM_FOREST_ID, 40, 255, "ID subtropical forest"));
		
		idMap.put("hot forest", config.getInt("Tropical Forest", 
						"Biome IDs", HOT_FOREST_ID, 40, 255, "ID for subtropical forest"));
		
		idMap.put("pinewoods", config.getInt("Pine Woods", 
				"Biome IDs", FLORIDA_ID, 40, 255, "ID for swampy Subtropical Pine Forest"));
		
		idMap.put("warm forest hills", config.getInt("Subtropical Forest Hills", 
				"Biome IDs", WARM_FOREST_HILLS_ID, 40, 255, "ID subtropical forest hills"));
		
		idMap.put("hot forest hills", config.getInt("Tropical Forest Hills", 
				"Biome IDs", HOT_FOREST_HILLS_ID, 40, 255, "ID tropical forest hills"));
		
		idMap.put("dense scrub", config.getInt("Dense Scrub", 
				"Biome IDs", DENSE_SCRUB_ID, 40, 255, "ID dense scrub / bush / chaparral"));
		
		idMap.put("dry scrub", config.getInt("Dry Scrub", 
				"Biome IDs", DRY_SCRUB_ID, 40, 255, "ID dry scrub / bush / chaparral"));
		
		idMap.put("dense scrub hills", config.getInt("Dense Scrub Hills", 
				"Biome IDs", DENSE_SCRUB_HILLS_ID, 40, 255, "ID dense scrub / bush / chaparral hills"));
		
		idMap.put("dry scrub hills", config.getInt("Dry Scrub Hills", 
				"Biome IDs", DRY_SCRUB_HILLS_ID, 40, 255, "ID dry scrub / bush / chaparral hills"));
				
		// Saving it all
		config.save();
	}
	
	
	public int getBiomeID(String name) {
		return idMap.get(name);
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
	
	
	public void findCustomBiomes() {
		File biomesFile = new File(dir.getPath() 
				+ File.separatorChar + Info.DIR + ".cfg");
		customBiomes = new BiomeConfigurator(biomesFile);
	}
	
	

}
