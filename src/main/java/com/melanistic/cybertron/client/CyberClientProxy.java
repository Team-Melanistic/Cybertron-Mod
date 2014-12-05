package com.melanistic.cybertron.client;

import com.melanistic.cybertron.client.render.entity.RenderCarrier;
import com.melanistic.cybertron.client.render.entity.RenderHumanBorg;
import com.melanistic.cybertron.common.CyberCommonProxy;
import com.melanistic.cybertron.common.entity.EntityCarrier;
import com.melanistic.cybertron.common.entity.EntityHumanborg;
import com.melanistic.cybertron.lib.CyberKeyBindings;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CyberClientProxy extends CyberCommonProxy 
{
	
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
	}
}
