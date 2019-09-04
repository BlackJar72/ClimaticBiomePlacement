package jaredbgreat.climaticbiome.gui;

public class GuiScaleSlider /*extends GuiSlider*/ {/*
	final ClimaticWorldSettings.MapScaleSetter target;

	public GuiScaleSlider(GuiResponder guiResponder, int idIn, int x, int y, int defaultValue,
			String nameIn, ClimaticWorldSettings.MapScaleSetter target) {
		super(guiResponder, idIn, x, y, nameIn, 0, 3, defaultValue, new ScaleFormatter());
		this.target = target;
	}
	
	
	@Override
	protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
		super.mouseDragged(mc, mouseX, mouseY);
		target.set(this);
	}

	
    @Override
    public float getSliderValue() {
        return Math.round(super.getSliderValue());
    }
    
    
    public void setSliderValue(int value, ClimaticWorldSettings settings) {
    	super.setSliderValue(value, false);   	
    	target.setTarget(settings);
    }
    

    @Override
    public void setSliderValue(float value, boolean notifyResponder) {
    	float v = Math.round(value);
    	super.setSliderValue(v, notifyResponder);
    	target.set(Math.round(v));
    }

	
	private static class ScaleFormatter implements GuiSlider.FormatHelper {
		private static final String[] valueStrings = new String[]{"x1", "x2", "x3", "x4"};
		@Override
		public String getText(int id, String name, float value) {
			return name + ": " + valueStrings[(int)value];
		}
	}

*/}
