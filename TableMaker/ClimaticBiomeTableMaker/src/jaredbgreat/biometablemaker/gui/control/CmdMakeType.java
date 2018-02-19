package jaredbgreat.biometablemaker.gui.control;

import jaredbgreat.biometablemaker.gui.EntryPanel;
import jaredbgreat.biometablemaker.gui.presentation.TypeRecord;
import java.awt.event.ActionEvent;

/**
 *
 * @author Jared Blackburn
 */
public class CmdMakeType implements ICommand {

    @Override
    public void execute(ActionEvent evt) {
        TypeRecord rec = new TypeRecord();
        ((EntryPanel)Components.map.get("mainPanel")).addEntry(rec);        
    }
    
}
