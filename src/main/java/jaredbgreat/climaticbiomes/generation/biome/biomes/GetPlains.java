package jaredbgreat.climaticbiomes.generation.biome.biomes;

//import jaredbgreat.climaticbiomes.compat.userdef.DefReader;
import jaredbgreat.climaticbiomes.generation.biome.BiomeList;
import jaredbgreat.climaticbiomes.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiomes.generation.biome.SeedDoubleBiome;
import jaredbgreat.climaticbiomes.generation.generator.ChunkTile;

public class GetPlains implements IBiomeSpecifier {
    private static GetPlains grassland;
    private GetPlains() {
        super();
        init();
    }
    private BiomeList plains;
    private GetAlpine alpine;


    public void init() {
        plains = new BiomeList();
        alpine = GetAlpine.getAlpine();
        //DefReader.readBiomeData(plains, "Plains.cfg"); // FIXME
        if(plains.isEmpty()) {
            plains.addItem(new SeedDoubleBiome(129, 7, 1));
        }
    }



    @Override
    public long getBiome(ChunkTile tile) {
        int seed = tile.getBiomeSeed();
        if((seed % 4) == 0) {
            tile.nextBiomeSeed();
            return alpine.getBiome(tile);
        }
        tile.nextBiomeSeed();
        return plains.getBiome(tile);
    }


    public static GetPlains getPlains() {
        if(grassland == null) {
            grassland = new GetPlains();
        }
        return grassland;
    }


    /**
     * For mixing temperate and cool temperate zones in 
     * for use in classic temperature zones.
     */
    public void collapseCool() {
        plains.merge(GetCoolPlains.getPlains().getList());
    }



    @Override
    public boolean isEmpty() {
        return false;
    }


    public void listOut() {
        plains.listOut();
        alpine.listOut();
    }

}
