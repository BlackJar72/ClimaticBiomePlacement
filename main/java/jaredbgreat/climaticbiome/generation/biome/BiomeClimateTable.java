package jaredbgreat.climaticbiome.generation.biome;

import jaredbgreat.climaticbiome.generation.biome.biomes.GetAlpine;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetChaparral;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetColdPlains;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetCoolForest;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetCoolPark;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetCoolPlains;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetDesert;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetJungle;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetOcean;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetPark;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetPlains;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetRiver;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetSavanna;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetWarmForest;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetSwamp;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetTaiga;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetForest;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetHotForest;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetTundra;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetWarmPlains;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

public class BiomeClimateTable implements IBiomeSpecifier {	
	private static BiomeClimateTable main;
	private static LandBiomeTable land;
	private IBiomeSpecifier[] table;
	
	public static class LandBiomeTable implements IBiomeSpecifier {
		private IBiomeSpecifier[] table;
		LandBiomeTable() {}
		void init(IBiomeSpecifier[] t) {
			table = t;
		}
		@Override
		public int getBiome(ChunkTile tile) {
			return table[(tile.getTemp() * 10) + tile.getWet()].getBiome(tile);
		}
		@Override
		public boolean isEmpty() {
			return table == null;
		}
	}
	
    IBiomeSpecifier OCEAN;
    IBiomeSpecifier SWAMP;
    IBiomeSpecifier TUNDRA;
    IBiomeSpecifier CGRASS;
    IBiomeSpecifier GRASS;
    IBiomeSpecifier SGRASS;
    IBiomeSpecifier SAVANNA;
    IBiomeSpecifier TAIGA;
    IBiomeSpecifier PARK;
    IBiomeSpecifier FOREST;
    IBiomeSpecifier SFOREST;
    IBiomeSpecifier TFOREST;
    IBiomeSpecifier JUNGLE;
    IBiomeSpecifier DESERT;
    IBiomeSpecifier SCRUB;
    IBiomeSpecifier ALPINE;
    IBiomeSpecifier FORESTb;
    IBiomeSpecifier GRASSb;
    IBiomeSpecifier PARKb;
    IBiomeSpecifier RIVER;
	
	/**
	 * Create a table for looking up biomes based on temperature 
	 * and wetness.  The there need to be exactly 250 IBiomeSpecifiers
	 * provided (less than 250 will crash, more is harmless but 
	 * wasteful).  These should usually be BiomeLists containing more 
	 * specific specifiers, as this is the master table for finding 
	 * climate types.
	 * 
	 * The data being provided will be treated as a table 10x25 table as 
	 * follows: 10 across representing wetness and 25 down representing 
	 * temperature.  The temperatures are devided into five (vanilla) or 
	 * six (BoP, etc.) climate zones four or five rows each (all five 
	 * for vanilla biomes).  That is arctic, sub-arctic, temperate, 
	 * sub-tropical, and tropical -- with temperate dived into cool 
	 * temperate and warm temperate when advanced modded biomes are 
	 * available.  The interpretation of wetness is relative to what 
	 * is typical for a temperature region (e.g., most deserts are in 
	 * the sub-tropics). 
	 * 
	 * @param table *MUST* be 250 elements!
	 */
	private BiomeClimateTable(IBiomeSpecifier... table) {
		this.table = table;
	}	
	
	
	private BiomeClimateTable() {
		init();
	}
	
	
	public static BiomeClimateTable getClimateTable() {
		if(main == null) {
			main = new BiomeClimateTable();			
		}
		return main;
	}
	
	
	public static LandBiomeTable getLandTable() {
		if(land == null) {
			land = new LandBiomeTable();
		}
		return land;
	}
	

	@Override
	public int getBiome(ChunkTile tile) {
		if(tile.getRlBiome() == 0) {
			return OCEAN.getBiome(tile);
		}
		if(tile.isRiver()) {
			//System.out.println("Generating a RIVER!!!");
			return RIVER.getBiome(tile);
		}
        if(tile.getTemp() > 4 && ((tile.getWet() - tile.getVal()) > (tile.getNoise() - 1))) {
            if((tile.getBiomeSeed() & 0x1) == 1) {
                tile.nextBiomeSeed();
                return SWAMP.getBiome(tile);
            }
            tile.nextBiomeSeed();
        }
		return table[(tile.getTemp() * 10) + tile.getWet()].getBiome(tile);
	}


	@Override
	/**
	 * For this class "empty" means not initialized,
	 * or not initialized correctly.
	 */
	public boolean isEmpty() {
		return ((table == null) || (table.length < 250));
	}
	
	
	public void init() {
		land = getLandTable();
		OCEAN = GetOcean.getOcean();
	    SWAMP = GetSwamp.getSwamp();
	    TUNDRA = GetTundra.getTundra();
	    CGRASS = GetColdPlains.getPlains();
	    GRASS = GetPlains.getPlains();
	    SGRASS = GetWarmPlains.getPlains();
	    SAVANNA = GetSavanna.getSavanna();
	    TAIGA = GetTaiga.getTaiga();
	    PARK = GetPark.getPark();
	    FOREST = GetForest.getForest();
	    SFOREST = GetWarmForest.getForest();
	    TFOREST = GetHotForest.getForest();
	    JUNGLE = GetJungle.getJungle();
	    DESERT = GetDesert.getDesert();
	    SCRUB = GetChaparral.getChaparral();
	    ALPINE = GetAlpine.getAlpine();
	    FORESTb = GetCoolForest.getForest();
	    GRASSb = GetCoolPlains.getPlains();
	    PARKb = GetCoolPark.getPark();
	    RIVER = GetRiver.getRiver();
	    // TODO: Logic to determine which table to create,
	    makeVanillaTable();
	    // Initialize land table to use same table
	    land.init(table);
	}
	
	
	public void makeVanillaTable() {
		table = new IBiomeSpecifier[]{
		    	//Arctic
		    	TUNDRA, TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA, TUNDRA, TUNDRA, TUNDRA, TUNDRA,
		    	TUNDRA, TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA, TUNDRA, TUNDRA, TUNDRA, TUNDRA,
		    	TUNDRA, TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA, TUNDRA, TUNDRA, TUNDRA, TUNDRA,
		    	TUNDRA, TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA, TUNDRA, TUNDRA, TUNDRA, TUNDRA,
		    	TUNDRA, TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA, TUNDRA, TUNDRA, TUNDRA, TUNDRA,
		    	//Sub-Arctic
		    	TUNDRA, TUNDRA,    TAIGA,   TAIGA,   TAIGA,   TAIGA,  TAIGA,  TAIGA,  TAIGA,  TAIGA,
		    	TUNDRA, CGRASS,    TAIGA,   TAIGA,   TAIGA,   TAIGA,  TAIGA,  TAIGA,  TAIGA,  TAIGA,
		    	CGRASS,  CGRASS,   TAIGA,   TAIGA,   TAIGA,   TAIGA,  TAIGA,  TAIGA,  TAIGA,  TAIGA,
		    	CGRASS,  CGRASS,   TAIGA,   TAIGA,   TAIGA,   TAIGA,  TAIGA,  TAIGA,  TAIGA,  TAIGA,
		    	CGRASS,  CGRASS,   TAIGA,   TAIGA,   TAIGA,   TAIGA,  TAIGA,  TAIGA,  TAIGA,  TAIGA,
		    	//Temperate
		    	GRASS,  GRASS,   GRASS,   GRASS,   PARK,  FOREST, FOREST, FOREST, FOREST, FOREST,
		    	SCRUB,  GRASS,   GRASS,   GRASS,   PARK,  FOREST, FOREST, FOREST, FOREST, FOREST,
		    	SCRUB,  GRASS,   GRASS,   GRASS,   PARK,  FOREST, FOREST, FOREST, FOREST, FOREST,
		    	DESERT, SCRUB,   GRASS,   GRASS,   PARK,  FOREST, FOREST, FOREST, FOREST, FOREST,
		    	DESERT, DESERT,  SCRUB,   GRASS,   PARK,  FOREST, FOREST, FOREST, FOREST, FOREST,
		    	//Sub-Tropical
		    	DESERT, DESERT,  DESERT,  SCRUB,   SGRASS,   SFOREST, SFOREST, SFOREST, SFOREST, SFOREST,
		    	DESERT, DESERT,  DESERT,  SCRUB,   SGRASS,   SFOREST, SFOREST, SFOREST, SFOREST, JUNGLE,
		    	DESERT, DESERT,  DESERT,  SCRUB,   SGRASS,   SFOREST, SFOREST, SFOREST, SFOREST, JUNGLE,
		    	DESERT, DESERT,  DESERT,  SCRUB,   SGRASS,   SFOREST, SFOREST, SFOREST, JUNGLE,  JUNGLE,
		    	DESERT, DESERT,  DESERT,  SCRUB,   SGRASS,   SFOREST, SFOREST, JUNGLE,  JUNGLE,  JUNGLE,
		    	//Tropical
		    	DESERT, DESERT,  SAVANNA, SAVANNA, TFOREST, TFOREST, JUNGLE, JUNGLE, JUNGLE, JUNGLE,
		    	DESERT, SAVANNA, SAVANNA, SAVANNA, TFOREST, JUNGLE,  JUNGLE, JUNGLE, JUNGLE, JUNGLE,
		    	DESERT, SAVANNA, SAVANNA, SAVANNA, TFOREST, JUNGLE,  JUNGLE, JUNGLE, JUNGLE, JUNGLE,
		    	DESERT, SAVANNA, SAVANNA, TFOREST, TFOREST, JUNGLE,  JUNGLE, JUNGLE, JUNGLE, JUNGLE,
		    	DESERT, SAVANNA, TFOREST, TFOREST, JUNGLE,  JUNGLE,  JUNGLE, JUNGLE, JUNGLE, JUNGLE
		    };
	}
	
	
	public void makeModdedTable() {
		table = new IBiomeSpecifier[]{
		    	//Arctic
		    	TUNDRA, TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA, TUNDRA, TUNDRA, TUNDRA, TUNDRA,
		    	TUNDRA, TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA, TUNDRA, TUNDRA, TUNDRA, TUNDRA,
		    	TUNDRA, TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA, TUNDRA, TUNDRA, TUNDRA, TUNDRA,
		    	TUNDRA, TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA, TUNDRA, TUNDRA, TUNDRA, TUNDRA,
		    	//Sub-Arctic
		    	TUNDRA,   TUNDRA,   TAIGA, TAIGA,   TAIGA,   TAIGA,  TAIGA,  TAIGA,  TAIGA,  TAIGA,
		    	TUNDRA,   CGRASS,   TAIGA, TAIGA,   TAIGA,   TAIGA,  TAIGA,  TAIGA,  TAIGA,  TAIGA,
		    	CGRASS,   CGRASS,   TAIGA, TAIGA,   TAIGA,   TAIGA,  TAIGA,  TAIGA,  TAIGA,  TAIGA,
		    	CGRASS,   CGRASS,   TAIGA, TAIGA,   TAIGA,   TAIGA,  TAIGA,  TAIGA,  TAIGA,  TAIGA,
		    	//Cool Temperate
		    	GRASSb,  GRASSb,  GRASSb,  GRASSb, PARKb, FORESTb, FORESTb, FORESTb, FORESTb, FORESTb,
		    	GRASSb,  GRASSb,  GRASSb,  GRASSb, PARKb, FORESTb, FORESTb, FORESTb, FORESTb, FORESTb,
		    	GRASS,   GRASSb,  GRASSb,  GRASSb, PARKb, FORESTb, FORESTb, FORESTb, FORESTb, FORESTb,
		    	GRASS,   GRASS,   GRASSb,  GRASSb, PARKb, FORESTb, FORESTb, FORESTb, FORESTb, FORESTb,
		    	//Warm Temperate
		    	GRASS,  GRASS,   GRASS,   GRASS,   PARK,  FOREST, FOREST, FOREST, FOREST, FOREST,
		    	SCRUB,  GRASS,   GRASS,   GRASS,   PARK,  FOREST, FOREST, FOREST, FOREST, FOREST,
		    	DESERT, SCRUB,   GRASS,   GRASS,   PARK,  FOREST, FOREST, FOREST, FOREST, FOREST,
		    	DESERT, DESERT,  SCRUB,   GRASS,   PARK,  FOREST, FOREST, FOREST, FOREST, FOREST,
		    	//Sub-Tropical
		    	DESERT, DESERT,  DESERT,  SCRUB,   SGRASS,   SFOREST, SFOREST, SFOREST, SFOREST, SFOREST,
		    	DESERT, DESERT,  DESERT,  SCRUB,   SGRASS,   SFOREST, SFOREST, SFOREST, SFOREST, JUNGLE,
		    	DESERT, DESERT,  DESERT,  SCRUB,   SGRASS,   SFOREST, SFOREST, SFOREST, SFOREST, JUNGLE,
		    	DESERT, DESERT,  DESERT,  SCRUB,   SGRASS,   SFOREST, SFOREST, SFOREST, JUNGLE,  JUNGLE,
		    	DESERT, DESERT,  DESERT,  SCRUB,   SGRASS,   SFOREST, SFOREST, JUNGLE,  JUNGLE,  JUNGLE,
		    	//Tropical
		    	DESERT, DESERT,  SAVANNA, SAVANNA, TFOREST, TFOREST, JUNGLE, JUNGLE, JUNGLE, JUNGLE,
		    	DESERT, SAVANNA, SAVANNA, SAVANNA, TFOREST, JUNGLE,  JUNGLE, JUNGLE, JUNGLE, JUNGLE,
		    	DESERT, SAVANNA, SAVANNA, TFOREST, JUNGLE,  JUNGLE,  JUNGLE, JUNGLE, JUNGLE, JUNGLE,
		    	DESERT, SAVANNA, TFOREST, JUNGLE,  JUNGLE,  JUNGLE,  JUNGLE, JUNGLE, JUNGLE, JUNGLE
		    };
	}

}
