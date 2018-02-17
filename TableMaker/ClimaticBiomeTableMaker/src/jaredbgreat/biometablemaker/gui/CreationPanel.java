package jaredbgreat.biometablemaker.gui;

import jaredbgreat.biometablemaker.gui.control.ActionInterpreter;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Jared Blackburn
 */
public class CreationPanel extends JPanel {
    
    JButton makeTable;
    JButton makeList;
    JButton makeNoise;
    JButton makeTemp;
    JButton makeIsland;
    JButton makeType;
    JButton makeLeaf;
    
    GridLayout layout;
    
    public CreationPanel() {        
        layout = new GridLayout(8, 1, 8, 8);
        this.setSize(128, 480);
        
        makeTable = new JButton("makeTable");
        makeTable.setText("Table");
        makeTable.addActionListener(ActionInterpreter.getInterpreter());
        makeTable.setActionCommand("makeTable");
        makeTable.setName("mskeTable");        
        add(makeTable);
        
        makeList = new JButton("makeList");
        makeList.setText("List");
        makeList.addActionListener(ActionInterpreter.getInterpreter());
        makeList.setActionCommand("makeList");
        makeTable.setName("mskeList");
        add(makeList);
        
        makeNoise = new JButton("makeNoise");
        makeNoise.setText("Noise");
        makeNoise.addActionListener(ActionInterpreter.getInterpreter());
        makeNoise.setActionCommand("makeNoise");
        makeTable.setName("makeNoise");
        add(makeNoise);
        
        makeTemp = new JButton("makeTemp");
        makeTemp.setText("Temperature");
        makeTemp.addActionListener(ActionInterpreter.getInterpreter());
        makeTemp.setActionCommand("makeTemp");
        makeTable.setName("makeTemp");
        add(makeTemp);
        
        makeIsland = new JButton("makeIsland");
        makeIsland.setText("Island");
        makeIsland.addActionListener(ActionInterpreter.getInterpreter());
        makeIsland.setActionCommand("makeIsland");
        makeIsland.setName("makeIsland");
        add(makeIsland);
        
        makeType = new JButton("makeType");
        makeType.setText("Biome Type");
        makeType.addActionListener(ActionInterpreter.getInterpreter());
        makeType.setActionCommand("makeType");
        makeType.setName("makeType");
        add(makeType);
        
        makeLeaf = new JButton("makeLeaf");
        makeLeaf.setText("Biome");
        makeLeaf.addActionListener(ActionInterpreter.getInterpreter());
        makeLeaf.setActionCommand("makeLeaf");
        makeLeaf.setName("makeLeaf");
        add(makeLeaf);
        
        setLayout(layout);
    }
    
}
