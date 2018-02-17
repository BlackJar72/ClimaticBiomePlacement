package jaredbgreat.biometablemaker.data;

import jaredbgreat.biometablemaker.gui.presentation.AbstractRecord;
import jaredbgreat.biometablemaker.gui.presentation.IncludeRecord;
import jaredbgreat.biometablemaker.gui.presentation.ListRecord;
import java.util.List;
import javax.swing.JTextField;

/**
 *
 * @author Jared Blackburn
 */
public class BiomeList implements ISpecifierData {
    List<String> biomes;

    @Override
    public void modify(AbstractRecord presentation) {
        int index;
        ListRecord rec = null;
        if(presentation instanceof IncludeRecord) {
            rec = (ListRecord)presentation;
        } else {
            System.err.println("ERROR! List data paired with wrong data "
                    + "type" + presentation.getClass().getCanonicalName() + ".");
            System.err.println(" \t Object was " + presentation.getName() 
                    + "  (" + presentation.toString() + ").");
            new Exception().printStackTrace();
            System.exit(1);
        }
        biomes.clear();
        for(JTextField f : rec.getFields()) {
            String text = f.getText().trim();
            f.setText(text);
            biomes.add(text);
        }
    }
    
    
    public List<String> getBiomes() {
        return biomes;
    }
}
