package jaredbgreat.climaticbiomes.configuration;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jaredbgreat.climaticbiomes.Info;
import jaredbgreat.climaticbiomes.generation.generator.SizeScale;
//import jaredbgreat.climaticbiomes.gui.GuiCBToggleButton;
//import jaredbgreat.climaticbiomes.gui.GuiIntSlider;
//import jaredbgreat.climaticbiomes.gui.GuiScaleSlider;
//import jaredbgreat.climaticbiomes.gui.GuiWorldTypeButton;
import jaredbgreat.climaticbiomes.util.Debug;

public class ClimaticWorldSettings {
    public static final String DATA_NAME = Info.ID + "GenSettings";
    private static volatile ClimaticWorldSettings queued;

    // Core settings
    public boolean addIslands;
    public boolean extraBeaches;
    public boolean forceWhole;
    public boolean rockyScrub;
    public boolean deepSand;
    public boolean volcanicIslands;
    public boolean hasRivers;

    public int biomeSize;
    public SizeScale regionSize;
    public int mode;
    public double sisize;


    /**
     * This default constructor will create a version that matches 
     * set in the global config file (treating them as run-time defaults).
     */
    private ClimaticWorldSettings() {
        setDataFromConfig();
    }


    /**
     * Populate data using the config (defaults)
     */
    private final void setDataFromConfig() {
        this.addIslands = ConfigHandler.addIslands;
        this.extraBeaches = ConfigHandler.extraBeaches;
        this.rockyScrub = ConfigHandler.rockyScrub;
        this.deepSand = ConfigHandler.deepSand;
        this.volcanicIslands = ConfigHandler.volcanicIslands;
        this.hasRivers = ConfigHandler.hasRivers;
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
    public ClimaticWorldSettings fromJson(JsonObject json) {
        {
            if(json.has("addIslands"))
                addIslands = json.get("addIslands").getAsBoolean();

            if(json.has("addBeaches"))
                extraBeaches = json.get("extraBeaches").getAsBoolean();

            if(json.has("rockyScrub"))
                rockyScrub = json.get("rockyScrub").getAsBoolean();

            if(json.has("deepSand"))
                deepSand = json.get("deepSand").getAsBoolean();

            if(json.has("volcanicIslands"))
                volcanicIslands = json.get("volcanicIslands").getAsBoolean();

            if(json.has("hasRivers"))
                hasRivers = json.get("hasRivers").getAsBoolean();

            if(json.has("forceWholeBiome"))
                forceWhole = json.get("forceWholeBiome").getAsBoolean();

            if(json.has("biomeSize"))
                biomeSize = json.get("biomeSize").getAsInt();

            if(json.has("regionSize"))
                regionSize = SizeScale.get(json.get("regionSize").getAsInt());

            if(json.has("mapType"))
                mode = json.get("mapType").getAsInt();

            if(json.has("SurvivalIslandSize"))
                sisize = json.get("SurvivalIslandSize").getAsInt();
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
        return fromJson(parser.parse(json).getAsJsonObject());
    }


    /**
     * This creates a JsonElement version of the world settings, for 
     * saving ... somewhere.
     *
     * @return A JsonElement representation of the settings.
     */
    public JsonObject toJson() {
        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("addIslands", addIslands);
        jsonObj.addProperty("extraBeaches", extraBeaches);
        jsonObj.addProperty("rockyScrub", rockyScrub);
        jsonObj.addProperty("deepSand", deepSand);
        jsonObj.addProperty("volcanicIslands", volcanicIslands);
        jsonObj.addProperty("hasRivers", hasRivers);
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


    /**
     * This is a convenience wrapper around toJson(), allowing 
     * a (text-based) json representation to the settings to 
     * be obtained ... for saving ... somewhere.
     *
     * @return A json (text) representation of the settings.
     */
    public String extendJsonString(String in) {
        JsonObject jsonObj;
        if((in != null) && !in.isEmpty()) {
            Gson gson = new Gson();
            JsonElement jsonElement = gson.toJsonTree(in);
            jsonObj = jsonElement.getAsJsonObject();
        } else {
            jsonObj = new JsonObject();
        }
        jsonObj.addProperty("addIslands", addIslands);
        jsonObj.addProperty("extraBeaches", extraBeaches);
        jsonObj.addProperty("forceWholeBiome", forceWhole);
        jsonObj.addProperty("biomeSize", biomeSize);
        jsonObj.addProperty("regionSize", regionSize.ordinal() + 1);
        jsonObj.addProperty("mapType", mode);
        jsonObj.addProperty("SurvivalIslandSideepSandze", sisize);
        Debug.bigSysout(jsonObj);
        return jsonObj.toString();
    }


    public String toString() {
        return super.toString() + " " + toJsonString();
    }

    //FIXME / TODO: Re-implement called methods uncomment contents
    public void applySettings() {/*
		Scrub.setDeepSand(deepSand);
		Scrub.setMakeRocks(rockyScrub);
		GetIslands.setVolcanicIslands(volcanicIslands);
	*/}

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
        if(queued == null) {
            queued = new ClimaticWorldSettings();
        }
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
        public void setTarget(ClimaticWorldSettings target);
    }

/*
    public static abstract class BooleanSetter implements ISetter<GuiCBToggleButton> {
        ClimaticWorldSettings target;
        public BooleanSetter(ClimaticWorldSettings target) {
            this.target = target;
        }
        public void setTarget(ClimaticWorldSettings target) {
            this.target = target;
        }
    }
	

	public static class BiomeSizeSetter implements ISetter<GuiIntSlider> {
		ClimaticWorldSettings target;
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
		public void setTarget(ClimaticWorldSettings target) {
			this.target = target;
		}
	}
	// TODO Auto-generated m
	
	
	public static class SISizeSetter implements ISetter<GuiIntSlider> {
		ClimaticWorldSettings target;
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
		public void setTarget(ClimaticWorldSettings target) {
			this.target = target;
		}
	}
	
	
	public static class MapScaleSetter implements ISetter<GuiScaleSlider> {
		ClimaticWorldSettings target;
		public MapScaleSetter(ClimaticWorldSettings target) {
			this.target = target;
		}
		@Override
		public void set(GuiScaleSlider input) {
			target.regionSize = SizeScale.get((int)input.getSliderValue() + 1);
		}
		@Override
		public void set(int input) {
			target.regionSize = SizeScale.get(input);			
		}
		public void setTarget(ClimaticWorldSettings target) {
			this.target = target;
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
	
	
	public static class RockyScrubSetter extends BooleanSetter {
		public RockyScrubSetter(ClimaticWorldSettings target) {
			super(target);
		}		
		@Override
		public void set(GuiCBToggleButton input) {
			target.rockyScrub = input.getState();
		}
		@Override
		public void set(int input) {
			target.rockyScrub = input != 0;			
		}		
	}
	
	
	public static class DeepSandSetter extends BooleanSetter {
		public DeepSandSetter(ClimaticWorldSettings target) {
			super(target);
		}		
		@Override
		public void set(GuiCBToggleButton input) {
			target.deepSand = input.getState();
		}
		@Override
		public void set(int input) {
			target.deepSand = input != 0;			
		}		
	}
	
	
	public static class VolcanicIslandsSetter extends BooleanSetter {
		public VolcanicIslandsSetter(ClimaticWorldSettings target) {
			super(target);
		}		
		@Override
		public void set(GuiCBToggleButton input) {
			target.volcanicIslands = input.getState();
		}
		@Override
		public void set(int input) {
			target.volcanicIslands = input != 0;			
		}		
	}
	
	
	public static class HasRiversSetter extends BooleanSetter {
		public HasRiversSetter(ClimaticWorldSettings target) {
			super(target);
		}		
		@Override
		public void set(GuiCBToggleButton input) {
			target.hasRivers = input.getState();
		}
		@Override
		public void set(int input) {
			target.hasRivers = input != 0;			
		}		
	}
	
	
	public static class ModeSetter implements ISetter<GuiWorldTypeButton> {
		ClimaticWorldSettings target;
		public ModeSetter(ClimaticWorldSettings target) {
			this.target = target;
		}
		@Override
		public void set(GuiWorldTypeButton input) {
			target.mode = input.getState() + 1;			
		}
		@Override
		public void set(int input) {
			target.mode = input + 1;
		}
		public void setTarget(ClimaticWorldSettings target) {
			this.target = target;
		}		
	}

*/}
