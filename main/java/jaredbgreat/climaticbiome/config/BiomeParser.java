package jaredbgreat.climaticbiome.config;

import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import static com.google.gson.stream.JsonToken.*;

public class BiomeParser {
	private final JsonReader reader;
	private JsonToken next;
	
	public BiomeParser(BufferedReader data) {
		reader = new JsonReader(data);
	}
	
	
	public void parse() {
		try {
			while(reader.hasNext()) {
				next = reader.peek();
				if(next.equals(JsonToken.BEGIN_OBJECT)) {
					reader.beginObject();
					parseObject();
				} else if(next.equals(JsonToken.BEGIN_ARRAY)) {
					reader.beginArray();
				} else if(next.equals(JsonToken.END_ARRAY)) {
					reader.endArray();
				} else if(next.equals(JsonToken.END_DOCUMENT)) {
					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();			
		}
	}
	
	
	/**
	 * Get the next entry expected to be a string, and 
	 * returns it as a string array with the name / key 
	 * in the first index and the string value in the 
	 * second index. 
	 * 
	 * @return
	 * @throws IOException
	 */
	private String[] getString() throws IOException {
		String[] out;
		if(reader.hasNext()) {
			next = reader.peek();
			if(!next.equals(JsonToken.NAME)) {
				throw new IOException("Invalid format for Biome JSON files.");
			} else {
				out = new String[2];
				out[0] = reader.nextName();
			}
		} else {
			throw new IOException("Invalid format for Biome JSON files.");
		}
		if(reader.hasNext()) {
			next = reader.peek();
			if(!next.equals(JsonToken.STRING)) {
				throw new IOException("Invalid format for Biome JSON files.");
			} else {
				out[1] = reader.nextString();
			}
		} else {
			throw new IOException("Invalid format for Biome JSON files.");
		}
		return out;
	}
	
	
	/**
	 * This begins parsing an object.  It will only find 
	 * the type of object, then hand it off to another 
	 * appropriate method for parsing an object of the 
	 * specified type.  It will through an exception if 
	 * type is not valid.
	 * 
	 * @throws IOException
	 */
	private void parseObject() throws IOException {
		String[] typeEntry = getString();
		if(!typeEntry[0].toLowerCase().trim().equals("type")) {
			// FIXME: This should be its own exception type!
			throw new IOException("Biome JSON object has no type!");			
		} else {
			// TODO: Create an efficient parsing system that 
			// maps the typeEntry[2] String to a parser.
		}
	}

}
