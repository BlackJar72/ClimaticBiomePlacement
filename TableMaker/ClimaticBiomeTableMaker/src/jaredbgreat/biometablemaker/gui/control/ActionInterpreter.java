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
        singleton.put("makeTable", new cmdMakeTable());
        singleton.put("makeList", new cmdMakeList());
        singleton.put("makeNoise", new cmdMakeList());
        singleton.put("makeTemp", new cmdMakeTemp());
        singleton.put("makeIsland", new cmdMakeIsland());
        singleton.put("makeType", new cmdMakeType());
        singleton.put("makeLeaf", new cmdMakeLeaf());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        get(e.getActionCommand()).execute(e);
    }
    
}
