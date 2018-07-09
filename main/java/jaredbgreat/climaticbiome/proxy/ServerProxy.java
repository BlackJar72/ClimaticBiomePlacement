package jaredbgreat.climaticbiome.proxy;

import net.minecraft.block.BlockLeaves;
import net.minecraft.item.Item;

public class ServerProxy implements IProxy {

	@Override
	public void registerItemRender(Item item, int meta, String id) {/*Do Nothing*/}

	@Override
	public void fixRenders(BlockLeaves in) {/*Do Nothing*/}
	

}
