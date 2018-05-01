package jaredbgreat.climaticbiome.config;

import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTempBiome {
	BiomeConfigurator config;
	List<String> data;
	
	
	public AbstractTempBiome(BiomeConfigurator config) {
		this.config = config;
		data = new ArrayList<>();
	}
	
	
	public void addString(String token) {
		data.add(token);
	}
	
	
	public void cleanup() {
		config = null;
		data   = null;
	}
	
	
	public abstract IBiomeSpecifier setupSpecifier(String name);
}
