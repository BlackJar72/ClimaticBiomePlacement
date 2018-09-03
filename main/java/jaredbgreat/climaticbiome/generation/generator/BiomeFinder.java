/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaredbgreat.climaticbiome.generation.generator;

import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.map.RegionMap;
import jaredbgreat.climaticbiome.util.SpatialNoise;

/**
 *
 * @author Jared Blackburn
 */
// FIXME: Does all this really need to be static?  Or 
//        should there just be an instance belonging 
//        to MapMaker?  Probably the later.
public class BiomeFinder {
	private static IBiomeSpecifier icecap;
	private static IBiomeSpecifier oceans;
	private static IBiomeSpecifier wetlands;
	private static IBiomeSpecifier land;
	private static IBiomeSpecifier mountains;
	private static IBiomeSpecifier islands;
	
    
    public static void makeBiomes(ChunkTile[] premap, MapMaker mapmaker, 
    			RegionMap map, SpatialNoise random, Region region) {
        int[] noise = refineNoise(premap, mapmaker.makeNoise(random, 4), mapmaker);
        int[] ice   = refineNoise10(premap, mapmaker.makeNoise(random, 5), mapmaker);
        int[] cn    = refineNoise10(premap, mapmaker.makeNoise(random, 6), mapmaker);
        for(int i = 0; i < premap.length; i++) {
            map.setBiomeExpress(findBiome(premap[i], noise[i], ice[i], cn[i]), 
            		(i % MapMaker.RSIZE), (i / MapMaker.RSIZE));
        }
        RiverMaker rm = new RiverMaker(mapmaker, random.longFor(0, 0, 16), 
                region);
        rm.build();
    }
    
    
    public static int findBiome(ChunkTile tile, int noise, int ice, int cn) {
        if(tile.rlBiome == 0) {
            if(((ice / 2) - tile.temp) > 0) {
                return icecap.getBiome(tile);
            } else if(tile.val < 3) {
                //tile.rlBiome = DOCEAN.ordinal();
            } else if((tile.biomeSeed % 5 == 0) 
                    && (cn > (3 + (tile.biomeSeed % 3)))) {
                return islands.getBiome(tile.nextBiomeSeed());
            }
            return oceans.getBiome(tile.nextBiomeSeed());
        }
        if(tile.temp > 4 && ((tile.wet - tile.val) > noise - 1)) {
            if((tile.getBiomeSeed() & 0x1) == 1) {
            	return wetlands.getBiome(tile.nextBiomeSeed());
            }
        }
        if((tile.getBiomeSeed() % 5) == 0) {
        	return mountains.getBiome(tile.nextBiomeSeed());
        }
        return land.getBiome(tile.nextBiomeSeed());
    }
    
    
    private static int[] refineNoise(ChunkTile[] premap, int[][] noise, MapMaker map) {
        int[] out = new int[premap.length];
        // Could be better optimized, but this is a test of the gui and api
        for(int i = 1; i < (map.RSIZE + 1); i++) 
            for(int j = 1; j < (map.RSIZE + 1); j++) {
                out[((j - 1) * map.RSIZE) + (i - 1)] = refineCell(noise, i, j);
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
        return sum / 5;
    }
    
    
    private static int[] refineNoise10(ChunkTile[] premap, int[][] noise, MapMaker map) {
        int[] out = new int[premap.length];
        // Could be better optimized, but this is a test of the gui and api
        for(int i = 1; i < (map.RSIZE + 1); i++) 
            for(int j = 1; j < (map.RSIZE + 1); j++) {
                out[((j - 1) * map.RSIZE) + (i - 1)] = refineCell10(noise, i, j);
            }
        return out;
    }
    
    
    private static int refineCell10(int[][] noise, int x, int y) {
        int sum = 0;
        // Yes, I include the cell itself -- its simpler and works for me
        for(int i = x - 1; i <= x + 1; i++) 
            for(int j = y - 1; j <= y + 1; j++) {
                sum += noise[i][j];
            }
        return sum;
    }
    
}
