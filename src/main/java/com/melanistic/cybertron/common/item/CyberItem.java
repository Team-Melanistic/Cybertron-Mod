package com.melanistic.cybertron.common.item;

import com.melanistic.cybertron.lib.ModInfo;

import net.minecraft.item.Item;

public class CyberItem extends Item
{
	
	public CyberItem()
	{
		super();
	}
	
	public Item setTextureName(String name)
	{
		return super.setTextureName(ModInfo.MODID + ":" + name);
	}

}
