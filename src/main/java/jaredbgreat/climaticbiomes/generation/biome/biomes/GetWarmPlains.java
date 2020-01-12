package jaredbgreat.climaticbiomes.generation.biome.biomes;

import jaredbgreat.climaticbiomes.configuration.ConfigHandler;
import jaredbgreat.climaticbiomes.generation.biome.BiomeList;
import jaredbgreat.climaticbiomes.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiomes.generation.biome.SeedDoubleBiome;
import jaredbgreat.climaticbiomes.generation.generator.ChunkTile;

public class GetWarmPlains implements IBiomeSpecifier {
    private static GetWarmPlains plains;
    private GetWarmPlains() {
        super();
        init();
    }
    private GetPlains cool;
    private GetSavanna hot;
    private int tbound = 15;


    public void init() {
        cool = GetPlains.getPlains();
        hot  = GetSavanna.getSavanna();
        if(ConfigHandler.useBoP || ConfigHandler.useBoPTable) {
            tbound = 17;
        } else {
            tbound = 15;
        }
    }


    @Override
    public long getBiome(ChunkTile tile) {
        int t = tile.getTemp() - tbound;
        if((tile.getBiomeSeed() % 5) < t) {
            tile.nextBiomeSeed();
            return hot.getBiome(tile);
        }
        tile.nextBiomeSeed();
        return cool.getBiome(tile);
    }


    public static GetWarmPlains getPlains() {
        if(plains == null) {
            plains = new GetWarmPlains();
        }
        return plains;
    }


    @Override
    public boolean isEmpty() {
        return false;
    }


    public void listOut() {
        cool.listOut();
        hot.listOut();
    }

}
