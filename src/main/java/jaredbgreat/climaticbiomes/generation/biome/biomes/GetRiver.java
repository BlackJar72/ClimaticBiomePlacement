package jaredbgreat.climaticbiomes.generation.biome.biomes;

import jaredbgreat.climaticbiomes.generation.biome.AbstractTerminalSpecifier;
import jaredbgreat.climaticbiomes.util.BiomeRegistrar;
import jaredbgreat.climaticbiomes.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiomes.generation.generator.ChunkTile;

public class GetRiver implements IBiomeSpecifier {
    private static GetRiver river;
    private GetRiver() {
        super();
    }
    private static int frozen;
    private static int cool;
    private static int temperate;
    private static int warm;
    private static int hot;

    // TODO: Call this fuctions!
    public void init() {
        frozen    = AbstractTerminalSpecifier.getIdForBiome(BiomeRegistrar.iceRiver);
        cool      = AbstractTerminalSpecifier.getIdForBiome(BiomeRegistrar.coolRiver);
        temperate = AbstractTerminalSpecifier.getIdForBiome(BiomeRegistrar.river);
        warm      = AbstractTerminalSpecifier.getIdForBiome(BiomeRegistrar.warmRiver);
        hot       = AbstractTerminalSpecifier.getIdForBiome(BiomeRegistrar.hotRiver);
    }


    @Override
    public long getBiome(ChunkTile tile) {
        int t = tile.getTemp();
        if(t < 5) {
            return frozen;
        } else if(t < 10) {
            return cool;
        } else if(t < 16) {
            return temperate;
        } else if(t < 20) {
            return warm;
        } else {
            return hot;
        }
    }


    public static GetRiver getRiver() {
        if(river == null) {
            river = new GetRiver();
        }
        return river;
    }


    @Override
    public boolean isEmpty() {
        return false;
    }

}
