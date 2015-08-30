package com.melanistic.cybertron.common.world.dimension;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

import com.melanistic.cybertron.common.CyberCommonProxy;

public class WorldProviderCybertron extends WorldProvider
{
	/**
	 * Will check if the x, z position specified is alright to be set as the map
	 * spawn point
	 */
	@Override
	public boolean canCoordinateBeSpawn(int par1, int par2)
	{
		return false;
	}

	/**
	 * True if the player can respawn in this dimension (true = overworld, false
	 * = nether).
	 */
	@Override
	public boolean canRespawnHere()
	{
		return false;
	}

	/**
	 * Returns a new chunk provider which generates chunks for this world
	 */
	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderCybertron(worldObj, worldObj.getSeed());
	}

	@Override
	public String getDimensionName()
	{
		return "Cybertron";
	}

	/**
	 * Returns 'true' if in the "main surface world", but 'false' if in the
	 * Nether or End dimensions.
	 */
	@Override
	public boolean isSurfaceWorld()
	{
		return false;
	}

	/**
	 * creates a new world chunk manager for WorldProvider
	 */
	@Override
	public void registerWorldChunkManager()
	{
		worldChunkMgr = new WorldChunkManagerHell(CyberCommonProxy.cybertron_biome, 0.5F);
		isHellWorld = true;
		hasNoSky = true;
		dimensionId = -1;
	}
}
