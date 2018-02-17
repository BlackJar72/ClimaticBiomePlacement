package jaredbgreat.biometablemaker.gui.presentation;

import jaredbgreat.biometablemaker.data.BiomeTable;
import javax.swing.JTextField;


/**
 *
 * @author Jared Blackburn
 */
public class TableRecord extends AbstractRecord {
    BiomeTable model;
    private final JTextField[] fields;
    
    public TableRecord() {
        fields = new JTextField[BiomeTable.SIZE];
    }

    @Override
    public void modify() {
        super.modify();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
    

    public BiomeTable getModel() {
        return model;
    }
    

    public JTextField[] getFields() {
        return fields;
    }    
    
}
