package com.melanistic.cybertron;

import com.melanistic.cybertron.client.render.entity.RenderHumanBorg;
import com.melanistic.cybertron.common.CyberCommonProxy;
import com.melanistic.cybertron.common.entity.EntityHumanborg;
import com.melanistic.cybertron.common.entity.EntitySkeletron;
import com.melanistic.cybertron.lib.CyberGuiHandler;
import com.melanistic.cybertron.lib.CyberReference;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = CyberReference.MODID, name = CyberReference.MODNAME, version = CyberReference.VERSION)
public class Cybertron 
{
	
	@SidedProxy(clientSide = "com.melanistic.cybertron.client.CyberClientProxy", serverSide = "com.melanistic.cybertron.common.CyberCommonProxy")
	public static CyberCommonProxy proxy;
	
	@Instance(CyberReference.MODID)
	public static Cybertron instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		instance = this;
		proxy.preInit();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(CyberReference.MODID, new CyberGuiHandler());
		proxy.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
		proxy.postInit();
	}
}
