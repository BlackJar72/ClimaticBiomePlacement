/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaredbgreat.climaticbiome.generation.generator;

import jaredbgreat.climaticbiome.generation.cache.Cache;
import jaredbgreat.climaticbiome.generation.cache.MutableCoords;
import jaredbgreat.climaticbiome.generation.map.RegionMap;
import jaredbgreat.climaticbiome.util.SpatialNoise;
import jaredbgreat.climaticbiome.util.SpatialNoise.RandomAt;

import java.util.ArrayList;
import java.util.Arrays;



/**
 *
 * @author jared
 */
public class MapMaker { 
	// STATIC VARIABLES USED IN MANY PLACES
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

    private final Cache<Region> regionCache = new Cache(32);
    
    private MutableCoords regionCoords = new MutableCoords(); 
    private MutableCoords chunkCoords = new MutableCoords(); 
    
    //final ChunkTile[] premap;
    private BasinNode[] basins;
    private ClimateNode[] temp;
    private ClimateNode[] wet;
    private ClimateNode[] height;
    private BiomeBasin[][] subbiomes;
    
    public final SpatialNoise chunkNoise;
    public final SpatialNoise regionNoise;
    public final SpatialNoise biomeNoise;
    
    
    public MapMaker(SpatialNoise chunkNoise, SpatialNoise regionNoise, SpatialNoise biomeNoise) {
        this.chunkNoise  = chunkNoise;
        this.regionNoise = regionNoise;
        this.biomeNoise  = biomeNoise;
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
    
    
    private Region[] findRegions(int x, int z) {
        Region[] out = new Region[9];
        int index = 0;
        for(int i = -1; i < 2; i++)
            for(int j = -1; j < 2; j++) {
            	regionCoords.init(x + i, z + j);
                //out[index] = regionPool.getEntry(regionCoords);
                out[index] = regionCache.get(regionCoords);
                if(out[index] == null) {
                	out[index] = new Region(x + i, z + j, 
                                regionNoise);
                        regionCache.add(out[index]);
                } else {
                        out[index].use();
                	//out[index].init(coords[0] + i, coords[1] + j, regionNoise);
                	//regionPool.add(out[index], regionCoords);
                }
                index++;
            }
        return out;
    }
    
    
    public void generate(RegionMap datamap) {
        Region[] regions = findRegions(coords.getX(), coords.getZ());
        ArrayList<BasinNode> basins = new ArrayList<>();
        ArrayList<ClimateNode> temp = new ArrayList<>();
        ArrayList<ClimateNode> wet = new ArrayList<>();
        for (Region region : regions) {
            basins.addAll(Arrays.asList(region.basins));
            temp.addAll(Arrays.asList(region.temp));
            wet.addAll(Arrays.asList(region.wet));
        }
        BasinNode[] basinAr = basins.toArray(new BasinNode[basins.size()]);
        ClimateNode[] tempAr = temp.toArray(new ClimateNode[temp.size()]);
        ClimateNode[] wetAr = wet.toArray(new ClimateNode[wet.size()]);
        SpatialNoise random = regionNoise;
        for(int i = 0; i < premap.length; i++) {
            premap[i].val = BasinNode.summateEffect(basinAr, premap[i]);
            edgeFix(premap[i]);
        }
        int[] noisy = refineNoise(makeNoise(random, 0), 4);
        for(int i = 0; i < premap.length; i++) {
            premap[i].rlBiome = 1 - noisy[i];
        }
        double[] doubleNoise;
        doubleNoise = averageNoise(makeDoubleNoise(random, 1));
        for(int i = 0; i < premap.length; i++) {
            premap[i].temp = Math.min(ClimateNode.summateEffect(tempAr, premap[i], 
                    doubleNoise[i]), 24);
        }
        doubleNoise = averageNoise(makeDoubleNoise(random, 2));
        for(int i = 0; i < premap.length; i++) {
            premap[i].wet = Math.min(ClimateNode.summateEffect(wetAr, premap[i], 
                    doubleNoise[i]), 9);
        }
        makeBiomes(256, random.getRandomAt(0, 0, 3));
        BiomeType.makeBiomes(this, random, regions[4]);
    }
    
    
    public int[] getLandmass() {
        int[] out = new int[premap.length];
        for(int i = 0; i < out.length; i++) {
            out[i] = premap[i].rlBiome;
        }
        return out;
    }
    
    
    public int[] getLandiness() {
        int[] out = new int[premap.length];
        for(int i = 0; i < out.length; i++) {
            out[i] = Math.max(premap[i].val, 0);
        }
        return out;
    }

    public int[] getTemps() {
        int[] out = new int[premap.length];
        for(int i = 0; i < out.length; i++) {
            out[i] = Math.max(premap[i].temp, 0);
        }
        return out;
     }

    public int[] getRain() {
        int[] out = new int[premap.length];
        for(int i = 0; i < out.length; i++) {
            out[i] = Math.max(premap[i].wet, 0);
        }
        return out;
     }

    public int[] getBiomes() {
        int[] out = new int[premap.length];
        for(int i = 0; i < out.length; i++) {
            out[i] = premap[i].biome;
        }
        return out;
     }

    public int[] getBiomes2() {
        int[] out = new int[premap.length];
        for(int i = 0; i < out.length; i++) {
            out[i] = colorBlend(BiomeType.values()[premap[i].rlBiome].color, 
                    premap[i].biome);
        }
        return out;
     }
    
    public ChunkTile getTile(int x, int y) {
        int index = (x * RSIZE) + y;
        if(index >= premap.length) {
            return null;
        } else {
            return premap[(x * RSIZE) + y];
        }
    }
    
    
    protected int[][] makeNoise(SpatialNoise random, int t) {
        int[][] noise = new int[RSIZE + 2][RSIZE + 2];
        for(int i = 0; i < (RSIZE + 2); i++)
            for(int j = 0; j < (RSIZE + 2); j++) {
                noise[i][j] = absModulus(random.intFor(i, j, t), 2);
            }
        return noise;
    }
    
    
    protected int[] refineNoise(int[][] noise, int times) {
        int[][] out = noise;
        for(int i = times; i > 0; i--) {
            out = refineNoise2(out);
        }
        return refineNoise(out);
    }
    
    
    protected int[] refineNoise(int[][] noise) {
        int[] out = new int[premap.length];
        // Could be better optimized, but this is a test of the gui and api
        for(int i = 1; i < (RSIZE + 1); i++) 
            for(int j = 1; j < (RSIZE + 1); j++) {
                out[((j - 1) * RSIZE) + (i - 1)] = refineCell(noise, i, j);
            }
        return out;
    }
    
    
    protected int[][] refineNoise2(int[][] noise) {
        int[][] out = new int[noise.length][noise[0].length];
        // Could be better optimized, but this is a test of the gui and api
        for(int i = 1; i < (RSIZE + 1); i++) 
            for(int j = 1; j < (RSIZE + 1); j++) {
                out[i][j] = refineCell(noise, i, j);
            }
        return out;
    }
    
    
    public int refineCell(int[][] noise, int x, int y) {
        int sum = 0;
        // Yes, I include the cell itself -- its simpler and works for me
        for(int i = x - 1; i <= x + 1; i++) 
            for(int j = y - 1; j <= y + 1; j++) {
                sum += noise[i][j];
            }
        if(sum < premap[((y -1) * RSIZE) + (x - 1)].val) {
            return 0;
        } else {
            return 1;
        }
    }
    
    
    private double[][] makeDoubleNoise(SpatialNoise random, int t) {
        double[][] noise = new double[RSIZE + 4][RSIZE + 4];
        for(int i = 0; i < (RSIZE + 2); i++)
            for(int j = 0; j < (RSIZE + 2); j++) {
                noise[i][j] = (random.doubleFor(i, j, t) / 5) - 0.1;
            }
        return noise;
    }
    
    
    public double[] averageNoise(double[][] noise) {
        double[] out = new double[premap.length];
        // Could be better optimized, but this is a test of the gui and api
        for(int i = 2; i < (RSIZE + 2); i++) 
            for(int j = 2; j < (RSIZE + 2); j++) {
                out[((j - 2) * RSIZE) + (i - 2)] = averageNoise(noise, i, j);
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
    
    
    public void makeBiomes(int sizeBlocks, RandomAt random) {
        int size = sizeBlocks / 16;
        int across = BSIZE;
        int down = across;
        subbiomes = new BiomeBasin[across][down];
        for(int i = 0; i < across; i++) 
            for(int j = 0; j < down; j++) {
                subbiomes[i][j]
                        = new BiomeBasin((i * size) + random.nextInt(size),
                                    (j * size) + random.nextInt(size),
                                    random.nextInt() | 0xff000000,
                                    1.0 + random.nextDouble());
            }
        for (ChunkTile tile : premap) {
            tile.biome = BiomeBasin.summateEffect(subbiomes, tile, size);
            tile.biomeSeed = tile.biome & 0x7fffffff;
        }
    }
    
    
    private void edgeFix(ChunkTile t) {
        if(t.x < 10) {
            t.val += ((t.x - 10) / 2);
        } else if(t.x >= (RSIZE - 10)) {
            t.val -= ((t.x - RSIZE + 10) / 2);
        }
        if(t.z < 10) {
            t.val += ((t.z - 10) /  2);
        } else if(t.z >= (RSIZE - 10)) {
            t.val -= ((t.z - RSIZE + 10) / 2);
        }
    }
    
    
    private int colorBlend(int c1, int c2) {
        int r = ((((0x00ff0000 & c1) >> 16) * 4) 
                + ((0x00ff0000 & c2) >> 16)) / 5; 
        int g = ((((0x0000ff00 & c1) >> 8) * 4) 
                + ((0x0000ff00 & c2) >> 8))  / 5; 
        int b = (((0x000000ff & c1) * 4) + (0x000000ff & c2)) / 5; 
        return 0xff000000 + (r << 16) + (g << 8) + b;
    }
    
    
}
