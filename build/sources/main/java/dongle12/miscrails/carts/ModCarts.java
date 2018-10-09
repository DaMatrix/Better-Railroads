package dongle12.miscrails.carts;

import dongle12.miscrails.MiscRails;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModCarts {
	
	public static void init(){
    	EntityRegistry.registerModEntity(new ResourceLocation(MiscRails.MODID, "speed_cart"), SpeedCart.class, "speed_cart", 0, MiscRails.instance, 64, 1, false);
	}
	
    @SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(SpeedCart.class, RenderSpeedCart.FACTORY);
    }
}
