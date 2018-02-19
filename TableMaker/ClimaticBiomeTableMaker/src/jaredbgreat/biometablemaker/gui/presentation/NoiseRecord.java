package jaredbgreat.biometablemaker.gui.presentation;

import jaredbgreat.biometablemaker.data.BiomeGroup;
import jaredbgreat.biometablemaker.data.BiomeNoise;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Jared Blackubr
 */
public class NoiseRecord extends AbstractRecord {
    BiomeNoise model;
    private final JTextField lower;  // Lowe noise value
    private final JTextField divide; // The int dividing them
    private final JTextField upper;  // High noise value
    
    private final JLabel llabel;
    private final JLabel dlabel;
    private final JLabel ulabel;
    
    GridLayout layout;
    
    
    public NoiseRecord() {
        model = new BiomeNoise();
        
        layout = new GridLayout(4, 2, 4, 4);
        setLayout(layout);
        
        nameLabel = new JLabel("Name: ");
        nameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        add(nameLabel);
        
        nameField = new JTextField(FWIDTH);
        nameField.setHorizontalAlignment(SwingConstants.LEADING);
        nameField.addKeyListener(this);
        add(nameField); 
        
        ulabel = new JLabel("Upper Biome: ");
        ulabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(ulabel);
        
        upper = new JTextField(FWIDTH);
        upper.setHorizontalAlignment(SwingConstants.LEFT);
        upper.addKeyListener(this);
        add(upper);
        
        dlabel = new JLabel("Bound: ");
        dlabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(dlabel);
        
        divide = new JTextField(FWIDTH);
        divide.setHorizontalAlignment(SwingConstants.LEFT);
        divide.addKeyListener(this);
        add(divide);
        
        llabel = new JLabel("Lower Biome: ");
        llabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(llabel);
        
        lower = new JTextField(FWIDTH);
        lower.setHorizontalAlignment(SwingConstants.LEFT);
        lower.addKeyListener(this);
        add(lower);        
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
        int bound;
        try {
            bound = Integer.parseInt(divide.getText().trim());
        } catch (NumberFormatException ex) {
            divide.setText("");
            bound = 0;
        }
        if((bound < 1) || (bound > 9)) {
            divide.setText("");
            bound = 0;            
        } else {
            divide.setText(Integer.toString(bound));
        }
        return bound;
    }
}
