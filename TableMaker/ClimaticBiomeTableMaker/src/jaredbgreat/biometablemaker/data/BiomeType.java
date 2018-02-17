/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaredbgreat.biometablemaker.data;

import jaredbgreat.biometablemaker.gui.presentation.AbstractRecord;
import jaredbgreat.biometablemaker.gui.presentation.IncludeRecord;
import jaredbgreat.biometablemaker.gui.presentation.TypeRecord;

/**
 *
 * @author jared
 */
public class BiomeType implements ISpecifierData {
    String base;
    String hills;
    String mutated;
    String mutatedHills;
    String mountains;

    @Override
    public void modify(AbstractRecord presentation) {        
        TypeRecord rec = null;
        if(presentation instanceof IncludeRecord) {
            rec = (TypeRecord)presentation;
        } else {
            System.err.println("ERROR! Type data paired with wrong data "
                    + "type" + presentation.getClass().getCanonicalName() + ".");
            System.err.println(" \t Object was " + presentation.getName() 
                    + "  (" + presentation.toString() + ").");
            new Exception().printStackTrace();
            System.exit(1);
        }
        base = rec.getBase().getText();
        hills = rec.getHills().getText();
        mutated = rec.getMutated().getText();
        mutatedHills = rec.getMutatedHills().getText();
        mountains = rec.getMountains().getText();
    }
    
}
