package jaredbgreat.climaticbiome.generation.biome;

import jaredbgreat.climaticbiome.generation.mapgenerator.ChunkTile;

public class CentralSpecialBiome implements IBiomeSpecifier {
	IBiomeSpecifier a, b;
	private final int boundary;
	
	
	public CentralSpecialBiome(IBiomeSpecifier a, int boundary, IBiomeSpecifier b) {
		this.a = a;
		this.b = b;
		this.boundary = boundary;
	}
	

	@Override
	public long getBiome(ChunkTile tile) {
		tile.nextBiomeSeed();
		if(tile.getCNoise() < boundary) {
			return a.getBiome(tile);
		} else {
			return b.getBiome(tile);
		}
	}


	@Override
	public boolean isEmpty() {
		return a.isEmpty() || b.isEmpty();
	}

}
