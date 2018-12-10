package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.biome.NoiseDoubleBiome;
import jaredbgreat.climaticbiome.generation.biome.compat.BoP;
import jaredbgreat.climaticbiome.generation.biome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

public class GetColdPlains implements IBiomeSpecifier {
	private static GetColdPlains plains;
	private GetColdPlains() {
		super();
		init();
	}
	private BiomeList coldPlains;
	
	public void init() {
		coldPlains = new BiomeList();
		if(ConfigHandler.cleanSlate) {
			DefReader.readBiomeData(coldPlains, "PlainsCold.cfg");
			return;
		}
		if(ConfigHandler.useBoP) {
			BoP.addColdPlains(coldPlains);
		}
		if(ConfigHandler.useCfg) {
			DefReader.readBiomeData(coldPlains, "PlainsCold.cfg");
		}
		if(coldPlains.isEmpty()) {
			coldPlains.addItem(new LeafBiome(1), 2);			
		}
	}

	@Override
	public int getBiome(ChunkTile tile) {
		if((tile.getBiomeSeed() % 3) == 0) {
			return 13;  // TODO: Once GetAlpine is more generic use that
		}
		tile.nextBiomeSeed();
		return coldPlains.getBiome(tile);
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
	
	
	public static GetColdPlains getPlains() {
		if(plains == null) {
			plains = new GetColdPlains();
		}
		return plains;
	}

}
