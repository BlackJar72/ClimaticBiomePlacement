package jaredbgreat.climaticbiome.generation.biome;

import java.util.StringTokenizer;

import jaredbgreat.climaticbiome.generation.generator.ChunkTile;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.IForgeRegistry;

public class SeedDoubleBiome implements IBiomeSpecifier {
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
		StringTokenizer tokens = new StringTokenizer(a, ":");
		if(tokens.countTokens() < 3) {
			this.a = Biome.getIdForBiome((Biome)biomeReg.getValue(new ResourceLocation(a)));
		} else {
			this.a = Biome.getIdForBiome((Biome)biomeReg
					.getValue(new ResourceLocation(tokens.nextToken() + ":" + tokens.nextToken())))
					+ (Long.parseLong(tokens.nextToken()) << 32);
		}
		tokens = new StringTokenizer(b, ":");
		if(tokens.countTokens() < 3) {
			this.b = Biome.getIdForBiome((Biome)biomeReg.getValue(new ResourceLocation(b)));
		} else {
			this.b = Biome.getIdForBiome((Biome)biomeReg
					.getValue(new ResourceLocation(tokens.nextToken() + ":" + tokens.nextToken())))
					+ (Long.parseLong(tokens.nextToken()) << 32);
		}
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
		return false;
	}



}
