package dongle12.better_railroads.proxy;

import dongle12.better_railroads.blocks.ModBlocks;
import dongle12.better_railroads.carts.ModCarts;
import dongle12.better_railroads.items.ModItems;
import dongle12.better_railroads.rails.ModRails;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;


@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        System.out.println("Calling INIT MODELS");
        ModCarts.initModels();
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
       ModRails.initModels();
       ModItems.initModels();
       ModBlocks.initModels();
    }
}