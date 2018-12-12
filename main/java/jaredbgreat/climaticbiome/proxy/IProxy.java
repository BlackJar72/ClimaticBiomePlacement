package jaredbgreat.climaticbiome.proxy;

import java.io.File;

import net.minecraft.block.BlockLeaves;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public interface IProxy {

	public void registerItemRender(Item item, int meta, String id);
	public void fixRenders(BlockLeaves in);
	public void preInit();
	public void init();
	
	// Debugging
	public void output(Exception e);
	
	
	
}
