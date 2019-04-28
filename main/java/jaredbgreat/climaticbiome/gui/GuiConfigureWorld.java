package jaredbgreat.climaticbiome.gui;

import jaredbgreat.climaticbiome.ClimaticWorldSettings;
import jaredbgreat.climaticbiome.Info;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlider;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings.Options;

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
        parent = (GuiCreateWorld)guiCreateWorld;
        settings = new ClimaticWorldSettings();
	}
	
	
	@Override
    public void initGui() {
		title = I18n.format("options.customizeTitle");
		buttonList.clear();
		buttonList.add(new GuiButton(302, 20, 5, 80, 20, I18n.format("createWorld.customize.custom.prev")));
		buttonList.add(new GuiButton(303, this.width - 100, 5, 80, 20, 
				I18n.format("createWorld.customize.custom.next")));
        buttonList.add(new GuiButton(304, this.width / 2 - 187, this.height - 27, 90, 20, 
        		I18n.format("createWorld.customize.custom.defaults")));
        buttonList.add(new GuiButton(300, this.width / 2 + 98, this.height - 27, 90, 20, 
        		I18n.format("gui.done")));
        
        // Sizing sliders -- these will be hidden from release version until / unless 
        //                   scaling options are implemented in the generator.
        buttonList.add(new GuiIntSlider(this, 64, 40, 40, 
        		I18n.format("createWorld." + Info.ID + ".biomesize"), 4, 64, 16, this));
        buttonList.add(new GuiScaleSlider(this, 65, width - 190, 40, 
        		I18n.format("createWorld." + Info.ID + ".genscale")));
        
        // Buttons for boolean options
        
		
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
        				64, I18n.format(Info.ID + "createWorld.customize.addboulders"), 
        				true, true),
        		new GuiPageButtonList.GuiButtonEntry(
        				64, I18n.format(Info.ID + "createWorld.customize.deepsand"), 
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
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawCenteredString(this.fontRenderer, this.title, this.width / 2, 2, 16777215);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

}
