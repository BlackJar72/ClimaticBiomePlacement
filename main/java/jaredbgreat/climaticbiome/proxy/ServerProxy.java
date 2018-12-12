package jaredbgreat.climaticbiome.proxy;

import java.io.File;

import net.minecraft.block.BlockLeaves;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class ServerProxy implements IProxy {

	@Override
	public void registerItemRender(Item item, int meta, String id) {/*Do Nothing*/}

	@Override
	public void fixRenders(BlockLeaves in) {/*Do Nothing*/}

	@Override
	public void preInit() {/*Do Nothing*/}

	@Override
	public void init() {/*Do Nothing*/}
	
	
	@Override
	public void output(Exception e) {
		System.err.println();
		System.err.println(" ********************");
		System.err.println(" * 	I AM THE SERVER * ");
		System.err.println(" ********************");
		System.err.println();
		if(e != null) {
			e.printStackTrace();
		}
		
	}
	

}
