package jaredbgreat.climaticbiome.generation.biome;

import jaredbgreat.climaticbiome.generation.generator.ChunkTile;
import net.minecraft.world.biome.Biome;

public class NoiseDoubleBiome implements IBiomeSpecifier {
	private final int a, b, boundary;
	
	
	public NoiseDoubleBiome(int a, int boundary, int b) {
		this.a = a;
		this.b = b;
		this.boundary = boundary;
	}
	
	
	public NoiseDoubleBiome(Biome a, int boundary, Biome b) {
		this.a = Biome.getIdForBiome(a);
		this.b = Biome.getIdForBiome(b);
		this.boundary = boundary;
	}
	

	@Override
	public int getBiome(ChunkTile tile) {
		if(tile.getNoise() < boundary) {
			return a;
		} else {
			return b;
		}
	}

}
