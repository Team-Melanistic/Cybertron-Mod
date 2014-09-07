package com.melanistic.cybertron.client.render.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;

public class RenderHumanBorg extends RenderBiped 
{

	private static final ResourceLocation zombieTextures = new ResourceLocation("cybertron", "textures/entity/borg_human.png");
	
//	private ModelBiped field_82434_o;
//	private ModelZombieVillager zombieVillagerModel;
//	protected ModelBiped field_82437_k;
//	protected ModelBiped field_82435_l;
//	protected ModelBiped field_82436_m;
//	protected ModelBiped field_82433_n;
//	private int field_82431_q = 1;
//	private static final String __OBFID = "CL_00001037";

	public RenderHumanBorg() 
	{
		super(new ModelBiped(), 0.5F, 1.0F);
	}


	protected void rotateCorpse(EntityZombie p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) 
	{
		if (p_77043_1_.isConverting()) 
		{
			p_77043_3_ += (float) (Math.cos((double) p_77043_1_.ticksExisted * 3.25D) * Math.PI * 0.25D);
		}

		super.rotateCorpse(p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
	}

//	protected void renderEquippedItems(EntityLiving p_77029_1_, float p_77029_2_) 
//	{
//		this.renderEquippedItems((EntityZombie) p_77029_1_, p_77029_2_);
//	}
//
//	protected ResourceLocation getEntityTexture(EntityLiving p_110775_1_) 
//	{
//		return this.getEntityTexture((EntityZombie) p_110775_1_);
//	}
//
//	public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) 
//	{
//		this.doRender((EntityZombie) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
//	}
//
//	protected int shouldRenderPass(EntityLiving p_77032_1_, int p_77032_2_, float p_77032_3_) 
//	{
//		return this.shouldRenderPass((EntityZombie) p_77032_1_, p_77032_2_, p_77032_3_);
//	}
//
//	protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_) 
//	{
//		return this.shouldRenderPass((EntityZombie) p_77032_1_, p_77032_2_, p_77032_3_);
//	}
//
//	protected void renderEquippedItems(EntityLivingBase p_77029_1_, float p_77029_2_) 
//	{
//		this.renderEquippedItems((EntityZombie) p_77029_1_, p_77029_2_);
//	}
//
//	protected void rotateCorpse(EntityLivingBase p_77043_1_, float p_77043_2_,float p_77043_3_, float p_77043_4_) 
//	{
//		this.rotateCorpse((EntityZombie) p_77043_1_, p_77043_2_, p_77043_3_,	p_77043_4_);
//	}
//
//	public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_,double p_76986_4_, double p_76986_6_, float p_76986_8_,float p_76986_9_) 
//	{
//		this.doRender((EntityZombie) p_76986_1_, p_76986_2_, p_76986_4_,p_76986_6_, p_76986_8_, p_76986_9_);
//	}
//
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) 
	{
		return zombieTextures;
	}
//
//	public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) 
//	{
//		this.doRender((EntityZombie) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
//	}
}
