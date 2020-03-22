package mod.cirque.init;

import mod.akrivus.kagic.items.ItemGem;
import mod.akrivus.kagic.init.ModItems;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

public class CirqueItems {
	public static final ItemGem COVELLITE_GEM = new ItemGem("covellite");
	public static final ItemGem CRACKED_COVELLITE_GEM = new ItemGem("covellite", true);
	public static final ItemGem GADOLINITE_GEM = new ItemGem("gadolinite");
	public static final ItemGem CRACKED_GADOLINITE_GEM = new ItemGem("gadolinite", true);
	public static final ItemGem INDICOLITE_GEM = new ItemGem("indicolite");
	public static final ItemGem CRACKED_INDICOLITE_GEM = new ItemGem("indicolite", true);
	
	public static void register(RegistryEvent.Register<Item> event) {
		ModItems.registerExternalGem(COVELLITE_GEM, CRACKED_COVELLITE_GEM, "cirque", event);
		ModItems.registerExternalGem(GADOLINITE_GEM, CRACKED_GADOLINITE_GEM, "cirque", event);
		ModItems.registerExternalGem(INDICOLITE_GEM, CRACKED_INDICOLITE_GEM, "cirque", event);
	}
}