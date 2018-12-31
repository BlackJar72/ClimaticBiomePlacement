/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaredbgreat.climaticbiome.generation.generator;

import static jaredbgreat.climaticbiome.util.SpatialNoise.absModulus;
import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.generation.biome.BiomeClimateTable;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.cache.Cache;
import jaredbgreat.climaticbiome.generation.cache.Coords;
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
	IBiomeSpecifier specifier;
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
    
    private int xoff;
    private int zoff;    

    private ChunkTile[] premap;
    
    
    public MapMaker(SpatialNoise chunkNoise, SpatialNoise regionNoise, SpatialNoise biomeNoise) {
        this.chunkNoise  = chunkNoise;
        this.regionNoise = regionNoise;
        this.biomeNoise  = biomeNoise;
        specifier = BiomeClimateTable.getClimateTable();
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
//        System.out.println();
//        System.out.println("******************************");
//        System.out.println("Getting Regions For: " + x + ", " + z);
        Region[] out = new Region[9];
        int index = 0;
        for(int i = -1; i < 2; i++)
            for(int j = -1; j < 2; j++) {
            	regionCoords.init(x + i, z + j);
                out[index] = regionCache.get(regionCoords);
//                System.out.println("Making Region: " + regionCoords);
                if(out[index] == null) {
                	out[index] = new Region(x + i, z + j, 
                                regionNoise);
                        regionCache.add(out[index]);
                } else {
                        out[index].use();
                }
                index++;            }
//        System.out.println("******************************");
//        System.out.println();
        return out;
    }
    
    
    public void generate(RegionMap datamap) {
        Coords coords = datamap.getCoords();
        System.out.println();
        System.out.println("******************************");
        System.out.println("Creating Region Map: " + coords);
        System.out.println("******************************");
        System.out.println();
        xoff = (coords.getX() * 256) - 128;
        zoff = (coords.getZ() * 256) - 128;
		Region[] regions = findRegions(coords.getX(), coords.getZ());
		/*for(int i = 0; i < 3; i++) {
				System.out.println(regions[(i * 3) + 0].toCoords() + " "
				                 + regions[(i * 3) + 1].toCoords() + " "
				                 + regions[(i * 3) + 2].toCoords());
		}*/
        ArrayList<BasinNode> basins = new ArrayList<>();
        ArrayList<ClimateNode> temp = new ArrayList<>();
        ArrayList<ClimateNode> wet = new ArrayList<>();
        for(Region region : regions) {
            basins.addAll(Arrays.asList(region.basins));
            temp.addAll(Arrays.asList(region.temp));
            wet.addAll(Arrays.asList(region.wet));
        }
        BasinNode[] basinAr = basins.toArray(new BasinNode[basins.size()]);
        ClimateNode[] tempAr = temp.toArray(new ClimateNode[temp.size()]);
        ClimateNode[] wetAr = wet.toArray(new ClimateNode[wet.size()]);
        SpatialNoise random = chunkNoise;
        premap = new ChunkTile[RSIZE * RSIZE];
        for(int i = 0; i < RSIZE; i++) 
            for(int j = 0; j < RSIZE; j++) {
                premap[(i * RSIZE) + j] = new ChunkTile(i, j, xoff, zoff);
            }
		for(int i = 0; i < premap.length; i++) {
            premap[i].val = BasinNode.summateEffect(basinAr, premap[i]);
            edgeFix(premap[i]);
        }
        int[] noisy = refineNoise(premap, makeNoise(random, 0), 4);
        for(int i = 0; i < premap.length; i++) {
            premap[i].rlBiome = 1 - noisy[i];
        }
        double[] doubleNoise;
        doubleNoise = averageNoise(premap, makeDoubleNoise(random, 1));
        for(int i = 0; i < premap.length; i++) {
            premap[i].temp = Math.min(ClimateNode.summateEffect(tempAr, premap[i], 
                    doubleNoise[i]), 24);
        }
        doubleNoise = averageNoise(premap, makeDoubleNoise(random, 2));
        for(int i = 0; i < premap.length; i++) {
            premap[i].wet = Math.min(ClimateNode.summateEffect(wetAr, premap[i], 
                    doubleNoise[i]), 9);
        }
        int[] noise = refineNoise10(makeNoise(random, 4), premap);
        for(int i = 0; i < premap.length; i++) {
            premap[i].noiseVal = noise[i];
        }
        RiverMaker rm = new RiverMaker(this, random.longFor(coords.getX(), coords.getZ(), 16), 
                regions[4], coords.getX(), coords.getZ());
        rm.build();
        makeBiomes(premap, 256, random.getRandomAt(coords.getX(), coords.getZ(), 3));
        if(ConfigHandler.addBeaches) {
	        for(int i = 0; i < premap.length; i++) {
	        	makeBeach(premap[i]);
	        }
	        for(int i = 0; i < premap.length; i++) {
	        	growBeach1(premap[i]);
	        }
        }
        for(int i = 0; i < premap.length; i++) {
        	if(ConfigHandler.addBeaches) {
        		growBeach2(premap[i]);
        	}
        	datamap.setBiomeExpress(specifier.getBiome(premap[i]), i);
        }
    }
    
    
    public ChunkTile getTile(ChunkTile[] premap, int x, int y) {
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
            	// TODO: Adjust for map position
                noise[i][j] = absModulus(random.intFor(i + xoff, j + zoff, t), 2);
            }
        return noise;
    }
    
    
    protected int[] refineNoise(ChunkTile[] premap, int[][] noise, int times) {
        int[][] out = noise;
        for(int i = times; i > 0; i--) {
            out = refineNoise2(premap, out);
        }
        return refineNoise(premap, out);
    }
    
    
    protected int[] refineNoise(ChunkTile[] premap, int[][] noise) {
        int[] out = new int[premap.length];
        for(int i = 1; i < (RSIZE + 1); i++) 
            for(int j = 1; j < (RSIZE + 1); j++) {
                out[((j - 1) * RSIZE) + (i - 1)] = refineCell(premap, noise, i, j);
            }
        return out;
    }
    
    
    protected int[][] refineNoise2(ChunkTile[] premap, int[][] noise) {
        int[][] out = new int[noise.length][noise[0].length];
        // Could be better optimized, but this is a test of the gui and api
        for(int i = 1; i < (RSIZE + 1); i++) 
            for(int j = 1; j < (RSIZE + 1); j++) {
                out[i][j] = refineCell(premap, noise, i, j);
            }
        return out;
    }
    
    
    public int refineCell(ChunkTile[] premap, int[][] noise, int x, int y) {
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
                noise[i][j] = (random.doubleFor(i + xoff, j + zoff, t) / 5) - 0.1;
            }
        return noise;
    }
    
    
    public double[] averageNoise(ChunkTile[] premap, double[][] noise) {
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
    
    
    public void makeBiomes(ChunkTile[] premap, int sizeBlocks, RandomAt random) {
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
            tile.biomeSeed = BiomeBasin.summateEffect(subbiomes, tile) & 0x7fffffff;
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
    
    
    private static int[] refineBasicNoise(int[][] noise, ChunkTile[] premap) {
        int[] out = new int[premap.length];
        // Could be better optimized, but this is a test of the gui and api
        for(int i = 1; i < (RSIZE + 1); i++) 
            for(int j = 1; j < (RSIZE + 1); j++) {
                out[((j - 1) * RSIZE) + (i - 1)] = refineBasicCell(noise, i, j);
            }
        return out;
    }
    
    
    private static int refineBasicCell(int[][] noise, int x, int y) {
        int sum = 0;
        // Yes, I include the cell itself -- its simpler and works for me
        for(int i = x - 1; i <= x + 1; i++) 
            for(int j = y - 1; j <= y + 1; j++) {
                sum += noise[i][j];
            }
        return sum / 5;
    }
    
    
    private static int[] refineNoise10(int[][] noise, ChunkTile[] premap) {
        int[] out = new int[premap.length];
        // Could be better optimized, but this is a test of the gui and api
        for(int i = 1; i < (RSIZE + 1); i++) 
            for(int j = 1; j < (RSIZE + 1); j++) {
                out[((j - 1) * RSIZE) + (i - 1)] = refineCell10(noise, i, j);
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
    
    
    private boolean notLand(ChunkTile t) {
        return t.rlBiome == 0;
    }
    
    
    void makeBeach(ChunkTile t) {
        if(notLand(t) || (t.getX() < 1) || (t.getX() > 254)
                      || (t.getZ() < 1) || (t.getZ() > 254)) return;
        int oceans = 0;
        for(int i = -1; i < 2; i++) 
            for(int j = -1; j < 2; j++) {
                ChunkTile x = premap[((t.getX() + i) * RSIZE) + t.getZ() + j];
                if(notLand(x)) {
                    oceans++;
                }
            }
        if(oceans < 3) return;
        t.beach = t.getNoise() < (oceans - (2 * Math.max(oceans - 5, 0)) + 5 
                - ((t.getBiomeSeed() >> 16) & 1)
                + ((t.getBiomeSeed() >> 15) & 1));
    }
    
    
    void growBeach1(ChunkTile t) {
        if(!notLand(t) || (t.getX() < 1) || (t.getX() > 254)
                       || (t.getZ() < 1) || (t.getZ() > 254)) return;
        int beaches = 0;
        for(int i = -1; i < 2; i++) 
            for(int j = -1; j < 2; j++) {
                ChunkTile x = premap[((t.getX() + i) * RSIZE) + t.getZ() + j];
                if(!notLand(x) && x.beach) {
                    beaches++;
                }
            }
        if(beaches < 1) return;
        t.beach = t.getNoise() < (beaches + 4 
                - ((t.getBiomeSeed() >> 14) & 1)
                + ((t.getBiomeSeed() >> 13) & 1));
    }
    
    
    void growBeach2(ChunkTile t) {
    	if(t.beach) t.rlBiome = 1;
    }
    
    
    public ChunkTile getTile(int x, int y) {
    	//System.err.println("Geting Tile: " + x + ", " + y + " = " + ((x * RSIZE) + y));
        int index = (x * RSIZE) + y;
        //System.err.println(x + ", " + y + " = " + index);
        return premap[index];
    }
    
    
    public int getXoff() {
    	return xoff;
    }
    
    
    public int getZoff() {
    	return zoff;
    }
    
    
}
