package jaredbgreat.climaticbiomes.generation.biome.biomes;

//import jaredbgreat.climaticbiomes.compat.userdef.DefReader;
import jaredbgreat.climaticbiomes.generation.biome.BiomeList;
import jaredbgreat.climaticbiomes.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiomes.generation.generator.ChunkTile;

public class GetPark implements IBiomeSpecifier {
    private static GetPark pland;
    private GetPark() {
        super();
        init();
    }
    private BiomeList parks;
    private GetPlains plains;
    private GetForest woods;


    public void init() {
        parks = new BiomeList();
        plains = GetPlains.getPlains();
        woods = GetForest.getForest();
        //DefReader.readBiomeData(parks, "Parkland.cfg");
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


    public static GetPark getPark() {
        if(pland == null) {
            pland = new GetPark();
        }
        return pland;
    }


    /**
     * For mixing temperate and cool temperate zones in 
     * for use in classic temperature zones.
     */
    public void collapseCoole() {
        parks.merge(GetCoolPark.getPark().getList());
        // These have been merges elsewhere so get rid of the copies
        parks.remove(GetCoolForest.getForest());
        parks.remove(GetCoolPlains.getPlains());
    }


    @Override
    public boolean isEmpty() {
        return false;
    }

}
