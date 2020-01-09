package jaredbgreat.climaticbiomes.util;

import jaredbgreat.climaticbiomes.ClimaticBiomes;
import jaredbgreat.climaticbiomes.Info;
import jaredbgreat.climaticbiomes.biomes.WarmForest;
import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;

public class BiomeRegistrar {

    // Biomes
    public static WarmForest subtropicalForest;


    public static void setupBlocks(final RegistryEvent.Register<Biome> event) {
        ClimaticBiomes.getLogger().info("Preparing Biomes for Climatic Biomes");
        makeBiomes();
        registerBiomes(event);
    }


    private static void makeBiomes() {
        subtropicalForest = new WarmForest("subtropical_forest");
    }


    private static void registerBiomes(final RegistryEvent.Register<Biome> event) {
        registerBiome(subtropicalForest, event);
    }


    private static void registerBiome(Biome biome, final RegistryEvent.Register<Biome> event) {
        event.getRegistry().register(biome);
    }


}
