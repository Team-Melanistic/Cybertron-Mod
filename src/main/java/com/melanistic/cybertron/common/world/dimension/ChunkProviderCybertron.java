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

import com.melanistic.cybertron.common.block.CybertronBlocks;

import cpw.mods.fml.common.eventhandler.Event.Result;

public class ChunkProviderCybertron implements IChunkProvider
{
    private Random cybertronRNG;
    /** A NoiseGeneratorOctaves used in generating cybertron terrain */
    private NoiseGeneratorOctaves cybertronNoiseGen1;
    private NoiseGeneratorOctaves cybertronNoiseGen2;
    private NoiseGeneratorOctaves cybertronNoiseGen3;
    /**
     * Determines whether something other than reinforced concrete can be generated at a location
     */
    private NoiseGeneratorOctaves concreteExculsivityNoiseGen;
    public NoiseGeneratorOctaves cybertronNoiseGen6;
    public NoiseGeneratorOctaves cybertronNoiseGen7;
    /** Is the world that cybertron is getting generated. */
    private World worldObj;
    private double[] noiseField;
    /**
     * Holds the noise used to determine whether something other than reinforced concrete can be generated at a location
     */
    private double[] concreteExclusivityNoise = new double[256];
    double[] noiseData1;
    double[] noiseData2;
    double[] noiseData3;
    double[] noiseData4;
    double[] noiseData5;

    public ChunkProviderCybertron(World par1World, long par2)
    {
        this.worldObj = par1World;
        this.cybertronRNG = new Random(par2);
        this.cybertronNoiseGen1 = new NoiseGeneratorOctaves(this.cybertronRNG, 16);
        this.cybertronNoiseGen2 = new NoiseGeneratorOctaves(this.cybertronRNG, 16);
        this.cybertronNoiseGen3 = new NoiseGeneratorOctaves(this.cybertronRNG, 8);
        this.concreteExculsivityNoiseGen = new NoiseGeneratorOctaves(this.cybertronRNG, 4);
        this.cybertronNoiseGen6 = new NoiseGeneratorOctaves(this.cybertronRNG, 10);
        this.cybertronNoiseGen7 = new NoiseGeneratorOctaves(this.cybertronRNG, 16);

        NoiseGenerator[] noiseGens = {cybertronNoiseGen1, cybertronNoiseGen2, cybertronNoiseGen3, concreteExculsivityNoiseGen, cybertronNoiseGen6, cybertronNoiseGen7};
        noiseGens = TerrainGen.getModdedNoiseGenerators(par1World, this.cybertronRNG, noiseGens);
        this.cybertronNoiseGen1 = (NoiseGeneratorOctaves)noiseGens[0];
        this.cybertronNoiseGen2 = (NoiseGeneratorOctaves)noiseGens[1];
        this.cybertronNoiseGen3 = (NoiseGeneratorOctaves)noiseGens[2];
        this.concreteExculsivityNoiseGen = (NoiseGeneratorOctaves)noiseGens[3];
        this.cybertronNoiseGen6 = (NoiseGeneratorOctaves)noiseGens[4];
        this.cybertronNoiseGen7 = (NoiseGeneratorOctaves)noiseGens[5];
    }

    public void func_147419_a(int p_147419_1_, int p_147419_2_, Block[] p_147419_3_)
    {
        byte b0 = 4;
        byte b1 = 32;
        int k = b0 + 1;
        byte b2 = 17;
        int l = b0 + 1;
        this.noiseField = this.initializeNoiseField(this.noiseField, p_147419_1_ * b0, 0, p_147419_2_ * b0, k, b2, l);

        for (int i1 = 0; i1 < b0; ++i1)
        {
            for (int j1 = 0; j1 < b0; ++j1)
            {
                for (int k1 = 0; k1 < 16; ++k1)
                {
                    double d0 = 0.125D;
                    double d1 = this.noiseField[((i1 + 0) * l + j1 + 0) * b2 + k1 + 0];
                    double d2 = this.noiseField[((i1 + 0) * l + j1 + 1) * b2 + k1 + 0];
                    double d3 = this.noiseField[((i1 + 1) * l + j1 + 0) * b2 + k1 + 0];
                    double d4 = this.noiseField[((i1 + 1) * l + j1 + 1) * b2 + k1 + 0];
                    double d5 = (this.noiseField[((i1 + 0) * l + j1 + 0) * b2 + k1 + 1] - d1) * d0;
                    double d6 = (this.noiseField[((i1 + 0) * l + j1 + 1) * b2 + k1 + 1] - d2) * d0;
                    double d7 = (this.noiseField[((i1 + 1) * l + j1 + 0) * b2 + k1 + 1] - d3) * d0;
                    double d8 = (this.noiseField[((i1 + 1) * l + j1 + 1) * b2 + k1 + 1] - d4) * d0;

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
                                    block = CybertronBlocks.energon_block;
                                }

                                if (d15 > 0.0D)
                                {
                                    block = CybertronBlocks.reinforced_concrete;
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

    public void func_147418_b(int p_147418_1_, int p_147418_2_, Block[] p_147418_3_)
    {
        ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, p_147418_1_, p_147418_2_, p_147418_3_, null);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Result.DENY) return;

        byte b0 = 64;
        double d0 = 0.03125D;
        this.concreteExclusivityNoise = this.concreteExculsivityNoiseGen.generateNoiseOctaves(this.concreteExclusivityNoise, p_147418_1_ * 16, p_147418_2_ * 16, 0, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);

        for (int k = 0; k < 16; ++k)
        {
            for (int l = 0; l < 16; ++l)
            {
                int i1 = (int)(this.concreteExclusivityNoise[k + l * 16] / 3.0D + 3.0D + this.cybertronRNG.nextDouble() * 0.25D);
                int j1 = -1;
                Block block = CybertronBlocks.reinforced_concrete;
                Block block1 = CybertronBlocks.reinforced_concrete;

                for (int k1 = 127; k1 >= 0; --k1)
                {
                    int l1 = (l * 16 + k) * 128 + k1;

                    if (k1 < 127 - this.cybertronRNG.nextInt(5) && k1 > 0 + this.cybertronRNG.nextInt(5))
                    {
                        Block block2 = p_147418_3_[l1];

                        if (block2 != null && block2.getMaterial() != Material.air)
                        {
                            if (block2 == CybertronBlocks.reinforced_concrete)
                            {
                                if (j1 == -1)
                                {
                                    if (i1 <= 0)
                                    {
                                        block = null;
                                        block1 = CybertronBlocks.reinforced_concrete;
                                    }
                                    else if (k1 >= b0 - 4 && k1 <= b0 + 1)
                                    {
                                        block = CybertronBlocks.reinforced_concrete;
                                        block1 = CybertronBlocks.reinforced_concrete;
                                    }

                                    if (k1 < b0 && (block == null || block.getMaterial() == Material.air))
                                    {
                                        block = CybertronBlocks.energon_block;
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

    /**
     * loads or generates the chunk at the chunk location specified
     */
    public Chunk loadChunk(int par1, int par2)
    {
        return this.provideChunk(par1, par2);
    }

    /**
     * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
     * specified chunk from the map seed and chunk seed
     */
    public Chunk provideChunk(int par1, int par2)
    {
        this.cybertronRNG.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
        Block[] ablock = new Block[32768];
        this.func_147419_a(par1, par2, ablock);
        this.func_147418_b(par1, par2, ablock);
        Chunk chunk = new Chunk(this.worldObj, ablock, par1, par2);
        BiomeGenBase[] abiomegenbase = this.worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[])null, par1 * 16, par2 * 16, 16, 16);
        byte[] abyte = chunk.getBiomeArray();

        for (int k = 0; k < abyte.length; ++k)
        {
            abyte[k] = (byte)abiomegenbase[k].biomeID;
        }

        chunk.resetRelightChecks();
        return chunk;
    }

    /**
     * generates a subset of the level's terrain data. Takes 7 arguments: the [empty] noise array, the position, and the
     * size.
     */
    private double[] initializeNoiseField(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, int par6, int par7)
    {
        ChunkProviderEvent.InitNoiseField event = new ChunkProviderEvent.InitNoiseField(this, par1ArrayOfDouble, par2, par3, par4, par5, par6, par7);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Result.DENY) return event.noisefield;

        if (par1ArrayOfDouble == null)
        {
            par1ArrayOfDouble = new double[par5 * par6 * par7];
        }

        double d0 = 684.412D;
        double d1 = 2053.236D;
        this.noiseData4 = this.cybertronNoiseGen6.generateNoiseOctaves(this.noiseData4, par2, par3, par4, par5, 1, par7, 1.0D, 0.0D, 1.0D);
        this.noiseData5 = this.cybertronNoiseGen7.generateNoiseOctaves(this.noiseData5, par2, par3, par4, par5, 1, par7, 100.0D, 0.0D, 100.0D);
        this.noiseData1 = this.cybertronNoiseGen3.generateNoiseOctaves(this.noiseData1, par2, par3, par4, par5, par6, par7, d0 / 80.0D, d1 / 60.0D, d0 / 80.0D);
        this.noiseData2 = this.cybertronNoiseGen1.generateNoiseOctaves(this.noiseData2, par2, par3, par4, par5, par6, par7, d0, d1, d0);
        this.noiseData3 = this.cybertronNoiseGen2.generateNoiseOctaves(this.noiseData3, par2, par3, par4, par5, par6, par7, d0, d1, d0);
        int k1 = 0;
        int l1 = 0;
        double[] adouble1 = new double[par6];
        int i2;

        for (i2 = 0; i2 < par6; ++i2)
        {
            adouble1[i2] = Math.cos((double)i2 * Math.PI * 6.0D / (double)par6) * 2.0D;
            double d2 = (double)i2;

            if (i2 > par6 / 2)
            {
                d2 = (double)(par6 - 1 - i2);
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
                double d3 = (this.noiseData4[l1] + 256.0D) / 512.0D;

                if (d3 > 1.0D)
                {
                    d3 = 1.0D;
                }

                double d4 = 0.0D;
                double d5 = this.noiseData5[l1] / 8000.0D;

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
                d5 = d5 * (double)par6 / 16.0D;
                ++l1;

                for (int j2 = 0; j2 < par6; ++j2)
                {
                    double d6 = 0.0D;
                    double d7 = adouble1[j2];
                    double d8 = this.noiseData2[k1] / 512.0D;
                    double d9 = this.noiseData3[k1] / 512.0D;
                    double d10 = (this.noiseData1[k1] / 10.0D + 1.0D) / 2.0D;

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
                        d11 = (double)((float)(j2 - (par6 - 4)) / 3.0F);
                        d6 = d6 * (1.0D - d11) + -10.0D * d11;
                    }

                    if ((double)j2 < d4)
                    {
                        d11 = (d4 - (double)j2) / 4.0D;

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
     * Checks to see if a chunk exists at x, y
     */
    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }

    /**
     * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
     * Return true if all chunks have been saved.
     */
    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
    {
        return true;
    }

    /**
     * Save extra data not associated with any Chunk.  Not saved during autosave, only during world unload.  Currently
     * unimplemented.
     */
    public void saveExtraData() {}

    /**
     * Unloads chunks that are marked to be unloaded. This is not guaranteed to unload every such chunk.
     */
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    /**
     * Returns if the IChunkProvider supports saving.
     */
    public boolean canSave()
    {
        return true;
    }

    /**
     * Converts the instance data to a readable string.
     */
    public String makeString()
    {
        return "CybertronRandomLevelSource";
    }

    /**
     * Returns a list of creatures of the specified type that can spawn at the given location.
     */
    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
    {
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(par2, par4);
        return biomegenbase.getSpawnableList(par1EnumCreatureType);
    }

    public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_)
    {
        return null;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }

	@Override
	public void recreateStructures(int par1, int par2) {

	}

	@Override
	public void populate(IChunkProvider var1, int var2, int var3) {

	}
}