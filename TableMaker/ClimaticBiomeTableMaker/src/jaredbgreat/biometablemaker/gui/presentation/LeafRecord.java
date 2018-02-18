package jaredbgreat.biometablemaker.gui.presentation;

import jaredbgreat.biometablemaker.data.BiomeLeaf;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Jared Blackburn
 */
public class LeafRecord extends AbstractRecord {
    GridLayout layout;
    BiomeLeaf model;
    private final JTextField biome;
    private final JLabel biomeLabel;
    private int id;
    
    
    public LeafRecord() {
        model = new BiomeLeaf();
        layout = new GridLayout(2, 2, 8, 8);
        setLayout(layout);
        setSize(384, 480);
        
        nameLabel = new JLabel("Name: ");
        nameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        add(nameLabel, 0, 0);
        
        nameField = new JTextField(FWIDTH);
        nameField.setHorizontalAlignment(SwingConstants.LEADING);
        nameField.addKeyListener(this);
        add(nameField);
        
        biomeLabel = new JLabel("Biome ID: ");
        biomeLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        add(biomeLabel);
        
        biome = new JTextField(FWIDTH);
        biome.setHorizontalAlignment(SwingConstants.LEADING);
        biome.addKeyListener(this);
        add(biome);
        
        setVisible(true);
    }
    

    @Override
    public void modify() {
        super.modify();
        try {
            id = Integer.parseInt(biome.getText().trim());
        } catch (NumberFormatException ex) {
            id = model.getBiome();
        }
        biome.setText(Integer.toString(id));
        model.modify(this);
    }
    

    public int getId() {
        return id;
    }
}
