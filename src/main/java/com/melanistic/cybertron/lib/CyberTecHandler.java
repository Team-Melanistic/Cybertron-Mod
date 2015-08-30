package com.melanistic.cybertron.lib;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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
		System.out.println(String.format("Set TevLevel %s for %s",level,item));
	}

	/**
	 * Get the TecLevel from the Item
	 */
	public static byte getTecLevelFormItem(ItemStack it)
	{
		if(it!=null)
		{
			Item item = it.getItem();
			if(item instanceof ICustomTecLevel)
			{
				return ((ICustomTecLevel)item).getTecLevel(it);
			}
			if(tecMap.containsKey(item))
			{
				return tecMap.get(item);
			}
		}
		return 0;
	}
	
	/**
	 * Get the TecLevel from the Player
	 */
	public static byte getTecLevelFromPlayer(EntityPlayer player)
	{
		return 0;
	}
	
	/**
	 * @param item the Item.
	 * @param player the Player.
	 * @return if the Players TecLevel is High enough to use the Item.
	 */
	public static boolean canPlayerUseItem(ItemStack item, EntityPlayer player)
	{
		return getTecLevelFormItem(item) <= getTecLevelFromPlayer(player);
	}
	
	/**
	 * Json Format like: {"minecarft:bow":600}
	 * @param in uses a Simple {@link Reader} or an {@link InputStream} with {@link InputStreamReader}
	 */
	private static void loadTecFromFile(Reader in)
	{
		Gson gson = new Gson();
		JsonElement base = gson.fromJson(in, JsonElement.class);
		if(base.isJsonObject())
			readTecLevels(base.getAsJsonObject());
		else
			System.err.println("Wrong Json-Format");
	}
	
	private static void readTecLevels(JsonObject obj)
	{
		for(Entry<String, JsonElement> e : obj.entrySet())
		{
			Item item = (Item) Item.itemRegistry.getObject(e.getKey());
			if(item == null)
			{
				Block b = (Block) Block.blockRegistry.getObject(e.getKey());
				if(b!=null)
				{
					item = Item.getItemFromBlock(b);
				}
			}
			
			if(item != null)
			{
				JsonElement elm = e.getValue();
				if(elm.isJsonPrimitive())
				{
					setTecLevel(item, elm.getAsByte());
				}
			}
		}
	}
	
	public static void main(String[] args) 
	{
		loadTecFromFile(null);
	}
}
