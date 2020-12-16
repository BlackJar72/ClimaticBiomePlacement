package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.mapgenerator.ChunkTile;

public class GetColdPlains implements IBiomeSpecifier {
	private static GetColdPlains plains;
	private GetColdPlains() {
		super();
		init();
	}
	private BiomeList coldPlains;
	
	public void init() {
		coldPlains = new BiomeList();
		DefReader.readBiomeData(coldPlains, "PlainsCold.cfg");
		if(coldPlains.isEmpty()) {
			coldPlains.addItem(new LeafBiome(1), 2);			
		}
	}

	@Override
	public long getBiome(ChunkTile tile) {
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
