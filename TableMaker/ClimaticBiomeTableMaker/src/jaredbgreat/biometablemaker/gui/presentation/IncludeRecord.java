package jaredbgreat.biometablemaker.gui.presentation;

import jaredbgreat.biometablemaker.data.IncludeData;
import java.util.List;
import javax.swing.JTextField;

/**
 *
 * @author Jared Blackburn
 */
public class IncludeRecord extends AbstractRecord {
    private List<JTextField> fields;
    private IncludeData model;

    @Override
    public void modify() {
        super.modify();
        model.modify(this);
    }
    
    
    public List<JTextField> getFields() {
        return fields;
    }
}
