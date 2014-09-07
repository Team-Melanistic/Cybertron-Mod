package com.melanistic.cybertron.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.BlockFluidClassic;

import com.melanistic.cybertron.Cybertron;
import com.melanistic.cybertron.lib.CyberReference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEnergon extends BlockFluidClassic {
	@SideOnly(Side.CLIENT)
	public static IIcon[] icons;
	
	public BlockEnergon() {
		super(CyberBlocks.energon, Material.water);
		this.setBlockName("energon");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon) {
		icons = new IIcon[2];

		icons[0] = icon.registerIcon(CyberReference.MODID + ":" + "energon_still");
		icons[1] = icon.registerIcon(CyberReference.MODID + ":" + "energon_flow");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		if(side != 0 && side != 1) return icons[1];
		else return icons[0];
	}
	
	@SideOnly(Side.CLIENT)
	public static IIcon getFluidIcon(String string) {
		if(string.equals("energon_still")) return icons[0];
		else if(string.equals("energon_flow")) return icons[1];
		else return null;
	}
}
