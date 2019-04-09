package jaredbgreat.climaticbiome.gui;

import jaredbgreat.climaticbiome.ClimaticWorldSettings;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlider;
import net.minecraft.client.resources.I18n;

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
	

	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawCenteredString(this.fontRenderer, this.title, this.width / 2, 2, 16777215);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

}
