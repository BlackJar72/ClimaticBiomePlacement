package jaredbgreat.climaticbiome.generation;

import javax.annotation.Nonnull;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderOverworld;

public class BetterWorldType extends WorldType {
    private static BiomeProvider biomeProvider;
    public static IChunkProvider chunkProvider;

	public BetterWorldType() {
		super("BetterBP");		
	}

    public IChunkGenerator getChunkGenerator(World world, String generatorOptions) { 
        return new ChunkProviderOverworld(world, world.getSeed(), 
        		world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions);
    }

    @Override @Nonnull
    public BiomeProvider getBiomeProvider(@Nonnull World world) { 
        return new BetterBiomeProvider(world);
    }


}
