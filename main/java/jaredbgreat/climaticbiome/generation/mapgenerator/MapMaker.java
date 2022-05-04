/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaredbgreat.climaticbiome.generation.mapgenerator;

import static jaredbgreat.climaticbiome.util.SpatialHash.absModulus;

import java.util.ArrayList;
import java.util.Arrays;

import jaredbgreat.climaticbiome.configuration.ClimaticWorldSettings;
import jaredbgreat.climaticbiome.generation.biome.BiomeClimateTable;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.cache.Cache;
import jaredbgreat.climaticbiome.generation.cache.Coords;
import jaredbgreat.climaticbiome.generation.cache.MutableCoords;
import jaredbgreat.climaticbiome.generation.map.IRegionMap;
import jaredbgreat.climaticbiome.util.Debug;
import jaredbgreat.climaticbiome.util.NoiseMap2D;
import jaredbgreat.climaticbiome.util.SpatialHash;
import jaredbgreat.climaticbiome.util.SpatialHash.RandomAt;
import net.minecraft.world.World;



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
    
    public final int rend;
    
    ClimaticWorldSettings settings;
    public static int sporaticMountains;

    private final Cache<Region> regionCache = new Cache(32);
    
    private MutableCoords regionCoords = new MutableCoords(); 
    private MutableCoords chunkCoords = new MutableCoords(); 
    
    private ClimateNode[] height;
    private BiomeBasin[][] subbiomes;
    
    public final SpatialHash chunkNoise;
    public final SpatialHash regionNoise;
    public final SpatialHash biomeNoise;
    
    public final SizeScale scale;
    
    private int xoff;
    private int zoff;    

    private ChunkTile[] premap;
    
    private double[][] faulty;
    
    
    public MapMaker(SpatialHash chunkNoise, SpatialHash regionNoise, 
    			SpatialHash biomeNoise, ClimaticWorldSettings settings) {
        this.chunkNoise  = chunkNoise;
        this.regionNoise = regionNoise;
        this.biomeNoise  = biomeNoise;
        this.settings    = settings;
        scale = settings.regionSize;
        specifier = BiomeClimateTable.getClimateTable(settings);
        rend = (RSIZE * settings.regionSize.whole) - 2;
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
                out[index] = regionCache.get(regionCoords);
                if(out[index] == null) {
                	out[index] = new Region(x + i, z + j, 
                                regionNoise, settings);
                        regionCache.add(out[index]);
                } else {
                        out[index].use();
                }
                	index++;
                }
        return out;
    }
    
    
    public void generate(IRegionMap datamap, World world, boolean altChunks) {
        Coords coords = datamap.getCoords();
        xoff = ((coords.getX() * 256) - 128) * scale.whole;
        zoff = ((coords.getZ() * 256) - 128) * scale.whole;
		Region[] regions = findRegions(coords.getX(), coords.getZ());
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
        
        SpatialHash random = chunkNoise;
        makeLandmass(basinAr, coords.getX(), coords.getZ(), random);
        
        NoiseMap2D climateMaker 
                = new NoiseMap2D(chunkNoise, RSIZE * scale.whole, 
                        32 * scale.whole, 2.0, 
                        coords.getX(), coords.getZ());
        
        double[] doubleNoise;
        double[][] climateNoise = climateMaker.process(128);
        doubleNoise = averageNoise(premap, makeDoubleNoise(random, 1));
        for(int i = 0; i < premap.length; i++) {
            premap[i].temp = (int)Math.max(Math.min(
                    ClimateNode.summateEffect(tempAr, premap[i], 
                    doubleNoise[i], scale.inv) + 
                    climateNoise[i / (RSIZE * scale.whole)]
                            [i % (RSIZE * scale.whole)], 24), 0);
        }

        climateNoise = climateMaker.process(129);
        doubleNoise = averageNoise(premap, makeDoubleNoise(random, 2));
        for(int i = 0; i < premap.length; i++) {
            premap[i].wet = (int)Math.max(Math.min(ClimateNode.summateEffect(wetAr, premap[i], 
                    doubleNoise[i], scale.inv) + 
                    climateNoise[i / (RSIZE * scale.whole)]
                            [i % (RSIZE * scale.whole)], 9), 0);
        }
        
        int[] noise = refineNoise10(makeNoise(random, 4), premap);
        for(int i = 0; i < premap.length; i++) {
            premap[i].noiseVal = noise[i];
        }
        
        if(settings.hasRivers && (settings.mode < 3)) {
	        RiverMaker rm = new RiverMaker(this, random.longFor(coords.getX(), coords.getZ(), 16), 
	                regions[4], coords.getX(), coords.getZ(), scale);
	        rm.build();
        }
        
        if(settings.bigMountains) {
        	sporaticMountains = 17;
        } else {
        	sporaticMountains = 5;
        }
        
        if(settings.forceWhole) {
        	makeBiomesWhole(premap, random.getRandomAt(coords.getX(), coords.getZ(), 3));
        } else {
        	makeBiomes(premap, random.getRandomAt(coords.getX(), coords.getZ(), 3));
        }
        int start = (RSIZE * scale.whole * 2) + 2;
        int end = premap.length - start;
        for(int i = start; i < end; i++) {
        	thinBeach(premap[i]);
        }
        for(int i = start; i < end; i++) {
        	makeCoast(premap[i]);
        }
        if(settings.bigMountains) {
        	makeFaults(coords);
        }
        for(int i = 0; i < premap.length; i++) {
        	premap[i].rlBiome = (int)specifier.getBiome(premap[i]);
        	datamap.setBiomeExpress(premap[i].rlBiome, i);
        }
        if(altChunks) {
	        TerrainPrimer terrainPrimer = new TerrainPrimer();
	        terrainPrimer.processTerrain(premap, datamap, climateMaker, scale, settings);
        }
    }
    
    
    private void makeLandmass(BasinNode[] basins, int cx, int cz, SpatialHash random) {
        LandmassMaker maker;
        switch(settings.mode) {
        case 1:
        case 2:
        	maker = new LandmassMaker(cx, cz,
                random, basins, scale, RSIZE, xoff, zoff);
        	break;
        case 3:
        	maker = new WaterworldMaker(cx, cz,
                random, basins, scale, RSIZE, xoff, zoff);
        	break;
        case 4: 
        	maker = new SurvivalIslandMaker(cx, cz,
                    random, basins, scale, RSIZE, xoff, zoff);
            	break;
        default:
        	maker = new LandmassMaker(cx, cz,
                random, basins, scale, RSIZE, xoff, zoff);
        	break;
        }
        premap = maker.generate(settings);
    }
    
    
    public ChunkTile getTile(ChunkTile[] premap, int x, int y) {
        int index = (x * RSIZE) + y;
        if(index >= premap.length) {
            return null;
        } else {
            return premap[(x * RSIZE) + y];
        }
    }
    
    
    protected int[][] makeNoise(SpatialHash random, int t) {
        int[][] noise = new int[RSIZE * scale.whole + 2][RSIZE * scale.whole + 2];
        for(int i = 0; i < (RSIZE * scale.whole + 2); i++)
            for(int j = 0; j < (RSIZE * scale.whole + 2); j++) {
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
        for(int i = 1; i < (RSIZE * scale.whole + 1); i++) 
            for(int j = 1; j < (RSIZE * scale.whole + 1); j++) {
                out[((j - 1) * RSIZE * scale.whole) + (i - 1)] = refineCell(premap, noise, i, j);
            }
        return out;
    }
    
    
    protected int[][] refineNoise2(ChunkTile[] premap, int[][] noise) {
        int[][] out = new int[noise.length][noise[0].length];
        // Could be better optimized, but this is a test of the gui and api
        for(int i = 1; i < (RSIZE * scale.whole + 1); i++) 
            for(int j = 1; j < (RSIZE * scale.whole + 1); j++) {
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
        if(sum < premap[((y -1) * RSIZE * scale.whole) + (x - 1)].val) {
            return 0;
        } else {
            return 1;
        }
    }
    
    
    private double[][] makeDoubleNoise(SpatialHash random, int t) {
        double[][] noise = new double[RSIZE * scale.whole + 4][RSIZE * scale.whole + 4];
        for(int i = 0; i < (RSIZE * scale.whole + 2); i++)
            for(int j = 0; j < (RSIZE * scale.whole + 2); j++) {
                noise[i][j] = (random.doubleFor(i + xoff, j + zoff, t) / 5) - 0.1;
            }
        return noise;
    }
    
    
    public double[] averageNoise(ChunkTile[] premap, double[][] noise) {
        double[] out = new double[premap.length];
        for(int i = 2; i < (RSIZE * scale.whole + 2); i++) 
            for(int j = 2; j < (RSIZE * scale.whole + 2); j++) {
                out[((j - 2) * RSIZE * scale.whole) + (i - 2)] = averageNoise(noise, i, j);
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
    
    
    public void makeBiomes(ChunkTile[] premap, RandomAt random) {
        int size = settings.biomeSize;
        int across = (RSIZE * scale.whole) / size;
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
            BiomeBasin.summateEffect(subbiomes, tile);
            if(((tile.getBiomeSeed() % sporaticMountains) == 0) 
            			&& (tile.rlBiome != 0)) {
            	tile.setMountainous();
            }
            tile.nextBiomeSeed();
        }
    }
    
    
    public void makeBiomesWhole(ChunkTile[] premap, RandomAt random) {
        int size = settings.biomeSize;
        int across = (RSIZE * scale.whole) / size;
        int down = across;
        subbiomes = new BiomeBasin[across][down];
        for(int i = 0; i < across; i++) 
            for(int j = 0; j < down; j++) {
                subbiomes[i][j]
                        = new BiomeBasin((i * size) + random.nextInt(size),
                                    (j * size) + random.nextInt(size),
                                    random.nextInt() | 0xff000000,
                                    1.0 + random.nextDouble(), this);
            }
        for (ChunkTile tile : premap) {
        	ChunkTile basis = BiomeBasin.summateForCenter(subbiomes, tile);
            tile.biomeSeed = basis.biomeSeed;
            tile.wet  = basis.wet;
            tile.temp = basis.temp;
            if(((tile.getBiomeSeed() % sporaticMountains) == 0) 
        				&& (tile.rlBiome != 0)) { 
            	tile.setMountainous();
            }
            tile.nextBiomeSeed();
        }
    }
    
    
    private int[] refineBasicNoise(int[][] noise, ChunkTile[] premap) {
        int[] out = new int[premap.length];
        // Could be better optimized, but this is a test of the gui and api
        for(int i = 1; i < (RSIZE * scale.whole + 1); i++) 
            for(int j = 1; j < (RSIZE * scale.whole + 1); j++) {
                out[((j - 1) * RSIZE * scale.whole) + (i - 1)] = refineBasicCell(noise, i, j);
            }
        return out;
    }
    
    
    private int refineBasicCell(int[][] noise, int x, int y) {
        int sum = 0;
        // Yes, I include the cell itself -- its simpler and works for me
        for(int i = x - 1; i <= x + 1; i++) 
            for(int j = y - 1; j <= y + 1; j++) {
                sum += noise[i][j];
            }
        return sum / 5;
    }
    
    
    private int[] refineNoise10(int[][] noise, ChunkTile[] premap) {
        int[] out = new int[premap.length];
        // Could be better optimized, but this is a test of the gui and api
        for(int i = 1; i < (RSIZE * scale.whole + 1); i++) 
            for(int j = 1; j < (RSIZE * scale.whole + 1); j++) {
                out[((j - 1) * RSIZE * scale.whole) + (i - 1)] = refineCell10(noise, i, j);
            }
        return out;
    }
    
    
    private int refineCell10(int[][] noise, int x, int y) {
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
    
    
    void thinBeach(ChunkTile t) {
        if(!t.beach) return;
        int oceans = 0;
        if(settings.extraBeaches) {
	        for(int i = -2; i < 3; i++) 
	            for(int j = -2; j < 3; j++) {
	                ChunkTile x = premap[((t.getX() + i) * RSIZE * scale.whole) + t.getZ() + j];
	                if(notLand(x)) {
	                    oceans++;
	                }
	            }
        } else {
	        for(int i = -1; i < 2; i++) 
	            for(int j = -1; j < 2; j++) {
	                ChunkTile x = premap[((t.getX() + i) * RSIZE * scale.whole) + t.getZ() + j];
	                if(notLand(x)) {
	                    oceans++;
	                }
	            }
	        }
        if(oceans < 1) {
        	t.beach = false;
        	return;
        }
    }
    
    
    void makeCoast(ChunkTile t) {
        if(!notLand(t) || (t.getX() < 1) || (t.getX() > rend)
                       || (t.getZ() < 1) || (t.getZ() > rend)) return;
        int beaches = 0;
        for(int i = -2; i < 3; i++) 
            for(int j = -2; j < 3; j++) {
                ChunkTile x = premap[((t.getX() + i) * RSIZE * scale.whole) + t.getZ() + j];
                if(!notLand(x) && x.beach) {
                    beaches++;
                }
            }
        if(beaches < 1) return;
        t.beach = t.getNoise() < (beaches + 4 
                - ((t.getBiomeSeed() >> 14) & 1)
                + ((t.getBiomeSeed() >> 13) & 1));
    }
    
    
    public void makeFaults(Coords coords) {
    	NoiseMap2D faultMaker 
                = new NoiseMap2D(chunkNoise, RSIZE * scale.whole, 
                        128 * scale.whole, 1.0, 
                        coords.getX(), coords.getZ());
        faulty = faultMaker.process(130);
        for(int i = 0; i < faulty.length; i++)
            for(int j = 0; j < faulty[i].length; j++) {
                faulty[i][j] = faulty[i][j] * faulty[i][j];
            if(faulty[i][j] < 0.001) {  
            		ChunkTile t = premap[(j * RSIZE * scale.whole) + i]; 
                    if(t.rlBiome != 0) {
                        float hp = (float)(Math.max(0, (0.001 - faulty[i][j])) * 1000f);
                        t.terrainType = TerrainType.MOUNTIANOUS;
	                    t.height += hp; 
	                    t.scale += (hp * 0.2f);
                    }
            }
        }
    }
    
    
    public boolean tileIndexIsBad(int x, int z) {
    	int index = (x * RSIZE * scale.whole) + z;
    	return ((index < 0) || (index >= premap.length));
    }
    
    
    public ChunkTile getTile(int x, int y) {
        int index = (x * RSIZE * scale.whole) + y;
        return premap[index];
    }
    
    
    public int getXoff() {
    	return xoff;
    }
    
    
    public int getZoff() {
    	return zoff;
    }
    
    
    public ClimaticWorldSettings getSettings() {
    	return settings;
    }
    
    
}
