package jaredbgreat.biometablemaker.gui.presentation;

import jaredbgreat.biometablemaker.data.BiomeList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;

/**
 *
 * @author Jared Blackburn
 */
public class ListRecord extends AbstractRecord implements ActionListener {
    BiomeList model;
    private List<JTextField> fields;
    private List<JTextField> values;
    
    
    public ListRecord() {
        fields = new ArrayList<>();
    }

    @Override
    public void modify() {
        super.modify();
        model.modify(this);
        for(JTextField f : fields) {
            remove(f);
        }
        fields.clear();
        for(String s : model.getBiomes()) {
            JTextField f = new JTextField(FWIDTH);
            f.setText(s);
            f.addActionListener(this);
            fields.add(f);
            add(f);
        }
    }
    
    
    private void modifyText(List<JTextField> text) {
        
    }
    
    
    public List<JTextField> getFields() {
        return fields;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        modify();
    }
}
