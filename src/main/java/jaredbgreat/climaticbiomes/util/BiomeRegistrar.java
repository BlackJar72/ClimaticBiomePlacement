package jaredbgreat.climaticbiomes.util;

import jaredbgreat.climaticbiomes.ClimaticBiomes;
import jaredbgreat.climaticbiomes.biomes.*;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;

public class BiomeRegistrar {

    // Core Biomes
    public static WarmForest      warmForest;
    public static WarmForestHills warmForestHills;
    public static HotForest       tropicalForest;
    public static HotForestHills  tropicalForestHills;
    public static Pinewoods pinewoods;
//    public static Scrub           denseScrub;
//    public static Scrub           dryScrub;
//    public static Scrub           denseScrubHills;
//    public static Scrub           dryScrubHills;
//    public static BiomePlains     coolPlains;
//    public static WindsweptPlains windswept;
//    public static WindsweptPlains coolWindswept;
//    public static CoolPlains      coldPlains;
//    public static Wetland         bog;
//    public static Wetland         carr;
//    public static Wetland         marsh;
//
//    // Extra Mountains
//    public static SubtropicalMountains warmMountain;
//    public static SubtropicalMountains warmMountainTrees;
//    public static TropicalMountains    hotMountain;
//    public static TropicalMountains    hotMountainTrees;
//    public static ActiveVolcano        activeVolcano;
//    public static MontaneForest        coolMontaneForest;
//    public static MontaneForest        warmMontaneForest;
//    public static MontaneForest        mediMontaneForest;
//    public static MontaneForest        montaneJungle;
//
//    // Advanced Rivers
//    public static BiomeRiver coolRiver;
//    public static BiomeRiver river;
//    public static BiomeRiver warmRiver;
//    public static BiomeRiver hotRiver;


    public static void setupBlocks(final RegistryEvent.Register<Biome> event) {
        ClimaticBiomes.getLogger().info("Preparing Biomes for Climatic Biomes");
        makeBiomes();
        registerBiomes(event);
    }


    private static void makeBiomes() {
        warmForest = new WarmForest("subtropical_forest");
        warmForestHills = new WarmForestHills("subtropical_forest_hills");
        tropicalForest = new HotForest("tropical_forest");
        tropicalForestHills = new HotForestHills("tropical_forest_hills");
        pinewoods = new Pinewoods("pinewoods");
    }


    private static void registerBiomes(final RegistryEvent.Register<Biome> event) {
        registerBiome(warmForest, event);
        registerBiome(warmForestHills, event);
        registerBiome(tropicalForest, event);
        registerBiome(tropicalForestHills, event);
        registerBiome(pinewoods, event);
    }


    private static void registerBiome(Biome biome, final RegistryEvent.Register<Biome> event) {
        event.getRegistry().register(biome);
    }


}
