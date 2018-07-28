package jaredbgreat.climaticbiome.generation;

import jaredbgreat.climaticbiome.generation.chunk.BasinNode;
import jaredbgreat.climaticbiome.generation.chunk.BiomeBasin;
import jaredbgreat.climaticbiome.generation.chunk.BiomeFinder;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.Region;
import jaredbgreat.climaticbiome.generation.chunk.SpatialNoise;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.layer.IntCache;

public class BetterBiomeProvider extends BiomeProvider {
	private World world;
	private BiomeFinder finder;
	
	
	public BetterBiomeProvider(World world) {		
		super(/*world.getWorldInfo()*/);		
		this.world = world;
		finder = new BiomeFinder(world.getSeed());
	}
	

    @Nullable
    @Override
    public BlockPos findBiomePosition(int x, int z, int range, 
    			List<Biome> biomes, Random random) {
        int i = x - range >> 2;
        int j = z - range >> 2;
        int k = x + range >> 2;
        int l = z + range >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        Biome[] barr = new Biome[i1 * j1];
        barr = this.getBiomes(barr, i, j, i1, j1);
        BlockPos blockpos = null;
        int k1 = 0;

        for (int l1 = 0; l1 < i1 * j1; ++l1) {
            int i2 = i + l1 % i1 << 2;
            int j2 = j + l1 / i1 << 2;
            Biome biome = barr[l1];

            if (biomes.contains(biome) && (blockpos == null || random.nextInt(k1 + 1) == 0)) {
                blockpos = new BlockPos(i2, 0, j2);
                ++k1;
            }
        }
        return blockpos;
    }


    @Override
    public Biome[] getBiomesForGeneration(Biome[] biomes, int x, int z, int width, int height) {
        if(biomes == null || biomes.length < width * height) {
            biomes = new Biome[width * height];
        }
    	for(int i = 0; i < width; i++) 
    		for(int j = 0; j < height; j++) {
    			biomes[(j * 10) + i] = Biome.getBiome(getIDForCoords(x + i, z + j), 
    					Biomes.DEFAULT);
    		}
        return biomes;
    }


    @Override
    public Biome[] getBiomes(@Nullable Biome[] in, int x, int z, int width, int depth, boolean cacheFlag) {
    	IntCache.resetIntCache();
    	Biome[] out;
        if((in == null) || (in.length < (width * depth))) {
            in = new Biome[width * depth];
        }
        if(in.length == 256) {
    		out = finder.getChunkGrid(finder.makeChunk(x / 16, z / 16));
    		System.arraycopy(out, 0, in, 0, 256); 
    	} else {
    		out = finder.getChunkGrid((x / 16), (z / 16), width, depth);
    		System.arraycopy(out, 0, in, 0, width * depth); 
    	}
        return in;
    }
    
    
    private int getIDForCoords(int x, int z) {
    	return Biome.getIdForBiome(getBiome(new BlockPos(x * 4, 64, z * 4)));
    }
    
    
    private int modRight(int in) {
    	in %= 4;
    	if(in < 0) {
    		in += 4;
    	}
    	return in;
    }
    
    
    private int chunkModulus(int in) {
    	in %= 16;
    	if(in < 0) {
    		in += 16;
    	}
    	return in;
    }

    
    @Override
    public boolean areBiomesViable(int x, int z, int radius, List<Biome> allowed) {
        return allowed.contains(Biome.getBiome(finder.getChunk((x / 16), (z / 16)).getBiome()));
    }

    
    public void cleanupCache() {
        super.cleanupCache();
        finder.cleanCaches();
    }

}
