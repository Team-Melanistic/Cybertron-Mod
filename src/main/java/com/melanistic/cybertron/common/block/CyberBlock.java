package com.melanistic.cybertron.common.block;

import com.melanistic.cybertron.lib.ModInfo;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class CyberBlock extends Block
{

	protected CyberBlock(Material material)
	{
		super(material);
	}
	
	public Block setBlockTextureName(String name)
	{
		return super.setBlockTextureName(ModInfo.MODID + ":" + name);
	}

}
