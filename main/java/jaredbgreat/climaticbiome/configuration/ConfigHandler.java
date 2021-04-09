package jaredbgreat.climaticbiome.configuration;

import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.generation.ClimaticWorldType;
import jaredbgreat.climaticbiome.generation.mapgenerator.SizeScale;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {
	
	public static boolean useBoP = false;
	public static boolean useTraverse = false;
	public static boolean useNT = false;
	public static boolean useAby = false;
	public static boolean useAux = false;
	public static boolean useEnvirons = false;
	public static boolean usePVJ = false;
	public static boolean useZoe = false;
	public static boolean useBYG = false;
	public static boolean useDefiled = false;
	public static boolean useRWmod = false;
	public static boolean useSpecial = false;
	public static boolean useVanilla = true;
	public static boolean useCfg = true;
	
	public static boolean writeBiomeLists = true;
	public static boolean writeWTLists = false;	
	public static boolean hasDT = false;
	public static boolean useDT = false;		
	public static boolean useBoPTable = true;
	
	public static boolean moreMansion = true;
	public static boolean pineBlocks = true;
	public static boolean peatBlocks = true;
	public static boolean volcanicBlocks = true;
	public static boolean addPines = true;
	public static boolean badBiomeSpam = false;
	public static boolean makeDefault = false;
	public static boolean makeRealisticDefault = false;
	public static boolean addToVanilla = false;
	
	public static boolean includeForests = true;
	public static boolean includeMountains = true;
	public static boolean includePlains = true;
	public static boolean includeSwamps = true;
	public static boolean includeRivers = true;
	public static boolean includeVolcano = true;
	
	public static boolean biomeWater = false;
	
	public static final String BASALT_BLOCK = Info.ID + ":basalt";
	public static final String ASH_BLOCK = Info.ID + ":volcanic_ash";
	
	public static String basaltBlock = BASALT_BLOCK;
	public static String ashBlock = ASH_BLOCK;

	static boolean addIslands = true;
	static boolean extraBeaches = true;	
	static boolean volcanicIslands = false;
	static boolean deepSand = true;
	static boolean rockyScrub = true;
	static boolean hasRivers = true;
	
	static boolean bigMountains = true;
	
	//public static boolean allPolesCold = true;
	
	static int        biomeSize  = 16;
	static SizeScale  regionSize = SizeScale.X1;
	static boolean    forceWhole = false;
	static double     sisize     = 6.0; 
	static int        mode       = 1;
	
	public static boolean failfast = false;
		
	public static  int     modes     = 3;
	public static  boolean includeSI = false;
	
	public static int peatSmelts = 16;

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
		
		useNT = config.getBoolean("UseNovamTerram", "Compat", true, 
						"If true it will use Novam Terram biomes in its world type. \n"
						+ "If Novam Terram is not installed this does nothing.")
				 && net.minecraftforge.fml.common.Loader.isModLoaded("nt");
		
		useAby = config.getBoolean("UseAbyssalCraft", "Compat", true, 
						"If true it will use Ayssal Craft overworld biomes in its world type. \n"
						+ "If Abyssal Craft is not installed this does nothing.")
				 && net.minecraftforge.fml.common.Loader.isModLoaded("abyssalcraft");
		
		useAux = config.getBoolean("UseAuxiliaryBiomes", "Compat", true, 
						"If true it will use Auxiliary Biomes in its world type. \n"
						+ "If Auxilary Biomes is not installed this does nothing.")
				 && net.minecraftforge.fml.common.Loader.isModLoaded("auxbiomes");
		
		useEnvirons = config.getBoolean("UseEnvirons++", "Compat", true, 
						"If true it will use Evirons++ biomes in its world type. \n"
						+ "If Environs++ is not installed this does nothing.")
				 && net.minecraftforge.fml.common.Loader.isModLoaded("environs");
		
		usePVJ = config.getBoolean("UseVibrantJourney", "Compat", true, 
						"If true it will use Project Vibrant Journey biomes in its world type. \n"
						+ "If Project Vibrant Journey is not installed this does nothing.")
				 && net.minecraftforge.fml.common.Loader.isModLoaded("pvj");
		
		useZoe = config.getBoolean("UseZoestria", "Compat", true, 
						"If true it will use Zoestria biomes in its world type. \n"
						+ "If Zoestria is not installed this does nothing.")
				 && net.minecraftforge.fml.common.Loader.isModLoaded("valoegheses_be");
		
		useBYG = config.getBoolean("UseBiomeYoullGo", "Compat", true, 
						"If true it will use Biome's You'll Go in its world type. \n"
						+ "If Biomes You'll Go is not installed this does nothing.")
				 && net.minecraftforge.fml.common.Loader.isModLoaded("byg");
		
		useDefiled = config.getBoolean("UseDefiledLands", "Compat", true, 
						"If true it will use Defiled Land's biomes in its world type. \n"
						+ "If Zoestria is not installed this does nothing.")
				 && net.minecraftforge.fml.common.Loader.isModLoaded("defiledlands");
		
		useRWmod = config.getBoolean("UseRedwoodsMod", "Compat", true, 
						"If true it will use biomes from the Redwoods mod in its world type. \n"
						+ "If Redwoods is not installed this does nothing.")
				 && net.minecraftforge.fml.common.Loader.isModLoaded("redwoods");
		
		useSpecial = config.getBoolean("UseSpecialBiomes", "Compat", true, 
						"If true it will use special biomes from some non-biome mods \n"
						+ "in its world type. \n"
						+ "SET THIS TO FALSE IF USING FAIL-FAST, otherwise leave it as true.");
		
		useVanilla = config.getBoolean("UseMinecraftBiomes", "Compat", true, 
						"If true it will use vanilla Minecraft biomes in its world type, \n"
						+ "along with its own biomes.  Note: It may do this anyway if no "
						+ "other biomes are provided for a climate area.");	
		
		useBoPTable = config.getBoolean("ExpandedClimateTable", "Compat", true, 
						"If true the climate table for with 6 tmeperature zone instead \n"
						+ "of the original with only 5.");	
		
		volcanicIslands = config.getBoolean("VolcanicIslands", "General", true, 
						"If true create volcanic islands.");
				
		writeBiomeLists = config.getBoolean("WriteBiomelist", "Compat", true, 
						"If true a list of all biome resource locations will be saved to a file.");
		
		writeWTLists = config.getBoolean("WriteWorldTypeList", "Compat", false, 
						"If true a list creatable world types by resource location; /n"
						+ "WARNING: This has been known to crash the game with certain setups \n"
						+ "(core mods?  Forge version?).");

		
		useCfg = config.getBoolean("UseCustomConfigs", "Compat", true, 
						"If true it read will files from the BiomeConfig/custom folder to extends \n"
						+ "its worldgen. This is where to add extra biomes not otherwise supported.");
		
		extraBeaches = config.getBoolean("ExtraBeaches", "General", false, 
						"If true there will be more beaches.");
		
		rockyScrub = config.getBoolean("RockyScrub", "General", true, 
						"If true scrub biomes will have cobble bolders.");
		
		deepSand = config.getBoolean("DeepSandInScrub", "General", true, 
						"If true sand in dry scrub will be 3-4 blocks deep, otherwise it will be 1.");
		
		biomeWater = config.getBoolean("BiomeWaterColors", "General", false, 
						"If true some biomes (mostly wetlands) will have special colors for their water.");
		
		bigMountains = config.getBoolean("BiggerMountains", "ClimaticRealistic", true, 
						"If true mountains be organized into ranges. They should be bigger in Climatic \n"
						+ "Realistic world type, especially toward the middle of ranges (biomes).  \n"
						+ "This often doesn't work as well with Climatic Vanilla types but can still be used.");
		
		addIslands = config.getBoolean("AddIslands", "General", true, 
						"If true extra islands will be generated in the ocean \n "
						+ "for reason I don't understand these islands tend to be \n "
						+ "chunky and squarish, but are interesting to find.");
		
		moreMansion = config.getBoolean("MoreMansion", "General", true, 
						"If true woodConfigHandler.rivers && land mansion might appear in all forest types; \n"
						+ " this fits the mods theme and eliminates a major lag spike but \n"
						+ " will effect all world types (even vanilla); depending on Forge / \n"
						+ " Java / OS / unknown factors it might not work.");
				
		addPines = config.getBoolean("AddPines", "ModdedBlocks", true, 
						"If true modded pine trees will appear in warmer climate. \n"
						+ "If false spruce will be added instead.  \n"
						+ "This does not effect if pine blocks technically exist. \n");
		
		pineBlocks = config.getBoolean("PineBlocks", "ModdedBlocks", true, 
						"If pine blocks exist and pines will be made from them. \n"
						+ "If false these blocks don't exist and pines are made from spruce blocks. \n"
						+ "Disabling pine while using dynamic trees will crash the game!!");
		
		peatBlocks = config.getBoolean("PeatBlocks", "ModdedBlocks", true, 
						"If peat blocks exist; if so they will be the filler block in bogs!!");
		
		volcanicBlocks = config.getBoolean("VolcanicBlocks", "ModdedBlocks", true, 
						"If basalt and volcanic ash exist; if false a vanilla substitute will be used!!");
		
		basaltBlock = config.getString("BasaltBlocks", "ModdedBlocks", BASALT_BLOCK, 
						"The resource location of the block to use for basalt");
		
		ashBlock = config.getString("AshBlocks", "ModdedBlocks", ASH_BLOCK, 
						"The resource location of the block to use for volcanic ash");
		
		peatSmelts = config.getInt("PeatSmelts", "ModdedBlocks", 16, 0, 256, 
						"This is the number of smelts that can be done with a peat block; \n"
						+ "Set this to 0 to prevent peat from being used as a fuel.");
		
		badBiomeSpam = config.getBoolean("ErrorsForBadBiomeID", "Debugging", false, 
						"If true an error message will be printed to the console whenever a \n"
						+ "biome can't be fount for an ID (this will spam the console and may \n"
						+ "freeze the game).  Othewise they will silently become oceans.");
		
		makeDefault = config.getBoolean("MakeDefaut", "General", false, 
						"If true the Climatic Vanilla world type will be moved to the top of the list \n"
						+ "of world types and will be used by default on new worlds. \n"
						+ "(Note that it will be impossible to create a default vanilla world.)");
		
		makeRealisticDefault = config.getBoolean("MakeDefautRealistic", "General", false, 
						"If this an MakeDefault are both true the climatic realistic \n"
						+ "world type will become the default. \n"
						+ "(Note that it will be impossible to create a default vanilla world.)");
		
		addToVanilla = config.getBoolean("AddToVanilla", "General", false, 
						"If true the biomes from this mod will appear in vanilla world types.");
		
		hasRivers = config.getBoolean("MakeRivers", "General", true, 
						"If true rivers will be generated.");
		
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
		

		includeSI = config.getBoolean("Survival Island in GUI", "general", false, 
						"If true survival island world type will be available in the GUI; "
						+ " If survival island (4) is set as map type here it will also appear")
						|| (mode == 4);
				
		if(includeSI) {
			modes = 4;
		} else {
			modes = 3;
		}
		
		sisize = config.getInt("Survival Island Size", "Size", 4, 4, 18, "How big survival "
						+ "islands are.") + 6.0;
		
		failfast = config.getBoolean("FailFast", "debugging", false, 
						"If the game should crash with an exception when failign to read a biome. \n"
						+ "This is for modpack authors to catch config bugs, not for general use.");
		
		includeRivers  = config.getBoolean("RiverVariants", "Biomes", true, 
						"If true there will be temperature specific rivers.");
		includeForests = config.getBoolean("ForestVariants", "Biomes", true, 
						"If true there will be temperature specific forests.");
		includeMountains = config.getBoolean("MountainVariants", "Biomes", true, 
						"If true there will be climate specific mountains /n"
						+ "and montane forests.");
		includePlains   = config.getBoolean("PlainsVariants", "Biomes", true, 
						"If true there will be temperature specific plains.");
		includeSwamps   = config.getBoolean("SwampVariants", "Biomes", true, 
						"If true there will be temperature specific wetlands.");
		includeVolcano  = config.getBoolean("Volcanoes", "Biomes", true, 
						"If this and MountainVariants are both true there will \n"
						+ "be volcanoes.");
		
		
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
