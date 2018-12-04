package jaredbgreat.climaticbiome.biomes.pseudo;

import jaredbgreat.climaticbiome.biomes.SubBiome;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;

public class BiomeDeepRiver extends SubBiome {
	
    public BiomeDeepRiver(Biome parent, int sid, BiomeProperties properties) {
        super(parent, sid, properties);
        this.spawnableCreatureList.clear();
    }
}
