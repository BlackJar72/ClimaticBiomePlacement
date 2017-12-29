package jaredbgreat.climaticbiome;

import jaredbgreat.climaticbiome.blocks.Sapling;

import java.util.Random;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
