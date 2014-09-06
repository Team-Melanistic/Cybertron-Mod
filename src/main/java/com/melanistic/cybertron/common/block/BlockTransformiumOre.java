package com.melanistic.cybertron.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.melanistic.cybertron.common.item.CyberItems;
import com.melanistic.cybertron.lib.CybertronReference;

public class BlockTransformiumOre extends Block {
	public BlockTransformiumOre() {
		super(Material.rock);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypeStone);
		this.setBlockName("oreTransformium");
		this.setBlockTextureName(CybertronReference.MODID + ":" + "transformium_ore");
		this.setHarvestLevel("pickaxe", 3);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public Item getItemDropped(int i, Random random, int j) {
		return CyberItems.transformium_shard;
	}
	
	@Override
	public int quantityDropped(Random random) {
		return random.nextInt(16) + 1;
	}
}