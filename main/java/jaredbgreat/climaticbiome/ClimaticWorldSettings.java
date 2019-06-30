package jaredbgreat.climaticbiome;

import jaredbgreat.climaticbiome.generation.generator.SizeScale;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.JsonUtils;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ClimaticWorldSettings extends WorldSavedData {
	public static final String DATA_NAME = Info.ID + "GenSettings"; 
	
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
		super(DATA_NAME);
		setDataFromConfig();
	}
	
	
	/**
	 * Required constructor here only becaue required for some reason.
	 * 
	 * @param s
	 */
	public ClimaticWorldSettings(String s) {
		super(s);
		setDataFromConfig();
	}
	
	
	/**
	 * Populate data using the config (defaults)
	 */
	private final void setDataFromConfig() {
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
	
	
	public String toString() {
		return super.toString() + " " + toJsonString();
	}


	/*-***************************************************************-*
	 *                       NBT STUFF BELOW                           *
	 *-***************************************************************-*/
	
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
    	System.out.println("**********************");
    	System.out.println("Reading NBT");
    	System.out.println("**********************");
		NBTTagCompound tag = nbt.getCompoundTag(DATA_NAME);		
		useBoP = tag.getBoolean("useBoP");	
		useTraverse = tag.getBoolean("useTraverse");
		useVanilla = tag.getBoolean("useVanilla");	
		useBoPTable = tag.getBoolean("useBoPTable");
		volcanicBoP = tag.getBoolean("volcanicBoP");
		useCfg = tag.getBoolean("useCfg");
		rivers = tag.getBoolean("rivers");	
		rockyScrub = tag.getBoolean("rockyScrub");
		useDT = tag.getBoolean("useDT");	
		addIslands = tag.getBoolean("addIslands");
		extraBeaches = tag.getBoolean("extraBeaches");
		moreMansion = tag.getBoolean("moreMansion");
		deepSand = tag.getBoolean("deepSand");
		forceWhole = tag.getBoolean("forceWholeBiome");
		biomeSize = tag.getInteger("biomeSize");
		regionSize = SizeScale.get(tag.getInteger("regionSize"));
	}


	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    	System.out.println("**********************");
    	System.out.println("Reating NBT");
    	System.out.println("**********************");
		NBTTagCompound tag = compound.getCompoundTag(DATA_NAME);
		tag.setBoolean("useBoP", useBoP);	
		tag.setBoolean("useTraverse", useTraverse);
		tag.setBoolean("useVanilla", useVanilla);	
		tag.setBoolean("useBoPTable", useBoPTable);
		tag.setBoolean("volcanicBoP", volcanicBoP);
		tag.setBoolean("useCfg", useCfg);
		tag.setBoolean("rivers", rivers);	
		tag.setBoolean("rockyScrub", rockyScrub);
		tag.setBoolean("useDT", useDT);	
		tag.setBoolean("addIslands", addIslands);
		tag.setBoolean("extraBeaches", extraBeaches);	
		tag.setBoolean("moreMansion", moreMansion);
		tag.setBoolean("deepSand", deepSand);
		tag.setBoolean("forceWholeBiome", forceWhole);
		tag.setInteger("biomeSize", biomeSize);
		tag.setInteger("regionSize", regionSize.ordinal() + 1);
		return compound;
	}
	
	
	/*-****************************************************-*
	 *               Other Storage Stuff                   -*      
	 *-****************************************************-*/
	
	
	
	public static ClimaticWorldSettings get(World world) {
		MapStorage storage = world.getPerWorldStorage();
		ClimaticWorldSettings settings = 
					(ClimaticWorldSettings)storage
						.getOrLoadData(ClimaticWorldSettings.class, 
								DATA_NAME);
    	
    	if(!world.isRemote) {
	    	System.out.println();
	    	System.out.println("**********************");
	    	System.out.println(settings);
	    	System.out.println("**********************");
	    	System.out.println();
    	}
		
		if(settings == null) {
			settings = new ClimaticWorldSettings();
			settings.setDirty(true);
			storage.setData(DATA_NAME, settings);
			settings.setDirty(true);
			storage.saveAllData();
		}
		
		return settings;		
	}

}
