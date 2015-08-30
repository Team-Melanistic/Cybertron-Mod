package com.melanistic.cybertron;

import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;

import com.melanistic.cybertron.common.CyberCommonProxy;
import com.melanistic.cybertron.common.entity.EntityDeathLiving;
import com.melanistic.cybertron.lib.CyberGuiHandler;
import com.melanistic.cybertron.lib.CyberReference;
import com.melanistic.cybertron.lib.CyberTecHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

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
		MinecraftForge.EVENT_BUS.register(this);
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new CyberGuiHandler());
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init();
	}
	
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
		proxy.postInit();
	}
	
	@SubscribeEvent
	public void onPlayerUsingItem(PlayerUseItemEvent e)
	{
		if(!CyberTecHandler.canPlayerUseItem(e.item, e.entityPlayer))
		{
			if(e.isCancelable())
			{
				e.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent e)
	{
		EntityLivingBase live = e.entityLiving;
		if(!live.worldObj.isRemote)
		{
			EntityDeathLiving death = new EntityDeathLiving(live);
			live.worldObj.spawnEntityInWorld(death);
			live.worldObj.unloadEntities(Arrays.asList(live));
		}
	}
	
	@SubscribeEvent
	public void onLivingDeath(LivingDropsEvent e)
	{
		EntityLivingBase live = e.entityLiving;
		if(!live.worldObj.isRemote)
		{
			List<EntityDeathLiving> l = live.worldObj.getEntitiesWithinAABB(EntityDeathLiving.class, live.boundingBox);
			for(EntityDeathLiving death : l)
			{
				if(death.living==live)
				{
					for(int i=0;i<Math.min(death.getSizeInventory(), e.drops.size());i++)
					{
						EntityItem it =  e.drops.get(i);
						death.setInventorySlotContents(i, it.getEntityItem());
						it.setDead();
					}
					e.setCanceled(true);
					return;
				}
			}
		}
	}
}
