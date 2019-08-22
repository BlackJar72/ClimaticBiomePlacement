package jaredbgreat.climaticbiome.configuration;

import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.generation.generator.SizeScale;
import net.minecraft.util.JsonUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ClimaticWorldSettings {
	public static final String DATA_NAME = Info.ID + "GenSettings"; 
	
	// Core settings
	public boolean addIslands;
	public boolean extraBeaches;
	public boolean forceWhole;
	
	public int biomeSize;
	public SizeScale regionSize;
	public int mode;
	public double sisize;
		
	
	/**
	 * This default constructor will create a version that matches 
	 * set in the global config file (treating them as run-time defaults).
	 */
	public ClimaticWorldSettings() {
		setDataFromConfig();
	}
	
	
	/**
	 * Required constructor here only becaue required for some reason.
	 * 
	 * @param s
	 */
	public ClimaticWorldSettings(String s) {
		setDataFromConfig();
	}
	
	
	/**
	 * Populate data using the config (defaults)
	 */
	private final void setDataFromConfig() {
		this.addIslands = ConfigHandler.addIslands;
		this.extraBeaches = ConfigHandler.extraBeaches;
		this.forceWhole = ConfigHandler.forceWhole;	
		this.biomeSize = ConfigHandler.biomeSize;
		this.regionSize = ConfigHandler.regionSize;		
		this.mode = ConfigHandler.mode;	
		this.sisize = ConfigHandler.sisize;
	}
	
	
	public final void resetFromConfig() {
		setDataFromConfig();
	}
	
	
	/**
	 * A method to reset the settings based on json.  I'm
	 * not sure where the json is supposed to be from -- a 
	 * the string in the main world save file?
	 * 
	 * @param json
	 * @return this 
	 * 
	 */
	public ClimaticWorldSettings fromJson(JsonElement json) {
		JsonObject jsonObj = json.getAsJsonObject();		
		{			
			if(JsonUtils.hasField(jsonObj, "addIslands")) 		
				addIslands = JsonUtils.getBoolean(jsonObj, "addIslands");
			
			if(JsonUtils.hasField(jsonObj, "addBeaches")) 		
				extraBeaches = JsonUtils.getBoolean(jsonObj, "extraBeaches");
			
			if(JsonUtils.hasField(jsonObj, "forceWholeBiome")) 		
				forceWhole = JsonUtils.getBoolean(jsonObj, "forceWholeBiome");
			
			if(JsonUtils.hasField(jsonObj, "biomeSize")) 		
				biomeSize = JsonUtils.getInt(jsonObj, "biomeSize");
			
			if(JsonUtils.hasField(jsonObj, "regionSize")) 		
				regionSize = SizeScale.get(JsonUtils.getInt(jsonObj, "regionSize"));
			
			if(JsonUtils.hasField(jsonObj, "mapType")) 		
				mode = JsonUtils.getInt(jsonObj, "mapType");
			
			if(JsonUtils.hasField(jsonObj, "SurvivalIslandSize")) 		
				sisize = JsonUtils.getFloat(jsonObj, "SurvivalIslandSize");
		}		
		return this;
	}
	
	
	/**
	 * A wrapper for fromJson that allows this to be configured 
	 * by directy supply json text as a string.
	 * 
	 * @param json
	 * @return this
	 */
	public ClimaticWorldSettings fromJsonString(String json) {
		JsonParser parser = new JsonParser();
		return fromJson(parser.parse(json));
	}
	
	
	/**
	 * This creates a JsonElement version of the world settings, for 
	 * saving ... somewhere.
	 * 
	 * @return A JsonElement representation of the settings.
	 */
	public JsonElement toJson() {
		JsonObject jsonObj = new JsonObject();	
		jsonObj.addProperty("addIslands", addIslands);
		jsonObj.addProperty("extraBeaches", extraBeaches);	
		jsonObj.addProperty("forceWholeBiome", forceWhole);
		jsonObj.addProperty("biomeSize", biomeSize);
		jsonObj.addProperty("regionSize", regionSize.ordinal() + 1);
		jsonObj.addProperty("mapType", mode);
		jsonObj.addProperty("SurvivalIslandSize", sisize);
		return jsonObj;
	}
	
	
	/**
	 * This is a convenience wrapper around toJson(), allowing 
	 * a (text-based) json representation to the settings to 
	 * be obtained ... for saving ... somewhere.
	 * 
	 * @return A json (text) representation of the settings.
	 */
	public String toJsonString() {
		return toJson().toString();
	}
	
	
	public String toString() {
		return super.toString() + " " + toJsonString();
	}

}
