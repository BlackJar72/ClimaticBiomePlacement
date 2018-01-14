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
	private static final String delim = " ,\t";
	private boolean inBlock;
	HashMap<String, IBiomeSpecifier>   specifiers;
	HashMap<String, AbstractTempBiome> temps;
	ArrayList<String> identifiers;
	
	
	BiomeConfigurator(File file) {
		if(!file.exists() || !file.canRead() || !file.isFile()) {
			System.err.println("ERROR: The file " + file + " could not be read!");
			return;
		}
		specifiers  = new HashMap<>();
		temps       = new HashMap<>();
		identifiers = new ArrayList();
		try {
			readFile(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(String name : identifiers) {
			temps.get(name).setupSpecifier(name);
		}
	}
	
	
	/**
	 * This will read and parse a file into IBiomeSpecifiers.
	 * 
	 * This includes recognition of comments, blocks, and the 
	 * ability to import additional files.
	 * 
	 * @param file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void readFile(File file) throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		StringTokenizer tokens = null;
		AbstractTempBiome specifier;
		inBlock = false;
		String line, token1, token3;
		while((line = reader.readLine()) != null) {
			if(line.startsWith("#")) {
				continue;
			}
			tokens = new StringTokenizer(line, delim);
			token1 = tokens.nextToken();
			if(token1.toLowerCase().equals("import")) {
				readFile(new File(tokens.nextToken()));
			} else {
				specifier = addSpecifier(token1, tokens.nextToken());
				if((token3 = tokens.nextToken()).equals("{")) {
					readData(reader, tokens, specifier);
				} else {
					specifier.addString(token3);
				}
			}
			
		}			
	}
	
	
	/**
	 * Read data for a a specific IBiomeSpecifier (or more accurately,
	 * for its temporary stand-in, an AbstractTempBiome) from a block 
	 * of data.  Such blocks are delimited with curly braces.
	 * 
	 * @param reader
	 * @param tokens
	 * @param specifier
	 * @throws IOException
	 */
	private void readData(BufferedReader reader, StringTokenizer tokens, 
				AbstractTempBiome specifier) throws IOException {
		if(readDataLine(tokens, specifier)) {
			return;
		}
		String line;
		while((line = reader.readLine()) != null) {
			if(line.startsWith("#")) {
				continue;
			}
			tokens = new StringTokenizer(line, delim);
			if(readDataLine(tokens, specifier)) {
				return;
			}
		}
	}
	
	
	/**
	 * Reads one line of data for a specific IBiomeSpecifier (or more 
	 * accurately, for its temporary stand-in, an AbstractTempBiome).
	 * 
	 * Returns true if the end of the block was reached, otherwise 
	 * returns false.
	 * 
	 * @param tokens
	 * @param specifier
	 * @return
	 */
	private boolean readDataLine(StringTokenizer tokens, 
				AbstractTempBiome specifier) {
		String token;
		while(tokens.hasMoreTokens()) {
			token = tokens.nextToken();
			if(token.equals("}")) {
				return true;
			} else if(token.endsWith("}")) {
				// This should not be done, but someone is bound to do it
				token = token.substring(0, token.length() - 1);
				if(token.toLowerCase().equals("null")) {
					specifier.addString(null);
				} else {
					specifier.addString(token);
				}
				specifier.addString(token);
				return true;
			} else {
				if(token.toLowerCase().equals("null")) {
					specifier.addString(null);
				} else {
					specifier.addString(token);
				}
			}
		}
		return false;
	}
	
	
	/**
	 * This will take a name and type and create an empty IBiomeSpecifier 
	 * and matching AbstractTempBiome to hold its initialization data 
	 * unitl all data is read.  It will also add the name of the new 
	 * biome specifier to the list of identifiers which are iterated 
	 * during the follow data setup phase. 
	 * 
	 * 
	 * @param type
	 * @param name
	 * @return
	 */
	private AbstractTempBiome addSpecifier(String type, String name) {
		AbstractTempBiome out = null;
		identifiers.add(name);
		if(type.toLowerCase().equals("island")) {
			specifiers.put(name, new GetIslandBiome());
			temps.put(name, out = new TempIslandBiome(this));
		} else if(type.toLowerCase().equals("leaf")) {
			specifiers.put(name, new GetLeafBiome());
			temps.put(name, out = new TempLeafBiome(this));
		} else if(type.toLowerCase().equals("list")) {
			specifiers.put(name, new GetListedBiome());
			temps.put(name, out = new TempListedBiome(this));
		} else if(type.toLowerCase().equals("noise")) {
			specifiers.put(name, new GetNoiseBiome());
			temps.put(name, out = new TempNoiseBiome(this));
		} else if(type.toLowerCase().equals("single")) {
			specifiers.put(name, new GetSingleBiome());
			temps.put(name, out = new TempSingleBiome(this));
		} else if(type.toLowerCase().equals("table")) {
			specifiers.put(name, new BiomeTable());
			temps.put(name, out = new TempBiomeTable(this));
		} 
		return out;
	}
	

}
