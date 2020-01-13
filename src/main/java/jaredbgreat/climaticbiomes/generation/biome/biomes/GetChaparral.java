package jaredbgreat.climaticbiomes.generation.biome.biomes;

import jaredbgreat.climaticbiomes.compat.userdef.DefReader;
import jaredbgreat.climaticbiomes.generation.biome.BiomeList;
import jaredbgreat.climaticbiomes.generation.biome.SeedDoubleBiome;
import jaredbgreat.climaticbiomes.util.BiomeRegistrar;

public class GetChaparral extends BiomeList {
    private static GetChaparral scrub;
    private GetChaparral() {
        super();
        init();
    }


    public void init() {
        DefReader.readBiomeData(this, "ChaparralScrub.cfg"); // FIXME
        if(isEmpty()){
            addItem(new SeedDoubleBiome(
                    getIdForBiome(BiomeRegistrar.denseScrubHills), 3,
                    getIdForBiome(BiomeRegistrar.denseScrub)), 2);
            addItem(new SeedDoubleBiome(
                    getIdForBiome(BiomeRegistrar.dryScrubHills), 3,
                    getIdForBiome(BiomeRegistrar.dryScrub)));
        }
    }


    public static GetChaparral getChaparral() {
        if(scrub == null) {
            scrub = new GetChaparral();
        }
        return scrub;
    }
}
