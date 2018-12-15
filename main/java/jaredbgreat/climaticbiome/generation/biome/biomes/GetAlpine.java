package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.biome.NoiseDoubleBiome;
import jaredbgreat.climaticbiome.generation.biome.compat.BoP;
import jaredbgreat.climaticbiome.generation.biome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

public class GetAlpine implements IBiomeSpecifier {
	private static GetAlpine alpine;
	private GetAlpine() {
		super();
		init();
	}
	
	// TODO: More variants; make generic for all climate zones
	
	BiomeList wet;
	BiomeList dry;

	public void init() {
		wet = new BiomeList();
		dry = new BiomeList();
		DefReader.readBiomeData(wet, "AplineWet.cfg");
		DefReader.readBiomeData(dry, "AplineDry.cfg");
		if(wet.isEmpty()) {
			wet.addItem(new LeafBiome(34));
			wet.addItem(new LeafBiome(162));
			wet.addItem(new NoiseDoubleBiome(34, 5, 162));
		}
		if(dry.isEmpty()) {
			dry.addItem(new LeafBiome(3));
			dry.addItem(new LeafBiome(131));
			dry.addItem(new NoiseDoubleBiome(3, 5, 131));
		}
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
	
	
	public static GetAlpine getAlpine() {
		if(alpine == null) {
			alpine = new GetAlpine();
		}
		return alpine;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
