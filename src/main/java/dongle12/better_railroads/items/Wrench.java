package dongle12.better_railroads.items;

import dongle12.better_railroads.BetterRailroads;
import dongle12.better_railroads.blocks.JumpRailEntity;
import dongle12.better_railroads.carts.MinecartSpeed;
import dongle12.better_railroads.rails.powered_rails.JumpRail;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
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
				JumpRailEntity te = JumpRail.getTE(worldIn, pos);
        		if(player.isSneaking()){
        			te.decrement();
        			player.sendMessage(new TextComponentTranslation("Current Jump Height: " + te.getJumpHeight()));
    	        	player.swingArm(hand);
    	            return EnumActionResult.SUCCESS;
        		} else{
        			te.increment();
        			player.sendMessage(new TextComponentTranslation("Current Jump Height: " + te.getJumpHeight()));
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
        }
        return EnumActionResult.PASS;
    }
	
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    
}
