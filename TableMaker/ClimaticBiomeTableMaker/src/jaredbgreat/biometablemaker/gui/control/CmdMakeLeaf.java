package jaredbgreat.biometablemaker.gui.control;

import jaredbgreat.biometablemaker.gui.EntryPanel;
import jaredbgreat.biometablemaker.gui.presentation.LeafRecord;
import java.awt.event.ActionEvent;

/**
 *
 * @author Jared Blackburn
 */
public class CmdMakeLeaf implements ICommand {

    @Override
    public void execute(ActionEvent evt) {
        LeafRecord rec = new LeafRecord();
        ((EntryPanel)Components.map.get("mainPanel")).addEntry(rec);
    }
    
}
