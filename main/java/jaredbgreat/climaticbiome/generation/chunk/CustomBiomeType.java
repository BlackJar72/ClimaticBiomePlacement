package jaredbgreat.climaticbiome.generation.chunk;

public class CustomBiomeType implements IBiomeType {

	@Override
	public void makeBiomes(ChunkTile[] map, BiomeFinder maker,
			SpatialNoise random) {
        int[] noise = EnumBiomeType.refineNoise(maker.makeNoise(map[24].x, map[24].z, 4), map);
        for(int i = 0; i < map.length; i++) {
            findBiome(map[i], noise[i]);
        }
    }

	private void findBiome(ChunkTile chunk, int noise) {
        if(!chunk.land) {
            if((noise - (chunk.temp / 2)) > 0) {
                chunk.rlBiome = EnumBiomeType.FROCEAN;
            } else {
            	chunk.rlBiome = EnumBiomeType.OCEAN;
            }
            return;
        }
        if(chunk.temp > 7 && ((chunk.wet - chunk.val) > noise - 1)) {
            chunk.rlBiome = EnumBiomeType.SWAMP;
            return;
        }
        findLandBiome(chunk);
    }
    
    
    void findLandBiome(ChunkTile chunk) {
    	// TODO: Implement this
    }

}
