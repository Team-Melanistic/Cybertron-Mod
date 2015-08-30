package com.melanistic.cybertron.lib;

import net.minecraft.item.ItemStack;

/**
 * This Interface is used for e.g. metadata based TecLevels.
 * It must be implemented by the Item-Class
 * @author MCenderdragon
 */
public interface ICustomTecLevel 
{
	public byte getTecLevel(ItemStack it);
}
