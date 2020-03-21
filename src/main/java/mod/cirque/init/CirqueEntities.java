package mod.cirque.init;

import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.init.ModEntities;
import mod.cirque.entity.gem.EntityCovellite;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class CirqueEntities {
	private static int currentID = 0;
	public static void register() {
		ModEntities.registerExternalGem("cirque", "covellite", EntityCovellite.class, "mod/cirque/client/render/RenderCovellite", 0xECF404, 0xEBFD64, false);
	}
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static <T extends Entity> void registerMob(String name, Class<T> entity, int back, int fore) {
		EntityRegistry.registerModEntity(new ResourceLocation("cirque:" + name), entity, name, currentID, Cirque.instance, 256, 1, true, back, fore);
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			try {
				Class<Render<? extends T>> render = (Class<Render<? extends T>>) Cirque.class.getClassLoader().loadClass("mod/cirque/client/render/" + entity.getName().replaceAll(".+?Entity", "Render"));
				RenderingRegistry.registerEntityRenderingHandler(entity, render.newInstance());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		++currentID;
	}
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static <T extends Entity> void registerEntity(String name, Class<T> entity) {
		EntityRegistry.registerModEntity(new ResourceLocation("cirque:" + name), entity, name, currentID, Cirque.instance, 256, 1, true);
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			try {
				Class<Render<? extends T>> render = (Class<Render<? extends T>>) Cirque.class.getClassLoader().loadClass("mod/cirque/client/render/" + entity.getName().replaceAll(".+?Entity", "Render"));
				RenderingRegistry.registerEntityRenderingHandler(entity, render.newInstance());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		++currentID;
	}
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static <T extends Entity> void registerFusion(String name, Class<? extends EntityGem> entity) {
		EntityRegistry.registerModEntity(new ResourceLocation("cirque:" + name), entity, name, currentID, Cirque.instance, 256, 1, true);
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			try {
				Class<Render<? extends EntityGem>> render = (Class<Render<? extends EntityGem>>) Cirque.class.getClassLoader().loadClass("mod/cirque/client/render/" + entity.getName().replaceAll(".+?Entity", "Render"));
				RenderingRegistry.registerEntityRenderingHandler(entity, render.newInstance());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		ModEntities.GEMS.put(name, entity);
		++currentID;
	}
}