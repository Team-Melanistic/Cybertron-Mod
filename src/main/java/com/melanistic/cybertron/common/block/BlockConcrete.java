package com.melanistic.cybertron.common.block;

import net.minecraft.block.BlockCompressed;
import net.minecraft.block.material.MapColor;

import com.melanistic.cybertron.lib.CyberReference;

public class BlockConcrete extends BlockCompressed
{
	public BlockConcrete()
	{
		super(MapColor.ironColor);
		setHardness(5.0F);
		setResistance(10.0F);
		setStepSound(soundTypeMetal);
		setBlockName("concreteReinforced");
		setBlockTextureName(CyberReference.MODID + ":" + "reinforced_concrete");
	}
}
