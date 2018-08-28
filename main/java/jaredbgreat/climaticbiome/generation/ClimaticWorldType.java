package jaredbgreat.climaticbiome.generation;

import javax.annotation.Nonnull;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.IChunkGenerator;

public class ClimaticWorldType extends WorldType {
	private static BiomeProvider biomeProvider;
    public static IChunkProvider chunkProvider;
    

    public ClimaticWorldType() {
		super("climatic_bp");		
	}

    public IChunkGenerator getChunkGenerator(World world, String generatorOptions) { 
        return new ChunkGeneratorOverworld(world, world.getSeed(), 
        		world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions);
    }

    @Override @Nonnull
    public BiomeProvider getBiomeProvider(@Nonnull World world) { 
        return new ClimaticBiomeProvider(world);
    }


    
    

}
