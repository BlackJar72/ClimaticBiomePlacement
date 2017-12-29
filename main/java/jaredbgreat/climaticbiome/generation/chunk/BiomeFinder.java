/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaredbgreat.climaticbiome.generation.chunk;

import static jaredbgreat.climaticbiome.generation.chunk.SpatialNoise.absModulus;
import jaredbgreat.climaticbiome.generation.cache.Cache;
import jaredbgreat.climaticbiome.generation.cache.CachedPool;
import jaredbgreat.climaticbiome.generation.cache.CachedPool.ObjectFactory;
import jaredbgreat.climaticbiome.generation.cache.MutableCoords;

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
    public static final int CSQ   = CSIZE * CSIZE; // chuck size
    public static final int RSIZE = 4096 / CSIZE; // region / "continent" size
    public static final int RADIUS = RSIZE / 2; // radius for basin effect range
    public static final int SQRADIUS = RADIUS * RADIUS;
    public static final int BSIZE = 256 / CSIZE; // base size for (sub)biomes
    public static final int GENSIZE = 7; // area of chunks to looks at
    public static final int GENHALF1 = GENSIZE  / 2;
    public static final int GENHALF0 = GENHALF1 - 1;
    public static final int GENHALF2 = GENHALF1 + 1;
    public static final int GENSQ = GENSIZE * GENSIZE; // area of chunks to looks at
    
    private volatile ObjectFactory<Region> regionFact = new ObjectFactory() {
		@Override
		public Region create() {
			return new Region();
		}
    };
    private CachedPool<Region> regionPool = new CachedPool<>(regionFact, 72, 72);
    private Cache<BiomeArray>  biomeCache = new Cache<>(256);
    private Cache<ChunkTile>   chunkCache = new Cache<>(256);
    
    private MutableCoords regionCoords = new MutableCoords(); 
    
    public final SpatialNoise chunkNoise;
    public final SpatialNoise regionNoise;
    public final SpatialNoise biomeNoise;   
    
    
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
    
    
    private Region[] findRegions(int x, int z) {
        Region[] out = new Region[9];
        int[] coords = findRegion(x, z);
        int index = 0;
        for(int i = -1; i < 2; i++)
            for(int j = -1; j < 2; j++) {
            	regionCoords.init(coords[0] + i, coords[1] + j);
                out[index] = regionPool.getEntry(regionCoords);
                if(out[index].isCached()) {
                	out[index].use();
                } else {
                	out[index].init(coords[0] + i, coords[1] + j, regionNoise);
                	regionPool.add(out[index], regionCoords);
                }
                index++;
            }
        return out;
    }
    
    
    private ChunkTile[] makeChunks(int x, int z) {
    	ChunkTile[] map = new ChunkTile[GENSQ];
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
        for(int i = 0; i < GENSIZE; i++)
            for(int j = 0; j < GENSIZE; j++) {                
                map[(j * GENSIZE) + i] = new ChunkTile(x + i - GENHALF0, z + j - GENHALF0);
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
        BiomeType.makeBiomes(map, this, chunkNoise);        
        makeBiomes(x, z, map);
        return map;
    }
    
    
    public ChunkTile findSingleChunk(int x, int z) {
    	ChunkTile out = chunkCache.get(x, z); 
    	if(out == null) {
    		out = makeChunks(x, z)[25];
    		out.getCoords().init(x, z);
    		chunkCache.add(out);
    	}
    	return out;
    }
    
    
    protected int[][] makeNoise(int x, int z, int t) {
        int[][] noise = new int[GENSIZE + 2][GENSIZE + 2];
        for(int i = 1; i < (GENSIZE + 1); i++)
            for(int j = 0; j < (GENSIZE + 2); j++) {
                noise[i][j] = absModulus(chunkNoise.intFor(x + i - GENHALF0, z + j - GENHALF0, 
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
        for(int i = 1; i < (GENSIZE + 1); i++) 
            for(int j = 1; j < (GENSIZE + 1); j++) {
                out[((j - 1) * GENSIZE) + (i - 1)] = refineCell(noise, map, i, j);
            }
        return out;
    }
    
    
    protected int[][] refineNoise2(int[][] noise, ChunkTile[] map) {
        int[][] out = new int[noise.length][noise[0].length];
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
                noise[i][j] = (chunkNoise.doubleFor(x + i - GENHALF0, z + j - GENHALF0, t) / 5) - 0.1;
            }
        return noise;
    }
    
    
    public double[] averageNoise(double[][] noise) {
        double[] out = new double[GENSQ];
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
    
    
    public Biome[] findChunkGrid(int x, int z) {
    	if(biomeCache.contains(x, z)) {
    		return biomeCache.get(x, z).getArray();
    	} else {
    		Biome[] out = makeChunkGrid(makeChunks(x, z));
    		biomeCache.add(new BiomeArray(out, x, z));
    		return out;
    	}
    }
    
    
    public Biome[] makeChunkGrid(ChunkTile in[]) {
    	Biome[] out = new Biome[CSQ];
    	ChunkTile[] tiles = new ChunkTile[9];
    	BiomeBasin[][] basins = new BiomeBasin[3][3];
    	for(int i = 0; i < tiles.length; i++) {
    		int x1 = (i / 3) + 2;
    		int z1 = (i % 3) + 2;
    		tiles[i] = in[(z1 * GENSIZE) + x1];
    		basins[i / 3][i % 3] = new BiomeBasin(
    				(x1 * CSIZE) + (chunkNoise.intFor(tiles[i].x, tiles[i].z, 10) % CSIZE),
    				(z1 * CSIZE) + (chunkNoise.intFor(tiles[i].x, tiles[i].z, 11) % CSIZE),
    				tiles[i].biome, 1.0 + chunkNoise.doubleFor(tiles[i].x, tiles[i].z, 12));    				
    	}
    	for(int i = 0; i < CSIZE; i++)
    		for(int j = 0; j < CSIZE; j++) {
    			out[(j * CSIZE) + i] = Biome.getBiome(BiomeBasin.summateEffect(basins, 48 + i, 48 + j),
    					Biomes.DEFAULT);
    		}
    	return out;
    }
    
    
    private void makeBiomes(int x, int z, ChunkTile[] map) {
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
            tile.biome = tile.rlBiome.specifier.getBiome(tile);
        }
    }
    
    
    public void cleanCaches() {
    	regionPool.cleanup();
    	biomeCache.cleanup();
    	chunkCache.cleanup();
    }
    
}
