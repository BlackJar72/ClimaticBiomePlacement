package jaredbgreat.climaticbiome.proxy;

import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockLeaves;
import net.minecraft.item.Item;

public interface IProxy {

	public void registerItemRender(Item item, int meta, String id);
	public void fixRenders(BlockLeaves in);
	public void registerGateRenders(BlockFenceGate gate);
	public void preInit();
	public void init();
	
	// Debugging
	public void output(Exception e);
	
	
	
}
