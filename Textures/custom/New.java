//Made with Blockbench

package net.minecraft.src;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBlockbench extends ModelBase {

    //fields
    ModelRenderer e0;
    ModelRenderer e1;
    ModelRenderer e2;
    ModelRenderer e3;
    ModelRenderer e4;
    ModelRenderer e5;
    ModelRenderer e6;
    ModelRenderer e7;
    ModelRenderer e8;

    public ModelBlockbench() {
        this.textureWidth = 32;
        this.textureHeight = 32;

        this.e0 = new ModelRenderer(this, 0, 0);
        this.e0.addBox(0F, 0F, 1F, 1, 2, 2, 1.0F);
        this.e1 = new ModelRenderer(this, 0, 0);
        this.e1.addBox(0F, 0F, 13F, 1, 2, 2, 1.0F);
        this.e2 = new ModelRenderer(this, 0, 0);
        this.e2.addBox(15F, 0F, 13F, 1, 2, 2, 1.0F);
        this.e3 = new ModelRenderer(this, 0, 0);
        this.e3.addBox(15F, 0F, 1F, 1, 2, 2, 1.0F);
        this.e4 = new ModelRenderer(this, 0, 0);
        this.e4.addBox(1F, 1F, 15F, 14, 8, 1, 1.0F);
        this.e5 = new ModelRenderer(this, 0, 0);
        this.e5.addBox(1F, 1F, 0F, 14, 8, 1, 1.0F);
        this.e6 = new ModelRenderer(this, 0, 0);
        this.e6.addBox(2F, 1F, 1F, 12, 2, 14, 1.0F);
        this.e7 = new ModelRenderer(this, 0, 0);
        this.e7.addBox(1F, 1F, 1F, 1, 8, 14, 1.0F);
        this.e8 = new ModelRenderer(this, 0, 0);
        this.e8.addBox(14F, 1F, 1F, 1, 8, 14, 1.0F);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.e0.render(f5);
        this.e1.render(f5);
        this.e2.render(f5);
        this.e3.render(f5);
        this.e4.render(f5);
        this.e5.render(f5);
        this.e6.render(f5);
        this.e7.render(f5);
        this.e8.render(f5);

    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}