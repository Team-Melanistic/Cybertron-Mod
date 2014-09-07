package com.melanistic.cybertron.lib;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CyberGuiHandler implements IGuiHandler {
	private static Map<Integer, Class<? extends Container>> serverGui = new HashMap<Integer, Class<? extends Container>>();
	private static Map<Integer, Class<? extends GuiScreen>> clientGui = new HashMap<Integer, Class<? extends GuiScreen>>();

	private static void put(int id, Class<? extends Container> serverClass, Class<? extends GuiScreen> clientClass)
	{
		serverGui.put(id, serverClass);
		clientGui.put(id, clientClass);
	}

	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		Class<? extends Container> container = serverGui.get(id);
		if(container != null)
		{
			try 
			{
				return (Container) container.getConstructors()[0].newInstance(player, world, x, y, z);
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		Class<? extends GuiScreen> container = clientGui.get(id);
		if(container != null)
		{
			try 
			{
				return (GuiScreen) container.getConstructors()[0].newInstance(player, world, x, y, z);
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
}
