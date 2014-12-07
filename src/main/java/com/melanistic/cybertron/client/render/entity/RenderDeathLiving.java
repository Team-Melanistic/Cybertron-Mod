package com.melanistic.cybertron.client.render.entity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.melanistic.cybertron.Cybertron;
import com.melanistic.cybertron.client.CyberClientProxy;
import com.melanistic.cybertron.common.entity.EntityDeathLiving;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;

public class RenderDeathLiving extends RenderEntity
{
	
	@Override
	public void doRender(Entity e, double x, double y, double z, float f1, float f2)
	{
		if(e instanceof EntityDeathLiving)
		{
			EntityDeathLiving death = (EntityDeathLiving) e;
			if(death.living!=null)
			{
				RenderManager.instance.renderEntitySimple(death.living, death.getBrightness(f1));
			}
		}
	}
	
}
