package jaredbgreat.climaticbiome.generation.chunk;

public class BuiltinBiomeType implements IBiomeType {

	@Override
	public void makeBiomes(ChunkTile[] map, BiomeFinder maker,
			SpatialNoise random) {
		EnumBiomeType.makeBiomes(map, maker, random);		
	}

}
