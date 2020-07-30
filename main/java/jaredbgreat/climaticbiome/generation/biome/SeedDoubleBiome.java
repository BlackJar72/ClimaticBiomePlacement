package jaredbgreat.climaticbiome.generation.biome;

import java.util.StringTokenizer;

import jaredbgreat.climaticbiome.generation.mapgenerator.ChunkTile;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.IForgeRegistry;

public class SeedDoubleBiome extends AbstractTerminalSpecifier {
	private final long a, b;
	private final int chance;
	
	
	public SeedDoubleBiome(long a, int chance, long b) {
		this.a = a;
		this.b = b;
		this.chance = chance;
	}
	
	
	public SeedDoubleBiome(Biome a, int chance, Biome b) {
		this.a = Biome.getIdForBiome(a);
		this.b = Biome.getIdForBiome(b);
		this.chance = chance;
	}
	
	
	public SeedDoubleBiome(String a, int chance, String b, IForgeRegistry biomeReg) {
		this.a = getBiomeNumber(a, biomeReg);
		this.b = getBiomeNumber(b, biomeReg);
		this.chance = chance;
	}
	

	@Override
	public long getBiome(ChunkTile tile) {
		tile.nextBiomeSeed();
		if((tile.getBiomeSeed() % chance) == 0) {
			return a;
		} else {
			return b;
		}
	}


	@Override
	public boolean isEmpty() {
		return ((a < 0) || (b < 0));
	}



}
