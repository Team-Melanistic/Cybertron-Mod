package com.melanistic.cybertron.blocks;

import java.util.Random;

import com.melanistic.cybertron.CybertronMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BlockCybertronOre extends Block {
	public BlockCybertronOre() {
		super(Material.rock);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypeStone);
		this.setBlockName("oreCybertron");
		this.setBlockTextureName(CybertronMod.MODID + ":" + "cybertron_ore");
		this.setHarvestLevel("pickaxe", 3);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public Item getItemDropped(int i, Random random, int j) {
		return CybertronMod.cybertron_crystal;
	}
}