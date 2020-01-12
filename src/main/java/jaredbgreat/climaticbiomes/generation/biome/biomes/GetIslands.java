package jaredbgreat.climaticbiomes.generation.biome.biomes;

//import jaredbgreat.climaticbiomes.compat.BoP;
//import jaredbgreat.climaticbiomes.compat.userdef.DefReader;
import jaredbgreat.climaticbiomes.configuration.ConfigHandler;
import jaredbgreat.climaticbiomes.generation.biome.BiomeClimateTable;
import jaredbgreat.climaticbiomes.generation.biome.BiomeList;
import jaredbgreat.climaticbiomes.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiomes.generation.biome.NoiseSpecialBiome;
import jaredbgreat.climaticbiomes.generation.generator.ChunkTile;

public class GetIslands implements IBiomeSpecifier {
    private static GetIslands islands;
    private static boolean volcanicIslands = true;
    private GetIslands() {
        super();
        init();
    }
    BiomeList frozen;
    BiomeList cold;
    BiomeList cool;
    BiomeList warm;
    BiomeList hot;
    BiomeList desert;
    IBiomeSpecifier basic; // Main land biomes (fallback)
    private boolean hasfr   = true, hascold = true, hascool   = true,
            haswarm = true, hashot  = true, hasdesert = true;


    public void init() {
        frozen = new BiomeList();
        cold   = new BiomeList();
        cool   = new BiomeList();
        warm   = new BiomeList();
        hot    = new BiomeList();
        desert = new BiomeList();
        basic  = BiomeClimateTable.getLandTable();
//        DefReader.readBiomeData(frozen, "SpecialIslandFrozen.cfg"); // FIXME
//        DefReader.readBiomeData(cold,   "SpecialIslandCold.cfg");
//        DefReader.readBiomeData(cool,   "SpecialIslandCool.cfg");
//        DefReader.readBiomeData(warm,   "SpecialIslandWarm.cfg");
//        DefReader.readBiomeData(hot,    "SpecialIslandTropical.cfg");
//        DefReader.readBiomeData(desert, "SpecialIslandDesert.cfg");
//        if(volcanicIslands) addVolcanicIslands((BiomeList)frozen, (BiomeList)cold,
//                (BiomeList)cool, (BiomeList)warm,
//                (BiomeList)hot, (BiomeList)desert);
    }


    @Override
    public long getBiome(ChunkTile tile) {
        tile.nextBiomeSeed();
        int seed = tile.getBiomeSeed();
        int temp = tile.getTemp();
        tile.nextBiomeSeed();
        if(temp < 4) {
            if(seed % 4 < frozen.size()) {
                return frozen.getBiome(tile.nextBiomeSeed());
            } else return basic.getBiome(tile.nextBiomeSeed());
        }
        if(temp < 7) {
            if(seed % 4 < cold.size()) {
                return cold.getBiome(tile.nextBiomeSeed());
            } else return basic.getBiome(tile.nextBiomeSeed());
        }
        if(temp < 13) {
            if(seed % 4 < cool.size()) {
                return cool.getBiome(tile.nextBiomeSeed());
            } else return basic.getBiome(tile.nextBiomeSeed());
        }
        if(temp <19) {
            if(tile.getWet() < 4) {
                if(seed % 4 < desert.size()) {
                    return desert.getBiome(tile.nextBiomeSeed());
                } else return basic.getBiome(tile.nextBiomeSeed());
            }
            if(seed % 4 < warm.size()) {
                return warm.getBiome(tile.nextBiomeSeed());
            } else return basic.getBiome(tile.nextBiomeSeed());

        }
        if(tile.getWet() < 2) {
            if(seed % 4 < desert.size()) {
                return desert.getBiome(tile.nextBiomeSeed());
            } else return basic.getBiome(tile.nextBiomeSeed());
        }
        if(seed % 4 < hot.size()) {
            return hot.getBiome(tile.nextBiomeSeed());
        } else return basic.getBiome(tile.nextBiomeSeed());

    }


    public static GetIslands getIslands() {
        if(islands == null) {
            islands = new GetIslands();
        }
        return islands;
    }


    @Override
    public boolean isEmpty() {
        return false;
    }


//    public static void addVolcanicIslands(BiomeList frozen, BiomeList cold, BiomeList cool,
//                                          BiomeList warm, BiomeList hot, BiomeList desert) {;
//        frozen.addItem(new NoiseSpecialBiome(BiomeClimateTable.getLandTable(), 6,
//                GetVolcano.getVolcanoes()));
//        cold.addItem(new NoiseSpecialBiome(BiomeClimateTable.getLandTable(), 6,
//                GetVolcano.getVolcanoes()));
//        cool.addItem(new NoiseSpecialBiome(BiomeClimateTable.getLandTable(), 6,
//                GetVolcano.getVolcanoes()));
//        hot.addItem(new NoiseSpecialBiome(BiomeClimateTable.getLandTable(), 6,
//                GetVolcano.getVolcanoes()));
//        desert.addItem(new NoiseSpecialBiome(BiomeClimateTable.getLandTable(), 6,
//                GetVolcano.getVolcanoes()));
//        warm.addItem(new NoiseSpecialBiome(BiomeClimateTable.getLandTable(), 6,
//                GetVolcano.getVolcanoes()));
//    }


    public static void setVolcanicIslands(boolean in) {
        volcanicIslands = in;
    }


    public void listOut() {

    }

}
