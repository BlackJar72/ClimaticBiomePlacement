package jaredbgreat.biometablemaker.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 * @author Jared Blackburn
 */
public class MainWindow extends JFrame {
    
    BorderLayout layout;
    CreationPanel buttons;
    
    
    public MainWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(640, 480);
        
        layout = new BorderLayout();
        
        buttons = new CreationPanel();
        add(buttons, BorderLayout.PAGE_START);
        
        
        setLayout(layout);
        setVisible(true);
    }
    
}
