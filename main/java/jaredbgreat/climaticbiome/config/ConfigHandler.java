package jaredbgreat.climaticbiome.config;

import jaredbgreat.climaticbiome.Info;

import java.io.File;
import java.util.HashMap;

import net.minecraftforge.common.config.Configuration;

public final class ConfigHandler {
	private static File dir;
	private static File file;
	
	BiomeConfigurator customBiomes;
	
	
	public ConfigHandler(String configDir) {
		file = new File(findConfigDir(configDir).getPath() 
				+ File.separatorChar + Info.DIR + ".cfg");
		
	}
	
	
	public void load() {
		Configuration config = new Configuration(file);
		config.load();	// Saving it all
		config.save();
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
