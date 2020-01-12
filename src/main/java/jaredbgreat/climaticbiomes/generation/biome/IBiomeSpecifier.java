package jaredbgreat.climaticbiomes.generation.biome;

import jaredbgreat.climaticbiomes.generation.generator.ChunkTile;

public interface IBiomeSpecifier {
    public long getBiome(ChunkTile tile);
    public boolean isEmpty();

    // Debugging
    public void listOut();

}
