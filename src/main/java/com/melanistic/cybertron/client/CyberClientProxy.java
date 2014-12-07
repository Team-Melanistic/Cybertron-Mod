package com.melanistic.cybertron.client;

import java.util.HashMap;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;

import com.melanistic.cybertron.client.render.entity.RenderCarrier;
import com.melanistic.cybertron.client.render.entity.RenderDeathLiving;
import com.melanistic.cybertron.client.render.entity.RenderHumanBorg;
import com.melanistic.cybertron.common.CyberCommonProxy;
import com.melanistic.cybertron.common.entity.EntityCarrier;
import com.melanistic.cybertron.common.entity.EntityDeathLiving;
import com.melanistic.cybertron.common.entity.EntityHumanborg;
import com.melanistic.cybertron.lib.CyberKeyBindings;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CyberClientProxy extends CyberCommonProxy 
{
	HashMap<Class<? extends Entity>, Render> renderMap = new HashMap<Class<? extends Entity>, Render>();
	
	public void preInit()
	{
		super.preInit();
	}
	
	public void init()
	{
		super.init();
		CyberKeyBindings.registerKeys();
		
		RenderingRegistry.registerEntityRenderingHandler(EntityHumanborg.class, new RenderHumanBorg());
		RenderingRegistry.registerEntityRenderingHandler(EntityCarrier.class, new RenderCarrier());
		RenderingRegistry.registerEntityRenderingHandler(EntityDeathLiving.class, new RenderDeathLiving());
	}
	
	@Override
	public void postInit() 
	{
		super.postInit();
		
		RenderingRegistry.instance().loadEntityRenderers(renderMap);
		
	}
	
	public Render findRenderFor(Class c)
	{
		Render r = renderMap.get(c);
		if(r==null && c.getSuperclass()!=null)
		{
			return findRenderFor(c.getSuperclass());
		}
		return r;
	}
}
