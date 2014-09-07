package com.melanistic.cybertron.common.utils;

import java.util.Random;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class CommonUtils
{
	
	public static Random rand = new Random();
	
	public static int getRandBetween(int i, int i1)
	{
		return rand.nextInt(i - i1) + i1;
	}

}
