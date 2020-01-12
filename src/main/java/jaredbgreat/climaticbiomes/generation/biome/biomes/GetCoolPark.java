package jaredbgreat.climaticbiomes.generation.biome.biomes;

//import jaredbgreat.climaticbiomes.compat.userdef.DefReader;
import jaredbgreat.climaticbiomes.generation.biome.BiomeList;
import jaredbgreat.climaticbiomes.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiomes.generation.generator.ChunkTile;

public class GetCoolPark implements IBiomeSpecifier {
    private static GetCoolPark pland;
    private GetCoolPark() {
        super();
        init();
    }
    private BiomeList parks;
    private GetCoolPlains plains;
    private GetCoolForest woods;


    public void init() {
        parks = new BiomeList();
        plains = GetCoolPlains.getPlains();
        woods = GetCoolForest.getForest();
        //DefReader.readBiomeData(parks, "ParklandCool.cfg"); FIXME
    }


    @Override
    public long getBiome(ChunkTile tile) {
        int seed = tile.getBiomeSeed();
        if(parks.isEmpty() || ((seed & 5) == 0)) {
            if((seed & 1) == 0) {
                tile.nextBiomeSeed();
                return woods.getBiome(tile);
            } else {
                tile.nextBiomeSeed();
                return plains.getBiome(tile);
            }
        }
        tile.nextBiomeSeed();
        return parks.getBiome(tile);
    }


    BiomeList getList() {
        return parks;
    }


    public static GetCoolPark getPark() {
        if(pland == null) {
            pland = new GetCoolPark();
        }
        return pland;
    }


    @Override
    public boolean isEmpty() {
        return false;
    }

}
