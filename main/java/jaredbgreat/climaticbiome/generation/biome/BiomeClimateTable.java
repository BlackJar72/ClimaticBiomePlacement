package jaredbgreat.climaticbiome.generation.biome;

import jaredbgreat.climaticbiome.configuration.ClimaticWorldSettings;
import jaredbgreat.climaticbiome.configuration.ConfigHandler;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetAlpine;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetBeach;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetChaparral;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetColdPlains;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetCoolForest;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetCoolPark;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetCoolPlains;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetDesert;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetForest;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetHotForest;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetJungle;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetOcean;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetPark;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetPlains;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetRiver;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetSavanna;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetSwamp;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetTaiga;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetTundra;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetWarmForest;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetWarmPlains;
import jaredbgreat.climaticbiome.generation.mapgenerator.ChunkTile;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

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
		public long getBiome(ChunkTile tile) {
			return table[(tile.getTemp() * 10) + tile.getWet()].getBiome(tile);
		}
		@Override
		public boolean isEmpty() {
			return table == null;
		}
		public IBiomeSpecifier getBiomeType(ChunkTile tile) {
			return table[(tile.getTemp() * 10) + tile.getWet()];
		}
		public boolean isDesertTile(ChunkTile tile) {
			return table[(tile.getTemp() * 10) + tile.getWet()] == DESERT;
		}
	}
	
	static IBiomeSpecifier OCEAN;
    static IBiomeSpecifier SWAMP;
    static IBiomeSpecifier TUNDRA;
    static IBiomeSpecifier CGRASS;
    static IBiomeSpecifier GRASS;
    static IBiomeSpecifier SGRASS;
    static IBiomeSpecifier SAVANNA;
    static IBiomeSpecifier TAIGA;
    static IBiomeSpecifier PARK;
    static IBiomeSpecifier FOREST;
    static IBiomeSpecifier SFOREST;
    static IBiomeSpecifier TFOREST;
    static IBiomeSpecifier JUNGLE;
    static IBiomeSpecifier DESERT;
    static IBiomeSpecifier SCRUB;
    static IBiomeSpecifier ALPINE;
    static IBiomeSpecifier FORESTb;
    static IBiomeSpecifier GRASSb;
    static IBiomeSpecifier PARKb;
    static IBiomeSpecifier RIVER;
    GetBeach        BEACH;
	
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
	 * int
	 * @param table *MUST* be 250 elements!
	 */
	private BiomeClimateTable(IBiomeSpecifier... table) {
		this.table = table;
	}	
	
	
	private BiomeClimateTable(ClimaticWorldSettings settings) {
		init(settings);
	}
	
	
	public static BiomeClimateTable getClimateTable(ClimaticWorldSettings settings) {
		return main = new BiomeClimateTable(settings);
	}
	
	
	public static LandBiomeTable getLandTable() {
		if(land == null) {
			land = new LandBiomeTable();
		}
		return land;
	}
	

	@Override
	public long getBiome(ChunkTile tile) {
		if(tile.isRiver()) {
			return RIVER.getBiome(tile);
		}
		if(tile.getRlBiome() == 0) {
			return OCEAN.getBiome(tile);
		}
        if(tile.getTemp() > 7 && ((tile.getWet() - tile.getVal() - tile.getHeight()) > 0)) {
            if((tile.getBiomeSeed() & 0x1) == 1) {
                tile.nextBiomeSeed();
                return SWAMP.getBiome(tile);
            }
            tile.nextBiomeSeed();
        }
        if(tile.nextBiomeSeed().isMountain()) {
        	return ALPINE.getBiome(tile);
        }
		long out = table[(tile.getTemp() * 10) + tile.getWet()].getBiome(tile);
    	Biome outb = Biome.getBiome((int)out);
    	if(outb == null) {
    		Logger.getLogger("minecraft").log(Level.SEVERE, 
    				"[CLIMATIC_BIOMES] Error! Could not find biome with id " 
    						+ out + "; returning 0 (Ocean).  (Check your configs.)");
    		return 0;
    	}
        if(tile.isBeach()) {
        	if(BiomeDictionary.hasType(outb, Type.HILLS)
        	    || BiomeDictionary.hasType(outb, Type.MOUNTAIN)
        	    || tile.isMountain()) {
        	    //return BEACH.getHighBiome(tile);
        		return out;
        	}
        	return BEACH.getBiome(tile);
        }
		return out;
	}


	@Override
	/**
	 * For this class "empty" means not initialized,
	 * or not initialized correctly.
	 */
	public boolean isEmpty() {
		return ((table == null) || (table.length < 250));
	}
	
	
	public void init(ClimaticWorldSettings settings) {
		land = getLandTable();
		OCEAN = GetOcean.getOcean(settings);
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
	    BEACH = GetBeach.getBeach();
	    // TODO: Logic to determine which table to create,
	    if(ConfigHandler.useBoP || ConfigHandler.useBoPTable) {
	    	makeModdedTable();
	    } else {
	    	makeVanillaTable();
	    }
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
	
	
	// Interesting idea, but thing change too quickly to the point of breaking badly
	public void makeEquatorTable() {
		table = new IBiomeSpecifier[]{
		    	//Arctic
		    	TUNDRA, TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA, TUNDRA, TUNDRA, TUNDRA, TUNDRA,
		    	TUNDRA, TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA, TUNDRA, TUNDRA, TUNDRA, TUNDRA,
		    	//Sub-Arctic
		    	TUNDRA,   TUNDRA,   TAIGA, TAIGA,   TAIGA,   TAIGA,  TAIGA,  TAIGA,  TAIGA,  TAIGA,
		    	CGRASS,   CGRASS,   TAIGA, TAIGA,   TAIGA,   TAIGA,  TAIGA,  TAIGA,  TAIGA,  TAIGA,
		    	//Cool Temperate
		    	GRASSb,  GRASSb,  GRASSb,  GRASSb, PARKb, FORESTb, FORESTb, FORESTb, FORESTb, FORESTb,
		    	GRASSb,  GRASSb,  GRASSb,  GRASSb, PARKb, FORESTb, FORESTb, FORESTb, FORESTb, FORESTb,
		    	//Warm Temperate
		    	GRASS,  GRASS,   GRASS,   GRASS,   PARK,  FOREST, FOREST, FOREST, FOREST, FOREST,
		    	SCRUB,  SCRUB,   GRASS,   GRASS,   PARK,  FOREST, FOREST, FOREST, FOREST, FOREST,
		    	DESERT, DESERT,  SCRUB,   GRASS,   PARK,  FOREST, FOREST, FOREST, FOREST, FOREST,
		    	//Sub-Tropical
		    	DESERT, DESERT,  DESERT,  SCRUB,   SGRASS,   SFOREST, SFOREST, SFOREST, SFOREST, SFOREST,
		    	DESERT, DESERT,  DESERT,  SCRUB,   SGRASS,   SFOREST, SFOREST, SFOREST, SFOREST, JUNGLE,
		    	//Tropical
		    	DESERT, SAVANNA, SAVANNA, SAVANNA, TFOREST, JUNGLE,  JUNGLE, JUNGLE, JUNGLE, JUNGLE,
		    	DESERT, SAVANNA, SAVANNA, TFOREST, JUNGLE,  JUNGLE,  JUNGLE, JUNGLE, JUNGLE, JUNGLE,
		    	DESERT, SAVANNA, SAVANNA, SAVANNA, TFOREST, JUNGLE,  JUNGLE, JUNGLE, JUNGLE, JUNGLE,		    	
		    	//Now Reverse
		    	//Sub-Tropical
		    	DESERT, DESERT,  DESERT,  SCRUB,   SGRASS,   SFOREST, SFOREST, SFOREST, SFOREST, JUNGLE,
		    	DESERT, DESERT,  DESERT,  SCRUB,   SGRASS,   SFOREST, SFOREST, SFOREST, SFOREST, SFOREST,
		    	//Warm Temperate
		    	DESERT, DESERT,  SCRUB,   GRASS,   PARK,  FOREST, FOREST, FOREST, FOREST, FOREST,
		    	SCRUB,  SCRUB,   GRASS,   GRASS,   PARK,  FOREST, FOREST, FOREST, FOREST, FOREST,
		    	GRASS,  GRASS,   GRASS,   GRASS,   PARK,  FOREST, FOREST, FOREST, FOREST, FOREST,
		    	//Cool Temperate
		    	GRASSb,  GRASSb,  GRASSb,  GRASSb, PARKb, FORESTb, FORESTb, FORESTb, FORESTb, FORESTb,
		    	GRASSb,  GRASSb,  GRASSb,  GRASSb, PARKb, FORESTb, FORESTb, FORESTb, FORESTb, FORESTb,
		    	//Sub-Arctic
		    	CGRASS,   CGRASS,   TAIGA, TAIGA,   TAIGA,   TAIGA,  TAIGA,  TAIGA,  TAIGA,  TAIGA,
		    	TUNDRA,   TUNDRA,   TAIGA, TAIGA,   TAIGA,   TAIGA,  TAIGA,  TAIGA,  TAIGA,  TAIGA,
		    	//Arctic
		    	TUNDRA, TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA, TUNDRA, TUNDRA, TUNDRA, TUNDRA,
		    	TUNDRA, TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA, TUNDRA, TUNDRA, TUNDRA, TUNDRA
		    };
	}

}
