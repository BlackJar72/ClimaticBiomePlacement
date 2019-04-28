package jaredbgreat.climaticbiome.gui;

import net.minecraft.client.gui.GuiPageButtonList.GuiResponder;
import net.minecraft.client.gui.GuiSlider;

public class GuiScaleSlider extends GuiSlider {

	public GuiScaleSlider(GuiResponder guiResponder, int idIn, int x, int y,
			String nameIn) {
		super(guiResponder, idIn, x, y, nameIn, 0, 2, 0, new ScaleFormatter());
	}

	
    @Override
    public float getSliderValue() {
        return Math.round(super.getSliderValue());
    }
    

    @Override
    public void setSliderValue(float value, boolean notifyResponder) {
    	super.setSliderValue(Math.round(value), notifyResponder);
    }

	
	private static class ScaleFormatter implements GuiSlider.FormatHelper {
		private static final String[] valueStrings = new String[]{"x1", "x2", "x4"};
		@Override
		public String getText(int id, String name, float value) {
			return name + ": " + valueStrings[(int)value];
		}
	}

}
