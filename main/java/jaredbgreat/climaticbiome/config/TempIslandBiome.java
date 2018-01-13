package jaredbgreat.climaticbiome.config;

import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.chunk.biomes.generic.GetIslandBiome;

public class TempIslandBiome extends AbstractTempBiome {

	public TempIslandBiome(BiomeConfigurator config) {
		super(config);
	}

	@Override
	public IBiomeSpecifier setupSpecifier(String name) {
		GetIslandBiome out = (GetIslandBiome)config.specifiers.get(name);
		if(data.isEmpty()) {
			out.init();
		} else {
			out.init(getSpecifierFromString(data.get(0)), 
					 getSpecifierFromString(data.get(1)));
		}		
		return out;
	}
	
	
	private IBiomeSpecifier getSpecifierFromString(String in) {
		if(in.toLowerCase().equals("null")) {
			return null;
		} else {
			return config.specifiers.get(in);
		}
	}

}
