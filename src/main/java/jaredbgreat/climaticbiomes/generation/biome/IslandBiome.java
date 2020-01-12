package jaredbgreat.climaticbiomes.generation.biome;

import java.util.StringTokenizer;

import jaredbgreat.climaticbiomes.generation.biome.biomes.GetOcean;
import jaredbgreat.climaticbiomes.generation.generator.ChunkTile;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.IForgeRegistry;

public class IslandBiome extends AbstractTerminalSpecifier {
    private final long island;


    public IslandBiome(long island) {
        this.island = island;
    }


    public IslandBiome(Biome island) {
        this.island = getIdForBiome(island);
    }


    public IslandBiome(String island, IForgeRegistry biomeReg) {
        this.island = getBiomeNumber(island, biomeReg);
    }


    @Override
    public long getBiome(ChunkTile tile) {
        if(tile.getNoise() > (4 + (tile.getBiomeSeed() % 3))) {
            return island;
        }
        return GetOcean.getOceanForIslands().getForIsland(tile);
    }


    @Override
    public boolean isEmpty() {
        return (island < 0);
    }

}
