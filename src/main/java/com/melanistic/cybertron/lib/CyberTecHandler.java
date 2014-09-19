package com.melanistic.cybertron.lib;

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
	
	private void loadTecFromFile(Reader in)
	{
		Gson gson = new Gson();
		JsonElement base = gson.fromJson(in, JsonElement.class);
		if(base.isJsonObject())
			readTecLevels(base.getAsJsonObject());
		else
			System.err.println("Wrong Json-Format");
	}
	
	private void readTecLevels(JsonObject obj)
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
}
