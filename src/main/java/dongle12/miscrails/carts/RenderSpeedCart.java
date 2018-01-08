package dongle12.miscrails.carts;

import net.minecraft.client.model.ModelMinecart;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderSpeedCart<T extends EntityMinecart> extends Render<T>{

	private ResourceLocation cartTexture = new ResourceLocation("miscrails:textures/speed_cart.png");
    public static final Factory FACTORY = new Factory();

	public RenderSpeedCart(RenderManager renderManagerIn) {
		super(renderManagerIn);
	}
    
    public static class Factory implements IRenderFactory<SpeedCart> {

        @Override
        public Render<? super SpeedCart> createRenderFor(RenderManager manager) {
            return new RenderSpeedCart(manager);
        }

    }

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		// TODO Auto-generated method stub
		return cartTexture;
	}
	
}
