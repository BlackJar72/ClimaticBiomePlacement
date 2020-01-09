package jaredbgreat.climaticbiomes.util;

import jaredbgreat.climaticbiomes.ClimaticBiomes;
import jaredbgreat.climaticbiomes.biomes.*;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import static net.minecraftforge.common.BiomeDictionary.*;
import net.minecraftforge.common.BiomeManager;
import static net.minecraftforge.common.BiomeManager.*;
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
//    // TODO: IceCap
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
        makeForests();
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
        BiomeManager.addSpawnBiome(biome);
    }


    private static void registerBiome(Biome biome, final RegistryEvent.Register<Biome> event, boolean spawn) {
        event.getRegistry().register(biome);
        if(spawn) {
            BiomeManager.addSpawnBiome(biome);
        }
    }


    /*----------------------------------------------------------------------------------------------------------------*/
    /*                          More Specific Methods for General Types                                               */
    /*----------------------------------------------------------------------------------------------------------------*/


    private static void makeForests() {
        warmForest = new WarmForest("subtropical_forest");
        warmForestHills = new WarmForestHills("subtropical_forest_hills");
        tropicalForest = new HotForest("tropical_forest");
        tropicalForestHills = new HotForestHills("tropical_forest_hills");
        pinewoods = new Pinewoods("pinewoods");
        BiomeDictionary.addTypes(warmForest, Type.FOREST, BiomeDictionary.Type.CONIFEROUS);
        BiomeDictionary.addTypes(tropicalForest, BiomeDictionary.Type.FOREST, Type.JUNGLE,
                Type.HOT);
        BiomeDictionary.addTypes(pinewoods, Type.FOREST, Type.WET, Type.HOT,
                Type.CONIFEROUS, Type.SWAMP, Type.SAVANNA);
        BiomeDictionary.addTypes(warmForestHills, Type.FOREST, Type.CONIFEROUS,
                Type.HILLS);
        BiomeDictionary.addTypes(tropicalForestHills, Type.FOREST, Type.JUNGLE,
                Type.HOT, Type.HILLS);
    }


    private static void makeScrub() {

    }


    private static void makeMountains() {

    }


    private static void makePlains() {

    }


    private static void makeSwamps() {

    }


    private static void registerForests(final RegistryEvent.Register<Biome> event) {
        registerBiome(warmForest, event);
        registerBiome(warmForestHills, event);
        registerBiome(tropicalForest, event);
        registerBiome(tropicalForestHills, event);
        registerBiome(pinewoods, event);
    }


    private static void registerScrub(final RegistryEvent.Register<Biome> event) {

    }


    private static void registerMountains(final RegistryEvent.Register<Biome> event) {

    }


    private static void registerPlains(final RegistryEvent.Register<Biome> event) {

    }


    private static void registerSwamps(final RegistryEvent.Register<Biome> event) {

    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /*                        Add Biomes to Default World (is this still needed / effective?)                         */
    /*----------------------------------------------------------------------------------------------------------------*/


    public static void addToVanilla() {
//        if(ConfigHandler.includeForests) {
//            addForests2MC();
//        }
//        addScrub2MC();
//        if(ConfigHandler.includeMountains) {
//            addMountains2MC();
//        }
//        if(ConfigHandler.includePlains) {
//            addPlains2MC();
//        }
//        if(ConfigHandler.includeSwamps) {
//            addSwamps2MC();
//        }
    }


    private static void addForests2MC() {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(warmForest, 5));
        BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(tropicalForest, 5));
        BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(warmForestHills, 5));
        BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(tropicalForestHills, 5));
        BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(pinewoods, 5));
    }


    private static void addScrub2MC() {
//        BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(denseScrub, 5));
//        BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(denseScrubHills, 5));
//        BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(dryScrub, 5));
//        BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(dryScrubHills, 5));
    }


    private static void addMountains2MC() {
//        BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(warmMountain, 5));
//        BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(warmMountainTrees, 5));
//        BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(hotMountain, 5));
//        BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(hotMountainTrees, 5));
//        BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(warmMontaneForest, 5));
//        BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(montaneJungle, 5));
//        BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(mediMontaneForest, 5));
//        BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(coolMontaneForest, 10));
//        if(ConfigHandler.includeVolcano) {
//            BiomeManager.addBiome(BiomeType.ICY, new BiomeEntry(activeVolcano, 1));
//            BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(activeVolcano, 1));
//            BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(activeVolcano, 1));
//            BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(activeVolcano, 1));
//        }
    }


    private static void addPlains2MC() {
//        BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(windswept, 5));
//        BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(coolPlains, 5));
//        BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(coldPlains, 5));
//        BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(coolWindswept, 5));
    }


    private static void addSwamps2MC() {
//        BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(marsh, 5));
//        BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(bog, 5));
//        BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(carr, 10));
    }

}
