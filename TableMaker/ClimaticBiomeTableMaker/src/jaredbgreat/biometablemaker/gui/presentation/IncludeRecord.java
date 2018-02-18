package jaredbgreat.biometablemaker.gui.presentation;

import jaredbgreat.biometablemaker.data.IncludeData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTextField;

/**
 *
 * @author Jared Blackburn
 */
public class IncludeRecord extends AbstractRecord implements ActionListener {
    private List<JTextField> fields;
    private IncludeData model;

    @Override
    public void modify() {
        super.modify();
        model.modify(this);
        for(JTextField f : fields) {
            remove(f);
        }
        fields.clear();
        // TODO: (Not here) learn functional crap, though it seems pointless.
        for(String s : model.getFiles()) {
            JTextField f = new JTextField(FWIDTH);
            f.setText(s);
            f.addActionListener(this);
            fields.add(f);
            add(f);
        }
    }
    
    
    public List<JTextField> getFields() {
        return fields;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        modify();
    }
}
