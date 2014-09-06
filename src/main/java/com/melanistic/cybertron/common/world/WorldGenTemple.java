package com.melanistic.cybertron.common.world;

import java.util.Random;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;

import com.melanistic.cybertron.common.utils.StructureFile;
import com.melanistic.cybertron.lib.CybertronReference;

import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenTemple implements IWorldGenerator
{
	
	private static StructureFile temple = StructureFile.loadStructure(new ResourceLocation(CybertronReference.MODID, "/structures/OverworldTemple.txt"));

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{
		if(world.provider.dimensionId == 0)
		{
			chunkX = chunkX * 16 + random.nextInt(16);
			chunkZ = chunkZ * 16 + random.nextInt(16);
			if(world.getBiomeGenForCoords(chunkX, chunkZ) == BiomeGenBase.desert)
			{
				int yCoord = world.getHeightValue(chunkX, chunkZ);
				for(int x = chunkX - 12; x <= chunkX + 12; x++)
				{
					for(int z = chunkZ - 12; z <= chunkZ + 12; z++)
					{
						int yHeight = world.getHeightValue(x, z);
						
						if(!(world.getBiomeGenForCoords(x, z) == BiomeGenBase.desert && yCoord - yHeight >= -6 && yCoord - yHeight <= 6))
						{
							return;
						}
					}
				}
				temple.generateStructureSmoothly(random, chunkX, yCoord, chunkZ, world);
			}
		}
	}
	
}
