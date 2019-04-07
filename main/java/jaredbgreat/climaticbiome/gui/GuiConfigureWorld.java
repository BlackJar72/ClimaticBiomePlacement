package jaredbgreat.climaticbiome.gui;

import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlider;

public class GuiConfigureWorld extends GuiScreen 
implements GuiSlider.FormatHelper, GuiPageButtonList.GuiResponder {
    private final GuiCreateWorld parent;

	public GuiConfigureWorld(GuiCreateWorld guiCreateWorld,
			String chunkProviderSettingsJson) {
        this.parent = (GuiCreateWorld)guiCreateWorld;
        //this.loadValues(chunkProviderSettingsJson);
	}

	@Override
	public void setEntryValue(int id, boolean value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEntryValue(int id, float value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEntryValue(int id, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getText(int id, String name, float value) {
		// TODO Auto-generated method stub
		return null;
	}

}
