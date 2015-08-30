package com.melanistic.cybertron.client.render;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public abstract class BackRender 
{
	
	public abstract void render(EntityPlayer player, ItemStack stack, double partialTicks);

}
