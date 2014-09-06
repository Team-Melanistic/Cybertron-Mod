package com.melanistic.cybertron.common.world.dimension;

import com.melanistic.cybertron.Cybertron;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class CybertronBiome extends BiomeGenBase {
	public CybertronBiome() {
		super(50);
		this.setBiomeName("Cybertron");
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
	}
}
