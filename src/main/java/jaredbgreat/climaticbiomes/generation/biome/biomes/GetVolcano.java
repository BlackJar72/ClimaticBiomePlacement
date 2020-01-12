package jaredbgreat.climaticbiomes.generation.biome.biomes;

//import jaredbgreat.climaticbiomes.compat.userdef.DefReader;
import jaredbgreat.climaticbiomes.configuration.ConfigHandler;
import jaredbgreat.climaticbiomes.generation.biome.BiomeList;
import jaredbgreat.climaticbiomes.generation.biome.LeafBiome;
import jaredbgreat.climaticbiomes.util.BiomeRegistrar;

public class GetVolcano extends BiomeList {
    private static GetVolcano volcanoes;
    private GetVolcano() {
        super();
        init();
    }


    public void init() {
        //DefReader.readBiomeData(this, "IslandVolcanoes.cfg");//FIXME
        if(isEmpty()) {
            if(false/*ConfigHandler.includeMountains*/) { // FIXME too....
                //addItem(new LeafBiome(BiomeRegistrar.activeVolcano));
            } else {
                addItem(GetAlpine.getAlpine());
            }
        }
    }


    public static GetVolcano getVolcanoes() {
        if(volcanoes == null) {
            volcanoes = new GetVolcano();
        }
        return volcanoes;
    }

}
