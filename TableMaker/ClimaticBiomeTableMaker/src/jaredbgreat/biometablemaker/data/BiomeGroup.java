package jaredbgreat.biometablemaker.data;

import java.util.ArrayList;
import java.util.List;

public class BiomeGroup {
	String name;
	EnumSpecifierType type;
	List<String> data;
	
	
	public BiomeGroup(String name, EnumSpecifierType type) {
		this.name = name;
		this.type = type;
		data = new ArrayList<>();
	}

}
