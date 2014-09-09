package com.melanistic.cybertron.common.world.dimension;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import com.melanistic.cybertron.common.block.CyberBlocks;

import cpw.mods.fml.common.eventhandler.Event.Result;

public class ChunkProviderCybertron implements IChunkProvider
{
	/**
	 * Holds the noise used to determine whether something other than reinforced
	 * concrete can be generated at a location
	 */
	private double[] concreteExclusivityNoise = new double[256];
	/**
	 * Determines whether something other than reinforced concrete can be
	 * generated at a location
	 */
	private NoiseGeneratorOctaves concreteExculsivityNoiseGen;
	/** A NoiseGeneratorOctaves used in generating cybertron terrain */
	private NoiseGeneratorOctaves cybertronNoiseGen1;
	private NoiseGeneratorOctaves cybertronNoiseGen2;
	private NoiseGeneratorOctaves cybertronNoiseGen3;
	public NoiseGeneratorOctaves cybertronNoiseGen6;
	public NoiseGeneratorOctaves cybertronNoiseGen7;
	private Random cybertronRNG;
	double[] noiseData1;
	double[] noiseData2;
	double[] noiseData3;
	double[] noiseData4;
	double[] noiseData5;
	private double[] noiseField;
	/** Is the world that cybertron is getting generated. */
	private World worldObj;

	public ChunkProviderCybertron(World par1World, long par2)
	{
		worldObj = par1World;
		cybertronRNG = new Random(par2);
		cybertronNoiseGen1 = new NoiseGeneratorOctaves(cybertronRNG, 16);
		cybertronNoiseGen2 = new NoiseGeneratorOctaves(cybertronRNG, 16);
		cybertronNoiseGen3 = new NoiseGeneratorOctaves(cybertronRNG, 8);
		concreteExculsivityNoiseGen = new NoiseGeneratorOctaves(cybertronRNG, 4);
		cybertronNoiseGen6 = new NoiseGeneratorOctaves(cybertronRNG, 10);
		cybertronNoiseGen7 = new NoiseGeneratorOctaves(cybertronRNG, 16);

		NoiseGenerator[] noiseGens = { cybertronNoiseGen1, cybertronNoiseGen2, cybertronNoiseGen3, concreteExculsivityNoiseGen, cybertronNoiseGen6, cybertronNoiseGen7 };
		noiseGens = TerrainGen.getModdedNoiseGenerators(par1World, cybertronRNG, noiseGens);
		cybertronNoiseGen1 = (NoiseGeneratorOctaves) noiseGens[0];
		cybertronNoiseGen2 = (NoiseGeneratorOctaves) noiseGens[1];
		cybertronNoiseGen3 = (NoiseGeneratorOctaves) noiseGens[2];
		concreteExculsivityNoiseGen = (NoiseGeneratorOctaves) noiseGens[3];
		cybertronNoiseGen6 = (NoiseGeneratorOctaves) noiseGens[4];
		cybertronNoiseGen7 = (NoiseGeneratorOctaves) noiseGens[5];
	}

	/**
	 * Returns if the IChunkProvider supports saving.
	 */
	@Override
	public boolean canSave()
	{
		return true;
	}

	/**
	 * Checks to see if a chunk exists at x, y
	 */
	@Override
	public boolean chunkExists(int par1, int par2)
	{
		return true;
	}

	@Override
	public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_)
	{
		return null;
	}

	public void func_147418_b(int p_147418_1_, int p_147418_2_, Block[] p_147418_3_)
	{
		ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, p_147418_1_, p_147418_2_, p_147418_3_, null);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.getResult() == Result.DENY)
			return;

		byte b0 = 64;
		double d0 = 0.03125D;
		concreteExclusivityNoise = concreteExculsivityNoiseGen.generateNoiseOctaves(concreteExclusivityNoise, p_147418_1_ * 16, p_147418_2_ * 16, 0, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);

		for (int k = 0; k < 16; ++k)
		{
			for (int l = 0; l < 16; ++l)
			{
				int i1 = (int) (concreteExclusivityNoise[k + l * 16] / 3.0D + 3.0D + cybertronRNG.nextDouble() * 0.25D);
				int j1 = -1;
				Block block = CyberBlocks.concrete;
				Block block1 = CyberBlocks.concrete;

				for (int k1 = 127; k1 >= 0; --k1)
				{
					int l1 = (l * 16 + k) * 128 + k1;

					if (k1 < 127 - cybertronRNG.nextInt(5) && k1 > 0 + cybertronRNG.nextInt(5))
					{
						Block block2 = p_147418_3_[l1];

						if (block2 != null && block2.getMaterial() != Material.air)
						{
							if (block2 == CyberBlocks.concrete)
							{
								if (j1 == -1)
								{
									if (i1 <= 0)
									{
										block = null;
										block1 = CyberBlocks.concrete;
									}
									else if (k1 >= b0 - 4 && k1 <= b0 + 1)
									{
										block = CyberBlocks.concrete;
										block1 = CyberBlocks.concrete;
									}

									if (k1 < b0 && (block == null || block.getMaterial() == Material.air))
									{
										block = CyberBlocks.energonBlock;
									}

									j1 = i1;

									if (k1 >= b0 - 1)
									{
										p_147418_3_[l1] = block;
									}
									else
									{
										p_147418_3_[l1] = block1;
									}
								}
								else if (j1 > 0)
								{
									--j1;
									p_147418_3_[l1] = block1;
								}
							}
						}
						else
						{
							j1 = -1;
						}
					}
					else
					{
						p_147418_3_[l1] = Blocks.bedrock;
					}
				}
			}
		}
	}

	public void func_147419_a(int p_147419_1_, int p_147419_2_, Block[] p_147419_3_)
	{
		byte b0 = 4;
		byte b1 = 32;
		int k = b0 + 1;
		byte b2 = 17;
		int l = b0 + 1;
		noiseField = initializeNoiseField(noiseField, p_147419_1_ * b0, 0, p_147419_2_ * b0, k, b2, l);

		for (int i1 = 0; i1 < b0; ++i1)
		{
			for (int j1 = 0; j1 < b0; ++j1)
			{
				for (int k1 = 0; k1 < 16; ++k1)
				{
					double d0 = 0.125D;
					double d1 = noiseField[((i1 + 0) * l + j1 + 0) * b2 + k1 + 0];
					double d2 = noiseField[((i1 + 0) * l + j1 + 1) * b2 + k1 + 0];
					double d3 = noiseField[((i1 + 1) * l + j1 + 0) * b2 + k1 + 0];
					double d4 = noiseField[((i1 + 1) * l + j1 + 1) * b2 + k1 + 0];
					double d5 = (noiseField[((i1 + 0) * l + j1 + 0) * b2 + k1 + 1] - d1) * d0;
					double d6 = (noiseField[((i1 + 0) * l + j1 + 1) * b2 + k1 + 1] - d2) * d0;
					double d7 = (noiseField[((i1 + 1) * l + j1 + 0) * b2 + k1 + 1] - d3) * d0;
					double d8 = (noiseField[((i1 + 1) * l + j1 + 1) * b2 + k1 + 1] - d4) * d0;

					for (int l1 = 0; l1 < 8; ++l1)
					{
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;

						for (int i2 = 0; i2 < 4; ++i2)
						{
							int j2 = i2 + i1 * 4 << 11 | 0 + j1 * 4 << 7 | k1 * 8 + l1;
							short short1 = 128;
							double d14 = 0.25D;
							double d15 = d10;
							double d16 = (d11 - d10) * d14;

							for (int k2 = 0; k2 < 4; ++k2)
							{
								Block block = null;

								if (k1 * 8 + l1 < b1)
								{
									block = CyberBlocks.energonBlock;
								}

								if (d15 > 0.0D)
								{
									block = CyberBlocks.concrete;
								}

								p_147419_3_[j2] = block;
								j2 += short1;
								d15 += d16;
							}

							d10 += d12;
							d11 += d13;
						}

						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}
	}

	@Override
	public int getLoadedChunkCount()
	{
		return 0;
	}

	/**
	 * Returns a list of creatures of the specified type that can spawn at the
	 * given location.
	 */
	@Override
	public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
	{
		BiomeGenBase biomegenbase = worldObj.getBiomeGenForCoords(par2, par4);
		return biomegenbase.getSpawnableList(par1EnumCreatureType);
	}

	/**
	 * generates a subset of the level's terrain data. Takes 7 arguments: the
	 * [empty] noise array, the position, and the
	 * size.
	 */
	private double[] initializeNoiseField(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, int par6, int par7)
	{
		ChunkProviderEvent.InitNoiseField event = new ChunkProviderEvent.InitNoiseField(this, par1ArrayOfDouble, par2, par3, par4, par5, par6, par7);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.getResult() == Result.DENY)
			return event.noisefield;

		if (par1ArrayOfDouble == null)
		{
			par1ArrayOfDouble = new double[par5 * par6 * par7];
		}

		double d0 = 684.412D;
		double d1 = 2053.236D;
		noiseData4 = cybertronNoiseGen6.generateNoiseOctaves(noiseData4, par2, par3, par4, par5, 1, par7, 1.0D, 0.0D, 1.0D);
		noiseData5 = cybertronNoiseGen7.generateNoiseOctaves(noiseData5, par2, par3, par4, par5, 1, par7, 100.0D, 0.0D, 100.0D);
		noiseData1 = cybertronNoiseGen3.generateNoiseOctaves(noiseData1, par2, par3, par4, par5, par6, par7, d0 / 80.0D, d1 / 60.0D, d0 / 80.0D);
		noiseData2 = cybertronNoiseGen1.generateNoiseOctaves(noiseData2, par2, par3, par4, par5, par6, par7, d0, d1, d0);
		noiseData3 = cybertronNoiseGen2.generateNoiseOctaves(noiseData3, par2, par3, par4, par5, par6, par7, d0, d1, d0);
		int k1 = 0;
		int l1 = 0;
		double[] adouble1 = new double[par6];
		int i2;

		for (i2 = 0; i2 < par6; ++i2)
		{
			adouble1[i2] = Math.cos(i2 * Math.PI * 6.0D / par6) * 2.0D;
			double d2 = i2;

			if (i2 > par6 / 2)
			{
				d2 = par6 - 1 - i2;
			}

			if (d2 < 4.0D)
			{
				d2 = 4.0D - d2;
				adouble1[i2] -= d2 * d2 * d2 * 10.0D;
			}
		}

		for (i2 = 0; i2 < par5; ++i2)
		{
			for (int k2 = 0; k2 < par7; ++k2)
			{
				double d3 = (noiseData4[l1] + 256.0D) / 512.0D;

				if (d3 > 1.0D)
				{
					d3 = 1.0D;
				}

				double d4 = 0.0D;
				double d5 = noiseData5[l1] / 8000.0D;

				if (d5 < 0.0D)
				{
					d5 = -d5;
				}

				d5 = d5 * 3.0D - 3.0D;

				if (d5 < 0.0D)
				{
					d5 /= 2.0D;

					if (d5 < -1.0D)
					{
						d5 = -1.0D;
					}

					d5 /= 1.4D;
					d5 /= 2.0D;
					d3 = 0.0D;
				}
				else
				{
					if (d5 > 1.0D)
					{
						d5 = 1.0D;
					}

					d5 /= 6.0D;
				}

				d3 += 0.5D;
				d5 = d5 * par6 / 16.0D;
				++l1;

				for (int j2 = 0; j2 < par6; ++j2)
				{
					double d6 = 0.0D;
					double d7 = adouble1[j2];
					double d8 = noiseData2[k1] / 512.0D;
					double d9 = noiseData3[k1] / 512.0D;
					double d10 = (noiseData1[k1] / 10.0D + 1.0D) / 2.0D;

					if (d10 < 0.0D)
					{
						d6 = d8;
					}
					else if (d10 > 1.0D)
					{
						d6 = d9;
					}
					else
					{
						d6 = d8 + (d9 - d8) * d10;
					}

					d6 -= d7;
					double d11;

					if (j2 > par6 - 4)
					{
						d11 = (j2 - (par6 - 4)) / 3.0F;
						d6 = d6 * (1.0D - d11) + -10.0D * d11;
					}

					if (j2 < d4)
					{
						d11 = (d4 - j2) / 4.0D;

						if (d11 < 0.0D)
						{
							d11 = 0.0D;
						}

						if (d11 > 1.0D)
						{
							d11 = 1.0D;
						}

						d6 = d6 * (1.0D - d11) + -10.0D * d11;
					}

					par1ArrayOfDouble[k1] = d6;
					++k1;
				}
			}
		}

		return par1ArrayOfDouble;
	}

	/**
	 * loads or generates the chunk at the chunk location specified
	 */
	@Override
	public Chunk loadChunk(int par1, int par2)
	{
		return provideChunk(par1, par2);
	}

	/**
	 * Converts the instance data to a readable string.
	 */
	@Override
	public String makeString()
	{
		return "CybertronRandomLevelSource";
	}

	@Override
	public void populate(IChunkProvider var1, int var2, int var3)
	{

	}

	/**
	 * Will return back a chunk, if it doesn't exist and its not a MP client it
	 * will generates all the blocks for the
	 * specified chunk from the map seed and chunk seed
	 */
	@Override
	public Chunk provideChunk(int par1, int par2)
	{
		cybertronRNG.setSeed(par1 * 341873128712L + par2 * 132897987541L);
		Block[] ablock = new Block[32768];
		func_147419_a(par1, par2, ablock);
		func_147418_b(par1, par2, ablock);
		Chunk chunk = new Chunk(worldObj, ablock, par1, par2);
		BiomeGenBase[] abiomegenbase = worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[]) null, par1 * 16, par2 * 16, 16, 16);
		byte[] abyte = chunk.getBiomeArray();

		for (int k = 0; k < abyte.length; ++k)
		{
			abyte[k] = (byte) abiomegenbase[k].biomeID;
		}

		chunk.resetRelightChecks();
		return chunk;
	}

	@Override
	public void recreateStructures(int par1, int par2)
	{

	}

	/**
	 * Two modes of operation: if passed true, save all Chunks in one go. If
	 * passed false, save up to two chunks.
	 * Return true if all chunks have been saved.
	 */
	@Override
	public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
	{
		return true;
	}

	/**
	 * Save extra data not associated with any Chunk. Not saved during autosave,
	 * only during world unload. Currently
	 * unimplemented.
	 */
	@Override
	public void saveExtraData()
	{
	}

	/**
	 * Unloads chunks that are marked to be unloaded. This is not guaranteed to
	 * unload every such chunk.
	 */
	@Override
	public boolean unloadQueuedChunks()
	{
		return false;
	}
}
