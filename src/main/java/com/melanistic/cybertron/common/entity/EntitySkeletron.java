package com.melanistic.cybertron.common.entity;

import java.util.Calendar;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;

public class EntitySkeletron extends EntitySkeleton 
{

	public EntitySkeletron(World p_i1741_1_) 
	{
		super(p_i1741_1_);
		for(int i=0;i<this.tasks.taskEntries.size();i++)
		{
			EntityAITaskEntry b = (EntityAITaskEntry) this.tasks.taskEntries.get(i);
			if(b==null)
			{
				this.tasks.taskEntries.remove(i--);
				continue;
			}
			if(b.action instanceof EntityAIArrowAttack)
			{
				b.action = new EntityAIArrowAttack(this, 1.0D, 20, 100, 15.0F);
			}
		}
	}
	
	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
    }
	
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_)
    {
        EntitySlownessArrow entityarrow = new EntitySlownessArrow(this.worldObj, this, p_82196_1_, 1.6F, (float)(14 - this.worldObj.difficultySetting.getDifficultyId() * 4));
        int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItem());
        int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItem());
        entityarrow.setDamage((double)(p_82196_2_ * 2.0F) + this.rand.nextGaussian() * 0.25D + (double)((float)this.worldObj.difficultySetting.getDifficultyId() * 0.11F));

        if (i > 0)
        {
            entityarrow.setDamage(entityarrow.getDamage() + (double)i * 0.5D + 0.5D);
        }

        if (j > 0)
        {
            entityarrow.setKnockbackStrength(j);
        }

        if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, this.getHeldItem()) > 0 || this.getSkeletonType() == 1)
        {
            entityarrow.setFire(100);
        }

        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entityarrow);
    }
//
//	@Override
//	public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_)
//    {
//        p_110161_1_ = super.onSpawnWithEgg(p_110161_1_);
//
//        if (this.worldObj.provider instanceof WorldProviderHell && this.getRNG().nextInt(5) > 0)
//        {
//            this.tasks.addTask(4, this.aiAttackOnCollide);
//            this.setSkeletonType(1);
//            this.setCurrentItemOrArmor(0, new ItemStack(Items.stone_sword));
//            this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
//        }
//        else
//        {
//            this.tasks.addTask(4, this.aiArrowAttack);
//            this.addRandomArmor();
//            this.enchantEquipment();
//        }
//
//        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * this.worldObj.func_147462_b(this.posX, this.posY, this.posZ));
//
//        if (this.getEquipmentInSlot(4) == null)
//        {
//            Calendar calendar = this.worldObj.getCurrentDate();
//
//            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F)
//            {
//                this.setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.lit_pumpkin : Blocks.pumpkin));
//                this.equipmentDropChances[4] = 0.0F;
//            }
//        }
//
//        return p_110161_1_;
//    }
//
//	@Override
//    public void setCombatTask()
//    {
//        this.tasks.removeTask(this.aiAttackOnCollide);
//        this.tasks.removeTask(this.aiArrowAttack);
//        ItemStack itemstack = this.getHeldItem();
//
//        if (itemstack != null && itemstack.getItem() == Items.bow)
//        {
//            this.tasks.addTask(4, this.aiArrowAttack);
//        }
//        else
//        {
//            this.tasks.addTask(4, this.aiAttackOnCollide);
//        }
//    }
}
