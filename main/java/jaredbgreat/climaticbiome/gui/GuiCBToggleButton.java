package jaredbgreat.climaticbiome.gui;

import jaredbgreat.climaticbiome.configuration.ClimaticWorldSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;

public class GuiCBToggleButton extends GuiButton {
	final ClimaticWorldSettings.BooleanSetter target;
	private boolean state;
	private final String onText;
	private final String offText;

	public GuiCBToggleButton(int buttonId, int x, int y, String buttonText, 
				boolean defaultState, ClimaticWorldSettings.BooleanSetter target) {
		super(buttonId, x, y, 150, 20, buttonText);
		this.target = target;
		onText = buttonText + ".on";
		offText = buttonText + ".off";
		state = defaultState;
		setStateText();
	}
	
	
	private void setStateText() {
		if(state) {
			displayString = I18n.format(onText);
		} else {
			displayString = I18n.format(offText);			
		}
	}
	
	
	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		boolean out = super.mousePressed(mc, mouseX, mouseY);
		if(out) {
			state = !state;
			setStateText();
			target.set(this);
		}
		return out;
	}
	
	
	public boolean getState() {
		return state;
	}
    
    
    public void setValue(boolean value, ClimaticWorldSettings settings) {
    	state = value;
    	setStateText();   	
    	target.setTarget(settings);
    }
}
