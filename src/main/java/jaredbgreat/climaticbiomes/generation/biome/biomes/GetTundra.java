package jaredbgreat.climaticbiomes.generation.biome.biomes;

//import jaredbgreat.climaticbiomes.compat.userdef.DefReader;
import jaredbgreat.climaticbiomes.generation.biome.BiomeList;
import jaredbgreat.climaticbiomes.generation.biome.LeafBiome;
import jaredbgreat.climaticbiomes.generation.biome.TempDoubleBiome;

public class GetTundra extends BiomeList {
    private static GetTundra tundra;
    private GetTundra() {
        super();
        init();
    }


    public void init() {
        //DefReader.readBiomeData(this, "Tundra.cfg"); //FIXME
        if(isEmpty()) {
            addItem(new LeafBiome(12), 5);
            addItem(new TempDoubleBiome(140, 2, 12));
            addItem(new TempDoubleBiome(12,  2, 30));
        }
    }


    public static GetTundra getTundra() {
        if(tundra == null) {
            tundra = new GetTundra();
        }
        return tundra;
    }

}
