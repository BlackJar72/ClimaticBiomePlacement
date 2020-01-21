package jaredbgreat.climaticbiomes.generation.biome.biomes;

import jaredbgreat.climaticbiomes.configuration.ConfigHandler;
import jaredbgreat.climaticbiomes.generation.biome.AbstractTerminalSpecifier;
import jaredbgreat.climaticbiomes.generation.biome.BiomeList;
import jaredbgreat.climaticbiomes.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiomes.generation.biome.LeafBiome;
import jaredbgreat.climaticbiomes.generation.generator.ChunkTile;
import jaredbgreat.climaticbiomes.util.BiomeRegistrar;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;

public class GetBeach extends AbstractTerminalSpecifier {
    private static GetBeach beaches;
    private GetBeach() {
        super();
        init();
    }
    private static int frozen;
    private static int cold;
    private static int cool;
    private static int warm;
    private static int hot;
    private static int rock;
    private static int tbound;


    public void init() {
        if(ConfigHandler.useBoP || ConfigHandler.useBoPTable) {
            tbound = 6;
        } else {
            tbound = 7;
        }
        frozen    = getIdForBiome(Biomes.SNOWY_BEACH);
        cold      = getIdForBiome(BiomeRegistrar.coldBeach);
        cool      = getIdForBiome(BiomeRegistrar.coolBeach);
        warm      = getIdForBiome(Biomes.BEACH);
        hot       = getIdForBiome(BiomeRegistrar.hotBeach);
        rock      = getIdForBiome(Biomes.STONE_SHORE);
    }


    @Override
    public long getBiome(ChunkTile tile) {
        int temp = tile.getTemp();
        if(tile.getTemp() < tbound) {
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


    public long getHighBiome(ChunkTile tile, long nonBeach) {
        if((tile.getTemp() < 14) && (tile.getTemp() > tbound)) {
            return rock;
        } else return nonBeach;
    }


    @Override
    public boolean isEmpty() {
        return false;
    }


    public static GetBeach getBeach() {
        if(beaches == null) {
            beaches = new GetBeach();
        }
        return beaches;
    }


    public void listOut() {

    }

}
