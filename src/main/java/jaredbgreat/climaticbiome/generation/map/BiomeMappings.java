package jaredbgreat.climaticbiome.generation.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * A class to hold pseudo-IDs for biomes, creating an abstraction layer
 * similar to the DBlock class in Doomlike Dungeons.  This class also 
 * uses JSON strings to represent the mappings so they can be saved and 
 * retrieved as protections against mappings being change between runs, 
 * such as if new mods are adding and the mappings are re-defined.
 * 
 * This structure is backed directly be a primitive array and acts as its 
 * own dynamic array as ArrayList has some behavior that was not desired 
 * for this application.
 * 
 * @author Jared Blackburn
 */
public class BiomeMappings {
	protected static final int INIT_SIZE = 16;
	private int size = INIT_SIZE;
	private int next = 0;
	private Biome[] registry;
	private final Map<String, Integer> map;
	
	
	public BiomeMappings() {
		registry = new Biome[size];
		map = new HashMap<>();		
	}
	
	/**
	 * Returns the biome for the pseudo-ID index.
	 * 
	 * @param index
	 * @return the biome for the pseudo-ID index
	 */
	public Biome get(int index) {
		return registry[index];
	}
	
	/**
	 * Returns the biome for the pseudo-ID index, or 
	 * the supplied default if the pseudo-ID is 
	 * invalid (i.e., the result is null).
	 * 
	 * @param index
	 * @param aDefault
	 * @return the biome for the pseudo-ID index
	 */
	public Biome get(int index, Biome aDefault) {
		Biome out;
		try {
			out = registry[index];
			if(out == null) {
				out = aDefault;
			}
		} catch(ArrayIndexOutOfBoundsException ex) {
			out = aDefault;
		}
		return out;
	}
	
	/**
	 * Returns the biome for the resource location String.
	 * 
	 * @param rl
	 * @return the biome for the resource location
	 */
	public Biome get(String rl) {
		return registry[map.get(rl)];
	}
	
	/**
	 * Returns the biome for the ResourceLocation.
	 * 
	 * @param rl
	 * @return the biome for the ResourceLocation
	 */
	public Biome get(ResourceLocation rl) {
		return registry[map.get(rl.toString())];
	}
	
	/**
	 * Returns the integer pseudo-ID from the biome 
	 * represented by the resource location String.
	 * 
	 * @param rl
	 * @return pseudo-ID for the biome
	 */
	public int getID(String rl) {
		return map.get(rl);
	}
	
	/**
	 * Returns the integer pseudo-ID from the biome 
	 * represented by the ResourceLocation.
	 * 
	 * @param rl
	 * @return pseudo-ID for the biome
	 */
	public int getID(ResourceLocation rl) {
		return map.get(rl.toString());
	}
	
	
/*------------------------------------------------------------------------------*/
/*               METHODS TO POPULATE THE BIOME / PSEUDO-ID MAPPINGS             */
/*------------------------------------------------------------------------------*/
		
	
	/**
	 * Fills the mappings from.  This is a convenience method 
	 * for fillFromJson and fillFromRegistry.
	 * 
	 * @param json
	 * @return
	 */
	public JsonObject createMappings(JsonObject json) {
		return fillFromRegistries(fillFromJson(json));
	}
	
	/**
	 * Populates from the passed in JsonObject; if the 
	 * JsonObject is null it will generate an empty one.
	 * 
	 * The JsonObject is also returned after without 
	 * modification (other than being created if passed 
	 * as null).
	 * 
	 * This is intended to allow mappings that have been 
	 * loaded in with an existing save to be added, or 
	 * to create fresh new mappings if not.
	 * 
	 * @param json
	 * @return json
	 */
	public JsonObject fillFromJson(JsonObject json) {
		if(json == null) {
			json = new JsonObject();
		} else {
			Set<Entry<String, JsonElement>> entries = json.entrySet();
			for(Entry<String, JsonElement> entry : entries) {
				add(entry.getValue().getAsInt(), entry.getKey());
			}
		}
		return json;
	}
	
	/**
	 * Populates the mappings from the Forge biome registry, 
	 * skipping any biomes that are already mapped and 
	 * assigning new pseudo-IDs started after the last used 
	 * ID in its own registry.  
	 * 
	 * @param json
	 * @return json
	 */
	public JsonObject fillFromRegistries(JsonObject json) {
		if(json == null) {
			json = new JsonObject();
		}
		toEnd();
		IForgeRegistry<Biome> reg = GameRegistry.findRegistry(Biome.class);
		Set<Entry<ResourceLocation, Biome>> biomes = reg.getEntries();
		for(Entry<ResourceLocation, Biome> biome : biomes) {
			String rl = biome.getKey().toString(); 
			if(!map.containsKey(rl)) {
				json.addProperty(rl, add(rl, biome.getValue()));
			}
		}
		return json;
	}	
	
	/**
	 * Returns the current mappings as a JsonObject.
	 * 
	 * @return the current mappings as a JsonObject
	 */
	public JsonObject getJsonRepresentation() {
		JsonObject json = new JsonObject();
		for(int i = 0; i < size; i++) {
			if(registry[i] != null) {
				String rl = registry[i].getRegistryName().toString();
				json.addProperty(rl, i);
			}
		}
		return json;
	}
	
	
/*------------------------------------------------------------------------------*/
/*           "LOW LEVEL [sic]" ARRAY MANIPULATIONS & SIMILAR METHODS            */
/*------------------------------------------------------------------------------*/
	
	
	/**
	 * Adds a biome to the mappings with the given pseudo-ID 
	 * index, assigning it the String rl as its resource 
	 * location.
	 * 
	 * @param index
	 * @param rl
	 * @param b
	 */
	public void add(int index, String rl, Biome b) {
		if(index >= size) {
			grow(index);
		}
		// No, there should not be duplicates, but yes,
		// they should overwrite any if found, and that is 
		// why I'm not using ArrayList.
		registry[index] = b;
		map.put(rl, index);
	}
	
	/**	 * 
	 * Adds a biome to the mappings with the given pseudo-ID 
	 * index, assigning it the String rl as its resource.  
	 * The biome added will be the one found in the Forge 
	 * biome registry with the resource location represented 
	 * by the String rl.
	 * 
	 * @param index
	 * @param rl
	 */
	public void add(int index, String rl) {
		add(index, rl, GameRegistry.findRegistry(Biome.class)
				.getValue(new ResourceLocation(rl)));
	}	
	
	/**
	 * Add a biome to the mapping with the resource 
	 * location String rl with the next available 
	 * pseudo-ID.
	 * 
	 * @param rl
	 * @param b
	 * @return
	 */
	public int add(String rl, Biome b) {
		int index = next;
		if(index >= size) {
			grow(index);
		}
		next++;
		registry[index] = b;
		map.put(rl, index);
		return index;
	}
	
	/**
	 * Add the biome represented by the resource location 
	 * String rl to the mapping with the next available 
	 * pseudo-ID.
	 * 
	 * @param rl
	 * @param b
	 * @return
	 */
	public int add(String rl) {
		return add(rl, GameRegistry.findRegistry(Biome.class)
				.getValue(new ResourceLocation(rl)));
	}
	
	/**
	 * Grows the backing array to hold more data beyond 
	 * the current limit.  This will increase the size to one 
	 * that will hold (usually) hold a little more than 
	 * is needed for the current growth, in order to avoid 
	 * excessive calls to this method -- that is, excessive 
	 * allocations of new arrays.
	 * 
	 * Note that there is no corresponding shrink method as 
	 * the size should not get smaller, only grow, as long 
	 * as the object persists.
	 * 
	 * @param nsize
	 */
	private void grow(int nsize) {
		nsize = Math.max((Math.max(size, nsize) * 3) / 2, INIT_SIZE);
		Biome[] old = registry;
		registry = new Biome[nsize];
		for(int i = 0; i < size; i++) {
			registry[i] = old[i];
		}
		size = nsize;
	}
	
	/**
	 * Find the last used pseudo-ID and moves 
	 * sets the next to be used to one more; 
	 * i.e., it finds the pseudo-ID one past 
	 * the end.
	 */
	private void toEnd() {
		for(int i = next; i < size; i++) {
			if(registry[i] != null) {
				next = i;
			}
		}
	}

}
