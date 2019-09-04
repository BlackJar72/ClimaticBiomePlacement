package jaredbgreat.climaticbiome.util;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ItemRegistrar {/*	
	private static final List<Item> ITEMS = new ArrayList<>();
	
	public static ItemPeatBrick peatbrick;
	
	public static void initItems() {
		registerItems();
	}
	

	public static void registerItems() {
		IForgeRegistry<Item> regs = GameRegistry.findRegistry(Item.class);
		for(Item item : ITEMS) {
			regs.register(item);
		}
	}
	
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		for(Item item : ITEMS) {
			if(item instanceof IHaveModel) {
				((IHaveModel)item).registerModel();
			} else if(item instanceof ItemMultiblock){
				ClimaticBiomes.proxy.registerMultiRender((ItemMultiblock)item);
			} else {
				ClimaticBiomes.proxy.registerItemRender(item, 0);
			}
		}
		for(Block block : BlockRegistrar.getBlocks()) {
			if(block instanceof IHaveModel) {
				((IHaveModel)block).registerModel();
			}
		}
		ClimaticBiomes.proxy.fixRenders(BlockRegistrar.blockPineNeedles);
	}
	
	
	public static void registerMultiModels() {
		for(Item item : ITEMS) {
			if(item instanceof ItemMultiblock) {
				ClimaticBiomes.proxy.registerMultiRender((ItemMultiblock)item);
			} 
		}
	}
	
	
	public static void addItem(Item in) {
		ITEMS.add(in);
	}
	
	
	public static void oreDict() {
		OreDictionary.registerOre("logWood", BlockRegistrar.blockPineLog);
		OreDictionary.registerOre("plankWood", BlockRegistrar.blockPinePlanks);
		OreDictionary.registerOre("slabWood", BlockRegistrar.pineHalfSlab);
		OreDictionary.registerOre("treeLeaves", BlockRegistrar.blockPineNeedles);
		OreDictionary.registerOre("treeSapling", BlockRegistrar.blockPineSappling);
		if(ConfigHandler.includeVolcano) {
			OreDictionary.registerOre("blockBasalt", BlockRegistrar.blockBasalt);
			OreDictionary.registerOre("blockVolcanicAsh", BlockRegistrar.blockAsh);
		}
		if(ConfigHandler.includeSwamps) {
			OreDictionary.registerOre("blockPeat", BlockRegistrar.blockPeat);
			OreDictionary.registerOre("brickPeat", BlockRegistrar.blockPeat);
		}
	}
	
	
	/**
	 * For adding recipes -- should only be used for smelting, as crafting is being 
	 * done in JSON.
	 *//*
	public static void addRecipes() {
		GameRegistry.addSmelting(ForgeRegistries.ITEMS
				.getValue(new ResourceLocation(Info.ID + ":pine_log")), 
				new ItemStack(Items.COAL, 1, 1), 0.15f);
	}

*/}
