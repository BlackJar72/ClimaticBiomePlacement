package jaredbgreat.climaticbiome.generation;

import jaredbgreat.climaticbiome.generation.map.IMapRegistry;
import jaredbgreat.climaticbiome.generation.map.MapRegistry;
import jaredbgreat.climaticbiome.generation.map.NewMapRegistry;
import jaredbgreat.climaticbiome.util.Debug;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.structure.MapGenVillage;


public class ClimaticBiomeProvider extends BiomeProvider {
        private World world;
        private IMapRegistry finder;
        private boolean vanillaCacheValid;
        private boolean altChunks;
        
        
        public ClimaticBiomeProvider(World world, boolean altChunks) {             
                super(/*world.getWorldInfo()*/);
                vanillaCacheValid = true;
                this.world = world;
                {
	                if(net.minecraftforge.fml.common.Loader.isModLoaded("jeid")) {
	                	finder = new NewMapRegistry(world.getSeed(), world, altChunks);
	                } else {
	                	finder = new MapRegistry(world.getSeed(), world, altChunks);
	                }
                }
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
                        biomes[(j * width) + i] = findBiomeAt((x + i) * 4, (z + j) * 4);
                }
        return biomes;
    }


    public float[][] getdataForGeneration(float[][] biomes, int x, int z, int width, int height) {
        if(biomes == null || biomes.length < width * height) {
            biomes = new float[width * height][];
        }
        for(int i = 0; i < width; i++) 
                for(int j = 0; j < height; j++) {
                        //System.err.println(findBiomeAt((x + i) * 4, (z + j) * 4));
                        biomes[(j * width) + i] = finder.getHeightData((x + i) * 4, (z + j) * 4);
                }
        return biomes;
    }
    
    
    private Biome findBiomeAt(int x, int z) {
        Biome[] chunk = new Biome[256];
        finder.getChunkBiomeGen(x / 16, z / 16, chunk);
        return chunk[(chunkModulus(z) * 16) + chunkModulus(x)];
    }
    

    @Override
    public Biome[] getBiomes(@Nullable Biome[] in, int x, int z, int width, int depth, boolean cacheFlag) {
        //IntCache.resetIntCache();
        if((in == null) || (in.length < (width * depth))) {
            in = new Biome[width * depth];
        }
        // FIXME?? This should probably check more strictly if its an exact whole chunk...?
        if(in.length == 256) {
                finder.getChunkBiomeGrid(x / 16, z / 16, in);
        } else {
                finder.getUnalignedBiomeGrid(x, z, width, depth, in);
        }
        return in;
    }
    
    
    private int getIDForCoords(int x, int z) {
        return Biome.getIdForBiome(getBiome(new BlockPos(x * 4, 64, z * 4)));
    }
    
    
    private int modRight4(int in) {
        return in & 3;
    }
    
    
    private int chunkModulus(int in) {
        return in & 0xf;
    }

    
    @Override
    public boolean areBiomesViable(int x, int z, int radius, List<Biome> allowed) {
        x = (x - 8) / 16;
        z = (z - 8) / 16;
        if(radius > 0) {
	        int cr = radius >> 2;
            int i1 = x - cr;
            int j1 = x - cr;
            int i2 = x + cr;
            int j2 = x + cr;
	        for(int i = i1 + 1; i < i2; i++)
	                for(int j = j1; j < j2; j++) {
	                        if(!allowed.contains(finder.getBiomeChunk(x + i, z + j))) {
	                                return false;
	                        }
	                }
        }
        return allowed.contains(finder.getBiomeChunk(x, z));
    }
    
    
    public float[] getTerrainBiomeGen(int x, int z) {
    	return finder.getTerrainBiomeGen(x, z, new float[512]);
    }

    
    public void cleanupCache() {
        if(vanillaCacheValid) try {
                super.cleanupCache();
        } catch (Exception e) {
                // This is hacky and only testing will tell
                // if I needed to call the super class method;
                // i.e., if not doing so will cause memory
                // problems due to Minecraft creating such data
                // on its own.
                vanillaCacheValid = false;
                System.err.println("Error cleaning up vanilla biome cache; "
                                + "should I be trying to do that?!"); 
        }
        finder.cleanCaches();
    }
    
    
    public boolean isAltChunks() {
    	return altChunks;
    }

    


}
