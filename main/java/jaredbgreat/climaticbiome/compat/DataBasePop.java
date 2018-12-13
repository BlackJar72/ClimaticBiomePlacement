package jaredbgreat.climaticbiome.compat;

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
	private static Species spruce;
	private static Species birch;
	private static Species oakswamp;
	private static Species acacia;

	@Override
	public void populate(BiomeDataBase db) {
		// Define vanilla trees
		oak = TreeRegistry.findSpeciesSloppy("oak");
		spruce = TreeRegistry.findSpeciesSloppy("spruce");
		birch = TreeRegistry.findSpeciesSloppy("birch");
		acacia = TreeRegistry.findSpeciesSloppy("acacia");
		oakswamp = TreeRegistry.findSpeciesSloppy("oakswamp");
		db.setCancelVanillaTreeGen(ModBiomes.warmForest, true);
		db.setCancelVanillaTreeGen(ModBiomes.warmForestHills, true);
		db.setSpeciesSelector(ModBiomes.warmForest, 
	              new RandomSpeciesSelector().add(oak, 7)
	              .add(spruce, 2).add(birch, 1), 
	              Operation.REPLACE);
		db.setSpeciesSelector(ModBiomes.warmForestHills, 
    			  new RandomSpeciesSelector().add(oak, 7)
    			  .add(spruce, 2).add(birch, 1), 
                Operation.REPLACE);
		db.setCancelVanillaTreeGen(ModBiomes.tropicalForest, true);
		db.setCancelVanillaTreeGen(ModBiomes.tropicalForestHills, true);
		db.setSpeciesSelector(ModBiomes.tropicalForest,
				  new RandomSpeciesSelector().add(acacia, 4).add(oak, 2)
				  .add(birch, 1), 
				  Operation.REPLACE);
db.setSpeciesSelector(ModBiomes.tropicalForestHills, 
	  			  new RandomSpeciesSelector().add(acacia, 4).add(oak, 2)
	  			  .add(birch, 1), 
	  			  Operation.REPLACE);
		db.setCancelVanillaTreeGen(ModBiomes.pineWoods, true);
		db.setDensitySelector(ModBiomes.denseScrub, (rnd, nd) -> nd * 0.10, Operation.REPLACE);
	}

}
