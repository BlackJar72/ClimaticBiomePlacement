package jaredbgreat.climaticbiome.config;

import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.chunk.biomes.generic.GetSingleBiome;

public class TempSingleBiome extends AbstractTempBiome {

	public TempSingleBiome(BiomeConfigurator config) {
		super(config);
	}

	
	@Override
	public IBiomeSpecifier setupSpecifier(String name) {
		GetSingleBiome out = (GetSingleBiome)config.specifiers.get(name);
		int id = Integer.parseInt(data.get(0));
		out.init(id);
		return out;
	}
}
