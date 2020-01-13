package jaredbgreat.climaticbiomes.gui;

public class GuiWorldTypeButton /*extends GuiButton*/ {/*
	private int state;
	private ClimaticWorldSettings.ModeSetter target;
	private static final String[] texts = new String[]{
		"createWorld.climaticbiomesjbg.continents",
		"createWorld.climaticbiomesjbg.isles",
		"createWorld.climaticbiomesjbg.water",
		"createWorld.climaticbiomesjbg.sislands"
	};

	
	public GuiWorldTypeButton(int buttonId, int x, int y, int defaultState, 
				ClimaticWorldSettings.ModeSetter target) {
		super(buttonId, x, y, 150, 20, texts[(defaultState - 1) % ConfigHandler.modes]);
		state = (defaultState - 1) % ConfigHandler.modes;
		setStateText();
		this.target = target;
	}
	
	
	private void setStateText() {
		displayString = I18n.format(texts[state]);
	}
	
	
	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		boolean out = super.mousePressed(mc, mouseX, mouseY);
		if(out) {
			state = (state + 1) % ConfigHandler.modes;
			target.set(state);
			setStateText();
		}
		return out;
	}
    
    
    public void setValue(int value, ClimaticWorldSettings settings) {
    	state = value;
    	setStateText();  	
    	target.setTarget(settings);
    }
	
	
	public int getState() {
		return state + 1;
	}
*/}
