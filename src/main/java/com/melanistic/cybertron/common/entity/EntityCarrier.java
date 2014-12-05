package com.melanistic.cybertron.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityCarrier extends EntityMob
{
	public EntityCarrier(World w)
	{
		super(w);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		tasks.addTask(7, new EntityAIWander(this, 1.0D));

		tasks.addTask(8, new EntityAILookIdle(this));
		setSize(0.9F, 0.3F);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.9);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60D);
	}

	@Override
	protected Entity findPlayerToAttack()
	{
		return null;
	}

	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if(entityToAttack instanceof EntityPlayer)
			entityToAttack = null;
	}

}
