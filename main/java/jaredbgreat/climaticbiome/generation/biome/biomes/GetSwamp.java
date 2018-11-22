package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

// TODO / FIXME: Fix for use with mods!
public class GetSwamp extends BiomeList {
	private static GetSwamp swamp;
	private GetSwamp() {
		super();
	}
	
	
	public void init() {
		// TODO / FIXME: Fix for use with mods!
		addItem(new LeafBiome(6));
	}
	

	@Override
	public int getBiome(ChunkTile tile) {
		// TODO: Add variants
		return 6;
	}
	
	
	public static GetSwamp getSwamp() {
		if(swamp == null) {
			swamp = new GetSwamp();
		}
		return swamp;
	}

}
