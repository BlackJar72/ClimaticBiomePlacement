package jaredbgreat.biometablemaker.gui.presentation;

import jaredbgreat.biometablemaker.data.BiomeGroup;
import jaredbgreat.biometablemaker.data.BiomeNoise;
import javax.swing.JTextField;

/**
 *
 * @author Jared Blackubr
 */
public class NoiseRecord extends AbstractRecord {
    BiomeNoise model;
    private JTextField lower;  // Lowe noise value
    private JTextField divide; // The int dividing them
    private JTextField upper;  // High noise value
    
    public NoiseRecord() {
        lower   = new JTextField();
        divide  = new JTextField();
        upper   = new JTextField();
    }

    @Override
    public void modify() {
        super.modify();
        model.modify(this);
    }
    
    public String getLower() {
        String text = lower.getText().trim();
        lower.setText(text);
        return text;
    }
    
    public String getUpper() {
        String text = upper.getText().trim();
        upper.setText(text);
        return text;
    }
    
    
    public int getBound() {
        int id;
        try {
            id = Integer.parseInt(divide.getText().trim());
        } catch (NumberFormatException ex) {
            id = model.getBound();
        }
        divide.setText(Integer.toString(id));
        return id;
    }
}
