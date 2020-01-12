package jaredbgreat.climaticbiomes.generation.map;

import java.util.*;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import static com.sun.javafx.fxml.expression.Expression.add;

// TODO: Use this for look up; create and store a copy with each map; have the map look up the biome with it.

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
    private final List<Biome> registry;
    private final Map<String, Integer> map;
    private final Map<Integer, Integer> dict;


    public BiomeMappings() {
        registry = new ArrayList<>();
        map = new HashMap<>();
        dict = new HashMap<>();
    }

    /**
     * Returns the biome for the pseudo-ID index.
     *
     * @param index
     * @return the biome for the pseudo-ID index
     */
    public Biome get(int index) {
        return registry.get(index);
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
            out = registry.get(index);
            if (out == null) {
                out = aDefault;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
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
        return registry.get(map.get(rl));
    }

    /**
     * Returns the biome ID for the map from the ID for the game.
     *
     * @param mcid
     * @return the biome for the resource location
     */
    public int translate(int mcid) {
        return dict.get(mcid);
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
     * <p>
     * The JsonObject is also returned after without
     * modification (other than being created if passed
     * as null).
     * <p>
     * This is intended to allow mappings that have been
     * loaded in with an existing save to be added, or
     * to create fresh new mappings if not.
     *
     * @param json
     * @return json
     */
    public JsonObject fillFromJson(JsonObject json) {
        if (json == null) {
            json = new JsonObject();
        } else {
            int n;
            Biome b;
            Set<Entry<String, JsonElement>> entries = json.entrySet();
            for (Entry<String, JsonElement> entry : entries) {
                b = Registry.BIOME.getValue(new ResourceLocation(entry.getKey())).get();
                n = entry.getValue().getAsInt();
                registry.add(n, b);
                map.put(entry.getKey(), n);
                dict.put(Registry.BIOME.getId(b), n);
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
        if (json == null) {
            json = new JsonObject();
        }
        int n;
        String s;
        Biome b;
        IForgeRegistry<Biome> reg = GameRegistry.findRegistry(Biome.class);
        Set<Entry<ResourceLocation, Biome>> biomes = reg.getEntries();
        for (Entry<ResourceLocation, Biome> biome : biomes) {
            String rl = biome.getKey().toString();
            b = Registry.BIOME.getValue(new ResourceLocation(rl)).get();
            n = registry.size();
            registry.add(b);
            map.put(rl, n);
            dict.put(Registry.BIOME.getId(b), n);
            json.addProperty(rl, n);
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
        int size = registry.size();
        for (int i = 0; i < size; i++) {
            if (registry.get(i) != null) {
                String rl = registry.get(i).getRegistryName().toString();
                json.addProperty(rl, i);
            }
        }
        return json;
    }

}