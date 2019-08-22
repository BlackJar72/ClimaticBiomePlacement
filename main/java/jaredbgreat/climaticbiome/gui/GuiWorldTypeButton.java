package jaredbgreat.climaticbiome.gui;

import jaredbgreat.climaticbiome.generation.generator.SizeScale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;

public class GuiWorldTypeButton extends GuiButton {
	private int state;
	private int max;
	private static final String[] texts = new String[]{
		"createWorld.climaticbiomesjbg.continents",
		"createWorld.climaticbiomesjbg.isles",
		"createWorld.climaticbiomesjbg.water",
		"createWorld.climaticbiomesjbg.sislands"
	};

	
	public GuiWorldTypeButton(int buttonId, int x, int y, int defaultState) {
		super(buttonId, x, y, 150, 20, texts[(defaultState - 1) % 4]);
		state = (defaultState - 1) % 4;
		if(state == 4) {
			max = 4;
		} else {
			max = 3;
		}
		setStateText();
	}
	
	
	private void setStateText() {
		displayString = I18n.format(texts[state]);
	}
	
	
	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		boolean out = super.mousePressed(mc, mouseX, mouseY);
		if(out) {
			state = (state + 1) % max;
			setStateText();
		}
		return out;
	}
	
	
	public int getState() {
		return state + 1;
	}
}
