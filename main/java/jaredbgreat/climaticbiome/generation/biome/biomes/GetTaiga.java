package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.TempDoubleBiome;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

public class GetTaiga implements IBiomeSpecifier {
	private static GetTaiga taiga;
	private GetTaiga() {
		super();
		init();
	}
	private BiomeList forest;
	int tbound;
	
	
	public void init() {
		if(ConfigHandler.useBoP) { // FIXME: Use something more generic, not BoP specific
			tbound = 6;
		} else {
			tbound = 7;
		}
		forest = new BiomeList();
		forest.addItem(new TempDoubleBiome(30,  tbound, 5),  4);
		forest.addItem(new TempDoubleBiome(32,  tbound, 5),  2);
		forest.addItem(new TempDoubleBiome(30,  tbound, 160));
		forest.addItem(new TempDoubleBiome(158, tbound, 19),  3);
		forest.addItem(new TempDoubleBiome(158, tbound, 133), 2);
		
	}
	

	@Override
	public int getBiome(ChunkTile tile) {
		// Doing this to allow for possible addition of other types (e.g., alpine)
		return forest.getBiome(tile);
	}
	
	
	public static GetTaiga getTaiga() {
		if(taiga == null) {
			taiga = new GetTaiga();
		}
		return taiga;
	}


	@Override
	public boolean isEmpty() {
		return false;
	}

}
