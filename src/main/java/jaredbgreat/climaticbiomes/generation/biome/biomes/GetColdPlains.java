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

    public void init() {
        coldPlains = new BiomeList();
        DefReader.readBiomeData(coldPlains, "PlainsCold.cfg"); // FIXME
        if(coldPlains.isEmpty()) {
            coldPlains.addItem(new LeafBiome(1), 2);
        }
    }

    @Override
    public long getBiome(ChunkTile tile) {
        if((tile.getBiomeSeed() % 3) == 0) {
            return 13;  // TODO: Once GetAlpine is more generic use that
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
