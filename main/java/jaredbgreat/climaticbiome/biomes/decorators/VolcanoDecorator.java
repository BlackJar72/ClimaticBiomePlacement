package jaredbgreat.climaticbiome.biomes.decorators;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DeferredBiomeDecorator;

public class VolcanoDecorator extends BiomeDecorator {
    public WorldGenerator extraIron;
    public WorldGenerator extraGold;


	public VolcanoDecorator() {}

}
