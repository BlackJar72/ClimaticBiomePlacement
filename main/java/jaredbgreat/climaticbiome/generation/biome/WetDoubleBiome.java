package jaredbgreat.climaticbiome.generation.biome;

import java.util.StringTokenizer;

import jaredbgreat.climaticbiome.generation.generator.ChunkTile;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.IForgeRegistry;

public class WetDoubleBiome implements IBiomeSpecifier {
	private final long a, b;
	private final int boundary;
	
	
	public WetDoubleBiome(long a, int boundary, long b) {
		this.a = a;
		this.b = b;
		this.boundary = boundary;
	}
	
	
	public WetDoubleBiome(Biome a, int boundary, Biome b) {
		this.a = Biome.getIdForBiome(a);
		this.b = Biome.getIdForBiome(b);
		this.boundary = boundary;
	}
	
	
	public WetDoubleBiome(String a, int boundary, String b, IForgeRegistry biomeReg) {
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
		this.boundary = boundary;
	}
	

	@Override
	public long getBiome(ChunkTile tile) {
		int r = tile.getBiomeSeed();
		if((tile.getWet() + (r & 1) - ((r & 2) >> 1)) < boundary) {
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
