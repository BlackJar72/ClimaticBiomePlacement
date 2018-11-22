package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.biome.NoiseDoubleBiome;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

public class GetAlpine implements IBiomeSpecifier {
	BiomeList wet;
	BiomeList dry;

	public void init() {
		wet = new BiomeList();
		dry = new BiomeList();
		wet.addItem(new LeafBiome(34));
		wet.addItem(new LeafBiome(162));
		wet.addItem(new NoiseDoubleBiome(34, 5, 162));
		wet.addItem(new LeafBiome(3));
		wet.addItem(new LeafBiome(131));
		wet.addItem(new NoiseDoubleBiome(3, 5, 131));
	}

	@Override
	public int getBiome(ChunkTile tile) {
		boolean plus = (tile.getBiomeSeed() % 7) < tile.getWet();
		tile.nextBiomeSeed();
		if(plus) {
			return wet.getBiome(tile);
		} else {
			return dry.getBiome(tile);
		}
	}
}
