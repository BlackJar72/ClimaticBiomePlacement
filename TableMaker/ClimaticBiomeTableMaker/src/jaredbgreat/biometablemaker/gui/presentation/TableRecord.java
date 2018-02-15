package jaredbgreat.biometablemaker.gui.presentation;

import javax.swing.JTextField;


/**
 *
 * @author Jared Blackburn
 */
public class TableRecord extends AbstractRecord {
    private final JTextField[] fields;
    
    public TableRecord() {
        fields = new JTextField[50];
    }
}
