package com.melanistic.cybertron.common.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.melanistic.cybertron.common.item.CyberItems;

public class BlockCyberOre extends BlockCyber
{
	public BlockCyberOre()
	{
		super(Material.rock);
		setHardness(3.0F);
		setResistance(5.0F);
		setStepSound(soundTypeStone);
		setBlockName("oreTransformium");
		setBlockTextureName("transformium_ore");
		this.setHarvestLevel("pickaxe", 3);
		setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public Item getItemDropped(int i, Random random, int j)
	{
		return CyberItems.transformium_shard;
	}

	@Override
	public int quantityDropped(Random random)
	{
		return random.nextInt(16) + 1;
	}
}
