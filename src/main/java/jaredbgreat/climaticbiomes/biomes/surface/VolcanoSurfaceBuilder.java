package jaredbgreat.climaticbiomes.biomes.surface;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

import jaredbgreat.climaticbiomes.util.BlockRegistrar;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class VolcanoSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {
    BlockState ASH;
    BlockState BASALT;
    BlockState OBSIDIAN;
    SurfaceBuilderConfig PURE_BASALT;
    SurfaceBuilderConfig ASH_BASALT;
    SurfaceBuilderConfig PURE_ASH;


    public VolcanoSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> crap) {
        super(crap);
        ASH = BlockRegistrar.blockVolcanicAsh.getDefaultState();
        BASALT = BlockRegistrar.blockBasalt.getDefaultState();
        OBSIDIAN = Blocks.OBSIDIAN.getDefaultState();
        PURE_BASALT = new SurfaceBuilderConfig(BASALT, BASALT, ASH);
        ASH_BASALT = new SurfaceBuilderConfig(ASH, BASALT, ASH);
        PURE_ASH = new SurfaceBuilderConfig(ASH, ASH, ASH);
    }


    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight,
                             double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel,
                             long seed, SurfaceBuilderConfig config) {
        if (noise > 2.5d) {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock,
                    defaultFluid, seaLevel, seed, PURE_ASH);
        } else if (noise > 1.25d) {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock,
                    defaultFluid, seaLevel, seed, ASH_BASALT);
        } else {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock,
                    defaultFluid, seaLevel, seed, PURE_BASALT);
        }
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        int k1 = x & 15;
        int l1 = z & 15;
        int ty = seaLevel + (int)(noise * 4) - 2;

        for (int j1 = 255; j1 >= ty; --j1) {
            blockpos$mutableblockpos.setPos(l1, j1, k1);
            if((chunkIn.getBlockState(blockpos$mutableblockpos).getBlock() == Blocks.STONE)) {
                chunkIn.setBlockState(blockpos$mutableblockpos, BASALT, false);
            }
        }
    }
}