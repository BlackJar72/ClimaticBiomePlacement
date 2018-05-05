package jaredbgreat.climaticbiome.blocks;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.MapColor;
import net.minecraft.util.IStringSerializable;

public enum EnumWoodType implements IStringSerializable {
	PINE(0, "pine", MapColor.WOOD);

    private static final int SIZE = values().length;
    private final int meta;
    private final String name;
    private final MapColor color;

        
    private EnumWoodType(int meta, String name, MapColor color) {
      	this.meta = meta;
       	this.name = name;
       	this.color = color;
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
    
}
