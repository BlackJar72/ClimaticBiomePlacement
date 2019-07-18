package jaredbgreat.climaticbiome.generation.biome;

import java.util.StringTokenizer;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.IForgeRegistry;

public abstract class AbstractTerminalSpecifier implements IBiomeSpecifier {


	public long getBiomeNumber(String bstring, IForgeRegistry biomeReg) {		
		StringTokenizer tokens = new StringTokenizer(bstring, ":");
		if(tokens.countTokens() < 3) {
			Biome biome = (Biome)biomeReg.getValue(new ResourceLocation(bstring));
			if(biome == null) {
				return -1;
			}
			return Biome.getIdForBiome(biome);
		} else {
			Biome biome = (Biome)biomeReg
					.getValue(new ResourceLocation(tokens.nextToken() 
							+ ":" + tokens.nextToken()));
			if(biome == null) {
				return -1;
			}
			return Biome.getIdForBiome(biome)
					+ (Long.parseLong(tokens.nextToken()) << 32);
		}		
	}

}
