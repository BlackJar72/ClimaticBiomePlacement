package jaredbgreat.climaticbiomes.features;

import com.mojang.datafixers.Dynamic;
import jaredbgreat.climaticbiomes.util.BlockRegistrar;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class PineTreeFeature extends AbstractTreeFeature<NoFeatureConfig> {
    private static final BlockState TRUNK = BlockRegistrar.pineLog.getDefaultState();
    private static final BlockState LEAF = BlockRegistrar.pineNeedles.getDefaultState();


    public PineTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn,
                           boolean doBlockNotifyOnPlace) {
        super(configFactoryIn, doBlockNotifyOnPlace);
    }


    @Override
    public boolean place(Set<BlockPos> changed, IWorldGenerationReader world,
                          Random rand, BlockPos pos, MutableBoundingBox mbb) {

        int h1 = 4 + rand.nextInt(3) + rand.nextInt(2); // trunk height
        int h2 = Math.min(h1 -1 , 2 + rand.nextInt(1 + (h1 / 2))); // first leaf height

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        boolean flag = true;
        if (y >= 1 && y + h1 + 1 <= world.getMaxHeight()) {
            for(int j = y + h2; j <= y + 2 + h1; ++j) {

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for(int l = pos.getX() - 2; l <= pos.getX() + 2 && flag; ++l) {
                    for(int i1 = pos.getZ() - 2; i1 <= pos.getZ() + 2 && flag; ++i1) {
                        if (!isAirOrLeaves(world, blockpos$mutableblockpos.setPos(l, j, i1))) {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else if ((isSoil(world, pos.down(), getSapling())) && pos.getY() < world.getMaxHeight() - h1 - 1) {
                this.setDirtAt(world, pos.down(), pos);

                int w = 1;
                int bonus = rand.nextInt(2);
                int j1b = pos.getY() + h1 + 1;
                for(int j4 = pos.getY() + h2; j4 < j1b; j4++) {
                    w = 1 + ((j4 + bonus) % 2);
                    for(int i2 = x - w; i2 <= (x + w); i2++)
                        for(int k2 = z - w; k2 <= (z + w); k2++) {
                            BlockPos place = new BlockPos(i2, j4, k2);
                            if(isAirOrLeaves(world, place)) {
                                setLogState(changed, world, place, LEAF, mbb);
                            }
                        }
                }

                w = 1 + ((j1b + bonus) % 2);
                for(int j4 = j1b; w > -1; j4++, w--) {
                    for(int i2 = x - w; i2 <= (x + w); i2++)
                        for(int k2 = z - w; k2 <= (z + w); k2++) {
                            BlockPos place = new BlockPos(i2, j4, k2);
                            if(isAirOrLeaves(world, place)) {
                                setLogState(changed, world, place, LEAF, mbb);
                            }
                        }
                }

                for(int i2 = 0; i2 < h1; ++i2) {
                    setLogState(changed, world, pos.up(i2), TRUNK, mbb);
                }

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    
}
