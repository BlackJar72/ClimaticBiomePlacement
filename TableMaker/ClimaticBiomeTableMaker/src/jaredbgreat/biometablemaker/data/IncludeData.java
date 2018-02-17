package jaredbgreat.biometablemaker.data;

import jaredbgreat.biometablemaker.gui.presentation.AbstractRecord;
import jaredbgreat.biometablemaker.gui.presentation.IncludeRecord;
import java.util.List;
import javax.swing.JTextField;

/**
 *
 * @author Jared Blackburn
 */
public class IncludeData implements ISpecifierData {
    List<String> files;

    @Override
    public void modify(AbstractRecord presentation) {
        int index;
        IncludeRecord rec = null;
        if(presentation instanceof IncludeRecord) {
            rec = (IncludeRecord)presentation;
        } else {
            System.err.println("ERROR! Include data paired with wrong data "
                    + "type" + presentation.getClass().getCanonicalName() + ".");
            System.err.println(" \t Object was " + presentation.getName() 
                    + "  (" + presentation.toString() + ").");
            new Exception().printStackTrace();
            System.exit(1);
        }
        files.clear(); // Simple solution; this is not performance critical
        for(JTextField f : rec.getFields()) {
            String text = f.getText().trim();
            f.setText(text);
            files.add(text);
        }        
    }
    
    
}
