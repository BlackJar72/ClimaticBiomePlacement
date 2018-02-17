package jaredbgreat.biometablemaker.data;

import jaredbgreat.biometablemaker.gui.presentation.AbstractRecord;
import jaredbgreat.biometablemaker.gui.presentation.NoiseRecord;

/**
 *
 * @author Jared Blackburn
 */
public class BiomeNoise implements ISpecifierData {
    String upper;
    int bound;
    String lower;    

    
    @Override
    public void modify(AbstractRecord presentation) {
        NoiseRecord rec = null;
        if(presentation instanceof NoiseRecord) {
            rec = (NoiseRecord)presentation;
        } else {
            System.err.println("ERROR! Noise data paired with wrong data "
                    + "type" + presentation.getClass().getCanonicalName() + ".");
            System.err.println(" \t Object was " + presentation.getName() 
                    + "  (" + presentation.toString() + ").");
            new Exception().printStackTrace();
            System.exit(1);
        }
        lower = rec.getLower();
        upper = rec.getUpper();
        bound = rec.getBound();
    }
    
    
    public int getBound() {
        return bound;
    }
}
