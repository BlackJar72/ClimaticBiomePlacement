package jaredbgreat.biometablemaker.gui.presentation;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Jared Blackburn
 */
public abstract class AbstractRecord extends JPanel implements KeyListener {
    public static final int FWIDTH = 16;
    JTextField nameField;
    JLabel nameLabel;
    
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

    @Override
    public void keyTyped(KeyEvent e) {
        modify();
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
