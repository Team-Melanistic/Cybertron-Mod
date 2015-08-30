package com.melanistic.cybertron.common.world.dimension;

import net.minecraft.world.biome.BiomeGenBase;

public class CybertronBiome extends BiomeGenBase
{
	public CybertronBiome()
	{
		super(50);
		setBiomeName("Cybertron");
		spawnableMonsterList.clear();
		spawnableCreatureList.clear();
		spawnableCaveCreatureList.clear();
		spawnableWaterCreatureList.clear();
	}
}
