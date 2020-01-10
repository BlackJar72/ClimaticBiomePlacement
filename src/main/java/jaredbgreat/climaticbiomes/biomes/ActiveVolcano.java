package jaredbgreat.climaticbiomes.biomes;

import jaredbgreat.climaticbiomes.Info;
import jaredbgreat.climaticbiomes.biomes.surface.Surfaces;
import jaredbgreat.climaticbiomes.features.ClimaticBiomesFeatures;
import jaredbgreat.climaticbiomes.util.BlockRegistrar;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class ActiveVolcano extends Biome {

    public ActiveVolcano(String name) {
        super((new Biome.Builder()).surfaceBuilder(Surfaces.VOLCANIC,
                new SurfaceBuilderConfig(BlockRegistrar.blockBasalt.getDefaultState(),
                                         BlockRegistrar.blockBasalt.getDefaultState(),
                                         BlockRegistrar.blockVolcanicAsh.getDefaultState())).
                precipitation(Biome.RainType.RAIN)
                .category(Biome.Category.EXTREME_HILLS)
                .depth(1.5F).scale(0.5F)
                .temperature(2.0F)
                .downfall(0.3F)
                .waterColor(4159204)
                .waterFogColor(329011)
                .parent((String)null));
        this.setRegistryName(Info.ID, name);
        this.addStructure(Feature.MINESHAFT, new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL));
        this.addStructure(Feature.STRONGHOLD, IFeatureConfig.NO_FEATURE_CONFIG);
        DefaultBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addStructures(this);
        DefaultBiomeFeatures.addLakes(this);
        DefaultBiomeFeatures.addMonsterRooms(this);
        DefaultBiomeFeatures.addStoneVariants(this);
        DefaultBiomeFeatures.addOres(this);
        DefaultBiomeFeatures.addSedimentDisks(this);
        ClimaticBiomesFeatures.addVolcanoOres(this);
        DefaultBiomeFeatures.addSprings(this);
        DefaultBiomeFeatures.addExtraEmeraldOre(this);
        DefaultBiomeFeatures.addInfestedStone(this);
        DefaultBiomeFeatures.addFreezeTopLayer(this);
        this.addSpawn(EntityClassification.AMBIENT,
                new Biome.SpawnListEntry(EntityType.BAT, 10, 8, 8));
        this.addSpawn(EntityClassification.MONSTER,
                new Biome.SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER,
                new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
        this.addSpawn(EntityClassification.MONSTER,
                new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        this.addSpawn(EntityClassification.MONSTER,
                new Biome.SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER,
                new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER,
                new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER,
                new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
        this.addSpawn(EntityClassification.MONSTER,
                new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 1));
    }
}
