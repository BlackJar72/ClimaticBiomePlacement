package jaredbgreat.biometablemaker.data;

import jaredbgreat.biometablemaker.gui.presentation.AbstractRecord;
import jaredbgreat.biometablemaker.gui.presentation.LeafRecord;

/**
 *
 * @author Jared Blackburn
 */
public class BiomeLeaf implements ISpecifierData {
    int biome;
    
    public BiomeLeaf() {
        biome = 0;
    }

    @Override
    public void modify(AbstractRecord presentation) {
        LeafRecord rec = null;
        if(presentation instanceof LeafRecord) {
            rec = (LeafRecord)presentation;
        } else {
            System.err.println("ERROR! Leaf data paired with wrong data "
                    + "type" + presentation.getClass().getCanonicalName() + ".");
            System.err.println(" \t Object was " + presentation.getName() 
                    + "  (" + presentation.toString() + ").");
            new Exception().printStackTrace();
            System.exit(1);
        }
        biome = rec.getId();
    }
    

    public int getBiome() {
        return biome;
    }
}
