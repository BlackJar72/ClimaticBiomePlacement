package jaredbgreat.climaticbiomes.generation;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import jaredbgreat.climaticbiomes.generation.map.IMapRegistry;
import jaredbgreat.climaticbiomes.generation.map.MapRegistry;
import jaredbgreat.climaticbiomes.util.BiomeRegistrar;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.feature.structure.Structure;


public class ClimaticBiomeProvider extends BiomeProvider {
    private static Set<Biome> biomes = new HashSet<>();
    private MapRegistry finder;


    public ClimaticBiomeProvider(World world) {
        super();
        finder = new MapRegistry(world.getSeed(), world);
        if(biomes.isEmpty()) {
            for (Biome biome : Registry.BIOME) {
                biomes.add(biome);
            }
        }
    }



    /*-------------------------------------------------------------------------------------------------*/
    /*                              NEW METHOD FOR THE CURRENT API                                     */
    /*-------------------------------------------------------------------------------------------------*/


    @Override
    public Biome getBiome(int x, int z) {
        return finder.getBiomeAt(x, z);
    }


    @Override
    public Biome[] getBiomes(int x, int z, int width, int length, boolean cacheFlag) {
        Biome[] biomes = new Biome[width * length];
        finder.getUnalignedBiomeGrid(x, z, width, length, biomes);
        return biomes;
    }


    public Biome[] getChunkBiomes(int x, int z, boolean cacheFlag) {
        Biome[] biomes = new Biome[256];
        return finder.getChunkBiomeGrid(x, z, biomes);
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
    @Nullable
    public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random) {
        int i = x - range >> 2;
        int j = z - range >> 2;
        int k = x + range >> 2;
        int l = z + range >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        Biome[] abiome = getBiomes(i, j, i1, j1, false);
        BlockPos blockpos = null;
        int k1 = 0;

        for(int l1 = 0; l1 < i1 * j1; ++l1) {
            int i2 = i + l1 % i1 << 2;
            int j2 = j + l1 / i1 << 2;
            if (biomes.contains(abiome[l1])) {
                if (blockpos == null || random.nextInt(k1 + 1) == 0) {
                    blockpos = new BlockPos(i2, 0, j2);
                }

                ++k1;
            }
        }

        return blockpos;
    }


    @Override
    public boolean hasStructure(Structure<?> structureIn) {
        return hasStructureCache.computeIfAbsent(structureIn, (structure) -> {
            for(Biome biome : biomes) {
                if(biome.hasStructure(structure)) {
                    return true;
                }
            }
            return false;
        });
    }


    // What does this even do?!
    @Override
    public Set<BlockState> getSurfaceBlocks() {
        if (this.topBlocksCache.isEmpty()) {
            for(Biome biome : this.biomes) {
                this.topBlocksCache.add(biome.getSurfaceBuilderConfig().getTop());
            }
        }

        return this.topBlocksCache;
    }



    /*-------------------------------------------------------------------------------------------------*/
    /*                            OLD PIECES FROM THE 1.12.2 VERSION                                   */
    /*-------------------------------------------------------------------------------------------------*/


    private int chunkModulus(int in) {
        return in & 0xf;
    }


}
