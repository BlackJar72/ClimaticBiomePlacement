package jaredbgreat.climaticbiomes.generation;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import jaredbgreat.climaticbiomes.generation.map.IMapRegistry;
import jaredbgreat.climaticbiomes.generation.map.MapRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.feature.structure.Structure;

// FIXME BROKEN!!!
// TODO: Basically Everything
public class ClimaticBiomeProvider /*extends BiomeProvider*/ {
    private World world;
    private /*static*/ IMapRegistry finder;
    private boolean vanillaCacheValid;


    public ClimaticBiomeProvider(World world) {
        super(/*world.getWorldInfo()*/);
        vanillaCacheValid = true;
        this.world = world;
        finder = new MapRegistry(world.getSeed(), world);
    }



    /*-------------------------------------------------------------------------------------------------*/
    /*                              NEW METHOD FOR THE CURRENT API                                     */
    /*-------------------------------------------------------------------------------------------------*/
/*

    @Override
    public Biome getBiome(int x, int z) {
        //Biome[] biomes = new Biome[1];
        //finder.getUnalignedBiomeGrid(x, z, 1, 1, biomes);
        //return biomes[0];
        return finder.getBiomeChunk(x / 16, z / 16);
    }


    @Override
    public Biome[] getBiomes(int x, int z, int width, int length, boolean cacheFlag) {
        Biome[] biomes = new Biome[width * length];
        finder.getUnalignedBiomeGrid(x, z, width, length, biomes);
        return biomes;
    }


    @Override
    public Set<Biome> getBiomesInSquare(int centerX, int centerZ, int side) {
        Set<Biome> out = new HashSet<Biome>();
        int r = side / 2;
        Biome[] biomes = getBiomes(centerX - r, centerZ - r, side, side, false);
        for(int i = 0; i < biomes.length; i++) {
            out.add(biomes[i]);
        }
        return out;
    }


    @Override
    public boolean hasStructure(Structure<?> structureIn) {
        // FIXME!!!!!
        Break this!
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public Set<BlockState> getSurfaceBlocks() {
        // FIXME!!!!!
        Break this!
        // TODO Auto-generated method stub
        return null;
    }

*/

    /*-------------------------------------------------------------------------------------------------*/
    /*                            OLD PIECES FROM THE 1.12.2 VERSION                                   */
    /*-------------------------------------------------------------------------------------------------*/
/*

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
        barr = this.getBiomes(i, j, i1, j1, false);
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


    private Biome findBiomeAt(int x, int z) {
        Biome[] chunk = new Biome[256];
        finder.getChunkBiomeGen(x / 16, z / 16, chunk);
        return chunk[(chunkModulus(z) * 16) + chunkModulus(x)];
    }


    private int modRight4(int in) {
        return in & 3;
    }


    private int chunkModulus(int in) {
        return in & 0xf;
    }


    public boolean areBiomesViable(int x, int z, int radius, List<Biome> allowed) {
        x = (x - 8) / 16;
        z = (z - 8) / 16;
        int cr = (radius / 16) + 1;
        for(int i = -cr + 1; i < cr; i++)
            for(int j = -cr + 1; j < cr; j++) {
                if(!allowed.contains(finder.getBiomeChunk(x + i, z + j))) {
                    return false;
                }
            }
        return allowed.contains(finder.getBiomeChunk(x, z));
    }


    public void cleanupCache() {
        finder.cleanCaches();
    }

*/
}
