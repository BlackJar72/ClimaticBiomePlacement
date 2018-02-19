package jaredbgreat.biometablemaker.gui;

import jaredbgreat.biometablemaker.gui.presentation.AbstractRecord;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Jared Blackburn
 */
public class EntryPanel extends JPanel {
    List<AbstractRecord> entries;
    AbstractRecord current;
    int index;
    
    
    public EntryPanel() {
        index = 0;
        entries = new ArrayList<>();
        setMinimumSize(new Dimension(512, 480));
        setSize(512, 480);
        this.
        setVisible(true);
    }
    
    
    public void addEntry(AbstractRecord entry) {
        setVisible(false);
        entries.add(entry);
        index = entries.size() - 1;
        if(current != null) {
            remove(current);
        }
        current = entry;
        add(current);
        setVisible(true);
    }
    
    
    public void addEntryHere(AbstractRecord entry) {
        setVisible(false);
        entries.add(index, entry);
        if(current != null) {
            remove(current);
        }
        current = entry;
        add(current);
        current.setSize(this.getWidth(), this.getHeight());
        setVisible(true);
    }
    
    
    public void removeEntry(AbstractRecord entry) {
        setVisible(false);
        if(entries.contains(entry)) {
            if(entries.indexOf(entry) == index) {
                index = (index + 1) % entries.size() - 1;
                remove(entry);
                if(!entries.isEmpty()) {
                    current = entries.get(index);
                    add(current);
                }
            }
            entries.remove(entry);
        }
        setVisible(true);
    }
    
}
