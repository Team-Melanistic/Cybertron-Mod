package com.melanistic.cybertron.lib;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

/**
 * This class handles the Tec Level from the Items
 * @author MCenderdragon
 * 
 */
public class CyberTecHandler 
{
	static HashMap<Item, Byte> tecMap = new HashMap<Item,Byte>();
	
	public static void setTecLevel(Item item, byte level)
	{
		tecMap.put(item, level);
	}

	public static byte getTecLevelFormItem(Item item)
	{
		if(tecMap.containsKey(item))
		{
			return tecMap.get(item);
		}
		return 0;
	}
	
	public static byte getTecLevelFromPlayer(EntityPlayer player)
	{
		return 0;
	}
	
	public static boolean canPlayerUseItem(Item item, EntityPlayer player)
	{
		return getTecLevelFormItem(item) <= getTecLevelFromPlayer(player);
	}
}
