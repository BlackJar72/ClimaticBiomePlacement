package jaredbgreat.climaticbiomes.generation.biome;

import jaredbgreat.dldungeons.parser.Tokenizer;

import java.util.StringTokenizer;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public abstract class AbstractTerminalSpecifier implements IBiomeSpecifier {


    public long getBiomeNumber(String bstring, IForgeRegistry biomeReg) {
        Tokenizer tokens = new Tokenizer(bstring, ":");
        if(tokens.countTokens() < 3) {
            Biome biome = (Biome)biomeReg.getValue(new ResourceLocation(bstring));
            if(biome == null) {
                return -1;
            }
            return Registry.BIOME.getId(biome);
        } else {
            Biome biome = (Biome)biomeReg
                    .getValue(new ResourceLocation(tokens.nextToken()
                            + ":" + tokens.nextToken()));
            if(biome == null) {
                return -1;
            }
            return Registry.BIOME.getId(biome)
                    + (Long.parseLong(tokens.nextToken()) << 32);
        }
    }


    // FIXME: These methods do not belong here for static uses
    // TODO: This is a place to hook in per map data later.
    public static Biome getBiomeForID(int id) {
        return Registry.BIOME.getByValue(id);
    }


    // TODO: This is a place to hook in per map data later.
    public static int getIdForBiome(Biome b) {
        return Registry.BIOME.getId(b);
    }

}
