package com.melanistic.cybertron.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class CyberClientUtils 
{
	
	private static Minecraft mc = Minecraft.getMinecraft();

	public static Minecraft getMC()
	{
		return mc;
	}

	public static EntityPlayer getClientPlayer()
	{
		return mc.thePlayer;
	}

	public static World getClientWorld()
	{
		return mc.theWorld;
	}

	public static FontRenderer getFontRenderer()
	{
		return mc.fontRenderer;
	}

	public static EntityRenderer getRenderer()
	{
		return mc.entityRenderer;
	}

	public static Entity getEntityByID(int id)
	{
		return id == getClientPlayer().getEntityId() ? getClientPlayer() : getClientWorld().getEntityByID(id);
	}

}
