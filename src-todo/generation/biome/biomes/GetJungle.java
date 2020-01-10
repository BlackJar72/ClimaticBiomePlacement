package jaredbgreat.climaticbiomes.generation.biome.biomes;

import jaredbgreat.climaticbiomes.compat.userdef.DefReader;
import jaredbgreat.climaticbiomes.generation.biome.BiomeList;
import jaredbgreat.climaticbiomes.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiomes.generation.biome.LeafBiome;
import jaredbgreat.climaticbiomes.generation.generator.ChunkTile;

public class GetJungle implements IBiomeSpecifier {
	private static GetJungle jungle;
	private GetJungle() {
		super();
		init();
	}
	private BiomeList jungles;
	private GetAlpine alpine;
	private GetHotForest forest;
	private GetSwamp swamp;

	
	public void init() {
		jungles = new BiomeList();
		alpine  = GetAlpine.getAlpine();
		forest  = GetHotForest.getForest();
		swamp   = GetSwamp.getSwamp();
		DefReader.readBiomeData(jungles, "Jungle.cfg");
		if(isEmpty()) {
			jungles.addItem(new LeafBiome(21), 3);
			jungles.addItem(new LeafBiome(23), 2);
		}
	}


	@Override
	public long getBiome(ChunkTile tile) {
		int role1 = tile.getBiomeSeed() % 5;
		int role2 = tile.getBiomeSeed() % 11;
		int role3 = tile.getBiomeSeed() % 7;
		tile.nextBiomeSeed();
		if((role1) == 0) {
			return alpine.getBiome(tile);
		}
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
