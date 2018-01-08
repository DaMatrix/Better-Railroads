package dongle12.miscrails.rails.powered_rails;

import java.lang.reflect.Field;

import dongle12.miscrails.MiscRails;
import dongle12.miscrails.util.ConfigHandler;
import dongle12.miscrails.util.RailUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockRailPowered;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class JumpRail extends PoweredRail {
	
	public int jumpStrength = 1;
	
	public JumpRail(){
		super("jump_rail");
	}
	
	@Override
	public float getRailMaxSpeed(World world, EntityMinecart cart, BlockPos pos){
		//The default speed of a rail that 
		float retSpeed = 0.4f;
		//Look for Slopes upward
		for(EnumFacing side : EnumFacing.HORIZONTALS){
			//Look at the next block
			BlockPos nextPos = pos.offset(side);
			//Boolean to see if a rail has been found
			boolean foundRail = RailUtil.FoundRail(world, nextPos);
			//If no rail is found, check the next block up or down to find a rail
			if(!foundRail){
				if(RailUtil.FoundRail(world, nextPos.down())){
					foundRail = true;
					nextPos = nextPos.up();
				}
				else if(RailUtil.FoundRail(world,nextPos.down())){
					foundRail = true;
					nextPos = nextPos.down();
				}
			}
			if(foundRail){
				//Get the current direction of the rail found 
				BlockRailBase.EnumRailDirection dir = RailUtil.RailDirection(world, cart, pos);
				BlockRailBase.EnumRailDirection dirNext = RailUtil.RailDirection(world, cart, nextPos);

				//If the direction is not null, and the rail is ascending, start to slow
				if((dir != null && dir.isAscending()) || (dirNext != null && dirNext.isAscending())){
					retSpeed = .6f;
					System.out.println("Limiting Slope Speed");
				}
				else{
					retSpeed = super.getRailMaxSpeed(world, cart, pos) * ConfigHandler.POWERED_SPEED_RAIL_MULTIPLIER;
				}
			}
		}
		return retSpeed;
	}
	
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
        
    public ItemBlock getItemBlock() {
        return itemBlock;
    }
    
    public void cycleJumpStrength(boolean forward){

    	if(forward){
    		if(jumpStrength < 10.0D){
    			jumpStrength++;
    		}
    	}
    	else{
    		if(jumpStrength > 0){
    			jumpStrength--;
    		}
    	}
    }
    
    @Override
    public boolean canMakeSlopes(IBlockAccess world, BlockPos pos)
    {
        return false;
    }
    
    @Override
    public boolean isFlexibleRail(IBlockAccess world, BlockPos pos)
    {
        return false;
    }
	
    
        
    //////////////////////////////////////////////////////////
    //items below are needed to extend from BlockRailPowered//
    //////////////////////////////////////////////////////////
    @Override
    public void onMinecartPass(World world, EntityMinecart cart, BlockPos pos) {
    	  
    	if (!world.getBlockState(pos).getValue(BlockRailPowered.POWERED)){
            return;
    	}
    	else{
        	cart.motionY = (double)jumpStrength *.1;
        	cart.setDragAir(1.0D);
    	}
    }
	
}
