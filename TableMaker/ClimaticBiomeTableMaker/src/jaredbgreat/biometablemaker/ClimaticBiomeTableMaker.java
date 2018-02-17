package jaredbgreat.biometablemaker;

import jaredbgreat.biometablemaker.gui.MainWindow;
import jaredbgreat.biometablemaker.gui.control.ActionInterpreter;

/**
 *
 * @author Jared Blackburn
 */
public class ClimaticBiomeTableMaker {

    private static MainWindow window;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ActionInterpreter.setup();
        window = new MainWindow();
    }
    
}
