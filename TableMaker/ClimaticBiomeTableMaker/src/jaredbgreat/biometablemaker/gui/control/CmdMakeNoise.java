package jaredbgreat.biometablemaker.gui.control;

import jaredbgreat.biometablemaker.gui.EntryPanel;
import jaredbgreat.biometablemaker.gui.presentation.NoiseRecord;
import java.awt.event.ActionEvent;

/**
 *
 * @author Jared Blackburn
 */
public class CmdMakeNoise implements ICommand {

    @Override
    public void execute(ActionEvent evt) {
        NoiseRecord rec = new NoiseRecord();
        ((EntryPanel)Components.map.get("mainPanel")).addEntry(rec);         
    }
    
}
