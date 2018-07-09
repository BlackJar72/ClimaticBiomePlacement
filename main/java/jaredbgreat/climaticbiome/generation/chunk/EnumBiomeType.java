/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaredbgreat.climaticbiome.generation.chunk;

import jaredbgreat.climaticbiome.ClimaticBiomes;
import jaredbgreat.climaticbiome.generation.chunk.biomes.GetAlpine;
import jaredbgreat.climaticbiome.generation.chunk.biomes.GetChaparral;
import jaredbgreat.climaticbiome.generation.chunk.biomes.GetDesert;
import jaredbgreat.climaticbiome.generation.chunk.biomes.GetFrozenOcean;
import jaredbgreat.climaticbiome.generation.chunk.biomes.GetJungle;
import jaredbgreat.climaticbiome.generation.chunk.biomes.GetOcean;
import jaredbgreat.climaticbiome.generation.chunk.biomes.GetPark;
import jaredbgreat.climaticbiome.generation.chunk.biomes.GetPlains;
import jaredbgreat.climaticbiome.generation.chunk.biomes.GetSavanna;
import jaredbgreat.climaticbiome.generation.chunk.biomes.GetSubtropicalForest;
import jaredbgreat.climaticbiome.generation.chunk.biomes.GetSwamp;
import jaredbgreat.climaticbiome.generation.chunk.biomes.GetTaiga;
import jaredbgreat.climaticbiome.generation.chunk.biomes.GetTemporateForest;
import jaredbgreat.climaticbiome.generation.chunk.biomes.GetTropicalForest;
import jaredbgreat.climaticbiome.generation.chunk.biomes.GetTundra;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPAlpine;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPChaparral;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPColdPlains;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPCoolPlains;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPDesert;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPJungle;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPOcean;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPPark;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPPlains;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPSubtropicalForest;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPSwamp;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPTaiga;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPTemporateForest;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPTundra;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPVanillaTaiga;
import jaredbgreat.climaticbiome.generation.chunk.bopbiomes.GetBoPWetForest;

/**
 *
 * @author jared
 */
public enum EnumBiomeType {
    
    OCEAN (new GetOcean()),
    FROCEAN (new GetFrozenOcean()),
    SWAMP (new GetSwamp()),
    TUNDRA (new GetTundra()),
    CGRASS (new GetPlains()),
    GRASS (new GetPlains()),
    SGRASS (new GetPlains()),
    SAVANNA (new GetSavanna()),
    TAIGA (new GetTaiga()),
    PARK (new GetPark()), // TODO: Change generator
    FOREST (new GetTemporateForest()),
    SFOREST (new GetSubtropicalForest()),
    TFOREST (new GetTropicalForest()),
    JUNGLE (new GetJungle()),
    DESERT (new GetDesert()),
    SCRUB (new GetChaparral()),
    ALPINE (new GetAlpine()),
    
    // BoP Biomes Types 
    BOCEAN (new GetBoPOcean()),
    BSWAMP (new GetBoPSwamp()),
    BTUNDRA (new GetBoPTundra()),
    BCGRASS (new GetBoPColdPlains()),
    BCGRASS2 (new GetBoPCoolPlains()),
    BGRASS (new GetBoPPlains()),
    BSGRASS (new GetBoPPlains()),
    BVTAIGA (new GetBoPVanillaTaiga()),
    BTAIGA (new GetBoPTaiga()),
    BPARK (new GetBoPPark()),
    BFOREST (new GetBoPTemporateForest()),
    BCFOREST (new GetBoPTemporateForest()), // TODO: Replace with in specifier 
    BRFOREST (new GetBoPWetForest()), 
    BSFOREST (new GetBoPSubtropicalForest()),
    BJUNGLE (new GetBoPJungle()),
    BDESERT (new GetBoPDesert()),
    BSCRUB (new GetBoPChaparral()),
    BALPINE (new GetBoPAlpine());   
    
        public final IBiomeSpecifier specifier;
    
    EnumBiomeType(IBiomeSpecifier spec) {
        specifier = spec;
    }
    
    private static final EnumBiomeType[] table = {
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
    
    
    private static final EnumBiomeType[] bopvanillatable = {
    	//Arctic
    	TUNDRA, TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA, TUNDRA, TUNDRA, TUNDRA, TUNDRA,
    	TUNDRA, TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA, TUNDRA, TUNDRA, TUNDRA, TUNDRA,
    	TUNDRA, TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA, TUNDRA, TUNDRA, TUNDRA, TUNDRA,
    	TUNDRA, TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA, TUNDRA, TUNDRA, TUNDRA, TUNDRA,
    	//Sub-Arctic
    	TUNDRA,   TUNDRA,    BVTAIGA, BVTAIGA,   BVTAIGA,   BVTAIGA,  BVTAIGA,  BVTAIGA,  BVTAIGA,  BVTAIGA,
    	TUNDRA,   BCGRASS,   BVTAIGA, BVTAIGA,   BVTAIGA,   BVTAIGA,  BVTAIGA,  BVTAIGA,  BVTAIGA,  BVTAIGA,
    	BCGRASS,  BCGRASS,   BVTAIGA, BVTAIGA,   BVTAIGA,   BVTAIGA,  BVTAIGA,  BVTAIGA,  BVTAIGA,  BVTAIGA,
    	BCGRASS,  BCGRASS,   BVTAIGA, BVTAIGA,   BVTAIGA,   BVTAIGA,  BVTAIGA,  BVTAIGA,  BVTAIGA,  BVTAIGA,
    	//Cool Temperate
    	BCGRASS2,  BCGRASS2,  BCGRASS2,  BCGRASS2, BCFOREST, BCFOREST, BCFOREST, BCFOREST, BCFOREST, BCFOREST,
    	BCGRASS2,  BCGRASS2,  BCGRASS2,  BCGRASS2, BCFOREST, BCFOREST, BCFOREST, BCFOREST, BCFOREST, BCFOREST,
    	BCGRASS2,  BCGRASS2,  BCGRASS2,  BCGRASS2, BCFOREST, BCFOREST, BCFOREST, BCFOREST, BCFOREST, BCFOREST,
    	GRASS,     GRASS,     GRASS,     GRASS,    BCFOREST, BCFOREST, BCFOREST, BCFOREST, BCFOREST, BCFOREST,
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
    
    
    public static final EnumBiomeType[] boptable = {
    	//Arctic
    	BTUNDRA, BTUNDRA,  BTUNDRA,  BTUNDRA,  TUNDRA,   BTUNDRA, BTUNDRA, BTUNDRA, BTUNDRA, BTUNDRA,
    	BTUNDRA, BTUNDRA,  BTUNDRA,  BTUNDRA,  BTUNDRA,  BTUNDRA, BTUNDRA, BTUNDRA, BTUNDRA, BTUNDRA,
    	BTUNDRA, BTUNDRA,  BTUNDRA,  BTUNDRA,  BTUNDRA,  BTUNDRA, BTUNDRA, BTUNDRA, BTUNDRA, BTUNDRA,
    	BTUNDRA, BTUNDRA,  BTUNDRA,  BTUNDRA,  BTUNDRA,  BTUNDRA, BTUNDRA, BTUNDRA, BTUNDRA, BTUNDRA,
    	//Sub-Arctic
    	BTUNDRA, BTUNDRA, BTAIGA, BTAIGA, BTAIGA, BTAIGA, BTAIGA, BTAIGA, BTAIGA, BTAIGA,
    	BTUNDRA, BCGRASS, BTAIGA, BTAIGA, BTAIGA, BTAIGA, BTAIGA, BTAIGA, BTAIGA, BTAIGA,
    	BCGRASS, BCGRASS, BTAIGA, BTAIGA, BTAIGA, BTAIGA, BTAIGA, BTAIGA, BTAIGA, BTAIGA,
    	BCGRASS, BCGRASS, BTAIGA, BTAIGA, BTAIGA, BTAIGA, BTAIGA, BTAIGA, BTAIGA, BTAIGA,    	
    	//Cool Temperate
    	BCGRASS,  BCGRASS,  BCGRASS,  BCGRASS, BCFOREST, BCFOREST, BCFOREST, BCFOREST, BCFOREST, BCFOREST,
    	BCGRASS,  BCGRASS,  BCGRASS,  BCGRASS, BCFOREST, BCFOREST, BCFOREST, BCFOREST, BCFOREST, BCFOREST,
    	BCGRASS,  BCGRASS,  BCGRASS,  BCGRASS, BCFOREST, BCFOREST, BCFOREST, BCFOREST, BCFOREST, BCFOREST,
    	BGRASS,   BGRASS,   BGRASS,   BGRASS,  BCFOREST, BCFOREST, BCFOREST, BCFOREST, BCFOREST, BCFOREST,   	
    	//Warm Temperate
    	BSCRUB,  BGRASS,  BGRASS,  BGRASS,  BPARK, BFOREST, BFOREST, BRFOREST, BRFOREST, BRFOREST,
    	BSCRUB,  BGRASS,  BGRASS,  BGRASS,  BPARK, BFOREST, BFOREST, BRFOREST, BRFOREST, BRFOREST,
    	BDESERT, BSCRUB,  BGRASS,  BGRASS,  BPARK, BFOREST, BFOREST, BRFOREST, BRFOREST, BRFOREST,
    	BDESERT, BDESERT, BSCRUB,  BGRASS,  BPARK, BFOREST, BFOREST, BRFOREST, BRFOREST, BRFOREST,
    	//Sub-Tropical
    	BDESERT, BDESERT, BDESERT, BSCRUB, BSGRASS,  BSFOREST, BSFOREST, BSFOREST, BSFOREST, BSFOREST,
    	BDESERT, BDESERT, BDESERT, BSCRUB, BSGRASS,  BSFOREST, BSFOREST, BSFOREST, BSFOREST, BSFOREST,
    	BDESERT, BDESERT, BDESERT, BSCRUB, BSGRASS,  BSFOREST, BSFOREST, BSFOREST, BSFOREST, BJUNGLE,
    	BDESERT, BDESERT, BDESERT, BSCRUB, BSGRASS,  BSFOREST, BSFOREST, BSFOREST, BSFOREST, BJUNGLE,
    	BDESERT, BDESERT, BDESERT, BSCRUB, BSGRASS,  BSFOREST, BSFOREST, BJUNGLE,  BJUNGLE,  BJUNGLE,
    	//Tropical
    	BDESERT, BDESERT, SAVANNA, SAVANNA, SAVANNA, TFOREST,   TFOREST, BJUNGLE, BJUNGLE, BJUNGLE,
    	BDESERT, SAVANNA, SAVANNA, SAVANNA, TFOREST, BJUNGLE,   BJUNGLE, BJUNGLE, BJUNGLE, BJUNGLE,
    	BDESERT, SAVANNA, SAVANNA, TFOREST, BJUNGLE, BJUNGLE,   BJUNGLE, BJUNGLE, BJUNGLE, BJUNGLE,
    	BDESERT, SAVANNA, TFOREST, TFOREST, BJUNGLE, BJUNGLE,   BJUNGLE, BJUNGLE, BJUNGLE, BJUNGLE,
    };
    
    public static void makeBiomes(ChunkTile[] map, BiomeFinder maker, 
            SpatialNoise random) {
        int[] noise = refineNoise(maker.makeNoise(map[24].x, map[24].z, 4), map);
        for(int i = 0; i < map.length; i++) {
            findBiome(map[i], noise[i]);
        }
    }
    
    
    public static void findBiome(ChunkTile chunk, int noise) {
        if(!chunk.land) {
            if((noise - (chunk.temp / 2)) > 0) {
                chunk.rlBiome = FROCEAN;
            } else {
            	if(ClimaticBiomes.gotBoP) {
            		chunk.rlBiome = BOCEAN;
            	} else {
            		chunk.rlBiome = OCEAN;
            	}
            }
            return;
        }
        if(chunk.temp > 7 && ((chunk.wet - chunk.val) > noise - 1)) {
        	if((chunk.getBiomeSeed() & 0x1) == 1) {
        		if(ClimaticBiomes.gotBoP) {
        			chunk.rlBiome = BSWAMP;        			
        		} else {
        			chunk.rlBiome = SWAMP;
        		}
        		chunk.nextBiomeSeed();
        		return;
        	}
    		chunk.nextBiomeSeed();
        }
        findLandBiome(chunk);
    }
    
    
    public static void findLandBiome(ChunkTile chunk) {
    	if(ClimaticBiomes.gotBoP) {
    		// Rather than checking bit one and re-rolling, use a high bit
    		if((chunk.biomeSeed & 0x00010000) == 0) {
    			chunk.rlBiome = bopvanillatable[(chunk.temp * 10) + chunk.wet];
    		} else {
    			if((((chunk.biomeSeed >> 16) & 0xff) % 5) == 0) {
    				chunk.rlBiome = BALPINE;
    			}
    			chunk.rlBiome = boptable[(chunk.temp * 10) + chunk.wet];
    		}
    	} else {
    		chunk.rlBiome = table[(chunk.temp * 10) + chunk.wet];
    	}
    }
    
    
    static int[] refineNoise(int[][] noise, ChunkTile[] map) {
        int[] out = new int[BiomeFinder.GENSQ];
        // Could be better optimized, but this is a test of the gui and api
        for(int i = 1; i < (BiomeFinder.GENSIZE + 1); i++) 
            for(int j = 1; j < (BiomeFinder.GENSIZE + 1); j++) {
                map[((j - 1) * BiomeFinder.GENSIZE) + (i - 1)].noiseVal 
                		= refineCell(noise, i, j);
                out[((j - 1) * BiomeFinder.GENSIZE) + (i - 1)] 
                		= map[((j - 1) * BiomeFinder.GENSIZE) + (i - 1)].noiseVal / 5;
            }
        return out;
    }
    
    
    static int[] refineNoise10(int[][] noise, ChunkTile[] map) {
        int[] out = new int[BiomeFinder.GENSQ];
        // Could be better optimized, but this is a test of the gui and api
        for(int i = 1; i < (BiomeFinder.GENSIZE + 1); i++) 
            for(int j = 1; j < (BiomeFinder.GENSIZE + 1); j++) {
                map[((j - 1) * BiomeFinder.GENSIZE) + (i - 1)].noiseVal 
                		= refineCell(noise, i, j);
                out[((j - 1) * BiomeFinder.GENSIZE) + (i - 1)] 
                		= map[((j - 1) * BiomeFinder.GENSIZE) + (i - 1)].noiseVal;
            }
        return out;
    }
    
    
    static int[] refineNoise(int[][] noise, ChunkTile[] map, int bound) {
        int[] out = new int[BiomeFinder.GENSQ];
        // Could be better optimized, but this is a test of the gui and api
        for(int i = 1; i < (BiomeFinder.GENSIZE + 1); i++) 
            for(int j = 1; j < (BiomeFinder.GENSIZE + 1); j++) {
                map[((j - 1) * BiomeFinder.GENSIZE) + (i - 1)].noiseVal 
                		= refineCell(noise, i, j);
                out[((j - 1) * BiomeFinder.GENSIZE) + (i - 1)] 
                		= (map[((j - 1) * BiomeFinder.GENSIZE) + (i - 1)].noiseVal / bound) & 1;
            }
        return out;
    }
    
    
    static int refineCell(int[][] noise, int x, int y) {
        int sum = 0;
        // Yes, I include the cell itself -- its simpler and works for me
        for(int i = x - 1; i <= x + 1; i++) 
            for(int j = y - 1; j <= y + 1; j++) {
                sum += noise[i][j];
            }
        return sum;
    }
    
}
