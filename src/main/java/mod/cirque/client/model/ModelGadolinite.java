package mod.cirque.client.model;

import mod.akrivus.kagic.client.model.ModelGem;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelGadolinite - Undefined
 * Created using Tabula 7.1.0
 */
public class ModelGadolinite extends ModelGem {
    public ModelRenderer dress;
    public ModelRenderer bipedBody;
    public ModelRenderer hair;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedHead;

    public ModelGadolinite() {
        super(0F, 0F, 64, 64, false, 0F);
        this.bipedRightArm = new ModelRenderer(this, 0, 21);
        this.bipedRightArm.setRotationPoint(3.0F, 3.1F, -1.0F);
        this.bipedRightArm.addBox(0.0F, 0.0F, 0.0F, 2, 11, 2, 0.0F);
        this.dress = new ModelRenderer(this, 0, 0);
        this.dress.setRotationPoint(-4.0F, 11.0F, -4.0F);
        this.dress.addBox(0.0F, 0.0F, 0.0F, 8, 13, 8, 0.0F);
        this.bipedHead = new ModelRenderer(this, 9, 30);
        this.bipedHead.setRotationPoint(-3.5F, -4.0F, -3.5F);
        this.bipedHead.addBox(0.0F, 0.0F, 0.0F, 7, 7, 7, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 8, 21);
        this.bipedLeftArm.setRotationPoint(-5.0F, 3.1F, -1.0F);
        this.bipedLeftArm.addBox(0.0F, 0.0F, 0.0F, 2, 11, 2, 0.0F);
        this.hair = new ModelRenderer(this, 24, 14);
        this.hair.setRotationPoint(-4.0F, -5.0F, -4.0F);
        this.hair.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8, 0.0F);
        this.bipedBody = new ModelRenderer(this, 32, 0);
        this.bipedBody.setRotationPoint(-3.0F, 3.0F, -3.0F);
        this.bipedBody.addBox(0.0F, 0.0F, 0.0F, 6, 8, 6, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.bipedRightArm.render(f5);
        this.dress.render(f5);
        this.bipedHead.render(f5);
        this.bipedLeftArm.render(f5);
        this.hair.render(f5);
        this.bipedBody.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
