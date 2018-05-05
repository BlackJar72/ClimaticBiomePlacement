package jaredbgreat.climaticbiome.blocks;

import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.material.MapColor;
import net.minecraft.util.IStringSerializable;

public enum EnumWoodType implements IStringSerializable {
	PINE(0, "pine", MapColor.WOOD, EnumType.OAK);

    private static final int SIZE = values().length;
    private final int meta;
    private final String name;
    private final MapColor color;
    private final EnumType vanilla;

    private static final EnumWoodType[] TYPES = values();;
        
    private EnumWoodType(int meta, String name, MapColor color, EnumType vanilla) {
      	this.meta = meta;
       	this.name = name;
       	this.color = color;
       	this.vanilla = vanilla;
    }

        
    public int getMetadata() {
        return meta;
    }

        
    public MapColor getMapColor() {
        return color;
    }

        
    public String toString() {
        return name;
    }

        
    public static EnumWoodType byMetadata(int meta) {
        if (meta < 0 || meta >= SIZE) {
            meta = 0;
        }
        return values()[meta];
    }

        
    public String getName() {
        return name;
    }

        
    public String getUnlocalizedName() {
        return name;
    }
    
    
    public EnumType getVanilla() {
    	return vanilla;
    }
    
    public static EnumWoodType getFromVanilla(EnumType in) {
    	return TYPES[in.ordinal()];
    }
    
}
