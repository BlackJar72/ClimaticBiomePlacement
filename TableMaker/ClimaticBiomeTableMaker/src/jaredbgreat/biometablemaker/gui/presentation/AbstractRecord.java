package jaredbgreat.biometablemaker.gui.presentation;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Jared Blackburn
 */
public abstract class AbstractRecord extends JPanel {
    public static final int FWIDTH = 32;
    JTextField nameField;
    
    public void modify() {
        String name = nameField.getText().trim();
        nameField.setText(name);
    }
    
    
    public String getNameString() {
        return nameField.getText();
    }
    
    
    public void setNameString(String name) {
        nameField.setText(name);
    }
    
    
    public static void trim(JTextField f) {
        String text = f.getText().trim();
        f.setText(text);
    }
}
