package jaredbgreat.climaticbiome.generation.chunk.biomes.generic;

import jaredbgreat.climaticbiome.generation.chunk.EnumBiomeType;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

/**
 * A biome finder for producing islands.  Unlike most biome specifiers,
 * this has two contructors.  The default contructor will create an 
 * instance that will assign biomes to created islands as if they were 
 * ordinary land, and always return 0 for oceans.  The other constructor 
 * takes two IBiomeSpecifiers, either of which can be null; if not null 
 * these will be used to find the island's biome.  If either of these is 
 * set to null it will produce default behavior for the relevant area 
 * (always basic ocean, id=0, for ocean, or treat land as ordinary land).
 */
 public class GetIslandBiome implements IBiomeSpecifier {
	private IBiomeSpecifier oceanFinder;
	private IBiomeSpecifier landFinder;
	 
	
	public GetIslandBiome init() {
		oceanFinder = new GetDefaultOceans();
		landFinder  = new GetDefaultLand();
		return this;	
	}		
	
	 
	public GetIslandBiome init(IBiomeSpecifier oceans, IBiomeSpecifier land) {
		oceanFinder = oceans;
		landFinder  = land;	
		if(landFinder == null) {
			landFinder = new GetDefaultLand();
		}
		if(oceanFinder == null) {
			oceanFinder = new GetDefaultOceans();
		}
		return this;
	}		
	

	@Override
	public int getBiome(ChunkTile tile) {
		tile.nextBiomeSeed();		
		if(tile.getNoise() > (3 + (tile.getBiomeSeed() % 3))) {	
			tile.nextBiomeSeed();
			return landFinder.getBiome(tile);
		} else {			
			tile.nextBiomeSeed();
			return oceanFinder.getBiome(tile);
		}		
	}
	
	
	private class GetDefaultLand implements IBiomeSpecifier {
		@Override
		public int getBiome(ChunkTile tile) {
			EnumBiomeType.findLandBiome(tile);
			return tile.getRlBiome().specifier.getBiome(tile);
		}
	}
	
	
	private class GetDefaultOceans implements IBiomeSpecifier {
		@Override
		public int getBiome(ChunkTile tile) {
			return 0;
		}
	}
	 
	 
 }