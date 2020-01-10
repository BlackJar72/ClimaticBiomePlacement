package jaredbgreat.climaticbiomes.biomes.surface;

import net.minecraft.world.gen.surfacebuilders.DefaultSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.MountainSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class Surfaces {
    public static final SurfaceBuilder<SurfaceBuilderConfig> DENSE_SCRUB
            = new DenseScrubSurfaceBuilder(SurfaceBuilderConfig::deserialize);
    public static final SurfaceBuilder<SurfaceBuilderConfig> DRY_SCRUB
            = new DryScrubSurfaceBuilder(SurfaceBuilderConfig::deserialize);
    public static final SurfaceBuilder<SurfaceBuilderConfig> VOLCANIC
            = new VolcanoSurfaceBuilder(SurfaceBuilderConfig::deserialize);
}
