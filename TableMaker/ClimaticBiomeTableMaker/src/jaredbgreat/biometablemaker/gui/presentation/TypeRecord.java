package jaredbgreat.biometablemaker.gui.presentation;

import javax.swing.JTextField;

/**
 *
 * @author Jared Blackburn
 */
public class TypeRecord {
    private JTextField base;
    private JTextField hills;
    private JTextField mutated;
    private JTextField mutatedHills;
    private JTextField mountains;
    
    
    public TypeRecord() {
        base  = new JTextField();
        hills = new JTextField();
        mutated = new JTextField();
        mutatedHills = new JTextField();
        mountains = new JTextField();
    }
}
