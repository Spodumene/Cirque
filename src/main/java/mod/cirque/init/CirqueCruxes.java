package mod.cirque.init;

import mod.akrivus.kagic.init.ModBlocks;
import mod.akrivus.kagic.init.ModEntities;
import mod.cirque.entity.gem.*;
import net.minecraft.init.Blocks;

public class CirqueCruxes {
    public static void register() {

        {
            {

                EntityCovellite.COVELLITE_YIELDS.put(ModBlocks.PINK_SANDSTONE.getDefaultState(), 3.99);
                EntityCovellite.COVELLITE_YIELDS.put(Blocks.DIAMOND_BLOCK.getDefaultState(), 10.99);
                ModEntities.registerWithOreDictionary(EntityCovellite.COVELLITE_YIELDS, "Covellite");
                
                EntityGadolinite.GADOLINITE_YIELDS.put(Blocks.GRAVEL.getDefaultState(), 10.99);
                ModEntities.registerWithOreDictionary(EntityGadolinite.GADOLINITE_YIELDS, "Gadolinite");
                
                EntityIndicolite.INDICOLITE_YIELDS.put(Blocks.STONE.getDefaultState(), 10.99);
                ModEntities.registerWithOreDictionary(EntityIndicolite.INDICOLITE_YIELDS, "Indicolite");


            }
        }
    }
}