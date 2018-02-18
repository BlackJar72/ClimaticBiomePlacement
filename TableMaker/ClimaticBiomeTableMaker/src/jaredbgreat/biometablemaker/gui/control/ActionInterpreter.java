package jaredbgreat.biometablemaker.gui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 *
 * @author Jared Blackburn
 */
public class ActionInterpreter extends HashMap<String, ICommand> 
            implements ActionListener {
    private static final ActionInterpreter singleton = new ActionInterpreter();
   
    /**
     * This will return the default ActionInterpeter.
     * 
     * @return 
     */
    public static ActionInterpreter getInterpreter() {
        return singleton;
    }
    
    
    /**
     * This must be called during application initialization to setup the 
     * table of commands.
     */
    public static void setup() {
        singleton.put("makeIncludes", new CmdMakeIncludes());
        singleton.put("makeTable", new CmdMakeTable());
        singleton.put("makeList", new CmdMakeList());
        singleton.put("makeNoise", new CmdMakeList());
        singleton.put("makeIsland", new CmdMakeIsland());
        singleton.put("makeType", new CmdMakeType());
        singleton.put("makeLeaf", new CmdMakeLeaf());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        get(e.getActionCommand()).execute(e);
    }
    
}
