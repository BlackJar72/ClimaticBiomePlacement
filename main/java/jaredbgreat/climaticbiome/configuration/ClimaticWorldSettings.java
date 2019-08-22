package jaredbgreat.climaticbiome.configuration;

import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.generation.generator.SizeScale;
import jaredbgreat.climaticbiome.gui.GuiCBToggleButton;
import jaredbgreat.climaticbiome.gui.GuiIntSlider;
import jaredbgreat.climaticbiome.gui.GuiScaleSlider;
import jaredbgreat.climaticbiome.gui.GuiWorldTypeButton;
import net.minecraft.util.JsonUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ClimaticWorldSettings {
	public static final String DATA_NAME = Info.ID + "GenSettings";
	private static ClimaticWorldSettings queued;
	
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
	

	/*------------------------------------------------------------------------*/
	/*               Inner Classes / Interfaces / Factories                   */
	/*------------------------------------------------------------------------*/
	
	
	/**
	 * This will create a new instance, cache it for later 
	 * retrieval, then return it.
	 * 
	 * This is to be used by the GUI screen to get an instance 
	 * that will later be retrieved by map registry.
	 * 
	 * @return
	 */
	public static ClimaticWorldSettings getNew() {
		queued = new ClimaticWorldSettings();
		return queued;
	}
	
	
	/**
	 * This will return the currently cached settings, or 
	 * new settings if none are currently cashed.  It will
	 * then clear the cached setting to null for future 
	 * use.
	 * 
	 * @return
	 */
	public static ClimaticWorldSettings getQueued() {
		if(queued == null) {
			return new ClimaticWorldSettings();
		} else {
			ClimaticWorldSettings out = queued;
			queued = null;
			return out;
		}
	}
	
	
	public static interface ISetter<T> {
		public void set(T input);
		public void set(int input);
	}
	
	
	public static abstract class BooleanSetter implements ISetter<GuiCBToggleButton> {
		final ClimaticWorldSettings target;
		public BooleanSetter(ClimaticWorldSettings target) {
			this.target = target;
		}
	}
	
	
	public static class BiomeSizeSetter implements ISetter<GuiIntSlider> {
		final ClimaticWorldSettings target;
		public BiomeSizeSetter(ClimaticWorldSettings target) {
			this.target = target;
		}
		@Override
		public void set(GuiIntSlider input) {
			target.biomeSize = input.getSliderIntValue();
		}
		@Override
		public void set(int input) {
			target.biomeSize = input;			
		}
	}
	// TODO Auto-generated m
	
	
	public static class SISizeSetter implements ISetter<GuiIntSlider> {
		final ClimaticWorldSettings target;
		public SISizeSetter(ClimaticWorldSettings target) {
			this.target = target;
		}
		@Override
		public void set(GuiIntSlider input) {
			target.sisize = input.getSliderIntValue();
		}
		@Override
		public void set(int input) {
			target.sisize = input;			
		}
	}
	
	
	public static class MapScaleSetter implements ISetter<GuiScaleSlider> {
		final ClimaticWorldSettings target;
		public MapScaleSetter(ClimaticWorldSettings target) {
			this.target = target;
		}
		@Override
		public void set(GuiScaleSlider input) {
			target.regionSize = SizeScale.get((int)input.getSliderValue());
		}
		@Override
		public void set(int input) {
			target.regionSize = SizeScale.get(input);			
		}
	}
	
	
	public static class AddIslandsSetter extends BooleanSetter {
		public AddIslandsSetter(ClimaticWorldSettings target) {
			super(target);
		}		
		@Override
		public void set(GuiCBToggleButton input) {
			target.addIslands = input.getState();
		}
		@Override
		public void set(int input) {
			target.addIslands = input != 0;			
		}		
	}

	// TODO Auto-generated m
	
	public static class ExtraBeachSetter extends BooleanSetter {
		public ExtraBeachSetter(ClimaticWorldSettings target) {
			super(target);
		}		
		@Override
		public void set(GuiCBToggleButton input) {
			target.extraBeaches = input.getState();
		}
		@Override
		public void set(int input) {
			target.extraBeaches = input != 0;			
		}		
	}
	
	
	public static class ForceWholeSetter extends BooleanSetter {
		public ForceWholeSetter(ClimaticWorldSettings target) {
			super(target);
		}		
		@Override
		public void set(GuiCBToggleButton input) {
			target.forceWhole = input.getState();
		}
		@Override
		public void set(int input) {
			target.forceWhole = input != 0;			
		}		
	}
	
	
	public static class ModeSetter implements ISetter<GuiWorldTypeButton> {
		final ClimaticWorldSettings target;
		public ModeSetter(ClimaticWorldSettings target) {
			this.target = target;
		}
		@Override
		public void set(GuiWorldTypeButton input) {
			target.mode = input.getState();			
		}
		@Override
		public void set(int input) {
			target.mode = input;
		}		
	}

}
