package jaredbgreat.biometablemaker.data;

import jaredbgreat.biometablemaker.gui.presentation.AbstractRecord;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BiomeGroup {
    String name;
    EnumSpecifierType type;
    ISpecifierData data;

    public BiomeGroup(String name, EnumSpecifierType type, 
                ISpecifierData data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }
        
    
    public void modify(AbstractRecord presentation) {
        name = presentation.getNameString();
        data.modify(presentation);
    }

}
