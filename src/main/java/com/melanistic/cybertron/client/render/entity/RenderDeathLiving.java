package com.melanistic.cybertron.client.render.entity;

import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

import com.melanistic.cybertron.common.entity.EntityDeathLiving;

public class RenderDeathLiving extends RenderEntity
{
	
	@Override
	public void doRender(Entity e, double x, double y, double z, float f1, float f2)
	{
		GL11.glPushMatrix();
		if(e instanceof EntityDeathLiving)
		{
			EntityDeathLiving death = (EntityDeathLiving) e;
			GL11.glTranslated(0, -death.deathTime/16D, 0);
			if(death.living!=null)
			{
				RenderManager.instance.renderEntitySimple(death.living, death.getBrightness(f1));
			}
		}
		GL11.glPopMatrix();
	}
	
}
