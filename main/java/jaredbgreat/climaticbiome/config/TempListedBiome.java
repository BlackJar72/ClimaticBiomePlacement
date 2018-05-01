package jaredbgreat.climaticbiome.config;

import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.chunk.biomes.generic.GetListedBiome;

import java.util.ArrayList;

public class TempListedBiome extends AbstractTempBiome {

	public TempListedBiome(BiomeConfigurator config) {
		super(config);
	}

	@Override
	public IBiomeSpecifier setupSpecifier(String name) {
		GetListedBiome out = (GetListedBiome) config.specifiers.get(name);
		ArrayList<IBiomeSpecifier> temp = new ArrayList<>();
		int length = data.size();
		for(int i = 0; i < length; i += 2) {
			IBiomeSpecifier next = config.specifiers.get(data.get(i));
			int weight = Integer.parseInt(data.get(i+1));
			for(int j = 0; j < weight; j++) {
				temp.add(next);
			}
		}
		out.init((IBiomeSpecifier[])temp.toArray());
		return out;
	}

}
