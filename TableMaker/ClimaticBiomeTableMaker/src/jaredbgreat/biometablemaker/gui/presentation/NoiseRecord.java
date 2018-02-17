package jaredbgreat.biometablemaker.gui.presentation;

import javax.swing.JTextField;

/**
 *
 * @author Jared Blackubr
 */
public class NoiseRecord extends AbstractRecord {
    private Subtype subtype;
    private JTextField lower;  // Lowe noise value
    private JTextField divide; // The int dividing them
    private JTextField upper;  // High noise value
    
    public NoiseRecord(Subtype type) {
        subtype = type;
        lower   = new JTextField();
        divide  = new JTextField();
        upper   = new JTextField();
    }

    @Override
    void modify() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public enum Subtype {
        NOISE,
        ISLAND,
        TEMP,
        WET,
        SPECIAL;
    }
}
