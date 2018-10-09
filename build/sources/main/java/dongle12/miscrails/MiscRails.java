package dongle12.miscrails;

import dongle12.miscrails.proxy.CommonProxy;
import dongle12.miscrails.rails.ModRails;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MiscRails.MODID, name = MiscRails.NAME, version = MiscRails.VERSION)
public class MiscRails {

    public static final String MODID = "miscrails";
    public static final String NAME = "Misc Rails";
    public static final String VERSION = "1.0.0";
    
    @Mod.Instance
    public static MiscRails instance = new MiscRails();
    public static CreativeTabs miscRailsTab = new CreativeTabs("MiscRails"){
    	@Override
    	public ItemStack getTabIconItem(){
    		return new ItemStack(Item.getItemFromBlock(ModRails.speed_rail));
    	}
    };
    @SidedProxy(clientSide = "dongle12.miscrails.proxy.ClientProxy", serverSide = "dongle12.miscrails.proxy.ServerProxy", modId = MODID)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        proxy.postInit(event);
    }
}
