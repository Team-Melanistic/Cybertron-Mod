package com.melanistic.cybertron.common.entity;

import tv.twitch.chat.ChatMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class EntityDeathLiving extends Entity
{
	public EntityLivingBase living = null;
	
	public EntityDeathLiving(EntityLivingBase base) 
	{
		super(base.worldObj);
		setSize(base.height, base.width);
		living = base;
		setLivingsID(base.getEntityId());
		copyDataFrom(base, false);
		this.preventEntitySpawning = true;
	}
	
	public EntityDeathLiving(World w) 
	{
		super(w);
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
			if(living.isDead)
				living.onUpdate();
			copyDataFrom(living, false);
		}
		if(this.ticksExisted>=20*60)
		{
			this.setDead();
		}
		
	}
	
	@Override
	protected void entityInit() 
	{
		this.dataWatcher.addObject(5, (int)0);
	}

	private void setLivingsID(int id)
	{
		this.dataWatcher.updateObject(5, id);
	}
	
	private Entity getLiving()
	{
		if(living!=null)
			return living;
		int id = this.dataWatcher.getWatchableObjectInt(5);
		living = (EntityLivingBase) worldObj.getEntityByID(id);
		return living;
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt)
	{
		setLivingsID(nbt.getInteger("LivingsID"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt)
	{
		nbt.setInteger("LivingsID", this.dataWatcher.getWatchableObjectInt(5));	
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
	{
//		super.applyEntityCollision(p_70108_1_);
	}
	
	@Override
	public AxisAlignedBB getCollisionBox(Entity e)
	{
		return e.boundingBox;
	}
	
	@Override
	public boolean interactFirst(EntityPlayer pl)
	{
		pl.addChatMessage(new ChatComponentText(living.toString()));
		return true;
	}
	
	// ein abeun das wenn man mit schaufel drauf schlägt entity deth verschwidet
}
