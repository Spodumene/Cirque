package mod.cirque.client.render;

import mod.akrivus.kagic.client.render.layers.*;
import mod.akrivus.kagic.client.render.RenderGemBase;
import mod.cirque.client.model.ModelGadolinite;
import mod.cirque.entity.gem.EntityGadolinite;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class RenderGadolinite extends RenderGemBase<EntityGadolinite> {
	public RenderGadolinite() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelGadolinite(), 0.5F);
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerVisor(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerGemPlacement(this));
	}
	@Override
	protected ResourceLocation getEntityTexture(EntityGadolinite entity) {
		return new ResourceLocation("cirque:textures/entities/gadolinite/gadolinite.png");
	}
}