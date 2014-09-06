package com.melanistic.cybertron.common.world.dimension;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

import com.melanistic.cybertron.common.CyberCommonProxy;

public class WorldProviderCybertron extends WorldProvider {
	/**
	 * creates a new world chunk manager for WorldProvider
	 */
	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerHell(CyberCommonProxy.cybertron_biome, 0.5F);
		this.isHellWorld = true;
		this.hasNoSky = false;
		this.dimensionId = -1;
	}

	/**
	 * Returns a new chunk provider which generates chunks for this world
	 */
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderCybertron(this.worldObj, this.worldObj.getSeed());
	}

	/**
	 * Returns 'true' if in the "main surface world", but 'false' if in the
	 * Nether or End dimensions.
	 */
	public boolean isSurfaceWorld() {
		return false;
	}

	/**
	 * Will check if the x, z position specified is alright to be set as the map
	 * spawn point
	 */
	public boolean canCoordinateBeSpawn(int par1, int par2) {
		return false;
	}

	/**
	 * True if the player can respawn in this dimension (true = overworld, false
	 * = nether).
	 */
	public boolean canRespawnHere() {
		return false;
	}

	public String getDimensionName() {
		return "Cybertron";
	}
}