package com.melanistic.cybertron;

import com.melanistic.cybertron.common.CyberCommonProxy;
import com.melanistic.cybertron.lib.CybertronGuiHandler;
import com.melanistic.cybertron.lib.CybertronReference;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = CybertronReference.MODID, name = CybertronReference.MODNAME, version = CybertronReference.VERSION)
public class Cybertron 
{
	
	@SidedProxy(clientSide = "com.melanistic.cybertron.client.CyberClientProxy", serverSide = "com.melanistic.cybertron.common.CyberCommonProxy")
	public static CyberCommonProxy proxy;
	
	@Instance(CybertronReference.MODID)
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
		NetworkRegistry.INSTANCE.registerGuiHandler(CybertronReference.MODID, new CybertronGuiHandler());
		proxy.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
		proxy.postInit();
	}
}
