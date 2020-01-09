package jaredbgreat.climaticbiomes.testing;

import jaredbgreat.climaticbiomes.util.BiomeRegistrar;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;

public class TestWorldType extends WorldType {


    public TestWorldType() {
        super("Testing");
    }


    @Override
    public ChunkGenerator<?> createChunkGenerator(World world) {
        OverworldGenSettings settings = new OverworldGenSettings();
        SingleBiomeProviderSettings single = new SingleBiomeProviderSettings();
        // TODO: Change next line to biome being tested
        single.setBiome(BiomeRegistrar.windswept);
        return new OverworldChunkGenerator(world, new SingleBiomeProvider(single), settings);
    }
}
