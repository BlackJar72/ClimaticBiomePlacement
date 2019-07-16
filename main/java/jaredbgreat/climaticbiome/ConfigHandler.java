package jaredbgreat.climaticbiome;

import jaredbgreat.climaticbiome.generation.ClimaticWorldType;
import jaredbgreat.climaticbiome.generation.generator.SizeScale;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {
	
	public static boolean useBoP = false;
	public static boolean useTraverse = false;
	public static boolean useVanilla = true;	
	public static boolean useBoPTable = false;	
	public static boolean volcanicBoP = false;
	public static boolean useCfg = true;
	//public static boolean jeid = false;
	public static boolean writeBiomeLists = true;
	public static boolean writeWTLists = false;
	public static boolean rivers = true;
	public static boolean rockyScrub = true;
	public static boolean hasDT = false;
	public static boolean useDT = false;
	public static boolean addIslands = true;
	public static boolean moreMansion = true;
	public static boolean moddedBlocks = true;
	public static boolean addPines = true;
	public static boolean deepSand = true;
	public static boolean badBiomeSpam = false;
	public static boolean makeDefault = false;
	public static boolean addToVanilla = false;
	public static boolean extraBeaches = false;
	public static String  chunkProvider = "default";
	
	public static int        biomeSize  = 16;
	public static SizeScale  regionSize = SizeScale.X1;
	public static boolean    forceWhole = false;
	public static double     sisize     = 6.0; 
	
	public static int mode = 1;

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
				
		writeBiomeLists = config.getBoolean("WriteBiomelist", "Compat", true, 
						"If true a list of all biome resource locations will be saved to a file.");
		
		writeWTLists = config.getBoolean("WriteWorldTypeList", "Compat", false, 
						"If true a list creatable world types by resource location; /n"
						+ "WARNING: This has been known to crash the game with certain setups \n"
						+ "(core mods?  Forge version?).");

		
		useCfg = config.getBoolean("UseCustomConfigs", "Compat", true, 
						"If true it read will files from the BiomeConfig/custom folder to extends \n"
						+ "its worldgen. This is where to add extra biomes not otherwise supported.");	
		
		rivers = config.getBoolean("AdvancedRivers", "General", true, 
						"If true there will be temperature specific rivers, which are good with \n"
						+ "seasons and weather related mods, but will use a few more ids.  \n"
						+ "set this to false if you are running out of biome ids.");
		
		extraBeaches = config.getBoolean("ExtraBeaches", "General", false, 
						"If true there will be more beaches.");
		
		rockyScrub = config.getBoolean("RockyScrub", "General", true, 
						"If true scrub biomes will have cobble bolders.");
		
		deepSand = config.getBoolean("DeepSandInScrub", "General", true, 
						"If true sand in dry scrub will be 3-4 blocks deep, otherwise it will be 1.");
		
		addIslands = config.getBoolean("AddIslands", "General", true, 
						"If true extra islands will be generated in the ocean \n "
						+ "for reason I don't understand these islands tend to be \n "
						+ "chunky and squarish, but are interesting to find.");
		
		moreMansion = config.getBoolean("MoreMansion", "General", true, 
						"If true woodConfigHandler.rivers && land mansion might appear in all forest types; \n"
						+ " this fits the mods theme and eliminates a major lag spike but \n"
						+ " will effect all world types (even vanilla); depending on Forge / \n"
						+ " Java / OS / unknown factors it might not work.");
				
		addPines = config.getBoolean("AddPines", "Pines", true, 
						"If true modded pine trees will appear in warmer climate. \n"
						+ "If false spruce will be added instead.  \n"
						+ "This does not effect if pine blocks technically exist. \n"
						+ "Disabling pine while using dynamic trees will crash the game!!");
		
		moddedBlocks = config.getBoolean("ModdedBlocks", "Pines", true, 
						"If pine blocks exist and pines will be made from them. \n"
						+ "If false these blocks don't exist and pines are made from spruce blocks. \n"
						+ "If dynamic trees compatibility is ues this will do nothing!");
		
		badBiomeSpam = config.getBoolean("ErrorsForBadBiomeID", "Debugging", false, 
						"If true an error message will be printed to the console whenever a \n"
						+ "biome can't be fount for an ID (this will spam the console and may \n"
						+ "freeze the game).  Othewise they will silently become oceans.");
		
		makeDefault = config.getBoolean("MakeDefaut", "General", false, 
						"If true the Climatic world type will be moved to the top of the list \n"
						+ "of world types and will be used by default on new worlds. \n"
						+ "(Note that it will be impossible to create a default vanilla world.)");
		
		addToVanilla = config.getBoolean("AddToVanilla", "General", false, 
						"If true the biomes from this mod will appear in vanilla world types.");
		
		chunkProvider = config.getString("ChunkProvider", "General", "default", 
						"You can use another mods chunk provider here if you like; \n"
						+ "Warning: This is not guaraunteed to work (could depend "
						+ "on how the other mod is written, which is out of my control).");
		ClimaticWorldType.setChunkProviderType(chunkProvider);
		
		biomeSize = config.getInt("BiomeSize", "Size", 16, 4, 64, "The average width of a "
						+ "biome area in chunks.");
		
		regionSize = SizeScale.get(config.getInt("MapScale", "Size", 1, 1, 4, "The distance multiplier for "
						+ "scaling up the map \n"
						+ "     1 = x1 ->  4096 x 4096  blocks\n"
						+ "     2 = x2 ->  8192 x 8192  blocks\n"
						+ "     3 = x3 -> 12288 x 12288 blocks\n"
						+ "     4 = x4 -> 16384 x 16384 blocks\n"
						+ "WARNING: Chaning this will break existing worlds!!!"));
		
		forceWhole = config.getBoolean("ForceWholeBiome", "Size", false, 
				"If true biome areas will not be split.  Instead they will all be the same biome. \n "
				+ "(Note: This could have strange effects with large biomes on a small map");
		
		
		mode = config.getInt("MapType", "general", 1, 1, 4, "The kind of maps:\n"
				+ " \t 1: Continents\n"
				+ " \t 2: Islands (large)\n"
				+ " \t 3: Water-World (only ADDED islands)\n"
				+ " \t 4: Survival Island (Experimental; may put you in water)");
		
		sisize = config.getInt("Survival Island Size", "Size", 4, 4, 18, "How big survival "
						+ "islands are.") + 6.0;
		
		
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
