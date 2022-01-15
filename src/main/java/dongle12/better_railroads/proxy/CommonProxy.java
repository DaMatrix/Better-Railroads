package dongle12.better_railroads.proxy;

import dongle12.better_railroads.BetterRailroads;
import dongle12.better_railroads.blocks.JumpRailEntity;
import dongle12.better_railroads.blocks.LauncherBlock;
import dongle12.better_railroads.blocks.ModBlocks;
import dongle12.better_railroads.carts.ModCarts;
import dongle12.better_railroads.items.ItemChestCart;
import dongle12.better_railroads.items.ItemEmptyCart;
import dongle12.better_railroads.items.ItemHopperCart;
import dongle12.better_railroads.items.ItemTrainCart;
import dongle12.better_railroads.items.Wrench;
import dongle12.better_railroads.rails.ModRails;
import dongle12.better_railroads.rails.powered_rails.BrakeRail;
import dongle12.better_railroads.rails.powered_rails.DecelerationRail;
import dongle12.better_railroads.rails.powered_rails.JumpRail;
import dongle12.better_railroads.rails.powered_rails.LaunchingRail;
import dongle12.better_railroads.rails.powered_rails.PoweredSpeedRail;
import dongle12.better_railroads.rails.special_rails.DetectionRail;
import dongle12.better_railroads.rails.standard_rails.IntersectionRail;
import dongle12.better_railroads.rails.standard_rails.SpeedRail;
import dongle12.better_railroads.rails.standard_rails.WoodenRail;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {
	
	public static SpeedRail speedRail;
	
	//public void registerItemRenderer(Item item, int meta, String id){}
	
    public void preInit(FMLPreInitializationEvent event) {
    	ModCarts.init();
    }

    public void init(FMLInitializationEvent event) {
    }

    public void postInit(FMLPostInitializationEvent event) {
    }
    
    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> e){
    	e.getRegistry().register(new SpeedRail());
    	e.getRegistry().register(new PoweredSpeedRail());
    	e.getRegistry().register(new BrakeRail());
    	e.getRegistry().register(new DecelerationRail());
    	e.getRegistry().register(new WoodenRail());
    	e.getRegistry().register(new LaunchingRail());
    	e.getRegistry().register(new IntersectionRail());
    	e.getRegistry().register(new DetectionRail());
    	e.getRegistry().register(new JumpRail());
    	GameRegistry.registerTileEntity(JumpRailEntity.class, BetterRailroads.MODID + "_jumprailte");
    	e.getRegistry().register(new LauncherBlock());
    }
    
    @SubscribeEvent
    public static void OnRegisterItems(RegistryEvent.Register<Item> e){
    	e.getRegistry().register(new ItemBlock(ModRails.speed_rail).setRegistryName(ModRails.speed_rail.getRegistryName()));
    	e.getRegistry().register(new ItemBlock(ModRails.powered_speed_rail).setRegistryName(ModRails.powered_speed_rail.getRegistryName()));
    	e.getRegistry().register(new ItemBlock(ModRails.holding_rail).setRegistryName(ModRails.holding_rail.getRegistryName()));
    	e.getRegistry().register(new ItemBlock(ModRails.deceleration_rail).setRegistryName(ModRails.deceleration_rail.getRegistryName()));
    	e.getRegistry().register(new ItemBlock(ModRails.wooden_rail).setRegistryName(ModRails.wooden_rail.getRegistryName()));
    	e.getRegistry().register(new ItemBlock(ModBlocks.launcher_block).setRegistryName(ModBlocks.launcher_block.getRegistryName()));
    	e.getRegistry().register(new ItemBlock(ModRails.launching_rail).setRegistryName(ModRails.launching_rail.getRegistryName()));
    	e.getRegistry().register(new ItemBlock(ModRails.intersection_rail).setRegistryName(ModRails.intersection_rail.getRegistryName()));
    	e.getRegistry().register(new ItemBlock(ModRails.detection_rail).setRegistryName(ModRails.detection_rail.getRegistryName()));
    	e.getRegistry().register(new ItemBlock(ModRails.jump_rail).setRegistryName(ModRails.jump_rail.getRegistryName()));
    	
    	e.getRegistry().register(new ItemEmptyCart());
    	e.getRegistry().register(new ItemTrainCart());
    	e.getRegistry().register(new ItemHopperCart());
    	e.getRegistry().register(new ItemChestCart());


    	e.getRegistry().register(new Wrench());
    }

}
