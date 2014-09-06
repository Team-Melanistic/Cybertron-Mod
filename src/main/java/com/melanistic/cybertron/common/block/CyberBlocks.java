package com.melanistic.cybertron.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import com.melanistic.cybertron.common.block.item.ItemBlockTemple;

import cpw.mods.fml.common.registry.GameRegistry;

public class CyberBlocks
{
	
	public static BlockTransformiumPortal transformium_portal;
	public static Block reinforced_concrete;
	public static Block transformium_ore;
	public static Block transformium_block;
	public static Fluid energon;
	public static Block energon_block;
	public static Block templeBlocks;
	
	public static void registerBlocks()
	{
		transformium_ore = new BlockTransformiumOre();
		GameRegistry.registerBlock(transformium_ore, "transformium_ore");
		
		transformium_block = new BlockTransformium();
		GameRegistry.registerBlock(transformium_block, "transformium_block");
				
		transformium_portal = new BlockTransformiumPortal();
		GameRegistry.registerBlock(transformium_portal, "transformium_portal");
		
		reinforced_concrete = new BlockReinforcedConcrete();
		GameRegistry.registerBlock(reinforced_concrete, "reinforced_concrete");
		
		energon = new Fluid("energon").setViscosity(2000);
		FluidRegistry.registerFluid(energon);
		
		energon_block = new BlockEnergon();
		GameRegistry.registerBlock(energon_block, "energon_block");
		
		templeBlocks = new BlockTemple(Material.iron);
		GameRegistry.registerBlock(templeBlocks, ItemBlockTemple.class, "templeBlocks");
	}
}
