package jaredbgreat.climaticbiome.gui;

import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.configuration.ClimaticWorldSettings;
import jaredbgreat.climaticbiome.configuration.ConfigHandler;
import jaredbgreat.climaticbiome.util.Debug;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlider;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.storage.ISaveFormat;

public class GuiConfigureWorld extends GuiScreen 
implements GuiSlider.FormatHelper, GuiPageButtonList.GuiResponder {

    private final GuiCreateWorld parent;
	public ClimaticWorldSettings settings;
    protected String title = "Climatic Biomes Customization Settings";
    //protected String subtitle = "Page 1 of 3";
    protected String pageTitle = "Basic Settings";
    private GuiPageButtonList list;
    private GuiButton doneButton;
    private GuiButton resetButton;
    
    private GuiIntSlider biomeSizeSlider;
    private GuiScaleSlider sizeScaleSlider;
    private GuiWorldTypeButton modeButton;
    private GuiIntSlider siSizeButton;
    private GuiCBToggleButton forceWholeButton;
    private GuiCBToggleButton addIslandsButton;
    private GuiCBToggleButton extraBeachButton;
    

	public GuiConfigureWorld(GuiCreateWorld guiCreateWorld,
			String chunkProviderSettingsJson) {
        parent = (GuiCreateWorld)guiCreateWorld;
        settings = ClimaticWorldSettings.getNew();
	}
	
	
	@Override
    public void initGui() {
		title = I18n.format("options.customizeTitle");
		buttonList.clear();
//		buttonList.add(new GuiButton(302, 20, 5, 80, 20, I18n.format("createWorld.customize.custom.prev")));
//		buttonList.add(new GuiButton(303, this.width - 100, 5, 80, 20, 
//				I18n.format("createWorld.customize.custom.next")));
        buttonList.add(doneButton = new GuiButton(304, this.width / 2 - 187, this.height - 27, 90, 20, 
        		I18n.format("createWorld.customize.custom.defaults")));
        buttonList.add(resetButton = new GuiButton(300, this.width / 2 + 98, this.height - 27, 90, 20, 
        		I18n.format("gui.done")));
        
        buttonList.add(biomeSizeSlider = new GuiIntSlider(this, 64, 40, 40, 
        		I18n.format("createWorld." + Info.ID + ".biomesize"), 4, 64, 
        		settings.biomeSize, this, 
        		new ClimaticWorldSettings.BiomeSizeSetter(settings)));
        buttonList.add(sizeScaleSlider = new GuiScaleSlider(this, 65, width - 190, 40, 
        		settings.regionSize.ordinal(),
        		I18n.format("createWorld." + Info.ID + ".genscale"),
				new ClimaticWorldSettings.MapScaleSetter(settings)));
        
        // World type button
        buttonList.add(modeButton = new GuiWorldTypeButton(66, 40, 65, 
        		settings.mode, new ClimaticWorldSettings.ModeSetter(settings)));
        buttonList.add(siSizeButton = new GuiIntSlider(this, 67, 40, 90, 
        		I18n.format("createWorld." + Info.ID + ".sisize"), 4, 18, 
        		(int)settings.sisize, this,
        		new ClimaticWorldSettings.SISizeSetter(settings)));
        siSizeButton.visible = siSizeButton.enabled = ConfigHandler.includeSI;
        
        
        // Buttons for boolean options
        buttonList.add(forceWholeButton = new GuiCBToggleButton(68, width - 190, 65,  
        		"createWorld." + Info.ID + ".forcewhole", settings.forceWhole, 
        		new ClimaticWorldSettings.ForceWholeSetter(settings)));
        buttonList.add(addIslandsButton = new GuiCBToggleButton(69, 40, 115,  
        		"createWorld." + Info.ID + ".addisles", settings.addIslands, 
        		new ClimaticWorldSettings.AddIslandsSetter(settings)));
        buttonList.add(extraBeachButton = new GuiCBToggleButton(70, width - 190, 115,  
        		"createWorld." + Info.ID + ".addbeach", settings.extraBeaches, 
        		new ClimaticWorldSettings.ExtraBeachSetter(settings)));       
		
	}
	


    private void createPagedList() {
        GuiPageButtonList.GuiListEntry[] options1 = {
        		new GuiPageButtonList.GuiSlideEntry(160, 
        				I18n.format(Info.ID + "createWorld.customize.biomesize"), 
        				true, this, 8f, 64f, (float)16),
        		new GuiPageButtonList.GuiButtonEntry(
        				64, I18n.format(Info.ID + "createWorld.customize.addrivers"), 
        				true, true),
        		new GuiPageButtonList.GuiButtonEntry(
        				64, I18n.format(Info.ID + "createWorld.customize.addbeaches"), 
        				true, true),
        		new GuiPageButtonList.GuiButtonEntry(
        				64, I18n.format(Info.ID + "createWorld.customize.addislands"), 
        				true, true),
        		
        };
        
        list = new GuiPageButtonList(mc, this.width, this.height, 32, this.height - 32, 25, 
        		this, new GuiPageButtonList.GuiListEntry[][]{options1, options1, options1, options1});
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
		return name + ": " + (int)value;
	}	

    
	@Override
    protected void actionPerformed(GuiButton button) throws IOException {
		if(button.id == 300) {
			this.parent.chunkProviderSettingsJson = settings.toJsonString();
			this.mc.displayGuiScreen(this.parent);
		} else if(button.id == 304) {
			resetDefaults();
		}
	}
	
	
	private void resetDefaults() {
		settings = ClimaticWorldSettings.getNew();
	    biomeSizeSlider.setSliderValue(settings.biomeSize, settings);
	    sizeScaleSlider.setSliderValue(settings.regionSize.ordinal(), settings);
	    modeButton.setValue(settings.mode - 1, settings);
	    siSizeButton.setSliderValue((int)settings.sisize, settings);
	    forceWholeButton.setValue(settings.forceWhole, settings);
	    addIslandsButton.setValue(settings.addIslands, settings);
	    extraBeachButton.setValue(settings.extraBeaches, settings);
	}
	

	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawCenteredString(this.fontRenderer, this.title, this.width / 2, 2, 16777215);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

}
