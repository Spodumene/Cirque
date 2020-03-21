package mod.cirque.client.model;

import mod.akrivus.kagic.client.model.ModelGem;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelCovelite - Undefined
 * Created using Tabula 7.1.0
 */
public class ModelCovellite extends ModelGem {
    public ModelRenderer hips;
    public ModelRenderer chest;
    public ModelRenderer neck;
    public ModelRenderer hair;
    public ModelRenderer weave;
    public ModelRenderer bipedHead;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedRightLeg;

    public ModelCovellite() {
        super(0F, 0F, 100, 100, false, -2F);
        this.bipedBody = new ModelRenderer(this, 56, 0);
        this.bipedBody.setRotationPoint(-3.0F, 1.0F, -2.5F);
        this.bipedBody.addBox(0.0F, 0.0F, 0.0F, 6, 6, 6, 0.0F);
        this.weave = new ModelRenderer(this, 68, 14);
        this.weave.setRotationPoint(-3.5F, -13.1F, -2.0F);
        this.weave.addBox(0.0F, 0.0F, 0.0F, 7, 8, 6, 0.0F);
        this.hair = new ModelRenderer(this, 0, 23);
        this.hair.setRotationPoint(-9.0F, -11.5F, -2.1F);
        this.hair.addBox(0.0F, 0.0F, 0.0F, 18, 6, 6, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 12, 0);
        this.bipedLeftLeg.setRotationPoint(-4.0F, 13.0F, -1.0F);
        this.bipedLeftLeg.addBox(0.0F, 0.0F, 0.0F, 3, 11, 3, 0.0F);
        this.chest = new ModelRenderer(this, 18, 12);
        this.chest.setRotationPoint(-3.5F, -4.0F, -2.5F);
        this.chest.addBox(0.0F, 0.0F, 0.0F, 7, 5, 6, 0.0F);
        this.bipedHead = new ModelRenderer(this, 44, 12);
        this.bipedHead.setRotationPoint(-3.0F, -12.0F, -2.5F);
        this.bipedHead.addBox(0.0F, 0.0F, 0.0F, 6, 7, 6, 0.0F);
        this.bipedRightLeg = new ModelRenderer(this, 0, 0);
        this.bipedRightLeg.setRotationPoint(1.0F, 13.0F, -1.0F);
        this.bipedRightLeg.addBox(0.0F, 0.0F, 0.0F, 3, 11, 3, 0.0F);
        this.neck = new ModelRenderer(this, 21, 0);
        this.neck.setRotationPoint(-1.0F, -5.0F, -0.5F);
        this.neck.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2, 0.0F);
        this.hips = new ModelRenderer(this, 24, 0);
        this.hips.setRotationPoint(-5.0F, 7.0F, -2.5F);
        this.hips.addBox(0.0F, 0.0F, 0.0F, 10, 6, 6, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 88, 0);
        this.bipedLeftArm.setRotationPoint(-5.5F, -4.0F, -0.5F);
        this.bipedLeftArm.addBox(0.0F, 0.0F, 0.0F, 2, 12, 2, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 80, 0);
        this.bipedRightArm.setRotationPoint(3.5F, -4.0F, -0.5F);
        this.bipedRightArm.addBox(0.0F, 0.0F, 0.0F, 2, 12, 2, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.bipedBody.render(f5);
        this.weave.render(f5);
        this.hair.render(f5);
        this.bipedLeftLeg.render(f5);
        this.chest.render(f5);
        this.bipedHead.render(f5);
        this.bipedRightLeg.render(f5);
        this.neck.render(f5);
        this.hips.render(f5);
        this.bipedLeftArm.render(f5);
        this.bipedRightArm.render(f5);
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
