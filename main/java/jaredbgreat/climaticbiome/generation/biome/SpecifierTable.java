package jaredbgreat.climaticbiome.generation.biome;

import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

public class SpecifierTable implements IBiomeSpecifier {
	public static final int WIDTH  = 10;
	public static final int HEIGHT = 25;
	public static final int SIZE   = WIDTH * HEIGHT;	
	IBiomeSpecifier[] table;
	
	
	public SpecifierTable() {
		// FIXME: This constructor should not exist
	}
	
	
	public SpecifierTable(IBiomeSpecifier[] t) {
		init(t);
	}
	
	
	final void init(IBiomeSpecifier[] t) {
		table = t;
	}

	
	@Override
	public long getBiome(ChunkTile tile) {
		return table[(tile.getTemp() * WIDTH) + tile.getWet()].getBiome(tile);
	}

	
	@Override
	public boolean isEmpty() {
		return (table == null) || (table.length < SIZE);
	}

}
