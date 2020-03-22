package mod.cirque.client.render;

import mod.akrivus.kagic.client.render.layers.*;
import mod.akrivus.kagic.client.render.RenderGemBase;
import mod.cirque.client.model.ModelIndicolite;
import mod.cirque.entity.gem.EntityIndicolite;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class RenderIndicolite extends RenderGemBase<EntityIndicolite> {
	public RenderIndicolite() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelIndicolite(), 0.5F);
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerHair(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerGemPlacement(this));
	}
	@Override
	protected ResourceLocation getEntityTexture(EntityIndicolite entity) {
		return new ResourceLocation("cirque:textures/entities/indicolite/indicolite.png");
	}
}