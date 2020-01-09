package jaredbgreat.climaticbiomes.biomes;

import jaredbgreat.climaticbiomes.features.PineTree;
import jaredbgreat.climaticbiomes.features.PineTreeFeature;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.HeightWithChanceConfig;
import net.minecraft.world.gen.placement.Placement;

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
}
