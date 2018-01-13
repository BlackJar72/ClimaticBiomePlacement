package jaredbgreat.climaticbiome.config;

import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

import java.util.ArrayList;
import java.util.HashMap;

public class BiomeConfigurator {
	HashMap<String, IBiomeSpecifier>   specifiers;
	HashMap<String, AbstractTempBiome> temps;
	ArrayList<String> identifiers;

}
