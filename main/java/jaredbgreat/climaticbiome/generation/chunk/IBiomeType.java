package jaredbgreat.climaticbiome.generation.chunk;

public interface IBiomeType {

    public void makeBiomes(ChunkTile[] map, BiomeFinder maker, 
            SpatialNoise random);
    
}
