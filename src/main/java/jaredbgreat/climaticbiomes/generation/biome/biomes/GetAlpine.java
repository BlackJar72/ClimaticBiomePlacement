package jaredbgreat.climaticbiomes.generation.biome.biomes;

import jaredbgreat.climaticbiomes.compat.userdef.DefReader;
import jaredbgreat.climaticbiomes.generation.biome.BiomeList;
import jaredbgreat.climaticbiomes.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiomes.generation.biome.LeafBiome;
import jaredbgreat.climaticbiomes.generation.biome.NoiseDoubleBiome;
import jaredbgreat.climaticbiomes.generation.generator.ChunkTile;

import static jaredbgreat.climaticbiomes.compat.userdef.DefReader.readBiomeData;

public class GetAlpine implements IBiomeSpecifier {
    private static GetAlpine alpine;
    private GetAlpine() {
        super();
        init();
    }

    // TODO: More variants; make generic for all climate zones

    BiomeList cold;
    BiomeList wet;
    BiomeList dry;
    BiomeList warmwet;
    BiomeList warmdry;
    BiomeList hotwet;
    BiomeList hotdry;

    public void init() {
        cold    = new BiomeList();
        wet     = new BiomeList();
        dry     = new BiomeList();
        warmwet = new BiomeList();
        warmdry = new BiomeList();
        hotwet  = new BiomeList();
        hotdry = new BiomeList();
        readBiomeData(cold, "AplineCold.cfg");
        readBiomeData(wet,  "AplineWet.cfg");
        readBiomeData(dry,  "AplineDry.cfg");
        readBiomeData(warmwet, "AplineWetWarm.cfg");
        readBiomeData(warmdry, "AplineDryWarm.cfg");
        readBiomeData(hotwet,  "AplineWetHot.cfg");
        readBiomeData(hotdry,  "AplineDryHot.cfg");
        if(wet.isEmpty()) {
            wet.addItem(new LeafBiome(34));
            wet.addItem(new LeafBiome(162));
            wet.addItem(new NoiseDoubleBiome(34, 5, 162));
        }
        if(dry.isEmpty()) {
            dry.addItem(new LeafBiome(3));
            dry.addItem(new LeafBiome(131));
            dry.addItem(new NoiseDoubleBiome(3, 5, 131));
        }
        if(cold.isEmpty()) {
            cold.addItem(new LeafBiome(13));
        }
        if(warmwet.isEmpty()) warmwet = wet;
        if(warmdry.isEmpty()) warmdry = dry;
        if(hotwet.isEmpty())  hotwet = warmwet;
        if(hotdry.isEmpty())  hotdry = warmwet;
    }

    @Override
    public long getBiome(ChunkTile tile) {
        boolean plus = (tile.getBiomeSeed() % 7) < tile.getWet();
        tile.nextBiomeSeed();
        int t = tile.getTemp();
        if(t < 8) {
            return cold.getBiome(tile);
        } else if(t < 15) {
            if(plus) {
                return wet.getBiome(tile);
            } else {
                return dry.getBiome(tile);
            }
        } else if(t < 20) {
            if(plus) {
                return warmwet.getBiome(tile);
            } else {
                return warmdry.getBiome(tile);
            }
        } else {
            if(plus) {
                return hotwet.getBiome(tile);
            } else {
                return hotdry.getBiome(tile);
            }
        }
    }


    public static GetAlpine getAlpine() {
        if(alpine == null) {
            alpine = new GetAlpine();
        }
        return alpine;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }


    public void listOut() {
        cold.listOut();
        wet.listOut();
        dry.listOut();
        warmwet.listOut();
        warmdry.listOut();
        hotwet.listOut();
        hotdry.listOut();
    }
}
