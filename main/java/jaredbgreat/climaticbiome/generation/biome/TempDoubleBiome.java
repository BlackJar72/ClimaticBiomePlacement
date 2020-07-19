package jaredbgreat.climaticbiome.generation.biome;

import java.util.StringTokenizer;

import jaredbgreat.climaticbiome.generation.biomeprovider.ChunkTile;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.IForgeRegistry;

public class TempDoubleBiome extends AbstractTerminalSpecifier {
	private final long a, b;
	private final int boundary;
	
	
	public TempDoubleBiome(long a, int boundary, long b) {
		this.a = a;
		this.b = b;
		this.boundary = boundary;
	}
	
	
	public TempDoubleBiome(Biome a, int boundary, Biome b) {
		this.a = Biome.getIdForBiome(a);
		this.b = Biome.getIdForBiome(b);
		this.boundary = boundary;
	}
	
	
	public TempDoubleBiome(String a, int boundary, String b, IForgeRegistry biomeReg) {
		this.a = getBiomeNumber(a, biomeReg);
		this.b = getBiomeNumber(b, biomeReg);
		this.boundary = boundary;
	}
	

	@Override
	public long getBiome(ChunkTile tile) {
		int r = tile.getBiomeSeed();
		if((tile.getTemp() + (r & 1) - ((r & 2) >> 1)) < boundary) {
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
