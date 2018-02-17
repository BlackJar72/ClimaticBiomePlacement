package jaredbgreat.biometablemaker.data;

import jaredbgreat.biometablemaker.gui.presentation.AbstractRecord;
import jaredbgreat.biometablemaker.gui.presentation.TableRecord;
import javax.swing.JTextField;

/**
 *
 * @author jared
 */
public class BiomeTable implements ISpecifierData {
    public static final int SIZE = 25 * 10;    
    String[] biomes = new String[SIZE];

    
    @Override
    public void modify(AbstractRecord presentation) {
        TableRecord rec = null;
        if(presentation instanceof TableRecord) {
            rec = (TableRecord)presentation;
        } else {
            System.err.println("ERROR! Table data paired with wrong data "
                    + "type" + presentation.getClass().getCanonicalName() + ".");
            System.err.println(" \t Object was " + presentation.getName() 
                    + "  (" + presentation.toString() + ").");
            new Exception().printStackTrace();
            System.exit(1);
        }
        JTextField[] fields = rec.getFields();
        for(int i = 0; i < SIZE; i++) {
            String text = fields[i].getText().trim();
            fields[i].setText(text);
            biomes[i] = text;
        }
    }
}
