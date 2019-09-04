package jaredbgreat.climaticbiome.blocks;

public enum EnumWoodType /*implements IStringSerializable*/ {/*
	PINE(0, "pine", MapColor.WOOD);

    private static final int SIZE = values().length;
    private final int meta;
    private final String name;
    private final MapColor mapColor;

	private static final EnumWoodType[] META_LOOKUP 
		= new EnumWoodType[values().length];
	private final String unlocalizedName;

	
	private EnumWoodType(int metaIn, String nameIn, MapColor color) {
		this(metaIn, nameIn, nameIn, color);
	}
	
	
	private EnumWoodType(int metaIn, String nameIn, 
				String unlocalizedNameIn, MapColor color) {
		meta = metaIn;
		name = nameIn;
		unlocalizedName = unlocalizedNameIn;
		mapColor = color;
	}

	
	public int getMetadata() {
		return this.meta;
	}

	
	public String toString() {
		return this.name;
	}

	
	public static EnumWoodType byMetadata(int meta) {
		if (meta < 0 || meta >= META_LOOKUP.length) {
			meta = 0;
		}

		return META_LOOKUP[meta];
	}

	
	public String getName() {
		return this.name;
	}

	
	public String getUnlocalizedName() {
		return this.unlocalizedName;
	}

	
	static {
		for (EnumWoodType blockplanks$enumtype : values()) {
			META_LOOKUP[blockplanks$enumtype.getMetadata()] = blockplanks$enumtype;
		}
	}


    private static final EnumWoodType[] TYPES = values();

        
    public MapColor getMapColor() {
        return mapColor;
    }
    
    
    public static EnumWoodType getFromVanilla(EnumType in) {
    	return TYPES[in.ordinal()];
    }
    
*/}
