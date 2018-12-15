package jaredbgreat.climaticbiome.generation.biome.compat.dynamictrees;

import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.biomes.basic.ModBiomes;

import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.api.worldgen.BiomePropertySelectors.RandomSpeciesSelector;
import com.ferreusveritas.dynamictrees.api.worldgen.IBiomeDataBasePopulator;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.worldgen.BiomeDataBase;
import com.ferreusveritas.dynamictrees.worldgen.BiomeDataBase.Operation;

public class DataBasePop implements IBiomeDataBasePopulator {

	private static Species oak;
	private static Species acacia;
	private static Species jungle;
	private static Species apple;
	private static Species oakswamp;

	@Override
	public void populate(BiomeDataBase db) {
		// Define vanilla trees
		oak = TreeRegistry.findSpeciesSloppy("oak");
		acacia = TreeRegistry.findSpeciesSloppy("acacia");
		jungle = TreeRegistry.findSpeciesSloppy("jungle"); // FIXME: Create mini jungle tree variant
		apple = TreeRegistry.findSpeciesSloppy("apple");
		oakswamp = TreeRegistry.findSpeciesSloppy("oakswamp");
		// Actually set up biomes
		if(ConfigHandler.useDT) {
			db.setSpeciesSelector(ModBiomes.warmForest, 
					              new RandomSpeciesSelector().add(oak, 4)
					              .add(DynamicTreeHelper.floridaPine, 1), 
					              Operation.REPLACE);
			db.setCancelVanillaTreeGen(ModBiomes.warmForest, true);
			db.setSpeciesSelector(ModBiomes.warmForestHills, 
		              			  new RandomSpeciesSelector().add(oak, 4)
		              			  .add(DynamicTreeHelper.floridaPine, 1), 
		                          Operation.REPLACE);
			db.setCancelVanillaTreeGen(ModBiomes.warmForestHills, true);
			db.setSpeciesSelector(ModBiomes.tropicalForest,
								  new RandomSpeciesSelector().add(acacia, 4).add(oak, 2)
								  .add(DynamicTreeHelper.floridaPine, 1), 
								  Operation.REPLACE);
			db.setCancelVanillaTreeGen(ModBiomes.tropicalForest, true);
			db.setSpeciesSelector(ModBiomes.tropicalForestHills, 
	    			  			  new RandomSpeciesSelector().add(acacia, 4).add(oak, 2)
	    			  			  .add(DynamicTreeHelper.floridaPine, 1), 
	    			  			  Operation.REPLACE);
			db.setCancelVanillaTreeGen(ModBiomes.tropicalForestHills, true);
			db.setSpeciesSelector(ModBiomes.pineWoods, 
		  			  			  new RandomSpeciesSelector().add(DynamicTreeHelper.floridaPine, 4)
		  			  			  .add(oakswamp, 1), 
		  			  			  Operation.REPLACE);
			db.setCancelVanillaTreeGen(ModBiomes.pineWoods, true);
			db.setDensitySelector(ModBiomes.denseScrub, (rnd, nd) -> nd * 0.10, Operation.REPLACE);
		} else {
			db.setDensitySelector(ModBiomes.warmForest, (rnd, nd) -> 0, Operation.REPLACE);
			db.setDensitySelector(ModBiomes.warmForestHills, (rnd, nd) -> 0, Operation.REPLACE);
			db.setDensitySelector(ModBiomes.tropicalForest, (rnd, nd) -> 0, Operation.REPLACE);
			db.setDensitySelector(ModBiomes.tropicalForestHills, (rnd, nd) -> 0, Operation.REPLACE);
			db.setDensitySelector(ModBiomes.pineWoods, (rnd, nd) -> 0, Operation.REPLACE);
			db.setDensitySelector(ModBiomes.dryScrub, (rnd, nd) -> 0, Operation.REPLACE);
			db.setDensitySelector(ModBiomes.denseScrub, (rnd, nd) -> 0, Operation.REPLACE);
			db.setDensitySelector(ModBiomes.dryScrubHills, (rnd, nd) -> 0, Operation.REPLACE);
			db.setDensitySelector(ModBiomes.denseScrubHills, (rnd, nd) -> 0, Operation.REPLACE);
		}
	}

}
