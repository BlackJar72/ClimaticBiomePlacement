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
	
	// FIXME / TODO: Re-write this using GSon based JSon files...
	
/*
 * Plan: Load in BiomeGroup objects with GSon, then construct a tree using those.
 * Question, do I need to have the AbstractTempBiome and its subclasses, or just 
 * build the final tree of IBiomeSpecifiers directly from a list of tree of
 * BiomeGroupe objects.
 * 
 * System Idea 1: Build TempBiomes from the BiomeGroup objects, then build the 
 * actual IBiomeSpecifier decision tree from the TempBiomes.
 * 
 * System Idea 2: Process the BiomeGroup records (objects) directly into an 
 * ISpecifier decision tree use a two pass system.  The first pass reads the 
 * name and type and creates the IBiomeSpecifier objects without assigning 
 * fields, storing them into a HashMap.  The second pass then fills in the 
 * data for each IBiomeSpecifier, effectively linking them into a tree (or 
 * trees if multiple configurations is eventually allowed).
 * 
 * (At the moment, System 2 seems the better and cleaner approach.)
 *
 */
	
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
