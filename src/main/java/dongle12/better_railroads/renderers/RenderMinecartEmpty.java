package dongle12.better_railroads.renderers;

import dongle12.better_railroads.BetterRailroads;
import dongle12.better_railroads.carts.MinecartSpeed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderMinecartEmpty<T extends EntityMinecart> extends RenderMinecart<T> {
	


	public RenderMinecartEmpty(RenderManager renderManager, ResourceLocation t) {
		super(renderManager);
        this.shadowSize = 0.5F;
        texture = t;
	}

	public static final FactoryEmpty FACTORY_EMPTY = new FactoryEmpty();
	
	public static class FactoryEmpty implements IRenderFactory<MinecartSpeed> {
			@Override
			public Render<? super MinecartSpeed> createRenderFor(RenderManager rm) {
		      return new RenderMinecartEmpty<MinecartSpeed>(rm, new ResourceLocation(BetterRailroads.MODID, "textures/speed_cart.png"));
	   }
	}
	
	private ResourceLocation texture;
    /** instance of ModelMinecart for rendering */
    protected ModelBase modelMinecart = new ModelMinecart();


    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(T entity) {
      return texture;
    }

    protected void renderCartContents(T p_188319_1_, float partialTicks, IBlockState p_188319_3_)
    {
        GlStateManager.pushMatrix();
        Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlockBrightness(p_188319_3_, p_188319_1_.getBrightness());
        GlStateManager.popMatrix();
    }
    
  
}
