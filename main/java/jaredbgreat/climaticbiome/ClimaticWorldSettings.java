package jaredbgreat.climaticbiome;

import jaredbgreat.climaticbiome.generation.generator.SizeScale;
import net.minecraft.util.JsonUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ClimaticWorldSettings {
	
	// Core settings
	public boolean useBoP;
	public boolean useTraverse;
	public boolean useVanilla;	
	public boolean useBoPTable;	
	public boolean volcanicBoP;
	public boolean useCfg;
	public boolean rivers;
	public boolean rockyScrub;
	public boolean useDT;
	public boolean addIslands;
	public boolean extraBeaches;
	public boolean moreMansion;
	public boolean addPines;
	public boolean deepSand;
	public boolean forceWhole;
	
	public int biomeSize;
	public SizeScale regionSize;
	
	
	/**
	 * This default constructor will create a version that matches 
	 * set in the global config file (treating them as run-time defaults).
	 */
	public ClimaticWorldSettings() {
		this.useBoP = ConfigHandler.useBoP;
		this.useTraverse = ConfigHandler.useTraverse;
		this.useVanilla = ConfigHandler.useVanilla;	
		this.useBoPTable = ConfigHandler.useBoPTable;	
		this.volcanicBoP = ConfigHandler.volcanicBoP;
		this.useCfg = ConfigHandler.useCfg;
		this.rivers = ConfigHandler.rivers;
		this.rockyScrub = ConfigHandler.rockyScrub;
		this.useDT = ConfigHandler.useDT;
		this.addIslands = ConfigHandler.addIslands;
		this.extraBeaches = ConfigHandler.extraBeaches;
		this.moreMansion = ConfigHandler.moreMansion;
		this.deepSand = ConfigHandler.deepSand;	
		this.forceWhole = ConfigHandler.forceWhole;	
		this.biomeSize = ConfigHandler.biomeSize;
		this.regionSize = ConfigHandler.regionSize;
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
			if(JsonUtils.hasField(jsonObj, "useBoP"))
				useBoP = JsonUtils.getBoolean(jsonObj, "useBoP");
			
			if(JsonUtils.hasField(jsonObj, "useTraverse")) 			
				useTraverse = JsonUtils.getBoolean(jsonObj, "useTraverse");
			
			if(JsonUtils.hasField(jsonObj, "useVanilla")) 		
				useVanilla = JsonUtils.getBoolean(jsonObj, "useVanilla");
			
			if(JsonUtils.hasField(jsonObj, "useBoPTable")) 			
				useBoPTable = JsonUtils.getBoolean(jsonObj, "useBoPTable");
			
			if(JsonUtils.hasField(jsonObj, "volcanicBoP")) 			
				volcanicBoP = JsonUtils.getBoolean(jsonObj, "volcanicBoP");
			
			if(JsonUtils.hasField(jsonObj, "useCfg")) 		
				useCfg = JsonUtils.getBoolean(jsonObj, "useCfg");
			
			if(JsonUtils.hasField(jsonObj, "rivers")) 		
				rivers = JsonUtils.getBoolean(jsonObj, "rivers");
			
			if(JsonUtils.hasField(jsonObj, "rockyScrub")) 		
				rockyScrub = JsonUtils.getBoolean(jsonObj, "rockyScrub");
			
			if(JsonUtils.hasField(jsonObj, "useDT")) 		
				useDT = JsonUtils.getBoolean(jsonObj, "useDT");
			
			if(JsonUtils.hasField(jsonObj, "addIslands")) 		
				addIslands = JsonUtils.getBoolean(jsonObj, "addIslands");
			
			if(JsonUtils.hasField(jsonObj, "addBeaches")) 		
				extraBeaches = JsonUtils.getBoolean(jsonObj, "extraBeaches");
			
			if(JsonUtils.hasField(jsonObj, "moreMansion")) 		
				moreMansion = JsonUtils.getBoolean(jsonObj, "moreMansion");
			
			if(JsonUtils.hasField(jsonObj, "deepSand")) 		
				deepSand = JsonUtils.getBoolean(jsonObj, "deepSand");
			
			if(JsonUtils.hasField(jsonObj, "forceWholeBiome")) 		
				forceWhole = JsonUtils.getBoolean(jsonObj, "forceWholeBiome");
			
			if(JsonUtils.hasField(jsonObj, "biomeSize")) 		
				biomeSize = JsonUtils.getInt(jsonObj, "biomeSize");
			
			if(JsonUtils.hasField(jsonObj, "regionSize")) 		
				regionSize = SizeScale.get(JsonUtils.getInt(jsonObj, "regionSize"));
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
		jsonObj.addProperty("useBoP", useBoP);	
		jsonObj.addProperty("useTraverse", useTraverse);
		jsonObj.addProperty("useVanilla", useVanilla);	
		jsonObj.addProperty("useBoPTable", useBoPTable);
		jsonObj.addProperty("volcanicBoP", volcanicBoP);
		jsonObj.addProperty("useCfg", useCfg);
		jsonObj.addProperty("rivers", rivers);	
		jsonObj.addProperty("rockyScrub", rockyScrub);
		jsonObj.addProperty("useDT", useDT);	
		jsonObj.addProperty("addIslands", addIslands);
		jsonObj.addProperty("extraBeaches", extraBeaches);	
		jsonObj.addProperty("moreMansion", moreMansion);
		jsonObj.addProperty("deepSand", deepSand);
		jsonObj.addProperty("forceWholeBiome", forceWhole);
		jsonObj.addProperty("biomeSize", biomeSize);
		jsonObj.addProperty("regionSize", regionSize.ordinal() + 1);
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

}
