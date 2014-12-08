package com.melanistic.cybertron.common.entity;

import java.util.List;

import com.melanistic.cybertron.Cybertron;
import com.melanistic.cybertron.lib.CyberReference;

import tv.twitch.chat.ChatMessage;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class EntityDeathLiving extends Entity implements IInventory
{
	public EntityLivingBase living = null;
	public int deathTime = 0;
	ItemStack[] items= new ItemStack[18];
	
	public EntityDeathLiving(EntityLivingBase base) 
	{
		this(base.worldObj);
		NBTTagCompound nbt = new NBTTagCompound();
		base.writeToNBT(nbt);
		nbt.setString("id", EntityList.getEntityString(base));
		this.dataWatcher.updateObject(5, nbt.toString());
		initLiving(base);	

	}
	
	public EntityDeathLiving(World w) 
	{
		super(w);
		this.preventEntitySpawning = true;
	}

	@Override
	public void onUpdate() 
	{
		super.onUpdate();
		if(getLiving()==null)
		{
			this.setDead();
		}
		else
		{
			//if(living.isDead)
				living.onUpdate();
			copyDataFrom(living, false);
		}
		if(this.ticksExisted>=20*60 || deathTime > 20)
		{
			this.setDead();
		}
		if(deathTime>0)
			deathTime++;
	}
	
	private void initLiving(EntityLivingBase base)
	{
		living = base;
		if(base!=null)
		{
			setSize(base.height, base.width);
			copyDataFrom(base, false);
		}
	}
	
	@Override
	protected void entityInit() 
	{
		this.dataWatcher.addObject(5, "");
	}


	private Entity getLiving()
	{
		if(living!=null)
			return living;
		String s = this.dataWatcher.getWatchableObjectString(5);
		NBTTagCompound nbt;
		try {
			nbt = (NBTTagCompound) JsonToNBT.func_150315_a(s);
			living = (EntityLivingBase) EntityList.createEntityFromNBT(nbt, worldObj);
			initLiving(living);
		} catch (NBTException e) 
		{
			e.printStackTrace();
		}
		
		return living;
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt)
	{
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt)
	{
		
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource ds, float val)
	{
		if(val>0 && ds.getEntity() instanceof EntityLivingBase && deathTime==0)
		{
			EntityLivingBase live = (EntityLivingBase) ds.getEntity();
			ItemStack is = live.getHeldItem();
			if(is!=null && is.getItem() instanceof ItemSpade)
			{
				deathTime=1;
			}
		}
		return super.attackEntityFrom(ds, val);
	}

	@Override
	public AxisAlignedBB getBoundingBox() 
	{
		return null;//this.boundingBox;
	}

	@Override
	public boolean canBeCollidedWith() 
	{
		return true;
	}
	
	@Override
	public boolean canBePushed() 
	{
		return true;
	}
	
	@Override
	public void applyEntityCollision(Entity p_70108_1_) 
	{}
	
	@Override
	public AxisAlignedBB getCollisionBox(Entity e)
	{
		return e.boundingBox;
	}
	
	
	
	@Override
	public boolean interactFirst(EntityPlayer pl)
	{
		pl.openGui(Cybertron.instance, 1, worldObj, getEntityId(), 0, 0);
		return true;
	}

	@Override
	public int getSizeInventory()
	{
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		return items[i];
	}

	@Override
	public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) 
	{
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i)
	{
		return items[i];
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack it)
	{
		items[i] = it;
	}

	@Override
	public String getInventoryName()
	{
		String s = EntityList.getEntityString(living);

        if (s == null)
        {
            s = "generic";
        }

        return StatCollector.translateToLocal("entity." + s + ".name");
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return true;
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}

	@Override
	public void markDirty() {}

	@Override
	public boolean isUseableByPlayer(EntityPlayer pl)
	{
		return pl.getDistanceSqToEntity(this)<36;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
	{
		return false;
	}
//
//	@Override
//	public int[] getAccessibleSlotsFromSide(int p_94128_1_)
//	{
//		return new int[]{};
//	}
//
//	@Override
//	public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_,int p_102007_3_)
//	{
//		return false;
//	}
//
//	@Override
//	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_)
//	{
//		return false;
//	}
	
}
