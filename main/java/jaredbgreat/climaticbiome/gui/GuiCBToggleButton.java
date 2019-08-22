package jaredbgreat.climaticbiome.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;

public class GuiCBToggleButton extends GuiButton {
	private boolean state;
	private final String onText;
	private final String offText;

	public GuiCBToggleButton(int buttonId, int x, int y, String buttonText, boolean defaultState) {
		super(buttonId, x, y, 150, 20, buttonText);
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
		}
		return out;
	}
}
