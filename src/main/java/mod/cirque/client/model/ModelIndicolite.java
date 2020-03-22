package mod.cirque.client.model;

import mod.akrivus.kagic.client.model.ModelGem;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelIndicolite - Undefined
 * Created using Tabula 7.1.0
 */
public class ModelIndicolite extends ModelGem {
    public ModelRenderer shape1;
    public ModelRenderer shape1_1;
    public ModelRenderer shape3;
    public ModelRenderer shape4;
    public ModelRenderer shape4_1;
    public ModelRenderer shape6;

    public ModelIndicolite() {
        super(0F, 0F, 64, 64, false, 0F);
        this.shape4 = new ModelRenderer(this, 0, 12);
        this.shape4.setRotationPoint(-8.0F, 8.0F, -2.0F);
        this.shape4.addBox(0.0F, 0.0F, 0.0F, 4, 8, 4, 0.0F);
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(-4.0F, 16.0F, -2.0F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 4, 8, 4, 0.0F);
        this.shape4_1 = new ModelRenderer(this, 16, 12);
        this.shape4_1.setRotationPoint(4.0F, 8.0F, -2.0F);
        this.shape4_1.addBox(0.0F, 0.0F, 0.0F, 4, 8, 4, 0.0F);
        this.shape3 = new ModelRenderer(this, 32, 0);
        this.shape3.setRotationPoint(-4.0F, 8.0F, -2.0F);
        this.shape3.addBox(0.0F, 0.0F, 0.0F, 8, 8, 4, 0.0F);
        this.shape1_1 = new ModelRenderer(this, 16, 0);
        this.shape1_1.setRotationPoint(0.0F, 16.0F, -2.0F);
        this.shape1_1.addBox(0.0F, 0.0F, 0.0F, 4, 8, 4, 0.0F);
        this.shape6 = new ModelRenderer(this, 0, 24);
        this.shape6.setRotationPoint(-8.0F, -3.0F, -4.0F);
        this.shape6.addBox(0.0F, 0.0F, 0.0F, 16, 11, 9, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.shape4.render(f5);
        this.shape1.render(f5);
        this.shape4_1.render(f5);
        this.shape3.render(f5);
        this.shape1_1.render(f5);
        this.shape6.render(f5);
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
