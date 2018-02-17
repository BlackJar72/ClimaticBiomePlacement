package jaredbgreat.biometablemaker.data;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BiomeGroup {
	String name;
	EnumSpecifierType type;
	ISpecifierData data;
	
	public BiomeGroup(String name, EnumSpecifierType type) {
		this.name = name;
		this.type = type;
            try {
                data = (ISpecifierData)type.type.newInstance();
            } catch (Exception ex) {
                Logger.getLogger(BiomeGroup.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            } 
	}

}
