package jaredbgreat.climaticbiomes.biomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;

public class ClimaticBiomesFeatures {


    public static void addSubtropicalForestTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR,
                        new MultipleRandomFeatureConfig(new Feature[]
                                {Feature.BIRCH_TREE, Feature.FANCY_TREE},
                                new IFeatureConfig[]
                                        {IFeatureConfig.NO_FEATURE_CONFIG, IFeatureConfig.NO_FEATURE_CONFIG},
                                new float[]{0.2F, 0.1F}, Feature.NORMAL_TREE, IFeatureConfig.NO_FEATURE_CONFIG),
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
                                new float[]{0.2F, 0.1F}, Feature.SAVANNA_TREE, IFeatureConfig.NO_FEATURE_CONFIG),
                        Placement.COUNT_EXTRA_HEIGHTMAP,
                        new AtSurfaceWithExtraConfig(10, 0.1F, 1)));
    }
}
