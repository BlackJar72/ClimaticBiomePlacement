package jaredbgreat.climaticbiomes.generation.biome.biomes;

import jaredbgreat.climaticbiomes.configuration.ConfigHandler;
import jaredbgreat.climaticbiomes.generation.biome.BiomeList;
import jaredbgreat.climaticbiomes.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiomes.generation.biome.LeafBiome;
import jaredbgreat.climaticbiomes.generation.generator.ChunkTile;
import net.minecraft.world.biome.Biome;

public class GetBeach implements IBiomeSpecifier {
    private static GetBeach beaches;
    private GetBeach() {
        super();
        init();
    }
    private BiomeList cold;
    private BiomeList cool;
    private BiomeList rock;
    private BiomeList temporate;
    private BiomeList warm;
    private BiomeList hot;
    private static int tbound;


    public void init() {
        if(ConfigHandler.useBoP || ConfigHandler.useBoPTable) {
            tbound = 6;
        } else {
            tbound = 7;
        }
        cold = new BiomeList();
        rock = new BiomeList();
        warm = new BiomeList();
        // TODO: Make configurable
//        cold.addItem(new LeafBiome(Biome.getBiome(26)));
//        rock.addItem(new LeafBiome(Biome.getBiome(25)));
//        warm.addItem(new LeafBiome(Biome.getBiome(16)));
    }


    @Override
    public long getBiome(ChunkTile tile) {
        if(tile.getTemp() < tbound) {
            return cold.getBiome(tile);
        }
        return warm.getBiome(tile);
    }


    public long getHighBiome(ChunkTile tile) {
        if(tile.getTemp() < tbound) {
            return rock.getBiome(tile);
        } else return warm.getBiome(tile);//return 0;
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

}
