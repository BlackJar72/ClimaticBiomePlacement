package jaredbgreat.climaticbiome;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {
	
	public static boolean useBoP = false;
	public static boolean useTraverse = false;
	public static boolean useVanilla = true;	
	public static boolean useBoPTable = false;	
	public static boolean volcanicBoP = false;
	public static boolean useCfg = true;
	public static boolean writeList = true;
	public static boolean rivers = true;
	public static boolean rockyScrub = true;
	public static boolean hasDT = false;
	public static boolean useDT = false;
	public static boolean addIslands = true;

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
						"If true it will use Biomes O'Plenty biomes in its world type. \n"
						+ "If this is true it will also automatically use BoP climate table. \n"
						+ "If BoP is not installed this does nothing.")
				 && net.minecraftforge.fml.common.Loader.isModLoaded("biomesoplenty");
		
		useTraverse = config.getBoolean("UseTraverseBiomes", "Compat", true, 
						"If true it will use Traverse biomes in its world type. \n"
						+ "If Traverse is not installed this does nothing.")
				 && net.minecraftforge.fml.common.Loader.isModLoaded("traverse");
		
		useVanilla = config.getBoolean("UseMinecraftBiomes", "Compat", true, 
						"If true it will use vanilla Minecraft biomes in its world type, \n"
						+ "along with its own biomes.  Note: It may do this anyway if no "
						+ "other biomes are provided for a climate area.");	
		
		useBoPTable = config.getBoolean("UseBoPClimateTable", "Compat", false, 
						"If true the climate table for Biomes O'Plenty will be used even if \n"
						+ "BoP is not install or used; you will need to provide biomes for \n"
						+ "cool forest and cool plains.");	
		
		volcanicBoP = config.getBoolean("VolcanicBoPIslands", "Compat", true, 
						"If true and Biomes O'Plenty is installed it will use create special \n"
						+ "volvanic islands using BoP's volcanic island biome. If this biome \n"
						+ "it not available Minecraft will crash.  If BoP is not installed ths \n"
						+ "does nothing.")
				 && net.minecraftforge.fml.common.Loader.isModLoaded("biomesoplenty");
				
		writeList = config.getBoolean("WriteBiomelist", "Compat", true, 
						"If true a list of all biome resource locations will be saved to a file.");
		
		useCfg = config.getBoolean("UseCustomConfigs", "Compat", true, 
						"If true it read will files from the BiomeConfig/custom folder to extends \n"
						+ "its worldgen. This is where to add extra biomes not otherwise supported.");	
		
		rivers = config.getBoolean("AdvancedRivers", "General", true, 
						"If true there will be temperature specific rivers, which are good with \n"
						+ "seasons and weather related mods, but will use a few more ids.  \n"
						+ "set this to false if you are running out of biome ids.");
		
		rockyScrub = config.getBoolean("RockyScrub", "General", true, 
						"If true scrub biomes will have cobble bolders.");
		
		addIslands = config.getBoolean("AddIslands", "General", true, 
						"If true extra islands will be generated in the ocean \n "
						+ "for reason I don't understand these islands tend to be \n "
						+ "chunky and squarish, but are interesting to find. \n "
						+ "Since they can generate by land they may no be true isalnds.");
		
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
