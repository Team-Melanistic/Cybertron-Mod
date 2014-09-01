package com.melanistic.cybertron.dimension;

import com.melanistic.cybertron.CybertronMod;

import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderCybertron extends WorldProvider {
	/**
	 * creates a new world chunk manager for WorldProvider
	 */
	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerHell(CybertronMod.cybertron_biome, 0.5F);
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