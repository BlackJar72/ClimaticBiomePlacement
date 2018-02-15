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
    
    
    public enum Subtype {
        NOISE,
        TEMP,
        WET,
        ISLAND,
        SPECIAL;
    }
}
