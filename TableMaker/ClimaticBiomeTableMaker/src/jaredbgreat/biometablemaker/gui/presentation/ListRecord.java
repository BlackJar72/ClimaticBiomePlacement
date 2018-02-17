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

    @Override
    void modify() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
