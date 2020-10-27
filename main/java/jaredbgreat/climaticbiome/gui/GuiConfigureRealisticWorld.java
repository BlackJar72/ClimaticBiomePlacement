package jaredbgreat.climaticbiome.gui;

import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.configuration.ClimaticWorldSettings;
import net.minecraft.client.gui.GuiCreateWorld;

public class GuiConfigureRealisticWorld extends GuiConfigureWorld {
    private GuiCBToggleButton bigMountainsButton;

	public GuiConfigureRealisticWorld(GuiCreateWorld guiCreateWorld, String chunkProviderSettingsJson) {
		super(guiCreateWorld, chunkProviderSettingsJson);
	}
	
	
	@Override
    public void initGui() {
		super.initGui();		  
        buttonList.add(bigMountainsButton = new GuiCBToggleButton(75, 40, 190,  
        		"createWorld." + Info.ID + ".bigmountains", settings.volcanicIslands, 
        		new ClimaticWorldSettings.HasBigMountains(settings)));  
	}

}
