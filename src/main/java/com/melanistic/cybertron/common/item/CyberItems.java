package com.melanistic.cybertron.common.item;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class CyberItems 
{
	
	public static Item cybertron_crystal;
	
	public static void registerItems()
	{
		cybertron_crystal = new ItemCybertronCrystal();
		GameRegistry.registerItem(cybertron_crystal, "cybertron_crystal");
	}

}
