package jaredbgreat.climaticbiome.gui;

import jaredbgreat.climaticbiome.ClimaticWorldSettings;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlider;

public class GuiConfigureWorld extends GuiScreen 
implements GuiSlider.FormatHelper, GuiPageButtonList.GuiResponder {

    private final GuiCreateWorld parent;
	public ClimaticWorldSettings settings;
    protected String title = "Climatic Biomes Customization Settings";
    //protected String subtitle = "Page 1 of 3";
    protected String pageTitle = "Basic Settings";
    private GuiPageButtonList list;
    private GuiButton done;

	public GuiConfigureWorld(GuiCreateWorld guiCreateWorld,
			String chunkProviderSettingsJson) {
        this.parent = (GuiCreateWorld)guiCreateWorld;
        if(chunkProviderSettingsJson.isEmpty()) {
        	settings = new ClimaticWorldSettings();
        } else {
        	// FIXME: This is a stand-in until I've made a proper factory / interpreter; TODO: make factory
        	settings = new ClimaticWorldSettings();        	
        }
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
