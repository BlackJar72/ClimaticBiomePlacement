package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

public class GetIslands implements IBiomeSpecifier {
	private static GetIslands islands;
	private GetIslands() {
		super();
	}

	@Override
	public int getBiome(ChunkTile tile) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	public static GetIslands getIslands() {
		if(islands == null) {
			islands = new GetIslands();
		}
		return islands;
	}

}
