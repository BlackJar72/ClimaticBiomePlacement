package jaredbgreat.biometablemaker.gui.presentation;

import jaredbgreat.biometablemaker.data.BiomeType;
import javax.swing.JTextField;

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
    
    
    public TypeRecord() {
        base  = new JTextField();
        hills = new JTextField();
        mutated = new JTextField();
        mutatedHills = new JTextField();
        mountains = new JTextField();
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
