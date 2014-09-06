package com.melanistic.cybertron.common.item;

import com.melanistic.cybertron.Cybertron;
import com.melanistic.cybertron.lib.CybertronReference;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTransformiumIngot extends Item {
	public ItemTransformiumIngot()
	{
		super();
		this.setUnlocalizedName("ingotTransformium");
		this.setTextureName(CybertronReference.MODID + "transformium_ingot");
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}
}