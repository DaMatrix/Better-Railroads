package dongle12.miscrails.items;

import dongle12.miscrails.MiscRails;
import dongle12.miscrails.rails.powered_rails.JumpRail;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class Wrench extends Item {
	
	public Wrench(){
		super();
		setUnlocalizedName(MiscRails.MODID + "." + "wrench");
		setRegistryName(new ResourceLocation(MiscRails.MODID, "wrench"));
		setCreativeTab(MiscRails.miscRailsTab);
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
        			jump.cycleJumpStrength(false);
        			player.sendMessage(new TextComponentTranslation("Current Jump Height: " + jump.jumpStrength));
    	        	player.swingArm(hand);
    	            return EnumActionResult.SUCCESS;
        		}
        		else{
        			jump.cycleJumpStrength(true);
        			player.sendMessage(new TextComponentTranslation("Current Jump Height: " + jump.jumpStrength));
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
	
	
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    
}
