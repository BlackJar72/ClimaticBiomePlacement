package jaredbgreat.climaticbiomes.generation.biome.biomes;

import jaredbgreat.climaticbiomes.compat.userdef.DefReader;
import jaredbgreat.climaticbiomes.configuration.ConfigHandler;
import jaredbgreat.climaticbiomes.generation.biome.AbstractTerminalSpecifier;
import jaredbgreat.climaticbiomes.generation.biome.BiomeList;
import jaredbgreat.climaticbiomes.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiomes.generation.biome.LeafBiome;
import jaredbgreat.climaticbiomes.generation.generator.ChunkTile;
import jaredbgreat.climaticbiomes.util.BiomeRegistrar;

public class GetWarmForest implements IBiomeSpecifier {
    private static GetWarmForest sforest;
    private GetWarmForest() {
        super();
        init();
    }
    private BiomeList forests;
    private GetAlpine alpine;
    private GetPlains plains;
    private GetSwamp  swamp;


    public void init() {
        forests = new BiomeList();
        alpine  = GetAlpine.getAlpine();
        plains  = GetPlains.getPlains();
        swamp   = GetSwamp.getSwamp();
        DefReader.readBiomeData(forests, "ForestWarm.cfg");
        if(forests.isEmpty()) {
            if(ConfigHandler.includeForests) {
                forests.addItem(new LeafBiome(AbstractTerminalSpecifier
                        .getIdForBiome(BiomeRegistrar.warmForest)), 5);
                forests.addItem(new LeafBiome(AbstractTerminalSpecifier
                        .getIdForBiome(BiomeRegistrar.warmForestHills)), 3);
                forests.addItem(new LeafBiome(AbstractTerminalSpecifier
                        .getIdForBiome(BiomeRegistrar.pinewoods)));
            } else {
                forests.addItem(GetForest.getForest());
            }
        }
    }


    @Override
    public long getBiome(ChunkTile tile) {
        int role1 = tile.getBiomeSeed() % 5;
        int role2 = tile.getBiomeSeed() % 7;
        int role3 = tile.getBiomeSeed() % 12;
        tile.nextBiomeSeed();
        if((role1) == 0) {
            return alpine.getBiome(tile);
        }
        if((role2) == 0) {
            return swamp.getBiome(tile);
        }
        if((role3) == 0) {
            return plains.getBiome(tile);
        }
        return forests.getBiome(tile);
    }


    public static GetWarmForest getForest() {
        if(sforest == null) {
            sforest = new GetWarmForest();
        }
        return sforest;
    }


    @Override
    public boolean isEmpty() {
        return false;
    }


    public void listOut() {
        forests.listOut();
        alpine.listOut();
        plains.listOut();
        swamp.listOut();
    }


}
