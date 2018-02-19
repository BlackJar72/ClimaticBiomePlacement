package jaredbgreat.biometablemaker.gui.presentation;

import jaredbgreat.biometablemaker.data.BiomeType;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Jared Blackburn
 */
public class TypeRecord extends AbstractRecord {
    BiomeType model;
    private final JTextField base;
    private final JTextField hills;
    private final JTextField mutated;
    private final JTextField mutatedHills;
    private final JTextField mountains;
    
    private final JLabel blabel;
    private final JLabel hlabel;
    private final JLabel mlabel;
    private final JLabel mhlabel;
    private final JLabel mmlabel;
    
    GridLayout layout;
    
    
    public TypeRecord() {
        model = new BiomeType();
        layout = new GridLayout(6, 2, 4, 4);
        setLayout(layout);
        
        nameLabel = new JLabel("Name: ");
        nameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        add(nameLabel);
        
        nameField = new JTextField(FWIDTH);
        nameField.setHorizontalAlignment(SwingConstants.LEADING);
        nameField.addKeyListener(this);
        add(nameField); 
        
        blabel = new JLabel("Base Biome: ");
        blabel.setHorizontalAlignment(SwingConstants.TRAILING);
        add(blabel);
        
        base = new JTextField(FWIDTH);
        base.setHorizontalAlignment(SwingConstants.LEADING);
        base.addKeyListener(this);
        add(base); 
                
        hlabel = new JLabel("Hills Biome: ");
        hlabel.setHorizontalAlignment(SwingConstants.TRAILING);
        add(hlabel);
        
        hills = new JTextField(FWIDTH);
        hills.setHorizontalAlignment(SwingConstants.LEADING);
        hills.addKeyListener(this);
        add(hills); 
                
        mlabel = new JLabel("Mutated Biome: ");
        mlabel.setHorizontalAlignment(SwingConstants.TRAILING);
        add(mlabel);
        
        mutated = new JTextField(FWIDTH);
        mutated.setHorizontalAlignment(SwingConstants.LEADING);
        mutated.addKeyListener(this);
        add(mutated); 
                
        mhlabel = new JLabel("Mutated Hills: ");
        mhlabel.setHorizontalAlignment(SwingConstants.TRAILING);
        add(mhlabel);
        
        mutatedHills = new JTextField(FWIDTH);
        mutatedHills.setHorizontalAlignment(SwingConstants.LEADING);
        mutatedHills.addKeyListener(this);
        add(mutatedHills); 
                
        mmlabel = new JLabel("Mountain Biome: ");
        mmlabel.setHorizontalAlignment(SwingConstants.TRAILING);
        add(mmlabel);
        
        mountains = new JTextField(FWIDTH);
        mountains.setHorizontalAlignment(SwingConstants.LEADING);
        mountains.addKeyListener(this);
        add(mountains); 
    }

    @Override
    public void modify() {
        super.modify();
        trim(base);
        trim(hills);
        trim(mutated);
        trim(mutatedHills);
        trim(mountains);
        model.modify(this);
    }
    

    public JTextField getBase() {
        return base;
    }
    

    public JTextField getHills() {
        return hills;
    }
    

    public JTextField getMutated() {
        return mutated;
    }
    

    public JTextField getMutatedHills() {
        return mutatedHills;
    }
    

    public JTextField getMountains() {
        return mountains;
    }
    
    
}
