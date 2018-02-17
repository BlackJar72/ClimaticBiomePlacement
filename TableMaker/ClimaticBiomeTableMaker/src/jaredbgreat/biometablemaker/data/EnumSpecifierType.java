package jaredbgreat.biometablemaker.data;

public enum EnumSpecifierType {
    INCLUDE (IncludeData.class),
    TABLE   (BiomeTable.class),
    LIST    (BiomeList.class),
    NOISE   (BiomeNoise.class),
    ISLAND  (BiomeIsland.class),
    TYPE    (BiomeType.class), // Originally called "leaf"
    LEAF    (BiomeLeaf.class); // Originally called "single"
    
    public Class type;
    
    EnumSpecifierType(Class type) {
        this.type = type;
    }
}
