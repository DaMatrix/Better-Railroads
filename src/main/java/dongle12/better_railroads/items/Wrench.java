package dongle12.better_railroads.items;

import dongle12.better_railroads.BetterRailroads;
import dongle12.better_railroads.carts.MinecartEmpty;
import dongle12.better_railroads.rails.powered_rails.JumpRail;
import dongle12.better_railroads.util.RailUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityMinecartEmpty;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class Wrench extends Item {
	
	public boolean isLinking;
	public MinecartEmpty connectingCart;
	public Wrench(){
		super();
		setTranslationKey(BetterRailroads.MODID + "." + "wrench");
		setRegistryName(new ResourceLocation(BetterRailroads.MODID, "wrench"));
		setCreativeTab(BetterRailroads.miscRailsTab);
	}
	
	@Override 
    public EnumActionResult onItemUseFirst(EntityPlayer player, World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, EnumHand hand)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        

        if (!BlockRailBase.isRailBlock(iblockstate) || hand == EnumHand.OFF_HAND){
            return EnumActionResult.FAIL;
        }
        
        else{
        	
        	if(iblockstate.getBlock() instanceof JumpRail){
        		if(worldIn.isRemote){
        			return EnumActionResult.PASS;
        		}
        		JumpRail jump = (JumpRail) iblockstate.getBlock();
        		if(player.isSneaking()){
        			jump.cycleJumpStrength(false, worldIn, pos);
        			player.sendMessage(new TextComponentTranslation("Current Jump Height: " + jump.getJumpStrength()));
    	        	player.swingArm(hand);
    	            return EnumActionResult.SUCCESS;
        		}
        		else{
        			jump.cycleJumpStrength(true, worldIn, pos);
        			player.sendMessage(new TextComponentTranslation("Current Jump Height: " + jump.getJumpStrength()));
    	        	player.swingArm(hand);
    	            return EnumActionResult.SUCCESS;
        		}
        	}
        	else if(!player.isSneaking()){
	        	Block block = worldIn.getBlockState(pos).getBlock();
	        	block.rotateBlock(worldIn, pos, facing);
	        	player.swingArm(hand);
	            return EnumActionResult.SUCCESS;
        	}
        	else{
        		
        	}
        }
        return EnumActionResult.PASS;
    }
	
//	@SubscribeEvent
//	public void attachCart(EntityInteract event){
//		System.out.println("CLICKED ENTITY");
//		EntityPlayer player = event.getEntityPlayer();
//		if(player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == ModItems.wrench){
//			Wrench wrench = (Wrench) player.getHeldItemMainhand().getItem();
//			if(event.getTarget() instanceof SpeedCart){
//				SpeedCart target = (SpeedCart) event.getTarget();
//				if(!player.isSneaking()){
//					if(wrench.isLinking && wrench.connectingCart != target.connectedCart && !target.connected){
//						target.connectedCart = wrench.connectingCart;
//						target.connected = true;
//	        			player.sendMessage(new TextComponentTranslation("Carts Connected"));
//	        			event.setCanceled(true);
//					}
//					else if(!wrench.isLinking){
//						wrench.connectingCart = target;
//						event.setCanceled(true);
//					}
//				}
//				else{
//					target.connectedCart = null;
//					target.connected = false;
//					event.setCanceled(true);
//				}
//			}
//		}
//		event.setCanceled(true);
//	}
//	
	
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    
}
