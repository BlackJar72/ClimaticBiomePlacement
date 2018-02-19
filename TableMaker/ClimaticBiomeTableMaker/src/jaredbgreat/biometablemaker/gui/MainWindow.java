package jaredbgreat.biometablemaker.gui;

import jaredbgreat.biometablemaker.gui.control.Components;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * @author Jared Blackburn
 */
public class MainWindow extends JFrame {
    
    BorderLayout layout;
    CreationPanel buttons;
    EntryPanel entryPanel;
    
    
    public MainWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Climatic Biome Table Maker");
        setSize(640, 480);
        
        layout = new BorderLayout();
        
        buttons = new CreationPanel();
        add(buttons, BorderLayout.PAGE_START);
        Components.map.put("buttonPanel", buttons);
        
        entryPanel = new EntryPanel();
        add(entryPanel, BorderLayout.CENTER);
        Components.map.put("mainPanel", entryPanel);
        
        setLayout(layout);
        setVisible(true);
    }
    
}
