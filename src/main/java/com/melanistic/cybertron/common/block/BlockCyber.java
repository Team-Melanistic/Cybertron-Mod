package com.melanistic.cybertron.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.melanistic.cybertron.lib.CyberReference;

public class BlockCyber extends Block
{

	protected BlockCyber(Material material)
	{
		super(material);
	}

	@Override
	public Block setBlockTextureName(String name)
	{
		return super.setBlockTextureName(CyberReference.MODID + ":" + name);
	}

}
