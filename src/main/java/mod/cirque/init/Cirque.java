package mod.cirque.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = Cirque.MODID, version = Cirque.VERSION, acceptedMinecraftVersions = Cirque.MCVERSION, dependencies="after:kagic")
public class Cirque {
    public static final String VERSION = "@version";
    public static final String MCVERSION = "1.12.2";
	public static final String MODID = "cirque";
	
    @Instance
    public static Cirque instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    }
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	CirqueEntities.register();
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	CirqueCruxes.register();
    }
    @Mod.EventBusSubscriber(modid = Cirque.MODID)
	public static class RegistrationHandler {
    	@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			CirqueItems.register(event);
		}
    	@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			//CirqueBlocks.register(event);
		}
		@SubscribeEvent
		public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
			CirqueSounds.register(event);
		}
	}
}