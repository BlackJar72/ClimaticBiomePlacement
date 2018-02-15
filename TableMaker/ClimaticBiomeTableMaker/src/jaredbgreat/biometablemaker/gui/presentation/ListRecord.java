package jaredbgreat.biometablemaker.gui.presentation;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;

/**
 *
 * @author Jared Blackburn
 */
public class ListRecord extends AbstractRecord {
    private List<JTextField> fields;
    
    
    public ListRecord() {
        fields = new ArrayList<>();
    }
}
