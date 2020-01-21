package jaredbgreat.climaticbiomes.util;

import jaredbgreat.climaticbiomes.ClimaticBiomes;
import jaredbgreat.climaticbiomes.biomes.*;
import jaredbgreat.climaticbiomes.biomes.technical.Coast;
import jaredbgreat.climaticbiomes.biomes.technical.FrozenCoast;
import jaredbgreat.climaticbiomes.biomes.technical.SnowyCoast;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import static net.minecraftforge.common.BiomeDictionary.*;
import net.minecraftforge.common.BiomeManager;
import static net.minecraftforge.common.BiomeManager.*;
import net.minecraftforge.event.RegistryEvent;

public class BiomeRegistrar {

    // Core Biomes
    public static WarmForest          warmForest;
    public static WarmForestHills     warmForestHills;
    public static HotForest           tropicalForest;
    public static HotForestHills      tropicalForestHills;
    public static Pinewoods           pinewoods;
    public static BambooForest        bambooForest;
    public static DenseScrub          denseScrub;
    public static DryScrub            dryScrub;
    public static DenseScrubHills     denseScrubHills;
    public static DryScrubHills       dryScrubHills;
    public static CoolPlains          coolPlains;
    public static WindsweptPlains     windswept;
    public static CoolWindsweptPlains coolWindswept;
    public static ColdPlains          coldPlains;
    public static Bog                 bog;
    public static Carr                carr;
    public static Marsh               marsh;

    // Extra Mountains
    public static WarmMountains      warmMountain;
    public static WarmMountainsTrees warmMountainTrees;
    public static HotMountains       hotMountain;
    public static HotMountainsTrees  hotMountainTrees;
    public static CoolMontaneForest  coolMontaneForest;
    public static WarmMontaneForest  warmMontaneForest;
    public static MontaneJungle      montaneJungle;

    // Water
    public static IceCap      iceCap;
    public static IceRiver    iceRiver;
    public static CoolRiver   coolRiver;
    public static River       river;
    public static WarmRiver   warmRiver;
    public static HotRiver    hotRiver;
    public static Coast       coast;
    public static FrozenCoast frozenCoast;
    public static SnowyCoast  coldCoast;



    public static void setupBlocks(final RegistryEvent.Register<Biome> event) {
        ClimaticBiomes.getLogger().info("Preparing Biomes for Climatic Biomes");
        makeBiomes();
        registerBiomes(event);
    }


    private static void makeBiomes() {
        makeForests();
        makeScrub();
        makeMountains();
        makePlains();
        makeSwamps();
        makeRivers();
        makeOceans();
    }


    private static void registerBiomes(final RegistryEvent.Register<Biome> event) {
        registerForests(event);
        registerScrub(event);
        registerMountains(event);
        registerPlains(event);
        registerSwamps(event);
        registerRivers(event);
        registerOceans(event);
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
        bambooForest = new BambooForest("bamboo_forest");
    }


    private static void makeScrub() {
        denseScrub = new DenseScrub("dense_scrub");
        dryScrub = new DryScrub("dry_scrub");
        denseScrubHills = new DenseScrubHills("dense_scrub_hills");
        dryScrubHills = new DryScrubHills("dry_scrub_hills");
    }


    private static void makeMountains() {
        warmMountain = new WarmMountains("subtropical_mountains");
        warmMountainTrees = new WarmMountainsTrees("subtropical_wooded_mountains");
        hotMountain = new HotMountains("tropical_mountains");
        hotMountainTrees = new HotMountainsTrees("tropical_wooded_mountains");
        coolMontaneForest = new CoolMontaneForest("cool_montane_forest");
        warmMontaneForest = new WarmMontaneForest("warm_montane_forest");
        montaneJungle = new MontaneJungle("montane_jungle");
    }


    private static void makePlains() {
        coolPlains = new CoolPlains("cool_plains");
        windswept = new WindsweptPlains("windswept_plains");
        coolWindswept = new CoolWindsweptPlains("cool_windswept_plains");
        coldPlains = new ColdPlains("cold_plains");
    }


    private static void makeSwamps() {
        bog = new Bog("bog");
        carr = new Carr("carr");
        marsh = new Marsh("marsh");
    }


    private static void makeRivers() {
        iceCap = new IceCap("ice_cap");
        iceRiver = new IceRiver("frozen_river");
        coolRiver = new CoolRiver("cool_river");
        river = new River("temporate_river");
        warmRiver = new WarmRiver("warm_river");
        hotRiver = new HotRiver("tropical_river");
    }


    private static void makeOceans() {
        coast = new Coast("coastal_waters");
        frozenCoast = new FrozenCoast("frozen_coastal_waters");
        coldCoast = new SnowyCoast("cold_coastal_waters");
    }


    private static void registerForests(final RegistryEvent.Register<Biome> event) {
        registerBiome(warmForest, event);
        registerBiome(warmForestHills, event);
        registerBiome(tropicalForest, event);
        registerBiome(tropicalForestHills, event);
        registerBiome(pinewoods, event);
        registerBiome(bambooForest, event, false);
        BiomeDictionary.addTypes(warmForest, Type.FOREST, Type.CONIFEROUS, Type.OVERWORLD);
        BiomeDictionary.addTypes(tropicalForest, BiomeDictionary.Type.FOREST,
                Type.HOT, Type.OVERWORLD);
        BiomeDictionary.addTypes(pinewoods, Type.FOREST, Type.WET, Type.HOT,
                Type.CONIFEROUS, Type.SWAMP, Type.LUSH, Type.OVERWORLD);
        BiomeDictionary.addTypes(warmForestHills, Type.FOREST, Type.CONIFEROUS,
                Type.HILLS, Type.OVERWORLD);
        BiomeDictionary.addTypes(tropicalForestHills, Type.FOREST,
                Type.HOT, Type.HILLS, Type.OVERWORLD);
        BiomeDictionary.addTypes(bambooForest, Type.FOREST, Type.WET,
                Type.LUSH, Type.OVERWORLD);
    }


    private static void registerScrub(final RegistryEvent.Register<Biome> event) {
        registerBiome(denseScrub, event);
        registerBiome(dryScrub, event);
        registerBiome(denseScrubHills, event);
        registerBiome(dryScrubHills, event);
        BiomeDictionary.addTypes(denseScrub, Type.SPARSE, Type.HOT, Type.DRY,
                Type.OVERWORLD);
        BiomeDictionary.addTypes(dryScrub, Type.SPARSE, Type.HOT, Type.DRY,
                Type.SANDY, Type.OVERWORLD);
        BiomeDictionary.addTypes(denseScrubHills, Type.SPARSE, Type.HOT, Type.DRY,
                Type.HILLS, Type.OVERWORLD);
        BiomeDictionary.addTypes(dryScrubHills, Type.SPARSE, Type.HOT, Type.DRY,
                Type.HILLS, Type.SANDY, Type.OVERWORLD);
    }


    private static void registerMountains(final RegistryEvent.Register<Biome> event) {
        registerBiome(warmMountain, event);
        registerBiome(warmMountainTrees, event);
        registerBiome(hotMountain, event);
        registerBiome(hotMountainTrees, event);
        registerBiome(coolMontaneForest, event);
        registerBiome(warmMontaneForest, event);
        registerBiome(montaneJungle, event);
        BiomeDictionary.addTypes(warmMountain, Type.HILLS, Type.MOUNTAIN, Type.OVERWORLD);
        BiomeDictionary.addTypes(warmMountainTrees, Type.HILLS,
                Type.MOUNTAIN, Type.FOREST, Type.OVERWORLD);
        BiomeDictionary.addTypes(hotMountain, Type.HILLS, Type.MOUNTAIN,
                Type.HOT, Type.OVERWORLD);
        BiomeDictionary.addTypes(hotMountainTrees, Type.HILLS, Type.MOUNTAIN,
                Type.FOREST, Type.HOT, Type.OVERWORLD);
        BiomeDictionary.addTypes(coolMontaneForest, Type.HILLS, Type.MOUNTAIN,
                Type.COLD, Type.FOREST, Type.OVERWORLD);
        BiomeDictionary.addTypes(warmMontaneForest, Type.HILLS,
                Type.MOUNTAIN, Type.FOREST, Type.OVERWORLD);
        BiomeDictionary.addTypes(montaneJungle, Type.HILLS, Type.MOUNTAIN,
                Type.JUNGLE, Type.HOT, Type.WET, Type.OVERWORLD);
    }


    private static void registerPlains(final RegistryEvent.Register<Biome> event) {
        registerBiome(coolPlains, event);
        registerBiome(windswept, event);
        registerBiome(coolWindswept, event);
        registerBiome(coldPlains, event);
        BiomeDictionary.addTypes(coolPlains, Type.PLAINS, Type.OVERWORLD);
        BiomeDictionary.addTypes(windswept, Type.PLAINS, Type.OVERWORLD);
        BiomeDictionary.addTypes(coolWindswept, Type.PLAINS, Type.OVERWORLD);
        BiomeDictionary.addTypes(coldPlains, Type.PLAINS, Type.COLD, Type.OVERWORLD);
    }


    private static void registerSwamps(final RegistryEvent.Register<Biome> event) {
        registerBiome(bog, event);
        registerBiome(carr, event);
        registerBiome(marsh, event);
        BiomeDictionary.addTypes(bog, Type.SWAMP, Type.COLD, Type.WET, Type.OVERWORLD);
        BiomeDictionary.addTypes(carr, Type.SWAMP, Type.FOREST, Type.WET, Type.OVERWORLD);
        BiomeDictionary.addTypes(marsh, Type.SWAMP, Type.PLAINS, Type.WET, Type.OVERWORLD);
    }


    private static void registerRivers(final RegistryEvent.Register<Biome> event) {
        registerBiome(iceCap, event);
        registerBiome(iceRiver, event);
        registerBiome(coolRiver, event);
        registerBiome(river, event);
        registerBiome(warmRiver, event);
        registerBiome(hotRiver, event);
        BiomeDictionary.addTypes(iceCap, Type.OCEAN, Type.WATER, Type.COLD, Type.SNOWY,
                Type.WASTELAND, Type.OVERWORLD);
        BiomeDictionary.addTypes(iceRiver, Type.RIVER, Type.WATER, Type.COLD, Type.SNOWY,
                Type.OVERWORLD);
        BiomeDictionary.addTypes(coolRiver, Type.RIVER, Type.WATER, Type.COLD,
                Type.OVERWORLD);
        BiomeDictionary.addTypes(river, Type.RIVER, Type.WATER, Type.OVERWORLD);
        BiomeDictionary.addTypes(warmRiver, Type.RIVER, Type.WATER, Type.OVERWORLD);
        BiomeDictionary.addTypes(hotRiver, Type.RIVER, Type.WATER, Type.HOT,
                Type.OVERWORLD);
    }


    private static void registerOceans(final RegistryEvent.Register<Biome> event) {
        registerBiome(coast, event);
        registerBiome(frozenCoast, event);
        registerBiome(coldCoast, event);
        BiomeDictionary.addTypes(coast, Type.OCEAN, Type.WATER, Type.BEACH,
                Type.OVERWORLD);
        BiomeDictionary.addTypes(coldCoast, Type.OCEAN, Type.WATER, Type.BEACH,
                Type.COLD, Type.SNOWY, Type.OVERWORLD);
        BiomeDictionary.addTypes(frozenCoast, Type.OCEAN, Type.WATER, Type.BEACH,
                Type.COLD, Type.SNOWY, Type.OVERWORLD);
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
