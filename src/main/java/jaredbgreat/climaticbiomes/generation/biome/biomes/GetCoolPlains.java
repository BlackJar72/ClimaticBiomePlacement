package jaredbgreat.climaticbiomes.generation.biome.biomes;

import jaredbgreat.climaticbiomes.compat.userdef.DefReader;
import jaredbgreat.climaticbiomes.generation.biome.BiomeList;
import jaredbgreat.climaticbiomes.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiomes.generation.biome.LeafBiome;
import jaredbgreat.climaticbiomes.generation.generator.ChunkTile;

/**
 * This specifer is specifically for words using biome mods that 
 * imply a cool temperate band between the taiga zone and the 
 * basic temperate zone.
 *
 * @author JaredBGreat (Jared Blackburn)
 */
public class GetCoolPlains implements IBiomeSpecifier  {
    private static GetCoolPlains grassland;
    private GetCoolPlains() {
        super();
        init();
    }
    private BiomeList plains;
    private GetAlpine alpine;


    public void init() {
        plains = new BiomeList();
        alpine = GetAlpine.getAlpine();
        DefReader.readBiomeData(plains, "PlainsCool.cfg");
        if(plains.isEmpty()) {
            plains.addItem(new LeafBiome(1));
        }
    }


    @Override
    public long getBiome(ChunkTile tile) {
        int seed = tile.getBiomeSeed();
        if((seed % 4) == 0) {
            tile.nextBiomeSeed();
            return alpine.getBiome(tile);
        }
        tile.nextBiomeSeed();
        return plains.getBiome(tile);
    }


    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }


    BiomeList getList() {
        return plains;
    }


    public static GetCoolPlains getPlains() {
        if(grassland == null) {
            grassland = new GetCoolPlains();
        }
        return grassland;
    }


    public void listOut() {
        plains.listOut();
        alpine.listOut();
    }

}
