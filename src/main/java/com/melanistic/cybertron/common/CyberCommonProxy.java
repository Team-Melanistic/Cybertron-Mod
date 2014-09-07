package com.melanistic.cybertron.common;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;

import com.melanistic.cybertron.client.render.entity.RenderHumanBorg;
import com.melanistic.cybertron.common.block.CyberBlocks;
import com.melanistic.cybertron.common.entity.EntityHumanborg;
import com.melanistic.cybertron.common.entity.EntitySkeletron;
import com.melanistic.cybertron.common.item.CyberItems;
import com.melanistic.cybertron.common.world.WorldGenCybertron;
import com.melanistic.cybertron.common.world.dimension.CybertronBiome;
import com.melanistic.cybertron.common.world.dimension.WorldProviderCybertron;
import com.melanistic.cybertron.lib.CybertronRecipes;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CyberCommonProxy 
{
	public static BiomeGenBase cybertron_biome;
	public static final int CYBERTRON_DIMENSION_ID = DimensionManager.getNextFreeDimId();
	
	public void preInit()
	{
		CyberBlocks.registerBlocks();
		CyberItems.registerItems();
	}
	
	public void init()
	{
		DimensionManager.registerProviderType(CYBERTRON_DIMENSION_ID, WorldProviderCybertron.class, false);
		DimensionManager.registerDimension(CYBERTRON_DIMENSION_ID, CYBERTRON_DIMENSION_ID);
		
		cybertron_biome = new CybertronBiome();
		
		GameRegistry.registerWorldGenerator(new WorldGenCybertron(), 0);
		registerEntitys();
	}
	
	public void postInit()
	{
		CybertronRecipes.initRecipes();
	}

	private void registerEntitys() 
	{
		EntityRegistry.registerGlobalEntityID(EntitySkeletron.class, "cyberskelton", EntityRegistry.findGlobalUniqueEntityId(), 0xacacac, 0x0b0b0b);
		EntityRegistry.registerGlobalEntityID(EntityHumanborg.class, "zombieborg", EntityRegistry.findGlobalUniqueEntityId(), 0x00A0A0, 0x698C55);
	}

}
