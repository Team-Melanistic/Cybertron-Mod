package com.melanistic.cybertron.common.item;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class CyberItems 
{
	public static Item transformium_shard;
	
	public static void registerItems()
	{
		transformium_shard = new ItemTransformiumShard();
		GameRegistry.registerItem(transformium_shard, "transformium_shard");
	}
}
