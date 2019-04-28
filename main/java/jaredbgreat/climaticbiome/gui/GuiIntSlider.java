package jaredbgreat.climaticbiome.gui;

import net.minecraft.client.gui.GuiPageButtonList.GuiResponder;
import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.client.gui.GuiSlider;
import net.minecraft.client.resources.I18n;

public class GuiIntSlider extends GuiSlider {

	public GuiIntSlider(GuiResponder guiResponder, int idIn, int x, int y,
			String nameIn, int minIn, int maxIn, int defaultValue,
			FormatHelper formatter) {
		super(guiResponder, idIn, x, y, nameIn, minIn, maxIn, defaultValue, formatter);
	}

	
    @Override
    public float getSliderValue() {
        return (int)super.getSliderValue();
    }
    

    @Override
    public void setSliderValue(float value, boolean notifyResponder) {
    	super.setSliderValue((int)value, notifyResponder);
    }

}
