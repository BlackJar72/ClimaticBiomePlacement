package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.mapgenerator.ChunkTile;

public class GetJungle implements IBiomeSpecifier {
	private static GetJungle jungle;
	private GetJungle() {
		super();
		init();
	}
	private BiomeList jungles;
	private GetHotForest forest;
	private GetSwamp swamp;

	
	public void init() {
		jungles = new BiomeList();
		forest  = GetHotForest.getForest();
		swamp   = GetSwamp.getSwamp();
		DefReader.readBiomeData(jungles, "Jungle.cfg");
		if(jungles.isEmpty()) {
			jungles.addItem(new LeafBiome(21), 3);
			jungles.addItem(new LeafBiome(23), 2);
		}
	}


	@Override
	public long getBiome(ChunkTile tile) {
		int role2 = tile.getBiomeSeed() % 11;
		int role3 = tile.getBiomeSeed() % 7;
		tile.nextBiomeSeed();
		if((role2) == 0) {
			return swamp.getBiome(tile);
		}
		if((role3) == 0) {
			return forest.getBiome(tile);
		}
		return jungles.getBiome(tile);
	}
	
	
	public static GetJungle getJungle() {
		if(jungle == null) {
			jungle = new GetJungle();
		}
		return jungle;
	}


	@Override
	public boolean isEmpty() {
		return false;
	}

}
