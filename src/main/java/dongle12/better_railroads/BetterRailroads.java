package dongle12.better_railroads;

import dongle12.better_railroads.events.CartSpawnEvent;
import dongle12.better_railroads.events.LinkEvent;
import dongle12.better_railroads.proxy.CommonProxy;
import dongle12.better_railroads.rails.ModRails;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = BetterRailroads.MODID, useMetadata = true)
public class BetterRailroads {
    public static final String MODID = "miscrails";

    @Mod.Instance
    public static BetterRailroads instance = new BetterRailroads();
    public static CreativeTabs miscRailsTab = new CreativeTabs(MODID){
    	@Override
    	public ItemStack createIcon(){
    		return new ItemStack(Item.getItemFromBlock(ModRails.speed_rail));
    	}
    };
    @SidedProxy(clientSide = "dongle12.better_railroads.proxy.ClientProxy", serverSide = "dongle12.better_railroads.proxy.ServerProxy", modId = MODID)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
    	MinecraftForge.EVENT_BUS.register(new CartSpawnEvent());
    	MinecraftForge.EVENT_BUS.register(new LinkEvent());
        proxy.postInit(event);
    }
}
