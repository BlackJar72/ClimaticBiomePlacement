package jaredbgreat.climaticbiome.generation.biome;

import jaredbgreat.climaticbiome.generation.generator.ChunkTile;
import net.minecraft.world.biome.Biome;

public class WetDoubleBiome implements IBiomeSpecifier {
	private final int a, b, boundary;
	
	
	public WetDoubleBiome(int a, int boundary, int b) {
		this.a = a;
		this.b = b;
		this.boundary = boundary;
	}
	
	
	public WetDoubleBiome(Biome a, int boundary, Biome b) {
		this.a = Biome.getIdForBiome(a);
		this.b = Biome.getIdForBiome(b);
		this.boundary = boundary;
	}
	

	@Override
	public int getBiome(ChunkTile tile) {
		int r = tile.getBiomeSeed();
		if((tile.getWet() + (r & 1) - ((r & 2) >> 1)) < boundary) {
			return a;
		} else {
			return b;
		}
	}


	@Override
	public boolean isEmpty() {
		return false;
	}

}
