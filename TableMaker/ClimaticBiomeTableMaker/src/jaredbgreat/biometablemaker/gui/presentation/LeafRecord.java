package jaredbgreat.biometablemaker.gui.presentation;

import jaredbgreat.biometablemaker.data.BiomeLeaf;
import javax.swing.JTextField;

/**
 *
 * @author Jared Blackburn
 */
public class LeafRecord extends AbstractRecord {
    BiomeLeaf model;
    private JTextField biome;
    private int id;
    
    
    public LeafRecord() {
        biome = new JTextField();
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
