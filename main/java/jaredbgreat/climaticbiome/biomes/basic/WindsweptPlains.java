package jaredbgreat.climaticbiome.biomes.basic;

import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.biome.Biome;

public class WindsweptPlains extends Biome {

	public WindsweptPlains(BiomeProperties properties) {
		super(properties);
        this.spawnableCreatureList
        	.add(new Biome.SpawnListEntry(EntityHorse.class, 5, 2, 6));
        this.spawnableCreatureList
        	.add(new Biome.SpawnListEntry(EntityDonkey.class, 1, 1, 3));
        this.decorator.treesPerChunk = -999;
        this.decorator.extraTreeChance = 0.0f;
        this.decorator.flowersPerChunk = 4;
        this.decorator.grassPerChunk = 10;
	}

}
