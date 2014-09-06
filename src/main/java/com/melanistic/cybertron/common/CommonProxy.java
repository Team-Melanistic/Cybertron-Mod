package com.melanistic.cybertron.common;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;

import com.melanistic.cybertron.common.block.CyberBlocks;
import com.melanistic.cybertron.common.item.CyberItems;
import com.melanistic.cybertron.common.world.WorldGenCybertron;
import com.melanistic.cybertron.common.world.dimension.CybertronBiome;
import com.melanistic.cybertron.common.world.dimension.WorldProviderCybertron;
import com.melanistic.cybertron.lib.Craftings;
import com.melanistic.cybertron.lib.EntityIds;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy 
{
	
	public static BiomeGenBase cybertron_biome;
	public static final int CYBERTRON_DIMENSION_ID = 10;
	
	public void preInit()
	{
		EntityIds.init();
		
		CyberBlocks.registerBlocks();
		CyberItems.registerItems();
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
		Craftings.initRecipes();
	}

}
