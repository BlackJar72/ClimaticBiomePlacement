package jaredbgreat.climaticbiome.generation.chunk.biomes.generic;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetListedBiome implements IBiomeSpecifier {
	private IBiomeSpecifier[] biomes;
	
	/**
	 * The constructor will need to be able to be constructed from a list of 
	 * strings which my themselves be other IBiomeSpecifiers (such as using 
	 * noise for mesas and islands, or retrieving from another GetListedBiome()).
	 * These must be parsed and used to build whatever tree is in the config, 
	 * even if something ridiculous has been created (though ideally it should 
	 * be shallow, 2-3 layers counting leaf nodes).
	 */
	public GetListedBiome(/*PARAMETERS*/) {
		// TODO: EVRTYTHING!
	}
	

	@Override
	public int getBiome(ChunkTile tile) {
		tile.nextBiomeSeed();
		return biomes[tile.getBiomeSeed() % biomes.length].getBiome(tile);
	}

}
