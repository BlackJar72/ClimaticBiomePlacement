package jaredbgreat.climaticbiome.generation.mapgenerator;

import java.util.Set;

import jaredbgreat.climaticbiome.configuration.ClimaticWorldSettings;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;


public enum TerrainType {
    // This would be the norm if using non-biome height/scale data
	VARIABLE (new VariableHeightAdjuster()),
	// This would force the used of biome height/scale data
	VANILLA (new VanillaHeightAdjuster()),     
	// For mountains, this adds variable and vanilla height, uses the higher scale
	MOUNTIANOUS (new MountainousHeightAdjuster()),
	// Like vanilla but reduces some averaging; for plateaus and technical biomes
	STEEP (new VanillaHeightAdjuster()),       
	// Use the mean of biome and noise; might be used for hills?
	AVERAGED (new AveragedHeightAdjuster()),
	// Used for plateaus, before setting them to STEEP
	PLATEAU (new PlateauHeightAdjuster()),
	// Used for swamps, before setting them to STEEP
	SWAMP (new SwampHeightAdjuster());    
	
	public static final TerrainType[] types = values(); // Just make it public, with no method call... 
	
	public final BiomeHeightAdjuster heightAdjuster;
	
	TerrainType(BiomeHeightAdjuster adjuster) {
		heightAdjuster = adjuster;
	}
	    
	    
    /**
     * A convenience method, but one probably better coded locally in 
     * most situations for efficiency (at least in intended uses).  In 
     * some ways this is a reminder, but could be handy in non-performance 
     * critical code.
     * 
     * n is the number being converted to an asymptopic form.
     * start is the place where the output should start to curve.
     * rate is the reciprical of the value it should approach minus the start.
     * 
     * @param n
     * @param start
     * @param rate 
     * @return
     */
    public static float asymptote(float n, float start, float rate) {
    	if(n > start)
    	return start + (rate / (n - start + 1));
    	return n;
    }

    
    /**
     * Inverse of the ordinal() method, for use in decoding from serialized 
     * or otherwise stored ordinal values.  This does not bounds check.
     * 
     * @param number
     * @return The TerrainType corresponding the ordinal. 
     */
	public static TerrainType fromOrdinal(int number) {
		return types[number];
	}
	
	
	/*----------------------------------------------------------------------*/
	/*                    BIOME HEIGHT ADJUSTERS                            */
	/*----------------------------------------------------------------------*/
	
	
	public interface BiomeHeightAdjuster {
		public void processTile(ChunkTile tile, ClimaticWorldSettings settings);
	}
	
	
	public static final class VariableHeightAdjuster implements BiomeHeightAdjuster {
		@Override
		public void processTile(ChunkTile tile, ClimaticWorldSettings settings) {
			Biome biome = Biome.getBiome(tile.rlBiome, Biomes.DEFAULT);
			Set types = BiomeDictionary.getTypes(biome);
			if(types.contains(Type.MESA)) {
				// Mesa biomes are always treat as plateaus
				tile.height = (biome.getBaseHeight()) + (tile.height * 0.1f);
				tile.scale = biome.getHeightVariation() + 0.05f + (tile.scale * 0.1f);
				tile.terrainType = STEEP;
			} else if(types.contains(Type.HILLS) || types.contains(Type.MOUNTAIN)) {
                // Hills variants are made at least a little hilly but prevented from
                // being mountainous; only biome placed as mountains (alpine) are 
                // treated fully as mountains.
                tile.height = (tile.height + biome.getBaseHeight()) * 0.5d;
                tile.scale = (tile.scale + biome.getHeightVariation()) * 0.5f;
            }
		}
	}
	
	
	public static final class VanillaHeightAdjuster implements BiomeHeightAdjuster {
		@Override
		public void processTile(ChunkTile tile, ClimaticWorldSettings settings) {
			Biome biome = Biome.getBiome(tile.rlBiome, Biomes.DEFAULT);
			tile.height = biome.getBaseHeight();
			tile.scale = biome.getHeightVariation();
		}
	}
	
	
	public static final class MountainousHeightAdjuster implements BiomeHeightAdjuster {
		@Override
		public void processTile(ChunkTile tile, ClimaticWorldSettings settings) {
			Biome biome = Biome.getBiome(tile.rlBiome, Biomes.DEFAULT);
			int seed = tile.nextBiomeSeed().getBiomeSeed();
			if((seed & 0x1) == 0x1) {
				tile.height *= 2.0d;
			}
			tile.height += biome.getBaseHeight();
			if((seed & 0x2) == 0x2) {
				tile.scale  += biome.getHeightVariation();
			} else {
				tile.scale = Math.max(tile.scale, biome.getHeightVariation());
			}
			if(settings.bigMountains) {
				tile.height += tile.centrality;
				tile.scale += tile.centrality / 4.0;
			}
		}
	}
	
	
	public static final class AveragedHeightAdjuster implements BiomeHeightAdjuster {
		@Override
		public void processTile(ChunkTile tile, ClimaticWorldSettings settings) {
			Biome biome = Biome.getBiome(tile.rlBiome, Biomes.DEFAULT);
			tile.height = (tile.height + biome.getBaseHeight()) * 0.5f ;
			tile.scale  = (tile.scale + biome.getHeightVariation()) * 0.5f;
		}
	}
	
	
	public static final class PlateauHeightAdjuster implements BiomeHeightAdjuster {
		@Override
		public void processTile(ChunkTile tile, ClimaticWorldSettings settings) {
			Biome biome = Biome.getBiome(tile.rlBiome, Biomes.DEFAULT);
			tile.height = (biome.getBaseHeight()) + (tile.height * 0.1f);
			tile.scale = biome.getHeightVariation() + 0.05f + (tile.scale * 0.1f);
			tile.terrainType = STEEP;
		}
	}
	
	
	public static final class SwampHeightAdjuster implements BiomeHeightAdjuster {
		@Override
		public void processTile(ChunkTile tile, ClimaticWorldSettings settings) {
			Biome biome = Biome.getBiome(tile.rlBiome, Biomes.DEFAULT);
			tile.height = Math.max((biome.getBaseHeight()) + 0.2f, 0f);
			tile.scale = biome.getHeightVariation();
			tile.terrainType = STEEP;
		}
	}
	
}
