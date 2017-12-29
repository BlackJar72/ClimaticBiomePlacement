/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaredbgreat.climaticbiome.generation.chunk;

import jaredbgreat.climaticbiome.generation.chunk.biomes.*;

/**
 *
 * @author jared
 */
public enum BiomeType {
    
    OCEAN (new GetOcean()),
    FROCEAN (new GetFrozenOcean()),
    SWAMP (new GetSwamp()),
    TUNDRA (new GetTundra()),
    CGRASS (new GetPlains()),
    GRASS (new GetPlains()),
    SGRASS (new GetPlains()),
    SAVANNA (new GetSavanna()),
    TAIGA (new GetTaiga()),
    FOREST (new GetTemporateForest()),
    SFOREST (new GetSubtropicalForest()),
    TFOREST (new GetTropicalForest()),
    JUNGLE (new GetJungle()),
    DESERT (new GetDesert()),
    SCRUB (new GetChaparral()),
    ALPINE (new GetAlpine());   
    
    public final IBiomeSpecifier specifier;
    public static final int GENLOC = 25;
    
    BiomeType(IBiomeSpecifier spec) {
        specifier = spec;
    }
    
    private static final BiomeType[] table = {
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
    	GRASS,  GRASS,   GRASS,   GRASS,   FOREST,  FOREST, FOREST, FOREST, FOREST, FOREST,
    	SCRUB,  GRASS,   GRASS,   GRASS,   FOREST,  FOREST, FOREST, FOREST, FOREST, FOREST,
    	SCRUB,  GRASS,   GRASS,   GRASS,   FOREST,  FOREST, FOREST, FOREST, FOREST, FOREST,
    	DESERT, SCRUB,   GRASS,   GRASS,   FOREST,  FOREST, FOREST, FOREST, FOREST, FOREST,
    	DESERT, DESERT,  SCRUB,   GRASS,   FOREST,  FOREST, FOREST, FOREST, FOREST, FOREST,
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
    	DESERT, SAVANNA, SAVANNA, SAVANNA, TFOREST, JUNGLE,  JUNGLE, JUNGLE, JUNGLE, JUNGLE,
    	DESERT, SAVANNA, SAVANNA, SAVANNA, TFOREST, JUNGLE,  JUNGLE, JUNGLE, JUNGLE, JUNGLE
    };
    
    public static void makeBiomes(ChunkTile[] map, BiomeFinder maker, 
            SpatialNoise random) {
        int[] noise = refineNoise(maker.makeNoise(map[GENLOC].x, map[GENLOC].z, 4), map);
        for(int i = 0; i < map.length; i++) {
            findBiome(map[i], noise[i]);
        }
    }
    
    
    public static void findBiome(ChunkTile chunk, int noise) {
        if(!chunk.land) {
            if((noise - (chunk.temp / 2)) > 0) {
                chunk.rlBiome = FROCEAN;
            } else {
            	chunk.rlBiome = OCEAN;
            }
            return;
        }
        if(chunk.temp > 7 && ((chunk.wet - chunk.val) > noise - 1)) {
            chunk.rlBiome = SWAMP;
            return;
        }
        findLandBiome(chunk);
    }
    
    
    public static void findLandBiome(ChunkTile chunk) {
    	chunk.rlBiome = table[(chunk.temp * 10) + chunk.wet];
    }
    
    
    private static int[] refineNoise(int[][] noise, ChunkTile[] map) {
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
    
    
    private static int[] refineNoise10(int[][] noise, ChunkTile[] map) {
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
    
    
    private static int[] refineNoise(int[][] noise, ChunkTile[] map, int bound) {
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
    
    
    private static int refineCell(int[][] noise, int x, int y) {
        int sum = 0;
        // Yes, I include the cell itself -- its simpler and works for me
        for(int i = x - 1; i <= x + 1; i++) 
            for(int j = y - 1; j <= y + 1; j++) {
                sum += noise[i][j];
            }
        return sum;
    }
    
}
