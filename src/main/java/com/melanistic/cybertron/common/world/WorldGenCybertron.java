package com.melanistic.cybertron.common.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import com.melanistic.cybertron.common.block.CyberBlocks;

import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenCybertron implements IWorldGenerator
{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if (world.provider.dimensionId == 0)
		{
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
		}
	}

	private void generateSurface(World world, Random random, int i, int j)
	{
		for (int k = 0; k < 10; k++)
		{
			int cyberOreXCoord = i + random.nextInt(16);
			int cyberOreYCoord = random.nextInt(32);
			int cyberOreZCoord = j + random.nextInt(16);

			WorldGenMinable oreGen = new WorldGenMinable(CyberBlocks.ores, 7);
			oreGen.generate(world, random, cyberOreXCoord, cyberOreYCoord, cyberOreZCoord);
		}
	}
}
