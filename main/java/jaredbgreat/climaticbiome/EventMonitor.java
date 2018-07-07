package jaredbgreat.climaticbiome;

import net.minecraftforge.common.MinecraftForge;

public class EventMonitor {
	private static EventMonitor handler;
	
	private EventMonitor() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	
	public static EventMonitor init() {
		if(handler == null) {
			handler = new EventMonitor();
		}
		return handler;
	}
}
