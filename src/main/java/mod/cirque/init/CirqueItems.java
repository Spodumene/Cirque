package mod.cirque.init;

import mod.akrivus.kagic.items.ItemGem;
import mod.akrivus.kagic.init.ModItems;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

public class CirqueItems {
	public static final ItemGem COVELLITE_GEM = new ItemGem("covellite");
	public static final ItemGem CRACKED_COVELLITE_GEM = new ItemGem("covellite", true);
	
	public static void register(RegistryEvent.Register<Item> event) {
		ModItems.registerExternalGem(COVELLITE_GEM, CRACKED_COVELLITE_GEM, "cirque", event);
	}
}