package com.melanistic.cybertron.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.melanistic.cybertron.common.CyberCommonProxy;
import com.melanistic.cybertron.common.world.dimension.TeleporterCybertron;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCybertronPortal extends BlockBreakable {
	public BlockCybertronPortal()
	{
		super("stone", Material.portal, false);
		this.setTickRandomly(true);
		this.setHardness(-1.0F);
		this.setStepSound(soundTypeGlass);
		this.setLightLevel(0.75F);
		this.setBlockName("cybertronPortal");
	}
	
	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		super.updateTick(par1World, par2, par3, par4, par5Random);
		if (par1World.provider.isSurfaceWorld() && par5Random.nextInt(2000) < par1World.difficultySetting.getDifficultyId())
		{
			int l;
			for (l = par3; !par1World.doesBlockHaveSolidTopSurface(par1World, par2, l, par4) && l > 0; --l)
			{
				
			}
			if (l > 0 && !par1World.isBlockNormalCubeDefault(par2, l + 1, par4, true))
			{
				Entity entity = ItemMonsterPlacer.spawnCreature(par1World, 57, (double)par2 + 0.5D, (double)l + 1.1D, (double)par4 + 0.5D);
				if (entity != null)
				{
					entity.timeUntilPortal = entity.getPortalCooldown();
				}
			}
		}
	}
	
	/**
	 * Returns a bounding box from the pool of bounding boxes (CyberBlocks.cybertron_portal; means CyberBlocks.cybertron_portal; box can change after the pool has been
	 * cleared to be reused)
	 */
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		return null;
	}
	
	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		float f;
		float f1;
		if (par1IBlockAccess.getBlock(par2 - 1, par3, par4) != CyberBlocks.cybertron_portal && par1IBlockAccess.getBlock(par2 + 1, par3, par4) != this)
		{
			f = 0.125F;
			f1 = 0.5F;
			this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
		}
		else
		{
			f = 0.5F;
			f1 = 0.125F;
			this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
		}
	}
	
	/**
	 * Is CyberBlocks.cybertron_portal; block (a) opaque and (B) a full 1m cube? This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to CyberBlocks.cybertron_portal; block.
	 */
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	/**
	 * If CyberBlocks.cybertron_portal; block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	/**
	 * Checks to see if CyberBlocks.cybertron_portal; location is valid to create a portal and will return True if it does. Args: world, x, y, z
	 */
	public boolean tryToCreatePortal(World par1World, int par2, int par3, int par4)
	{
		byte b0 = 0;
		byte b1 = 0;
		if (par1World.getBlock(par2 - 1, par3, par4) == CyberBlocks.reinforced_concrete || par1World.getBlock(par2 + 1, par3, par4) == CyberBlocks.reinforced_concrete)
		{
			b0 = 1;
		}
		if (par1World.getBlock(par2, par3, par4 - 1) == CyberBlocks.reinforced_concrete || par1World.getBlock(par2, par3, par4 + 1) == CyberBlocks.reinforced_concrete)
		{
			b1 = 1;
		}
		if (b0 == b1)
		{
			return false;
		}
		else
		{
			if (par1World.getBlock(par2 - b0, par3, par4 - b1) == Blocks.air)
			{
				par2 -= b0;
				par4 -= b1;
			}
			int l;
			int i1;
			for (l = -1; l <= 2; ++l)
			{
				for (i1 = -1; i1 <= 3; ++i1)
				{
					boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;
					if (l != -1 && l != 2 || i1 != -1 && i1 != 3)
					{
						Block j1 = par1World.getBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l);
						if (flag)
						{
							if (j1 != CyberBlocks.reinforced_concrete)
							{
								return false;
							}
						}
						else if (j1 != Blocks.air && j1 != CyberBlocks.reinforced_concrete)
						{
							return false;
						}
					}
				}
			}
			for (l = 0; l < 2; ++l)
			{
				for (i1 = 0; i1 < 3; ++i1)
				{
					par1World.setBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l, this, 0, 2);
				}
			}
			return true;
		}
	}
	
	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
	 * their own) Args: x, y, z, neighbor block
	 */
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block neighbor)
	{
		byte b0 = 0;
		byte b1 = 1;
		if (par1World.getBlock(par2 - 1, par3, par4) == CyberBlocks.cybertron_portal || par1World.getBlock(par2 + 1, par3, par4) == this)
		{
			b0 = 1;
			b1 = 0;
		}
		int i1;
		for (i1 = par3; par1World.getBlock(par2, i1 - 1, par4) == this; --i1)
		{
			
		}
		if (par1World.getBlock(par2, i1 - 1, par4) != CyberBlocks.reinforced_concrete)
		{
			par1World.setBlockToAir(par2, par3, par4);
		}
		else
		{
			int j1;
			for (j1 = 1; j1 < 4 && par1World.getBlock(par2, i1 + j1, par4) == this; ++j1)
			{
				;
			}
			if (j1 == 3 && par1World.getBlock(par2, i1 + j1, par4) == CyberBlocks.reinforced_concrete)
			{
				boolean flag = par1World.getBlock(par2 - 1, par3, par4) == CyberBlocks.cybertron_portal || par1World.getBlock(par2 + 1, par3, par4) == this;
				boolean flag1 = par1World.getBlock(par2, par3, par4 - 1) == CyberBlocks.cybertron_portal || par1World.getBlock(par2, par3, par4 + 1) == this;
				if (flag && flag1)
				{
					par1World.setBlockToAir(par2, par3, par4);
				}
				else
				{
					if ((par1World.getBlock(par2 + b0, par3, par4 + b1) != CyberBlocks.reinforced_concrete || par1World.getBlock(par2 - b0, par3, par4 - b1) != this) && (par1World.getBlock(par2 - b0, par3, par4 - b1) != CyberBlocks.reinforced_concrete || par1World.getBlock(par2 + b0, par3, par4 + b1) != this))
					{
						par1World.setBlockToAir(par2, par3, par4);
					}
				}
			}
			else
			{
				par1World.setBlockToAir(par2, par3, par4);
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	/**
	 * Returns true if the given side of CyberBlocks.cybertron_portal; block type should be rendered, if the adjacent block is at the given
	 * coordinates. Args: blockAccess, x, y, z, side
	 */
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		if (par1IBlockAccess.getBlock(par2, par3, par4) == this)
		{
			return false;
		}
		else
		{
			boolean flag = par1IBlockAccess.getBlock(par2 - 1, par3, par4) == CyberBlocks.cybertron_portal && par1IBlockAccess.getBlock(par2 - 2, par3, par4) != this;
			boolean flag1 = par1IBlockAccess.getBlock(par2 + 1, par3, par4) == CyberBlocks.cybertron_portal && par1IBlockAccess.getBlock(par2 + 2, par3, par4) != this;
			boolean flag2 = par1IBlockAccess.getBlock(par2, par3, par4 - 1) == CyberBlocks.cybertron_portal && par1IBlockAccess.getBlock(par2, par3, par4 - 2) != this;
			boolean flag3 = par1IBlockAccess.getBlock(par2, par3, par4 + 1) == CyberBlocks.cybertron_portal && par1IBlockAccess.getBlock(par2, par3, par4 + 2) != this;
			boolean flag4 = flag || flag1;
			boolean flag5 = flag2 || flag3;
			return flag4 && par5 == 4 ? true : (flag4 && par5 == 5 ? true : (flag5 && par5 == 2 ? true : flag5 && par5 == 3));
		}
	}
	
	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random par1Random)
	{
		return 0;
	}
	
	/**
	 * Triggered whenever an entity collides with CyberBlocks.cybertron_portal; block (enters into the block). Args: world, x, y, z, entity
	 */
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		if ((par5Entity.ridingEntity == null) && (par5Entity.riddenByEntity == null) && ((par5Entity instanceof EntityPlayerMP)))
		{
			EntityPlayerMP thePlayer = (EntityPlayerMP)par5Entity;
			if (thePlayer.timeUntilPortal > 0)
			{
				thePlayer.timeUntilPortal = 10;
			}
			else if (thePlayer.dimension != CyberCommonProxy.CYBERTRON_DIMENSION_ID)
			{
				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, CyberCommonProxy.CYBERTRON_DIMENSION_ID, new TeleporterCybertron(thePlayer.mcServer.worldServerForDimension(CyberCommonProxy.CYBERTRON_DIMENSION_ID)));
			}
			else {
				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, new TeleporterCybertron(thePlayer.mcServer.worldServerForDimension(0)));
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	/**
	 * Returns which pass should CyberBlocks.cybertron_portal; block be rendered on. 0 for solids and 1 for alpha
	 */
	public int getRenderBlockPass()
	{
		return 1;
	}
	
	@SideOnly(Side.CLIENT)
	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (par5Random.nextInt(100) == 0)
		{
			par1World.playSound((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "portal.portal", 0.5F, par5Random.nextFloat() * 0.4F + 0.8F, false);
		}
		for (int l = 0; l < 4; ++l)
		{
			double d0 = (double)((float)par2 + par5Random.nextFloat());
			double d1 = (double)((float)par3 + par5Random.nextFloat());
			double d2 = (double)((float)par4 + par5Random.nextFloat());
			double d3 = 0.0D;
			double d4 = 0.0D;
			double d5 = 0.0D;
			int i1 = par5Random.nextInt(2) * 2 - 1;
			d3 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;
			d4 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;
			d5 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;
			if (par1World.getBlock(par2 - 1, par3, par4) != CyberBlocks.cybertron_portal && par1World.getBlock(par2 + 1, par3, par4) != this)
			{
				d0 = (double)par2 + 0.5D + 0.25D * (double)i1;
				d3 = (double)(par5Random.nextFloat() * 2.0F * (float)i1);
			}
			else
			{
				d2 = (double)par4 + 0.5D + 0.25D * (double)i1;
				d5 = (double)(par5Random.nextFloat() * 2.0F * (float)i1);
			}
			par1World.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
		}
	}
	
	@SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        return Item.getItemById(0);
    }
}