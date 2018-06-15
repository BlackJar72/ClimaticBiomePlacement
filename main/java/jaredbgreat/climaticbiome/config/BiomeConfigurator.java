package jaredbgreat.climaticbiome.config;

import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.chunk.biomes.generic.BiomeTable;
import jaredbgreat.climaticbiome.generation.chunk.biomes.generic.GetIslandBiome;
import jaredbgreat.climaticbiome.generation.chunk.biomes.generic.GetLeafBiome;
import jaredbgreat.climaticbiome.generation.chunk.biomes.generic.GetListedBiome;
import jaredbgreat.climaticbiome.generation.chunk.biomes.generic.GetNoiseBiome;
import jaredbgreat.climaticbiome.generation.chunk.biomes.generic.GetSingleBiome;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BiomeConfigurator {
	File file;
	private static final String delim = " ,\t";
	private boolean inBlock;
	HashMap<String, IBiomeSpecifier>   specifiers;
	HashMap<String, AbstractTempBiome> temps;
	ArrayList<String> identifiers;
	
	public BiomeConfigurator(File file) {
		if(!file.exists() || !file.canRead() || !file.isFile()) {
			System.err.println("ERROR: The file " + file + " could not be read!");
			return;
		}
		this.file = file;
		specifiers  = new HashMap<>();
		temps       = new HashMap<>();
		identifiers = new ArrayList();
	}
}
