package jaredbgreat.climaticbiome.generation.biome;

import jaredbgreat.climaticbiome.generation.mapgenerator.ChunkTile;
import net.minecraft.world.biome.Biome;

public class NoiseSpecialBiome implements IBiomeSpecifier {
	IBiomeSpecifier a, b;
	private final int boundary;
	
	
	public NoiseSpecialBiome(IBiomeSpecifier a, int boundary, IBiomeSpecifier b) {
		this.a = a;
		this.b = b;
		this.boundary = boundary;
	}
	

	@Override
	public long getBiome(ChunkTile tile) {
		tile.nextBiomeSeed();
		if(tile.getNoise() < boundary) {
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
