package com.melanistic.cybertron.common.item;

import com.melanistic.cybertron.Cybertron;
import com.melanistic.cybertron.lib.CyberReference;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTransformiumShard extends Item {
	public ItemTransformiumShard()
	{
		super();
		this.setUnlocalizedName("shardTransformium");
		this.setTextureName(CyberReference.MODID + "transformium_shard");
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}
}