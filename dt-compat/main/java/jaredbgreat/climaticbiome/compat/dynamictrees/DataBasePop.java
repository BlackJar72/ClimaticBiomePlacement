package jaredbgreat.climaticbiome.compat.dynamictrees;

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
	private static Species acacia;
	private static Species jungle;
	private static Species apple;
	private static Species oakswamp;
	private static Species cactus;

	@Override
	public void populate(BiomeDataBase db) {
		// Define vanilla trees
		oak = TreeRegistry.findSpeciesSloppy("oak");
		acacia = TreeRegistry.findSpeciesSloppy("acacia");
		jungle = TreeRegistry.findSpeciesSloppy("jungle"); // FIXME: Create mini jungle tree variant
		apple = TreeRegistry.findSpeciesSloppy("apple");
		oakswamp = TreeRegistry.findSpeciesSloppy("oakswamp");
		cactus = TreeRegistry.findSpeciesSloppy("cactus");
		db.setSpeciesSelector(ModBiomes.warmForest, 
				              new RandomSpeciesSelector().add(oak, 4)
				              .add(DynamicTreeHelper.floridaPine, 1), 
				              Operation.REPLACE);
		//db.setForestness(ModBiomes.warmForest, 1.0f);
		db.setCancelVanillaTreeGen(ModBiomes.warmForest, true);
		db.setDensitySelector(ModBiomes.warmForest, (rnd, nd) -> nd * 1.0f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.warmForest, (rnd, spc, rad) -> EnumChance.OK, Operation.REPLACE);
		db.setSpeciesSelector(ModBiomes.warmForestHills, 
	              			  new RandomSpeciesSelector().add(oak, 4)
	              			  .add(DynamicTreeHelper.floridaPine, 1), 
	                          Operation.REPLACE);
		//db.setForestness(ModBiomes.warmForestHills, 1.0f);
		db.setCancelVanillaTreeGen(ModBiomes.warmForestHills, true);
		db.setDensitySelector(ModBiomes.warmForestHills, (rnd, nd) -> nd * 1.0f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.warmForestHills, (rnd, spc, rad) -> EnumChance.OK, Operation.REPLACE);		
		db.setSpeciesSelector(ModBiomes.tropicalForest,
							  new RandomSpeciesSelector().add(acacia, 4).add(oak, 2)
							  /*.add(jungle, 1)*/, 
							  Operation.REPLACE);
		//db.setForestness(ModBiomes.tropicalForest, 1.0f);
		db.setCancelVanillaTreeGen(ModBiomes.tropicalForest, true);
		db.setDensitySelector(ModBiomes.tropicalForest, (rnd, nd) -> nd * 1.0f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.tropicalForest, (rnd, spc, rad) -> EnumChance.OK, Operation.REPLACE);
		db.setSpeciesSelector(ModBiomes.tropicalForestHills, 
    			  			  new RandomSpeciesSelector().add(acacia, 4).add(oak, 2)
    			  			  /*.add(jungle, 1)*/, 
    			  			  Operation.REPLACE);
		//db.setForestness(ModBiomes.tropicalForestHills, 1.0f);
		db.setCancelVanillaTreeGen(ModBiomes.tropicalForestHills, true);
		db.setDensitySelector(ModBiomes.tropicalForestHills, (rnd, nd) -> nd * 1.0f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.tropicalForestHills, (rnd, spc, rad) -> EnumChance.OK, Operation.REPLACE);
		db.setSpeciesSelector(ModBiomes.pineWoods, 
	  			  			  new RandomSpeciesSelector().add(DynamicTreeHelper.floridaPine, 4)
	  			  			  .add(oakswamp, 1), 
	  			  			  Operation.REPLACE);
		//db.setForestness(ModBiomes.pineWoods, 1.0f);
		db.setCancelVanillaTreeGen(ModBiomes.pineWoods, true);
		db.setDensitySelector(ModBiomes.pineWoods, (rnd, nd) -> nd * 1.0f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.pineWoods, (rnd, spc, rad) -> EnumChance.OK, Operation.REPLACE);
		
		db.setSpeciesSelector(ModBiomes.denseScrub, 
	  			  new RandomSpeciesSelector().add(oak, 1), Operation.REPLACE);
		db.setDensitySelector(ModBiomes.denseScrub, (rnd, nd) -> 0.0f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.denseScrub, (rnd, spc, rad) -> rnd.nextInt(10) == 0 
				? EnumChance.OK : EnumChance.CANCEL, Operation.REPLACE);
		db.setCancelVanillaTreeGen(ModBiomes.denseScrub, false);
		//db.setForestness(ModBiomes.denseScrub, 0.05f);

		db.setSpeciesSelector(ModBiomes.denseScrubHills, 
	  			  new RandomSpeciesSelector().add(oak, 1), Operation.REPLACE);
		db.setDensitySelector(ModBiomes.denseScrubHills, (rnd, nd) -> 0.0f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.denseScrubHills, (rnd, spc, rad) -> rnd.nextInt(10) == 0 
				? EnumChance.OK : EnumChance.CANCEL, Operation.REPLACE);
		db.setCancelVanillaTreeGen(ModBiomes.denseScrubHills, false);
		//db.setForestness(ModBiomes.denseScrubHills, 0.05f);
		
		db.setSpeciesSelector(ModBiomes.dryScrub, 
	  			  new RandomSpeciesSelector().add(cactus, 1), Operation.REPLACE);
		db.setDensitySelector(ModBiomes.dryScrub, (rnd, nd) -> 0.0f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.dryScrub, (rnd, spc, rad) -> rnd.nextInt(10) == 0 
				? EnumChance.OK : EnumChance.CANCEL, Operation.REPLACE);
		db.setCancelVanillaTreeGen(ModBiomes.dryScrub, false);
		//db.setForestness(ModBiomes.dryScrub, 0.05f);

		db.setSpeciesSelector(ModBiomes.dryScrubHills, 
	  			  new RandomSpeciesSelector().add(cactus, 1), Operation.REPLACE);
		db.setDensitySelector(ModBiomes.dryScrubHills, (rnd, nd) -> 0.0f, Operation.REPLACE);
		db.setChanceSelector(ModBiomes.dryScrubHills, (rnd, spc, rad) -> rnd.nextInt(10) == 0 
				? EnumChance.OK : EnumChance.CANCEL, Operation.REPLACE);
		db.setCancelVanillaTreeGen(ModBiomes.dryScrubHills, false);
		//db.setForestness(ModBiomes.dryScrubHills, 0.05f);
	}

}
