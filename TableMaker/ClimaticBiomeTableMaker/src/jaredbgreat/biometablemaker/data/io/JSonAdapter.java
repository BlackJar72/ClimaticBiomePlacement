package jaredbgreat.biometablemaker.data.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

import jaredbgreat.biometablemaker.data.EnumSpecifierType;
import jaredbgreat.biometablemaker.data.ISpecifierData;

/**
 * Hopefully this will provide a way to read and write Json files containing 
 * the data I'm working with here organized in a reasonable way.
 * 
 * This should be re-usable in the mod with some slight refactoring 
 * (specifically moving everything into relevant packages).
 * 
 * @author Jared Blackburn
 */
public class JSonAdapter implements JsonSerializer, JsonDeserializer {
    
    private JSonAdapter() {
        super();
    };
    
    public static Gson getJsonAdapter() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ISpecifierData.class, new JSonAdapter());
        return builder.create();
    }
    
    @Override
    public JsonElement serialize(Object src, Type typeOfSrc, 
                JsonSerializationContext context) {
        return new JsonObject();
    }

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, 
                JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        JsonPrimitive prim = (JsonPrimitive) obj.get("type");
        String typeName = prim.getAsString();
        Class type = EnumSpecifierType
                .valueOf(typeName.trim().toUpperCase()).type;
        return context.deserialize(obj.get("data"), type);
    }
    
}
