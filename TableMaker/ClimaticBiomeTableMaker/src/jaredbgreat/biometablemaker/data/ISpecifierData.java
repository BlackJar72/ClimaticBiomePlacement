package jaredbgreat.biometablemaker.data;

import jaredbgreat.biometablemaker.gui.presentation.AbstractRecord;

/**
 * This is purely a marker for the certain data types; specifically, this is 
 * to let GSon know how to serialize them as part of another object.
 * 
 * @author Jared Blackburn
 */
public interface ISpecifierData {
    public void modify(AbstractRecord presentation);
}
