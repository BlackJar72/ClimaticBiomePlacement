package jaredbgreat.climaticbiome.generation.biome;

import jaredbgreat.climaticbiome.generation.mapgenerator.ChunkTile;

import java.util.StringTokenizer;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.IForgeRegistry;

public class MesaDoubleBiome extends AbstractTerminalSpecifier {
	private final long a, b;
	private final int boundary;
	
	
	public MesaDoubleBiome(long a, int boundary, long b) {
		this.a = a;
		this.b = b;
		this.boundary = boundary;
	}
	
	
	public MesaDoubleBiome(Biome a, int boundary, Biome b) {
		this.a = Biome.getIdForBiome(a);
		this.b = Biome.getIdForBiome(b);
		this.boundary = boundary;
	}
	
	
	public MesaDoubleBiome(String a, int boundary, String b, IForgeRegistry biomeReg) {
		this.a = getBiomeNumber(a, biomeReg);
		this.b = getBiomeNumber(b, biomeReg);
		this.boundary = boundary;
	}
	

	@Override
	public long getBiome(ChunkTile tile) {
		tile.setPlateau();
		if(tile.getNoise() < boundary) {
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
