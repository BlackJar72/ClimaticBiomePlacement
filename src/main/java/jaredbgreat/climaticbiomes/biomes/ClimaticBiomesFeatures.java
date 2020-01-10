package jaredbgreat.climaticbiomes.biomes;

import com.google.common.collect.Lists;
import jaredbgreat.climaticbiomes.features.PineTree;
import jaredbgreat.climaticbiomes.features.PineTreeFeature;
import jaredbgreat.climaticbiomes.util.BlockRegistrar;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.*;

public class ClimaticBiomesFeatures {
    public static final PineTreeFeature PINE
            = new PineTreeFeature(NoFeatureConfig::deserialize, false);

    public static final Feature<NoFeatureConfig> SCRUB_BUSH1
            = new ShrubFeature(NoFeatureConfig::deserialize, Blocks.OAK_LOG.getDefaultState(),
                    Blocks.OAK_LEAVES.getDefaultState());

    public static final Feature<NoFeatureConfig> SCRUB_BUSH2
            = new ShrubFeature(NoFeatureConfig::deserialize, Blocks.SPRUCE_LOG.getDefaultState(),
                    Blocks.SPRUCE_LEAVES.getDefaultState());

    public static final Feature<NoFeatureConfig> BIG_ROCK_PILE
            = new ShrubFeature(NoFeatureConfig::deserialize, Blocks.COBBLESTONE.getDefaultState(),
            Blocks.COBBLESTONE.getDefaultState());

    public static final Feature<NoFeatureConfig> BOULDER
            = new ShrubFeature(NoFeatureConfig::deserialize, Blocks.STONE.getDefaultState(),
            Blocks.STONE.getDefaultState());


    public static void addSubtropicalForestTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR,
                        new MultipleRandomFeatureConfig(new Feature[]
                                { Feature.FANCY_TREE, PINE},
                                new IFeatureConfig[]
                                        {IFeatureConfig.NO_FEATURE_CONFIG, IFeatureConfig.NO_FEATURE_CONFIG},
                                new float[]{0.2F, 0.2F}, Feature.NORMAL_TREE, IFeatureConfig.NO_FEATURE_CONFIG),
                        Placement.COUNT_EXTRA_HEIGHTMAP,
                        new AtSurfaceWithExtraConfig(10, 0.1F, 1)));
    }


    public static void addTropicalForestTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR,
                        new MultipleRandomFeatureConfig(new Feature[]
                                {Feature.JUNGLE_TREE, Feature.FANCY_TREE},
                                new IFeatureConfig[]
                                        {IFeatureConfig.NO_FEATURE_CONFIG, IFeatureConfig.NO_FEATURE_CONFIG},
                                new float[]{0.1F, 0.2F}, Feature.SAVANNA_TREE, IFeatureConfig.NO_FEATURE_CONFIG),
                        Placement.COUNT_EXTRA_HEIGHTMAP,
                        new AtSurfaceWithExtraConfig(10, 0.1F, 1)));
    }


    public static void addPinewoodsTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR,
                        new MultipleRandomFeatureConfig(new Feature[]
                                {Feature.SWAMP_TREE},
                                new IFeatureConfig[]
                                        {IFeatureConfig.NO_FEATURE_CONFIG, IFeatureConfig.NO_FEATURE_CONFIG},
                                new float[]{0.1F}, PINE, IFeatureConfig.NO_FEATURE_CONFIG),
                        Placement.COUNT_EXTRA_HEIGHTMAP,
                        new AtSurfaceWithExtraConfig(10, 0.1F, 1)));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.SWAMP_TREE,
                        IFeatureConfig.NO_FEATURE_CONFIG,
                        Placement.COUNT_EXTRA_HEIGHTMAP,
                        new AtSurfaceWithExtraConfig(2, 0.1F, 1)));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.SWAMP_FLOWER,
                        IFeatureConfig.NO_FEATURE_CONFIG,
                        Placement.COUNT_HEIGHTMAP_32,
                        new FrequencyConfig(1)));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.GRASS,
                        new GrassFeatureConfig(Blocks.GRASS.getDefaultState()),
                        Placement.COUNT_HEIGHTMAP_DOUBLE,
                        new FrequencyConfig(10)));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.WATERLILY,
                        IFeatureConfig.NO_FEATURE_CONFIG,
                        Placement.COUNT_HEIGHTMAP_DOUBLE,
                        new FrequencyConfig(2)));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.BUSH,
                        new BushConfig(Blocks.BROWN_MUSHROOM.getDefaultState()),
                        Placement.COUNT_CHANCE_HEIGHTMAP,
                        new HeightWithChanceConfig(8, 0.25F)));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.BUSH,
                        new BushConfig(Blocks.RED_MUSHROOM.getDefaultState()),
                        Placement.COUNT_CHANCE_HEIGHTMAP_DOUBLE, new
                                HeightWithChanceConfig(8, 0.125F)));

    }


    public static void addDenseScrubBushes(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR,
                        new MultipleRandomFeatureConfig(new Feature[]{SCRUB_BUSH1, SCRUB_BUSH2},
                                new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG,
                                        IFeatureConfig.NO_FEATURE_CONFIG},
                                new float[]{0.4f, 0.4f}, Feature.NORMAL_TREE,
                                IFeatureConfig.NO_FEATURE_CONFIG), Placement.COUNT_EXTRA_HEIGHTMAP,
                        new AtSurfaceWithExtraConfig(3, 0.1F, 1)));
    }


    public static void addDryScrubBushes(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR,
                        new MultipleRandomFeatureConfig(new Feature[]{SCRUB_BUSH1},
                                new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG},
                                new float[]{0.5f}, SCRUB_BUSH2,
                                IFeatureConfig.NO_FEATURE_CONFIG), Placement.COUNT_EXTRA_HEIGHTMAP,
                        new AtSurfaceWithExtraConfig(1, 0.1F, 1)));
    }


    public static void addScrubRocks(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS,
                Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR,
                        new MultipleRandomFeatureConfig(new Feature[]{BIG_ROCK_PILE},
                                new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG},
                                new float[]{0.66f}, BOULDER,
                                IFeatureConfig.NO_FEATURE_CONFIG), Placement.COUNT_EXTRA_HEIGHTMAP,
                        new AtSurfaceWithExtraConfig(0, 0.2F, 1)));
        biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS,
                Biome.createDecoratedFeature(Feature.FOREST_ROCK,
                        new BlockBlobConfig(Blocks.COBBLESTONE.getDefaultState(), 0),
                        Placement.FOREST_ROCK, new FrequencyConfig(3)));
    }


    public static void addVolcanoOres(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Biome.createDecoratedFeature(Feature.ORE,
                        new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                Blocks.IRON_ORE.getDefaultState(), 12),
                        Placement.COUNT_RANGE,
                        new CountRangeConfig(20, 0, 0, 56)));

        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Biome.createDecoratedFeature(Feature.ORE,
                        new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                Blocks.GOLD_ORE.getDefaultState(), 12),
                        Placement.COUNT_RANGE,
                        new CountRangeConfig(4, 0, 0, 48)));

        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Biome.createDecoratedFeature(Feature.ORE,
                        new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                Blocks.LAVA.getDefaultState(), 9),
                        Placement.COUNT_RANGE,
                        new CountRangeConfig(4, 0, 0, 128)));

        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.SPRING_FEATURE,
                        new LiquidsConfig(Fluids.LAVA.getDefaultState()),
                        Placement.COUNT_VERY_BIASED_RANGE,
                        new CountRangeConfig(256, 8, 16, 256)));

        biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS,
                Biome.createDecoratedFeature(Feature.LAKE,
                        new LakesConfig(Blocks.LAVA.getDefaultState()),
                        Placement.LAVA_LAKE, new LakeChanceConfig(20)));

    }


    public static void addBambooForestPlants(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.BAMBOO, new ProbabilityConfig(0.25F),
                        Placement.TOP_SOLID_HEIGHTMAP_NOISE_BIASED,
                        new TopSolidWithNoiseConfig(160, 80.0D, 0.3D,
                                Heightmap.Type.WORLD_SURFACE_WG)));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR,
                        new MultipleRandomFeatureConfig(new Feature[]
                                {SCRUB_BUSH1, Feature.JUNGLE_GROUND_BUSH, Feature.JUNGLE_GRASS},
                                new IFeatureConfig[]
                                        {IFeatureConfig.NO_FEATURE_CONFIG, IFeatureConfig.NO_FEATURE_CONFIG,
                                                IFeatureConfig.NO_FEATURE_CONFIG},
                                new float[]{0.1f, 0.05f, 0.15f},
                                Feature.BAMBOO, new ProbabilityConfig(0.25F)),
                        Placement.COUNT_EXTRA_HEIGHTMAP,
                        new AtSurfaceWithExtraConfig(40, 0.1F, 1)));
    }


    public static void addWindsweptPlainsPlants(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.PLAIN_FLOWER,
                        IFeatureConfig.NO_FEATURE_CONFIG,
                        Placement.NOISE_HEIGHTMAP_32,
                        new NoiseDependant(-0.8D, 15, 4)));

        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.GRASS,
                        new GrassFeatureConfig(Blocks.GRASS.getDefaultState()),
                        Placement.NOISE_HEIGHTMAP_DOUBLE,
                        new NoiseDependant(-0.8D, 5, 10)));
    }


    public static void addCoolPlainsPlants(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR,
                        new MultipleRandomFeatureConfig(new Feature[]{Feature.SPRUCE_TREE, Feature.PINE_TREE},
                                new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG, IFeatureConfig.NO_FEATURE_CONFIG},
                                new float[]{0.25f, 0.25f}, Feature.NORMAL_TREE,
                                IFeatureConfig.NO_FEATURE_CONFIG),
                        Placement.COUNT_EXTRA_HEIGHTMAP,
                        new AtSurfaceWithExtraConfig(0, 0.05F, 1)));

        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.PLAIN_FLOWER,
                        IFeatureConfig.NO_FEATURE_CONFIG,
                        Placement.NOISE_HEIGHTMAP_32,
                        new NoiseDependant(-0.8D, 15, 4)));

        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.GRASS,
                        new GrassFeatureConfig(Blocks.GRASS.getDefaultState()),
                        Placement.NOISE_HEIGHTMAP_DOUBLE,
                        new NoiseDependant(-0.8D, 5, 10)));
    }


    public static void addColdPlainsPlants(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR,
                        new MultipleRandomFeatureConfig(new Feature[]{SCRUB_BUSH1},
                                new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG},
                                new float[]{0.5f}, SCRUB_BUSH2,
                                IFeatureConfig.NO_FEATURE_CONFIG),
                        Placement.COUNT_EXTRA_HEIGHTMAP,
                        new AtSurfaceWithExtraConfig(0, 0.5F, 1)));

        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.PLAIN_FLOWER,
                        IFeatureConfig.NO_FEATURE_CONFIG,
                        Placement.NOISE_HEIGHTMAP_32,
                        new NoiseDependant(-0.8D, 15, 4)));

        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.GRASS,
                        new GrassFeatureConfig(Blocks.GRASS.getDefaultState()),
                        Placement.NOISE_HEIGHTMAP_DOUBLE,
                        new NoiseDependant(-0.8D, 5, 10)));
    }


    public static void addBogPlants(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR,
                        new MultipleRandomFeatureConfig(new Feature[]{SCRUB_BUSH1, SCRUB_BUSH2},
                                new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG,
                                        IFeatureConfig.NO_FEATURE_CONFIG},
                                new float[]{0.1f, 0.1f}, Feature.JUNGLE_GRASS,
                                IFeatureConfig.NO_FEATURE_CONFIG),
                        Placement.COUNT_EXTRA_HEIGHTMAP,
                        new AtSurfaceWithExtraConfig(12, 0.5F, 1)));

        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.SWAMP_FLOWER, IFeatureConfig.NO_FEATURE_CONFIG,
                        Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(1)));

        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(Blocks.GRASS.getDefaultState()),
                        Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(10)));

        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.DEAD_BUSH, IFeatureConfig.NO_FEATURE_CONFIG,
                        Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(1)));

        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.WATERLILY, IFeatureConfig.NO_FEATURE_CONFIG,
                        Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(4)));

        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.BUSH,
                        new BushConfig(Blocks.BROWN_MUSHROOM.getDefaultState()),
                        Placement.COUNT_CHANCE_HEIGHTMAP, new HeightWithChanceConfig(8, 0.25F)));

        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(Blocks.RED_MUSHROOM.getDefaultState()),
                        Placement.COUNT_CHANCE_HEIGHTMAP_DOUBLE, new HeightWithChanceConfig(8, 0.125F)));
    }


    public static void addCarrPlants(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR,
                        new MultipleRandomFeatureConfig(new Feature[]{Feature.SPRUCE_TREE, Feature.PINE_TREE},
                                new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG,
                                        IFeatureConfig.NO_FEATURE_CONFIG},
                                new float[]{0.25f, 0.25f}, Feature.BIRCH_TREE,
                                IFeatureConfig.NO_FEATURE_CONFIG),
                        Placement.COUNT_EXTRA_HEIGHTMAP,
                        new AtSurfaceWithExtraConfig(8, 0.5F, 1)));

        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.SWAMP_FLOWER, IFeatureConfig.NO_FEATURE_CONFIG,
                        Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(1)));

        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(Blocks.GRASS.getDefaultState()),
                        Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(10)));

        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.WATERLILY, IFeatureConfig.NO_FEATURE_CONFIG,
                        Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(16)));

        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.BUSH,
                        new BushConfig(Blocks.BROWN_MUSHROOM.getDefaultState()),
                        Placement.COUNT_CHANCE_HEIGHTMAP, new HeightWithChanceConfig(8, 0.25F)));

        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(Blocks.RED_MUSHROOM.getDefaultState()),
                        Placement.COUNT_CHANCE_HEIGHTMAP_DOUBLE, new HeightWithChanceConfig(8, 0.125F)));
    }


    public static void addMarshPlants(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.SWAMP_FLOWER, IFeatureConfig.NO_FEATURE_CONFIG,
                        Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(1)));

        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(Blocks.GRASS.getDefaultState()),
                        Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(12)));

        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.WATERLILY, IFeatureConfig.NO_FEATURE_CONFIG,
                        Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(12)));

        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.BUSH,
                        new BushConfig(Blocks.BROWN_MUSHROOM.getDefaultState()),
                        Placement.COUNT_CHANCE_HEIGHTMAP, new HeightWithChanceConfig(8, 0.25F)));

        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(Blocks.RED_MUSHROOM.getDefaultState()),
                        Placement.COUNT_CHANCE_HEIGHTMAP_DOUBLE, new HeightWithChanceConfig(8, 0.125F)));
    }


    public static void addPeatDisks(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Biome.createDecoratedFeature(Feature.DISK,
                        new SphereReplaceConfig(BlockRegistrar.blockPeat.getDefaultState(),
                                6, 2, Lists.newArrayList(Blocks.DIRT.getDefaultState(),
                                BlockRegistrar.blockPeat.getDefaultState())),
                        Placement.COUNT_TOP_SOLID, new FrequencyConfig(3)));
    }


}
