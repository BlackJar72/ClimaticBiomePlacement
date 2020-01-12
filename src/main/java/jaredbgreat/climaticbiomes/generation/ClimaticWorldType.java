package jaredbgreat.climaticbiomes.generation;

import jaredbgreat.climaticbiomes.util.BiomeRegistrar;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;

// TODO: Basically Everything
public class ClimaticWorldType extends WorldType {
    public static final String NAME = "climatic_bp";

    public ClimaticWorldType() {
        super(NAME);
    }


    @Override
    public ChunkGenerator<?> createChunkGenerator(World world) {
        OverworldGenSettings settings = new OverworldGenSettings();
        //SingleBiomeProviderSettings single = new SingleBiomeProviderSettings();
        // TODO: Change next line to biome being tested

        return new OverworldChunkGenerator(world, new ClimaticBiomeProvider(world), settings);
    }
}
