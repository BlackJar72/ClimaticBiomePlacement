package jaredbgreat.climaticbiome.proxy;

import jaredbgreat.climaticbiome.blocks.itemblocks.ItemMultiblock;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockLeaves;
import net.minecraft.item.Item;

public interface IProxy {

	public void registerItemRender(Item item, int meta, String id);
	public void registerItemRender(Item item, int meta);
	public void registerMultiRender(ItemMultiblock item);
	public void fixRenders(BlockLeaves in);
	public void registerGateRenders(BlockFenceGate gate);
	public void registerDoorRenders(BlockDoor door);
	public void preInit();
	public void init();
	
	// Debugging
	public void output(Exception e);
	
	
	
}
