package com.melanistic.cybertron.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.melanistic.cybertron.common.item.CyberItems;
import com.melanistic.cybertron.lib.ModInfo;

public class BlockCybertronOre extends Block {
	public BlockCybertronOre() {
		super(Material.rock);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypeStone);
		this.setBlockName("oreCybertron");
		this.setBlockTextureName(ModInfo.MODID + ":" + "cybertron_ore");
		this.setHarvestLevel("pickaxe", 3);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public Item getItemDropped(int i, Random random, int j) {
		return CyberItems.cybertron_crystal;
	}
}