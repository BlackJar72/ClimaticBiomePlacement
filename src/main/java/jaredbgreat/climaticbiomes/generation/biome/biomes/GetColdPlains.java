package jaredbgreat.climaticbiomes.generation.biome.biomes;

import jaredbgreat.climaticbiomes.compat.userdef.DefReader;
import jaredbgreat.climaticbiomes.generation.biome.BiomeList;
import jaredbgreat.climaticbiomes.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiomes.generation.biome.LeafBiome;
import jaredbgreat.climaticbiomes.generation.generator.ChunkTile;

public class GetColdPlains implements IBiomeSpecifier {
    private static GetColdPlains plains;
    private GetColdPlains() {
        super();
        init();
    }
    private BiomeList coldPlains;
    private IBiomeSpecifier alpine;

    public void init() {
        coldPlains = new BiomeList();
        DefReader.readBiomeData(coldPlains, "PlainsCold.cfg");
        if(coldPlains.isEmpty()) {
            coldPlains.addItem(new LeafBiome(1));
        }
        alpine = GetAlpine.getAlpine();
    }

    @Override
    public long getBiome(ChunkTile tile) {
        if((tile.getBiomeSeed() % 4) == 0) {
            return alpine.getBiome(tile);
        }
        tile.nextBiomeSeed();
        return coldPlains.getBiome(tile);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }


    public static GetColdPlains getPlains() {
        if(plains == null) {
            plains = new GetColdPlains();
        }
        return plains;
    }


    public void listOut() {
        coldPlains.listOut();
    }

}
