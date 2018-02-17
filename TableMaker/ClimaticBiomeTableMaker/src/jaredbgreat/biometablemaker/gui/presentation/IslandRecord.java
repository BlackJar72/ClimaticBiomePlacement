package jaredbgreat.biometablemaker.gui.presentation;

import jaredbgreat.biometablemaker.data.BiomeIsland;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

/**
 *
 * @author Jared Blackurn
 */
public class IslandRecord extends AbstractRecord implements ActionListener {
    BiomeIsland model;
    JTextField land;
    JTextField ocean;
    
    
    @Override
    public void modify() {
        super.modify();
        model.modify(this);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        modify();
    }
    
    
    public String getLand() {
        String text = land.getText().trim();
        land.setText(text);
        return text;
    }
    
    
    public String getOcean() {
        String text = ocean.getText().trim();
        ocean.setText(text);
        return text;
    }
    
}
