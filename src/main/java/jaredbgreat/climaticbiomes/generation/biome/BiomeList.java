package jaredbgreat.climaticbiomes.generation.biome;

import jaredbgreat.climaticbiomes.generation.generator.ChunkTile;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BiomeList implements IBiomeSpecifier {
    private final List<IBiomeSpecifier> list;


    public BiomeList() {
        list = new ArrayList<>();
    }


    @Override
    public long getBiome(ChunkTile tile) {
        tile.nextBiomeSeed();
        try {
            return list.get(tile.getBiomeSeed() % list.size())
                    .getBiome(tile);
        } catch (ArithmeticException ex) {
            Logger.getLogger("Minecraft").log(Level.SEVERE,
                    "A biome was requested from an empty biome list!  "
                            + "\nAll lists must contain at least one biome (fix your configs)."
                    , ex);
            throw ex;
        }
    }


    public void addItem(IBiomeSpecifier biome, int n) {
        for(int i = 0; i < n; i++) {
            list.add(biome);
        }
    }


    public void addItem(IBiomeSpecifier biome) {
        list.add(biome);
    }


    public void addItems(IBiomeSpecifier... biomes) {
        for(IBiomeSpecifier biome : biomes) {
            list.add(biome);
        }
    }


    public void addItems(List<IBiomeSpecifier> biomes) {
        list.addAll(biomes);
    }


    public boolean remove(IBiomeSpecifier bs) {
        return list.remove(bs);
    }


    public boolean merge(BiomeList other) {
        return list.addAll(other.list);
    }


    public boolean isEmpty() {
        return list.isEmpty();
    }


    public int size() {
        return list.size();
    }


    // TODO: This is a place to hook in per map data later.
    protected int getIdForBiome(Biome b) {
        return Registry.BIOME.getId(b);
    }


}
