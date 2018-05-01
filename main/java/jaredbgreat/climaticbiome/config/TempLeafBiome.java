package jaredbgreat.climaticbiome.config;

import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.chunk.biomes.generic.GetLeafBiome;

public class TempLeafBiome extends AbstractTempBiome {

	public TempLeafBiome(BiomeConfigurator config) {
		super(config);
	}

	@Override
	public IBiomeSpecifier setupSpecifier(String name) {
		GetLeafBiome out = (GetLeafBiome)config.specifiers.get(name);
		int main = 0, hill =  -1, mutated = -1, mhill = -1, mountain = -1;
		int length = data.size();
		for(int i = 0; i < length; i += 2) {
			String s = data.get(i);
			int   id = Integer.parseInt(data.get(i+1));
			if(s.equals("main")) {
				main = id;
			} else if(s.equals("hills")) {
				hill = id;
			} else if(s.equals("mutated")) {
				mutated = id;
			} else if(s.equals("mhills")) {
				mhill = id;
			} else if(s.equals("mountain")) {
				mountain = id;
			}
		}
		if(hill == -1) {
			hill = main;
		}
		if(mutated == -1) {
			mutated = main;
		}
		if(mhill == -1) {
			mhill = hill;
		}
		if(mountain == -1) {
			mountain = hill;
		}
		out.init(main, hill, mutated, mhill, mountain);
		return out;
	}

}
