package com.melanistic.cybertron.client.render.entity;

import org.lwjgl.opengl.GL11;

import com.melanistic.cybertron.client.render.ModelCarrier;
import com.melanistic.cybertron.common.entity.EntityCarrier;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderCarrier extends RenderLiving
{
	public RenderCarrier() 
	{
		super(new ModelCarrier(), 0.9F);
		
	}
	//ModelCarrier c = 
	
	
	@Override
	public void doRender(EntityLiving e, double x, double y, double z, float f1, float f2) 
	{
		((ModelCarrier)mainModel).setSwingProgress(e.getSwingProgress(f2));
		super.doRender(e, x, y-1, z, f1, f2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity e) 
	{
		return new ResourceLocation("cybertron", "textures/entity/Carrier.png");
	}

}
