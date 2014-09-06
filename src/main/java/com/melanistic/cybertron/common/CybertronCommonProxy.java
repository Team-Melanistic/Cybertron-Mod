package com.melanistic.cybertron.common;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;

import com.melanistic.cybertron.common.block.CybertronBlocks;
import com.melanistic.cybertron.common.item.CybertronItems;
import com.melanistic.cybertron.common.world.WorldGenCybertron;
import com.melanistic.cybertron.common.world.dimension.CybertronBiome;
import com.melanistic.cybertron.common.world.dimension.WorldProviderCybertron;
import com.melanistic.cybertron.lib.CybertronRecipes;

import cpw.mods.fml.common.registry.GameRegistry;

public class CybertronCommonProxy {
	public static BiomeGenBase cybertron_biome;
	public static final int CYBERTRON_DIMENSION_ID = 10;
	
	public void preInit()
	{
		CybertronBlocks.registerBlocks();
		CybertronItems.registerItems();
	}
	
	public void init()
	{
		DimensionManager.registerProviderType(CYBERTRON_DIMENSION_ID, WorldProviderCybertron.class, false);
		DimensionManager.registerDimension(CYBERTRON_DIMENSION_ID, CYBERTRON_DIMENSION_ID);
		
		cybertron_biome = new CybertronBiome();
		
		GameRegistry.registerWorldGenerator(new WorldGenCybertron(), 0);
	}
	
	public void postInit()
	{
		CybertronRecipes.initRecipes();
	}

}
