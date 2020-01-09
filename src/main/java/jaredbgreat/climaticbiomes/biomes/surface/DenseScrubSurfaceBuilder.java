package jaredbgreat.climaticbiomes.biomes.surface;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;
import java.util.function.Function;

public class DenseScrubSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {

    public DenseScrubSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> p_i51305_1_) {
        super(p_i51305_1_);
    }


    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight,
                             double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel,
                             long seed, SurfaceBuilderConfig config) {
        if (noise > 1.25d) {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock,
                    defaultFluid, seaLevel, seed, SurfaceBuilder.CORASE_DIRT_DIRT_GRAVEL_CONFIG);
        } else {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock,
                    defaultFluid, seaLevel, seed, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG);
        }

    }

}
