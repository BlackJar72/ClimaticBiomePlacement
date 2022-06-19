package jaredbgreat.climaticbiome.structure;

import java.util.Random;

import jaredbgreat.climaticbiome.generation.ClimaticBiomeProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.structure.WoodlandMansion;
import net.minecraftforge.common.BiomeDictionary.Type;

public class CommonWoodlandMansion extends WoodlandMansion {

	public CommonWoodlandMansion(ChunkGeneratorOverworld ChunkGen) {
		super(ChunkGen);
	}

	
	@Override
	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        int i = chunkX / 80;
        int j = chunkZ / 80;
        Random random = this.world.setRandomSeed(i, j, 10387319);
        i = i * 80;
        j = j * 80;
        i = i + (random.nextInt(60) + random.nextInt(60)) / 2;
        j = j + (random.nextInt(60) + random.nextInt(60)) / 2;        
        BiomeProvider bp = world.getBiomeProvider();
        if (chunkX == i && chunkZ == j) {
        	if(bp instanceof ClimaticBiomeProvider) {
        		return ((ClimaticBiomeProvider)bp).areBiomesViable(chunkX * 16 + 8, chunkZ * 16 + 8, 32, Type.FOREST);
        	} else {
        		return bp.areBiomesViable(chunkX * 16 + 8, chunkZ * 16 + 8, 32, ALLOWED_BIOMES);
        	}
        }
        return false;
    }
}
