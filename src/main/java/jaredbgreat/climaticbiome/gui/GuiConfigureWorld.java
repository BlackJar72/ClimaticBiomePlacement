package jaredbgreat.climaticbiome.gui;

import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.configuration.ClimaticWorldSettings;
import jaredbgreat.climaticbiome.configuration.ConfigHandler;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlider;
import net.minecraft.client.resources.I18n;
import biomesoplenty.client.gui.GuiBOPConfigureWorld;

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
    private GuiCBToggleButton rockyScrubButton;
    private GuiCBToggleButton deepSandButton;
    private GuiCBToggleButton volcanicIslandsButton;
    private GuiCBToggleButton hasRiversButton;
    

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
//        buttonList.add(resetButton = new GuiButton(305, this.width / 2 - 44, this.height - 27, 90, 20, 
//        		I18n.format("BoP")));
        
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
        buttonList.add(rockyScrubButton = new GuiCBToggleButton(71, 40, 140,  
        		"createWorld." + Info.ID + ".rockyscrub", settings.rockyScrub, 
        		new ClimaticWorldSettings.RockyScrubSetter(settings)));
        buttonList.add(deepSandButton = new GuiCBToggleButton(72, width - 190, 140,  
        		"createWorld." + Info.ID + ".deepsand", settings.deepSand, 
        		new ClimaticWorldSettings.DeepSandSetter(settings)));  
        buttonList.add(volcanicIslandsButton = new GuiCBToggleButton(72, 40, 165,  
        		"createWorld." + Info.ID + ".volcanicislses", settings.volcanicIslands, 
        		new ClimaticWorldSettings.VolcanicIslandsSetter(settings)));   
        buttonList.add(hasRiversButton = new GuiCBToggleButton(72, width - 190, 165,  
        		"createWorld." + Info.ID + ".hasrivers", settings.hasRivers, 
        		new ClimaticWorldSettings.HasRiversSetter(settings)));      
		
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
			mc.displayGuiScreen(this.parent);
		} else if(button.id == 304) {
			resetDefaults();
		} /*else if(button.id == 305) {
			mc.displayGuiScreen(new GuiBOPConfigureWorld(parent, 
					parent.chunkProviderSettingsJson));
		}*/
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
	    rockyScrubButton.setValue(settings.rockyScrub, settings);
	    deepSandButton.setValue(settings.deepSand, settings);
	    volcanicIslandsButton.setValue(settings.volcanicIslands, settings);
	    hasRiversButton.setValue(settings.hasRivers, settings);
	}
	

	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawCenteredString(this.fontRenderer, this.title, this.width / 2, 2, 16777215);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

}
