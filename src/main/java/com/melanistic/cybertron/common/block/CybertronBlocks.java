package com.melanistic.cybertron.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class CybertronBlocks {
	public static BlockCybertronPortal cybertron_portal;
	public static Block reinforced_concrete;
	public static Block cybertron_ore;
	public static Fluid energon;
	public static Block energon_block;
	
	public static void registerBlocks()
	{
		cybertron_ore = new BlockCybertronOre();
		GameRegistry.registerBlock(cybertron_ore, "cybertron_ore");
		
		cybertron_portal = new BlockCybertronPortal();
		GameRegistry.registerBlock(cybertron_portal, "cybertron_portal");
		
		reinforced_concrete = new BlockReinforcedConcrete();
		GameRegistry.registerBlock(reinforced_concrete, "reinforced_concrete");
		
		energon = new Fluid("energon").setViscosity(2000);
		FluidRegistry.registerFluid(energon);
		
		energon_block = new BlockEnergon();
		GameRegistry.registerBlock(energon_block, "energon_block");
	}
}
