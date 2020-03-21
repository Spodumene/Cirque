package mod.cirque.client.render;

import mod.akrivus.kagic.client.render.layers.*;
import mod.akrivus.kagic.client.render.RenderGemBase;
import mod.cirque.client.model.ModelCovellite;
import mod.cirque.entity.gem.EntityCovellite;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class RenderCovellite extends RenderGemBase<EntityCovellite> {
	public RenderCovellite() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelCovellite(), 0.5F);
        this.addLayer(new LayerSkin(this));
		this.addLayer(new LayerNoDyeOverlay(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerGemPlacement(this));
	}
	@Override
	protected ResourceLocation getEntityTexture(EntityCovellite entity) {
		return new ResourceLocation("cirque:textures/entities/covellite/covellite.png");
	}
}