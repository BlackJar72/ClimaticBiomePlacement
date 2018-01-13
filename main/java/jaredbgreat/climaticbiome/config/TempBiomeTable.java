package jaredbgreat.climaticbiome.config;

import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.chunk.biomes.generic.BiomeTable;

public class TempBiomeTable extends AbstractTempBiome {

	public TempBiomeTable(BiomeConfigurator config) {
		super(config);
	}

	@Override
	public IBiomeSpecifier setupSpecifier(String name) {
		BiomeTable out = (BiomeTable)config.specifiers.get(name);
		IBiomeSpecifier[] table = new IBiomeSpecifier[BiomeTable.SIZE];
		for(int i = 0; i < BiomeTable.SIZE; i++) {
			table[i] = config.specifiers.get(data.get(i));
		}
		out.init(table);
		return out;
	}

}
