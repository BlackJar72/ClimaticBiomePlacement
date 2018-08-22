package jaredbgreat.climaticbiome.generation;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.IChunkProvider;

public class ClimaticWorldType extends WorldType {
	private static BiomeProvider biomeProvider;
    public static IChunkProvider chunkProvider;
    

    public ClimaticWorldType() {
		super("ClimaticBP");		
	}
    
    

}
