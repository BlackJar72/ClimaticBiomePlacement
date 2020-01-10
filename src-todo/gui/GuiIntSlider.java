package jaredbgreat.climaticbiomes.gui;

import jaredbgreat.climaticbiomes.configuration.ClimaticWorldSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiPageButtonList.GuiResponder;
import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.client.gui.GuiSlider;
import net.minecraft.client.resources.I18n;

public class GuiIntSlider extends GuiSlider {
	final ClimaticWorldSettings.ISetter target;

	public GuiIntSlider(GuiResponder guiResponder, int idIn, int x, int y,
			String nameIn, int minIn, int maxIn, int defaultValue,
			FormatHelper formatter, ClimaticWorldSettings.ISetter target) {
		super(guiResponder, idIn, x, y, nameIn, minIn, maxIn, defaultValue, formatter);
		this.target = target;
	}
	
	
	@Override
	protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
		super.mouseDragged(mc, mouseX, mouseY);
		target.set(this);
	}

	
    @Override
    public float getSliderValue() {
        return (int)super.getSliderValue();
    }

	
    public int getSliderIntValue() {
        return (int)super.getSliderValue();
    }
    
    
    public void setSliderValue(int value, ClimaticWorldSettings settings) {
    	super.setSliderValue(value, false);    	
    	target.setTarget(settings);
    }
    

    @Override
    public void setSliderValue(float value, boolean notifyResponder) {
    	super.setSliderValue((int)value, notifyResponder);
    	target.set((int)value);
    }

}
