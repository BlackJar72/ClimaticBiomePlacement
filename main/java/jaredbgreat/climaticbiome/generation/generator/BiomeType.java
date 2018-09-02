/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaredbgreat.climaticbiome.generation.generator;

import jaredbgreat.climaticbiome.util.SpatialNoise;

/**
 *
 * @author jared
 */
public enum BiomeType {
    
    OCEAN (0xff1122ff),
    DOCEAN (0xff111199),
    FROCEAN (0xff2244ff),
    RIVER (0xff1133ff),
    UNKOWN (0xff00ff44),
    SWAMP (0xff228844),
    MOUNTAIN (0xff888888),
    TUNDRA (0xffffffff),
    CGRASS (0xff22d477),
    GRASS (0xff22d455),
    SGRASS (0xff44d455),
    SAVANNA (0xff88ff44),
    TAIGA (0xff22aa99),
    FOREST (0xff00aa22),
    SFOREST (0xff00aa22),
    TFOREST (0xff00aa22),
    JUNGLE (0xff22ff44),
    DESERT (0xffaa9900),
    SCRUB (0xff668844),
    ALPINE (0xff776688), 
    ALPINE2 (0xffaaaacc),  
    HILLY (0xff556666);  
    
    public final int color;
    
    BiomeType(int color) {
        this.color = color;
    }
    
    private static final BiomeType[] table = {
    	//Arctic
    	TUNDRA, TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA, TUNDRA, TUNDRA, TUNDRA, TUNDRA,
    	TUNDRA, TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA, TUNDRA, TUNDRA, TUNDRA, TUNDRA,
    	TUNDRA, TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA, TUNDRA, TUNDRA, TUNDRA, TUNDRA,
    	TUNDRA, TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA, TUNDRA, TUNDRA, TUNDRA, TUNDRA,
    	TUNDRA, TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA,  TUNDRA, TUNDRA, TUNDRA, TUNDRA, TUNDRA,
    	//Sub-Arctic
    	CGRASS,  CGRASS,   TAIGA,   TAIGA,   TAIGA,   TAIGA,  TAIGA,  TAIGA,  TAIGA,  TAIGA,
    	CGRASS,  CGRASS,   TAIGA,   TAIGA,   TAIGA,   TAIGA,  TAIGA,  TAIGA,  TAIGA,  TAIGA,
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
    
    
//    public static void makeBiomes(MapMaker map, SpatialNoise random, 
//            Region region) {
//        int[] noise = refineNoise(map.makeNoise(random, 4), map);
//        int[] ice   = refineNoise10(map.makeNoise(random, 5), map);
//        int[] cn    = refineNoise10(map.makeNoise(random, 6), map);
//        for(int i = 0; i < map.premap.length; i++) {
//            findBiome(map.premap[i], noise[i], ice[i], cn[i]);
//        }
//        RiverMaker rm = new RiverMaker(map, random.longFor(0, 0, 16), 
//                region);
//        rm.build();
//    }
//    
//    
//    public static void findBiome(ChunkTile tile, int noise, int ice, int cn) {
//        if(tile.rlBiome == 0) {
//            if(((ice / 2) - tile.temp) > 0) {
//                tile.rlBiome = FROCEAN.ordinal();
//            } else if(tile.val < 3) {
//                //tile.rlBiome = DOCEAN.ordinal();
//            } else if((tile.biomeSeed % 5 == 0) 
//                    && (cn > (3 + (tile.biomeSeed % 3)))) {
//                findLandBiome(tile);
//            }
//            return;
//        }
//        if(tile.temp > 4 && ((tile.wet - tile.val) > noise - 1)) {
//            if((tile.getBiomeSeed() & 0x1) == 1) {
//                tile.rlBiome = SWAMP.ordinal();
//                tile.nextBiomeSeed();
//                return;
//            }
//            tile.nextBiomeSeed();
//        }
//        int mval = (int)(tile.faults * 10)  + 15 - tile.val - tile.noiseVal;
//        /*if(mval < 10) {
//            if(mval < 10) {
//                tile.mountain = true;
//                if(mval < 7) {                    
//                    tile.rlBiome = ALPINE2.ordinal();
//                } else {
//                    tile.rlBiome = ALPINE.ordinal();
//                }
//                return;
//            } else {
//                tile.rlBiome = HILLY.ordinal();
//                tile.hilly = true;
//                return;
//            }
//        }*/
//        findLandBiome(tile);
//    }
//    
//    
//    public static void findLandBiome(ChunkTile chunk) {
//    	chunk.rlBiome = table[(chunk.temp * 10) + chunk.wet].ordinal();
//    }
//    
//    
//    private static int[] refineNoise(int[][] noise, MapMaker map) {
//        int[] out = new int[map.premap.length];
//        // Could be better optimized, but this is a test of the gui and api
//        for(int i = 1; i < (map.RSIZE + 1); i++) 
//            for(int j = 1; j < (map.RSIZE + 1); j++) {
//                out[((j - 1) * map.RSIZE) + (i - 1)] = refineCell(noise, i, j);
//            }
//        return out;
//    }
//    
//    
//    private static int refineCell(int[][] noise, int x, int y) {
//        int sum = 0;
//        // Yes, I include the cell itself -- its simpler and works for me
//        for(int i = x - 1; i <= x + 1; i++) 
//            for(int j = y - 1; j <= y + 1; j++) {
//                sum += noise[i][j];
//            }
//        return sum / 5;
//    }
//    
//    
//    private static int[] refineNoise10(int[][] noise, MapMaker map) {
//        int[] out = new int[map.premap.length];
//        // Could be better optimized, but this is a test of the gui and api
//        for(int i = 1; i < (map.RSIZE + 1); i++) 
//            for(int j = 1; j < (map.RSIZE + 1); j++) {
//                out[((j - 1) * map.RSIZE) + (i - 1)] = refineCell10(noise, i, j);
//            }
//        return out;
//    }
//    
//    
//    private static int refineCell10(int[][] noise, int x, int y) {
//        int sum = 0;
//        // Yes, I include the cell itself -- its simpler and works for me
//        for(int i = x - 1; i <= x + 1; i++) 
//            for(int j = y - 1; j <= y + 1; j++) {
//                sum += noise[i][j];
//            }
//        return sum;
//    }
    
}
