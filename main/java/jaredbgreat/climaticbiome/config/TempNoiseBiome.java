package jaredbgreat.climaticbiome.config;

import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.chunk.biomes.generic.GetNoiseBiome;

public class TempNoiseBiome extends AbstractTempBiome {

	public TempNoiseBiome(BiomeConfigurator config) {
		super(config);
	}

	@Override
	public IBiomeSpecifier setupSpecifier(String name) {
		GetNoiseBiome out = (GetNoiseBiome)config.specifiers.get(name);
		int bound;
		IBiomeSpecifier a, b;
		a = config.specifiers.get(data.get(0));
		bound = Integer.parseInt(data.get(1));
		b = config.specifiers.get(data.get(2));
		out.init(a, bound, b);
		return out;
	}

}
