package jaredbgreat.biometablemaker.data;

import jaredbgreat.biometablemaker.gui.presentation.AbstractRecord;
import jaredbgreat.biometablemaker.gui.presentation.IslandRecord;

/**
 *
 * @author Jared
 */
public class BiomeIsland implements ISpecifierData {
    String land;
    String ocean;

    @Override
    public void modify(AbstractRecord presentation) {
        IslandRecord rec = null;
        if(presentation instanceof IslandRecord) {
            rec = (IslandRecord)presentation;
        } else {
            System.err.println("ERROR! Island data paired with wrong data "
                    + "type" + presentation.getClass().getCanonicalName() + ".");
            System.err.println(" \t Object was " + presentation.getName() 
                    + "  (" + presentation.toString() + ").");
            new Exception().printStackTrace();
            System.exit(1);
        }
        land = rec.getLand();
        ocean = rec.getOcean();
    }
}
