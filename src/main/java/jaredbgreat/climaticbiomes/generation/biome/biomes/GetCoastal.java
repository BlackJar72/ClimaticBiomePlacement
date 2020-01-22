package jaredbgreat.climaticbiomes.generation.biome.biomes;

import jaredbgreat.climaticbiomes.generation.biome.AbstractTerminalSpecifier;
import jaredbgreat.climaticbiomes.generation.generator.ChunkTile;
import jaredbgreat.climaticbiomes.util.BiomeRegistrar;

public class GetCoastal extends AbstractTerminalSpecifier {
    private static GetCoastal coastal;
    public GetCoastal() {
        super();
        init();
    }
    private static int frozen;
    private static int cold;
    private static int cool;
    private static int warm;
    private static int hot;


    private void init() {
        frozen    = getIdForBiome(BiomeRegistrar.frozenCoast);
        cold      = getIdForBiome(BiomeRegistrar.coldCoast);
        cool      = getIdForBiome(BiomeRegistrar.coolCoast);
        warm      = getIdForBiome(BiomeRegistrar.warmCoast);
        hot       = getIdForBiome(BiomeRegistrar.hotCoast);
    }


    @Override
    public long getBiome(ChunkTile tile) {
        int iceNoise = tile.getNoise();
        int temp = tile.getTemp();
        if(((iceNoise / 2) - temp) > -2) {
            return frozen;
        }
        if(temp < 10) {
            return cold;
        }
        if(temp < 16) {
            return cool;
        }
        if(temp < 20) {
            return warm;
        }
        return hot;
    }


    @Override
    public boolean isEmpty() {
        return false;
    }


    @Override
    public void listOut() {/*Do Nothing*/}


    public static GetCoastal getCoastal() {
        if(coastal == null) {
            coastal = new GetCoastal();
        }
        return coastal;
    }
}
