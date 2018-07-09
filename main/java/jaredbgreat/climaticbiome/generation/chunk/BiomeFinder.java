/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaredbgreat.climaticbiome.generation.chunk;

import static jaredbgreat.climaticbiome.generation.chunk.SpatialNoise.absModulus;
import jaredbgreat.climaticbiome.generation.cache.Cache;
import jaredbgreat.climaticbiome.generation.cache.Coords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

/**
 *
 * @author jared
 */
public class BiomeFinder {
    // Using division to make the derivation obvious; this should be done
    // at compile time with no loss of performance.
    public static final int CSIZE = 16; // chuck size
    public static final int RSIZE = 4096 / CSIZE; // region / "continent" size
    public static final int RADIUS = RSIZE / 2; // radius for basin effect range
    public static final int SQRADIUS = RADIUS * RADIUS;
    public static final int BSIZE = 256 / CSIZE; // base size for (sub)biomes
    public static final int GENSIZE = 7; // area of chunks to looks at
    public static final int GENHALF1 = GENSIZE  / 2;
    public static final int GENHALF0 = GENHALF1 - 1;
    public static final int GENHALF2 = GENHALF1 + 1;
    public static final int GENSQ = GENSIZE * GENSIZE; // area of chunks to looks at
    
    private final Cache<Region> regionCache = new Cache<Region>(32);
    private final Cache<ChunkTile> chunkCache = new Cache<ChunkTile>(64);
    
    public final SpatialNoise chunkNoise;
    public final SpatialNoise regionNoise;
    public final SpatialNoise biomeNoise;
    
    static IBiomeType realFinder = new BuiltinBiomeType();
    
    
    public BiomeFinder(long seed) {
        Random random = new Random(seed);
        chunkNoise = new SpatialNoise(random.nextLong(), random.nextLong());
        regionNoise = new SpatialNoise(random.nextLong(), random.nextLong());
        biomeNoise = new SpatialNoise(random.nextLong(), random.nextLong());
    }
    
    /**
     * This will take a set of chunk coordinates and return the region 
     * coordinates as an a length two int array containing the region 
     * coordinates.  Note that this is not the region of a region file 
     * but a region representing a continent of sorts for generation -- this
     * is a much bigger area.
     * 
     * @param x
     * @param z
     * @return 
     */
    private int[] findRegion(int x, int z) {
        int[] out = new int[2];
        out[0] = x / RSIZE;
        out[1] = z / RSIZE;
        return out;
    }
    
    
    /**
     * Returns the distance between the point represented by the two 
     * set of coordinates.
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return 
     */
    private int getSqIntDistance(int x1, int y1, int x2, int y2) {
        return ((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2));
    }
    
    
    /**
     * Returns true if the two points are within a distance less than half 
     * the distance across a continental region, false if they are farther
     * apart.  This is for determining if something (usually an attraction / 
     * influence basin) is within range of a chunk.
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return 
     */
    private boolean inRange(int x1, int y1, int x2, int y2) {
        return (getSqIntDistance(x1, y1, x2, y2) < SQRADIUS);
    }
    
    
    private Region[] findRegions(int x, int z) {
        Region[] out = new Region[9];
        int[] coords = findRegion(x, z);
        int index = 0;
        for(int i = -1; i < 2; i++)
            for(int j = -1; j < 2; j++) {
                out[index] = regionCache.get(coords[0] + i, coords[1] + j);
                if(out[index] == null) {
                	out[index] = new Region(coords[0] + i, coords[1] + j, 
                                regionNoise);
                        regionCache.add(out[index]);
                } else {
                        out[index].use();
                }
                index++;
            }
        return out;
    }
    
    
    public ChunkTile getChunk(int x, int z) {
    	Coords coords = new Coords(x, z);
    	ChunkTile out = chunkCache.get(coords);
    	if(out == null) {
    		out = makeChunk(x, z)[24];
    		chunkCache.add(new ChunkTile(coords, out));
    	} 
    	return out;
    }
    
    
    public ChunkTile[] makeChunk(int x, int z) {
        Region[] regions = findRegions(x, z);
        ArrayList<BasinNode> basins = new ArrayList<BasinNode>();
        ArrayList<ClimateNode> temp = new ArrayList<ClimateNode>();
        ArrayList<ClimateNode> wet = new ArrayList<ClimateNode>();
        for(int i = 0; i < regions.length; i++) {
            basins.addAll(Arrays.asList(regions[i].basins));
            temp.addAll(Arrays.asList(regions[i].temp));
            wet.addAll(Arrays.asList(regions[i].wet));
        }
        BasinNode[] basinAr = basins.toArray(new BasinNode[basins.size()]);
        ClimateNode[] tempAr = temp.toArray(new ClimateNode[temp.size()]);
        ClimateNode[] wetAr = wet.toArray(new ClimateNode[wet.size()]);
        ChunkTile[] map = new ChunkTile[GENSQ];
        for(int i = 0; i < GENSIZE; i++)
            for(int j = 0; j < GENSIZE; j++) {                
                map[(j * GENSIZE) + i] = new ChunkTile(x + i - 3, z + j - 3);
            }
        double[] tempNoise = averageNoise(makeDoubleNoise(x, z, 0));
        double[] wetNoise = averageNoise(makeDoubleNoise(x, z, 1));
        for(int i = 0; i < map.length; i++) {
            map[i].val = BasinNode.summateEffect(basinAr, map[i]);            
            map[i].temp = Math.min(ClimateNode.summateTemp(tempAr, map[i], 
                    tempNoise[i]), 24);
            map[i].wet = Math.min(ClimateNode.summateWet(wetAr, map[i], 
                    wetNoise[i]), 9);
        }        
        int[] landNoise = refineNoise(makeNoise(x, z, 2), map, 4);
        for(int i = 0; i < map.length; i++) {
            map[i].land = (landNoise[i] == 0);
        }       
        makeBiomesSeeds(x, z, map);
        realFinder.makeBiomes(map, this, chunkNoise);    
        makeBiomes(x, z, map);
        return map;
    }
    
    
    protected int[][] makeNoise(int x, int z, int t) {
        int[][] noise = new int[GENSIZE + 2][GENSIZE + 2];
        for(int i = 1; i < (GENSIZE + 1); i++)
            for(int j = 0; j < (GENSIZE + 2); j++) {
                noise[i][j] = absModulus(chunkNoise.intFor(x + i - 5, z + j - 5, 
                        t), 2);
            }
        return noise;
    }
    
    
    protected int[] refineNoise(int[][] noise, ChunkTile[] map, int times) {
        int[][] out = noise;
        for(int i = times; i > 0; i--) {
            out = refineNoise2(out, map);
        }
        return refineNoise(out, map);
    }
    
    
    protected int[] refineNoise(int[][] noise, ChunkTile[] map) {
        int[] out = new int[GENSQ];
        // Could be better optimized, but this is a test of the gui and api
        for(int i = 1; i < (GENSIZE + 1); i++) 
            for(int j = 1; j < (GENSIZE + 1); j++) {
                out[((j - 1) * GENSIZE) + (i - 1)] = refineCell(noise, map, i, j);
            }
        return out;
    }
    
    
    protected int[][] refineNoise2(int[][] noise, ChunkTile[] map) {
        int[][] out = new int[noise.length][noise[0].length];
        // Could be better optimized, but this is a test of the gui and api
        for(int i = 1; i < (GENSIZE + 1); i++) 
            for(int j = 1; j < (GENSIZE + 1); j++) {
                out[i][j] = refineCell(noise, map, i, j);
            }
        return out;
    }
    
    
    public int refineCell(int[][] noise, ChunkTile[] map, int x, int y) {
        int sum = 0;
        // Yes, I include the cell itself -- its simpler and works for me
        for(int i = x - 1; i <= x + 1; i++) 
            for(int j = y - 1; j <= y + 1; j++) {
                sum += noise[i][j];
            }
        if(sum < map[((y - 1) * GENSIZE) + (x - 1)].val) {
            return 0;
        } else {
            return 1;
        }
    }
    
    
    private double[][] makeDoubleNoise(int x, int z, int t) {
        double[][] noise = new double[GENSIZE + 4][GENSIZE + 4];
        for(int i = 0; i < (GENSIZE + 2); i++)
            for(int j = 0; j < (GENSIZE + 2); j++) {
                noise[i][j] = (chunkNoise.doubleFor(x + i - 6, z + j - 6, t) / 5) - 0.1;
            }
        return noise;
    }
    
    
    public double[] averageNoise(double[][] noise) {
        double[] out = new double[GENSQ];
        // Could be better optimized
        for(int i = 2; i < (GENSIZE + 2); i++) 
            for(int j = 2; j < (GENSIZE + 2); j++) {
                out[((j - 2) * GENSIZE) + (i - 2)] = averageNoise(noise, i, j);
            }
        return out;
    }
    
    
    public double averageNoise(double[][] noise, int x, int y) {
        double sum = 0;
        // Yes, I include the cell itself -- its simpler and works for me
        for(int i = x - 2; i <= x + 2; i++) 
            for(int j = y - 2; j <= y + 2; j++) {
                sum += noise[i][j];
            }
        return sum / 9;
    }
    
    
    public void makeBiomesSeeds(int x, int z, ChunkTile[] map) {
        BiomeBasin[][] subBiomes = new BiomeBasin[5][5];
        int bx = x / BSIZE;
        int bz = z / BSIZE;
        int xcoord, zcoord;
        for(int i = 0; i < subBiomes.length; i++) 
            for(int j = 0; j < subBiomes[i].length; j++) {
                xcoord = bx + i - 2;
                zcoord = bz + j - 2;
                subBiomes[i][j]                        
                    = new BiomeBasin((xcoord * BSIZE) 
                            + SpatialNoise.absModulus(biomeNoise.intFor(xcoord, 
                                    zcoord, 0), BSIZE),
                        (zcoord * BSIZE) 
                            + SpatialNoise.absModulus(biomeNoise.intFor(xcoord, 
                                    zcoord, 1), BSIZE),
                        biomeNoise.intFor(xcoord, zcoord, 2) & 0x7fffffff,
                        1.0 + biomeNoise.doubleFor(xcoord, zcoord, 3));
                if(subBiomes[i][j].value == 0) {
                    System.out.println(
                          xcoord + ", "
                        + zcoord + ", "
                        + (subBiomes[i][j].x - (xcoord * BSIZE)) + ", " 
                        + (subBiomes[i][j].z - (zcoord * BSIZE)) + ", "
                        + subBiomes[i][j].value + ", "
                        + subBiomes[i][j].strength);
                }
            }
        for (ChunkTile tile : map) {
            tile.biomeSeed = BiomeBasin.summateEffect(subBiomes, tile);
        }
    }
    
    
    public void makeBiomes(int x, int z, ChunkTile[] map) {
        for (ChunkTile tile : map) {
            tile.biome = tile.rlBiome.specifier.getBiome(tile);
        }
    }
    
    
    public Biome[] getChunkGrid(ChunkTile in[]) {
    	Biome[] out = new Biome[256];
    	ChunkTile[] tiles = new ChunkTile[9];
    	BiomeBasin[][] basins = new BiomeBasin[3][3];
    	for(int i = 0; i < tiles.length; i++) {
    		int x1 = (i / 3) + 2;
    		int z1 = (i % 3) + 2;
    		tiles[i] = in[(z1 * GENSIZE) + x1];
    		basins[i / 3][i % 3] = new BiomeBasin(
    				(x1 * 16) + (chunkNoise.intFor(tiles[i].x, tiles[i].z, 10) % 16),
    				(z1 * 16) + (chunkNoise.intFor(tiles[i].x, tiles[i].z, 11) % 16),
    				tiles[i].biome, 1.0 + chunkNoise.doubleFor(tiles[i].x, tiles[i].z, 12));    				
    	}
    	for(int i = 0; i < 16; i++)
    		for(int j = 0; j < 16; j++) {
    			out[(j * 16) + i] = Biome.getBiome(BiomeBasin.summateEffect(basins, 48 + i, 48 + j),
    					Biomes.DEFAULT);
    		}
    	return out;
    }
    
    
    public void cleanCaches() {
    	regionCache.cleanup();
    	chunkCache.cleanup();
    }
    
    
}
