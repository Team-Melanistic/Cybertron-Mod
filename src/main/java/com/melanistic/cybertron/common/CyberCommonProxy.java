package com.melanistic.cybertron.common;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;

import com.melanistic.cybertron.common.block.CyberBlocks;
import com.melanistic.cybertron.common.entity.CyberEntities;
import com.melanistic.cybertron.common.item.CyberItems;
import com.melanistic.cybertron.common.world.WorldGenCybertron;
import com.melanistic.cybertron.common.world.dimension.CybertronBiome;
import com.melanistic.cybertron.common.world.dimension.WorldProviderCybertron;
import com.melanistic.cybertron.lib.CyberRecipes;

import cpw.mods.fml.common.registry.GameRegistry;

public class CyberCommonProxy
{
	public static BiomeGenBase cybertron_biome;
	public static final int CYBERTRON_DIMENSION_ID = DimensionManager.getNextFreeDimId();

	public void init()
	{
		setupDimension();

		registerWorldGenerators();
		CyberEntities.registerEntities();
	}

	public void postInit()
	{
		CyberRecipes.initRecipes();
	}

	public void preInit()
	{
		CyberBlocks.registerBlocks();
		CyberItems.registerItems();
	}

	private void registerWorldGenerators()
	{
		GameRegistry.registerWorldGenerator(new WorldGenCybertron(), 0);
	}

	private void setupDimension()
	{
		DimensionManager.registerProviderType(CYBERTRON_DIMENSION_ID, WorldProviderCybertron.class, false);
		DimensionManager.registerDimension(CYBERTRON_DIMENSION_ID, CYBERTRON_DIMENSION_ID);

		cybertron_biome = new CybertronBiome();
	}

}
