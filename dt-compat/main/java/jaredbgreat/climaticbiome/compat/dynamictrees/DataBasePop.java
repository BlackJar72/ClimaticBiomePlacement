package jaredbgreat.climaticbiome.compat.dynamictrees;

import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.biomes.basic.ModBiomes;

import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.api.worldgen.BiomePropertySelectors.EnumChance;
import com.ferreusveritas.dynamictrees.api.worldgen.BiomePropertySelectors.RandomSpeciesSelector;
import com.ferreusveritas.dynamictrees.api.worldgen.IBiomeDataBasePopulator;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.worldgen.BiomeDataBase;
import com.ferreusveritas.dynamictrees.worldgen.BiomeDataBase.Operation;

public class DataBasePop implements IBiomeDataBasePopulator {

	private static Species oak;
	private static Species birch;
	private static Species spruce;
	private static Species acacia;
	private static Species jungle;
	private static Species apple;
	private static Species oakswamp;
	private static Species cactus;
	private static Species pine;

	@Override
	public void populate(BiomeDataBase db) {
		// Define vanilla trees
		oak = TreeRegistry.findSpeciesSloppy("oak");
		birch = TreeRegistry.findSpeciesSloppy("birch");
		spruce = TreeRegistry.findSpeciesSloppy("spruce");
		acacia = TreeRegistry.findSpeciesSloppy("acacia");
		jungle = TreeRegistry.findSpeciesSloppy("jungle"); // FIXME: Create mini jungle tree variant
		apple = TreeRegistry.findSpeciesSloppy("apple");
		oakswamp = TreeRegistry.findSpeciesSloppy("oakswamp");
		cactus = TreeRegistry.findSpeciesSloppy("cactus");
		if(ConfigHandler.addPines) {
			pine = DynamicTreeHelper.floridaPine;
		} else {
			pine = spruce;
		}
		
		db.setSpeciesSelector(ModBiomes.warmForest, 
				              new RandomSpeciesSelector().add(oak, 4)
				              .add(pine, 1), 
				              Operation.REPLACE);
		db.setForestness(ModBiomes.warmForest, 1.0f);
		db.setCancelVanillaTreeGen(ModBiomes.warmForest, true);
		db.setDensitySelector(ModBiomes.warmForest, (rnd, nd) -> nd * 1.0f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.warmForest, (rnd, spc, rad) -> EnumChance.OK, Operation.REPLACE);
		db.setSpeciesSelector(ModBiomes.warmForestHills, 
	              			  new RandomSpeciesSelector().add(oak, 4)
	              			  .add(pine, 1), 
	                          Operation.REPLACE);
		db.setForestness(ModBiomes.warmForestHills, 1.0f);
		db.setCancelVanillaTreeGen(ModBiomes.warmForestHills, true);
		db.setDensitySelector(ModBiomes.warmForestHills, (rnd, nd) -> nd * 1.0f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.warmForestHills, (rnd, spc, rad) -> EnumChance.OK, Operation.REPLACE);		
		db.setSpeciesSelector(ModBiomes.tropicalForest,
							  new RandomSpeciesSelector().add(acacia, 4).add(oak, 2)
							  /*.add(jungle, 1)*/, 
							  Operation.REPLACE);
		db.setForestness(ModBiomes.tropicalForest, 1.0f);
		db.setCancelVanillaTreeGen(ModBiomes.tropicalForest, true);
		db.setDensitySelector(ModBiomes.tropicalForest, (rnd, nd) -> nd * 1.0f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.tropicalForest, (rnd, spc, rad) -> EnumChance.OK, Operation.REPLACE);
		db.setSpeciesSelector(ModBiomes.tropicalForestHills, 
    			  			  new RandomSpeciesSelector().add(acacia, 4).add(oak, 2)
    			  			  /*.add(jungle, 1)*/, 
    			  			  Operation.REPLACE);
		db.setForestness(ModBiomes.tropicalForestHills, 1.0f);
		db.setCancelVanillaTreeGen(ModBiomes.tropicalForestHills, true);
		db.setDensitySelector(ModBiomes.tropicalForestHills, (rnd, nd) -> nd * 1.0f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.tropicalForestHills, (rnd, spc, rad) -> EnumChance.OK, Operation.REPLACE);
		db.setSpeciesSelector(ModBiomes.pineWoods, 
	  			  			  new RandomSpeciesSelector()
							  .add(pine, 4)
	  			  			  .add(oakswamp, 1), 
	  			  			  Operation.REPLACE);
		db.setForestness(ModBiomes.pineWoods, 1.0f);
		db.setCancelVanillaTreeGen(ModBiomes.pineWoods, true);
		db.setDensitySelector(ModBiomes.pineWoods, (rnd, nd) -> nd * 1.0f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.pineWoods, (rnd, spc, rad) -> EnumChance.OK, Operation.REPLACE);
		
		db.setSpeciesSelector(ModBiomes.denseScrub, 
	  			  new RandomSpeciesSelector().add(oak, 1), Operation.REPLACE);
		db.setDensitySelector(ModBiomes.denseScrub, (rnd, nd) -> 0.0f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.denseScrub, (rnd, spc, rad) -> rnd.nextInt(10) == 0 
				? EnumChance.OK : EnumChance.CANCEL, Operation.REPLACE);
		db.setCancelVanillaTreeGen(ModBiomes.denseScrub, false);
		db.setForestness(ModBiomes.denseScrub, 0.1f);

		db.setSpeciesSelector(ModBiomes.denseScrubHills, 
	  			  new RandomSpeciesSelector().add(oak, 1), Operation.REPLACE);
		db.setDensitySelector(ModBiomes.denseScrubHills, (rnd, nd) -> 0.0f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.denseScrubHills, (rnd, spc, rad) -> rnd.nextInt(10) == 0 
				? EnumChance.OK : EnumChance.CANCEL, Operation.REPLACE);
		db.setCancelVanillaTreeGen(ModBiomes.denseScrubHills, false);
		db.setForestness(ModBiomes.denseScrubHills, 0.1f);
		
		db.setSpeciesSelector(ModBiomes.dryScrub, 
	  			  new RandomSpeciesSelector().add(cactus, 1), Operation.REPLACE);
		db.setDensitySelector(ModBiomes.dryScrub, (rnd, nd) -> 0.0f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.dryScrub, (rnd, spc, rad) -> rnd.nextInt(10) == 0 
				? EnumChance.OK : EnumChance.CANCEL, Operation.REPLACE);
		db.setCancelVanillaTreeGen(ModBiomes.dryScrub, false);
		db.setForestness(ModBiomes.dryScrub, 0.0f);

		db.setSpeciesSelector(ModBiomes.dryScrubHills, 
	  			  new RandomSpeciesSelector().add(cactus, 1), Operation.REPLACE);
		db.setDensitySelector(ModBiomes.dryScrubHills, (rnd, nd) -> 0.0f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.dryScrubHills, (rnd, spc, rad) -> rnd.nextInt(10) == 0 
				? EnumChance.OK : EnumChance.CANCEL, Operation.REPLACE);
		db.setCancelVanillaTreeGen(ModBiomes.dryScrubHills, false);
		db.setForestness(ModBiomes.dryScrubHills, 0.0f);
		
		db.setSpeciesSelector(ModBiomes.warmMountain, 
	  			  new RandomSpeciesSelector().add(oak, 7)
	  			  							 .add(birch, 2)
	  			  							 .add(spruce, 1), Operation.REPLACE);
		db.setDensitySelector(ModBiomes.warmMountain, (rnd, nd) -> 0.0f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.warmMountain, (rnd, spc, rad) -> rnd.nextInt(24) == 0 
				? EnumChance.OK : EnumChance.CANCEL, Operation.REPLACE);
		db.setCancelVanillaTreeGen(ModBiomes.warmMountain, true);
		db.setForestness(ModBiomes.warmMountain, 0.01f);		
		
		db.setSpeciesSelector(ModBiomes.warmMountainTrees, 
	  			  new RandomSpeciesSelector().add(oak, 7)
	  			  							 .add(birch, 2)
	  			  							 .add(spruce, 1), Operation.REPLACE);
		db.setDensitySelector(ModBiomes.warmMountainTrees, (rnd, nd) -> 0.0f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.warmMountainTrees, (rnd, spc, rad) -> rnd.nextBoolean() 
				? EnumChance.OK : EnumChance.CANCEL, Operation.REPLACE);
		db.setCancelVanillaTreeGen(ModBiomes.warmMountainTrees, true);
		db.setForestness(ModBiomes.warmMountainTrees, 0.2f);
		
		db.setSpeciesSelector(ModBiomes.hotMountain, 
	  			  new RandomSpeciesSelector().add(oak, 4)
	  			  							 .add(pine, 2), 
	  			  							 Operation.REPLACE);
		db.setDensitySelector(ModBiomes.hotMountain, (rnd, nd) -> 0.0f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.hotMountain, (rnd, spc, rad) -> rnd.nextInt(24) == 0 
				? EnumChance.OK : EnumChance.CANCEL, Operation.REPLACE);
		db.setCancelVanillaTreeGen(ModBiomes.hotMountain, true);
		db.setForestness(ModBiomes.hotMountain, 0.01f);		
		
		db.setSpeciesSelector(ModBiomes.hotMountainTrees, 
	  			  new RandomSpeciesSelector().add(oak, 4)
											 .add(pine, 2), 
											 Operation.REPLACE);
		db.setDensitySelector(ModBiomes.hotMountainTrees, (rnd, nd) -> 0.0f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.hotMountainTrees, (rnd, spc, rad) -> rnd.nextBoolean() 
				? EnumChance.OK : EnumChance.CANCEL, Operation.REPLACE);
		db.setCancelVanillaTreeGen(ModBiomes.hotMountainTrees, true);
		db.setForestness(ModBiomes.hotMountainTrees, 0.2f);	
		
		db.setSpeciesSelector(ModBiomes.coolMontaneForest, 
	  			  new RandomSpeciesSelector().add(oak, 1)
											 .add(birch, 1)
											 .add(spruce, 2), 
											 Operation.REPLACE);
		db.setForestness(ModBiomes.coolMontaneForest, 1.0f);
		db.setCancelVanillaTreeGen(ModBiomes.coolMontaneForest, true);
		db.setDensitySelector(ModBiomes.coolMontaneForest, (rnd, nd) -> nd * 1.0f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.coolMontaneForest, (rnd, spc, rad) -> EnumChance.OK, Operation.REPLACE);	
		
		db.setSpeciesSelector(ModBiomes.warmMontaneForest, 
	  			  new RandomSpeciesSelector().add(oak, 1)
											 .add(birch, 1)
											 .add(spruce, 1)
											 .add(pine, 1), 
											 Operation.REPLACE);
		db.setForestness(ModBiomes.warmMontaneForest, 1.0f);
		db.setCancelVanillaTreeGen(ModBiomes.warmMontaneForest, true);
		db.setDensitySelector(ModBiomes.warmMontaneForest, (rnd, nd) -> nd * 1.0f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.warmMontaneForest, (rnd, spc, rad) -> EnumChance.OK, Operation.REPLACE);	
		
		db.setSpeciesSelector(ModBiomes.mediMontaneForest, 
	  			  new RandomSpeciesSelector().add(oak, 1)
											 .add(birch, 1)
											 .add(pine, 3), 
											 Operation.REPLACE);
		db.setForestness(ModBiomes.mediMontaneForest, 0.75f);
		db.setCancelVanillaTreeGen(ModBiomes.mediMontaneForest, true);
		db.setDensitySelector(ModBiomes.mediMontaneForest, (rnd, nd) -> nd * 0.75f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.mediMontaneForest, (rnd, spc, rad) -> (rnd.nextInt(4) == 0) 
				? EnumChance.CANCEL : EnumChance.OK, Operation.REPLACE);
		
		db.setSpeciesSelector(ModBiomes.montaneJungle, 
	  			  new RandomSpeciesSelector().add(oak, 1)
											 .add(acacia, 1)
											 .add(pine, 1)
											 .add(jungle, 3), 
											 Operation.REPLACE);
		db.setForestness(ModBiomes.montaneJungle, 1.0f);
		db.setCancelVanillaTreeGen(ModBiomes.montaneJungle, true);
		db.setDensitySelector(ModBiomes.montaneJungle, (rnd, nd) -> nd * 1.0f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.montaneJungle, (rnd, spc, rad) -> EnumChance.OK, Operation.REPLACE);
		
		db.setSpeciesSelector(ModBiomes.coolPlains, 
	  			  new RandomSpeciesSelector().add(oak, 1), 
											 Operation.REPLACE);
		db.setForestness(ModBiomes.coolPlains, 0.1f);
		db.setCancelVanillaTreeGen(ModBiomes.coolPlains, true);
		db.setDensitySelector(ModBiomes.coolPlains, (rnd, nd) -> nd * 0.1, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.coolPlains, (rnd, spc, rad) -> rnd.nextInt(10) == 0 
				? EnumChance.OK : EnumChance.CANCEL, Operation.REPLACE);
		
		db.setSpeciesSelector(ModBiomes.coldPlains, 
	  			  new RandomSpeciesSelector().add(oak, 1), 
											 Operation.REPLACE);
		db.setForestness(ModBiomes.coldPlains, 0.0f);
		db.setCancelVanillaTreeGen(ModBiomes.coldPlains, true);
		db.setDensitySelector(ModBiomes.coldPlains, (rnd, nd) -> 0.0, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.coldPlains, (rnd, spc, rad) -> EnumChance.CANCEL, Operation.REPLACE);
		
		db.setSpeciesSelector(ModBiomes.windswept, 
	  			  new RandomSpeciesSelector().add(oak, 1), 
											 Operation.REPLACE);
		db.setForestness(ModBiomes.windswept, 0.0f);
		db.setCancelVanillaTreeGen(ModBiomes.windswept, true);
		db.setDensitySelector(ModBiomes.windswept, (rnd, nd) -> 0.0, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.windswept, (rnd, spc, rad) -> EnumChance.CANCEL, Operation.REPLACE);
		
		db.setSpeciesSelector(ModBiomes.coolWindswept, 
	  			  new RandomSpeciesSelector().add(oak, 1), 
											 Operation.REPLACE);
		db.setForestness(ModBiomes.coolWindswept, 0.0f);
		db.setCancelVanillaTreeGen(ModBiomes.coolWindswept, true);
		db.setDensitySelector(ModBiomes.coolWindswept, (rnd, nd) -> 0.0, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.coolWindswept, (rnd, spc, rad) -> EnumChance.CANCEL, Operation.REPLACE);
		
		db.setSpeciesSelector(ModBiomes.activeVolcano, 
	  			  new RandomSpeciesSelector().add(oak, 1), 
											 Operation.REPLACE);
		db.setForestness(ModBiomes.activeVolcano, 0.0f);
		db.setCancelVanillaTreeGen(ModBiomes.activeVolcano, true);
		db.setDensitySelector(ModBiomes.activeVolcano, (rnd, nd) -> 0.0, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.activeVolcano, (rnd, spc, rad) -> EnumChance.CANCEL, Operation.REPLACE);
	}

}
