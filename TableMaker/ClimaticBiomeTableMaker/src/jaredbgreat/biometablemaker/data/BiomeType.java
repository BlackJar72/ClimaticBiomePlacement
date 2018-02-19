package jaredbgreat.biometablemaker.data;

import jaredbgreat.biometablemaker.gui.presentation.AbstractRecord;
import jaredbgreat.biometablemaker.gui.presentation.IncludeRecord;
import jaredbgreat.biometablemaker.gui.presentation.TypeRecord;

/**
 *
 * @author Jared Blackburn
 */
public class BiomeType implements ISpecifierData {
    String base;
    String hills;
    String mutated;
    String mutatedHills;
    String mountains;

    @Override
    public void modify(AbstractRecord presentation) {        
        TypeRecord rec = null;
        if(presentation instanceof TypeRecord) {
            rec = (TypeRecord)presentation;
        } else {
            System.err.println("ERROR! Type data paired with wrong data "
                    + "type" + presentation.getClass().getCanonicalName() + ".");
            System.err.println(" \t Object was " + presentation.getName() 
                    + "  (" + presentation.toString() + ").");
            new Exception().printStackTrace();
            System.exit(1);
        }
        base = rec.getBase().getText();
        hills = rec.getHills().getText();
        mutated = rec.getMutated().getText();
        mutatedHills = rec.getMutatedHills().getText();
        mountains = rec.getMountains().getText();
    }
    
}
