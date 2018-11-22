package jaredbgreat.climaticbiome.generation.biome;

import jaredbgreat.climaticbiome.generation.generator.ChunkTile;
import net.minecraft.world.biome.Biome;

public class SeedDoubleBiome implements IBiomeSpecifier {
	private final int a, b, chance;
	
	
	public SeedDoubleBiome(int a, int chance, int b) {
		this.a = a;
		this.b = b;
		this.chance = chance;
	}
	
	
	public SeedDoubleBiome(Biome a, int chance, Biome b) {
		this.a = Biome.getIdForBiome(a);
		this.b = Biome.getIdForBiome(b);
		this.chance = chance;
	}
	

	@Override
	public int getBiome(ChunkTile tile) {
		tile.nextBiomeSeed();
		if((tile.getBiomeSeed() % chance) == 0) {
			return a;
		} else {
			return b;
		}
	}



}
