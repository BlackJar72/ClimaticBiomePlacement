package jaredbgreat.climaticbiome.proxy;

public class ClientProxy implements IProxy {/*

	@Override
	public void registerItemRender(Item item, int meta) {
		ModelLoader.setCustomModelResourceLocation(item, meta, 
				new ModelResourceLocation(item.getRegistryName(), ""));
	}

	@Override
	public void registerItemRender(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, 
				new ModelResourceLocation(item.getRegistryName(), id));
	}
	
	
	@Override
	public void registerMultiRender(ItemMultiblock item) {
		for(int i = 0; i < item.names.length; i++) {
			ModelLoader.setCustomModelResourceLocation(item, i, 
					new ModelResourceLocation(item.getRegistryName().toString() + i, ""));
		
		}
	}
		

	@Override
	public void fixRenders(BlockLeaves in) {
		if(ConfigHandler.moddedBlocks) {
			in.setGraphicsLevel(Minecraft.getMinecraft().isFancyGraphicsEnabled());
		}
	}
	
	
	@Override
	public void registerGateRenders(BlockFenceGate gate) {
		ModelLoader.setCustomStateMapper(gate,
				(new StateMap.Builder()).ignore(new IProperty[] { BlockFenceGate.POWERED }).build());
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(gate), 0,
				new ModelResourceLocation(gate.getRegistryName().toString(), "inventory"));
		
	}
	
	
	@Override
	public void registerDoorRenders(BlockDoor door) {
		ModelLoader.setCustomStateMapper(door, 
				new StateMap.Builder().ignore(BlockDoor.POWERED).build());
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(door), 0,
				new ModelResourceLocation(door.getRegistryName(), "inventory"));
	}

	
	@Override
	public void output(Exception e) {
		System.err.println();
		System.err.println("*********************");
		System.err.println("*  I AM THE CLIENT  *");
		System.err.println("*********************");
		System.err.println();
		if(e != null) {
			e.printStackTrace();
		}		
	}

	@Override
	public void preInit() {
    	if(ConfigHandler.useDT) {
    		//DynamicTreeHelper.clientPreInit();
    	}
	}

	@Override
	public void init() {
    	if(ConfigHandler.useDT) {
    		//DynamicTreeHelper.clientInit();
    	}
	}

*/}
