package com.melanistic.cybertron.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class CyberBlocks {
	public static BlockTransformiumPortal transformium_portal;
	public static Block reinforced_concrete;
	public static Block transformium_ore;
	public static Block transformium_block;
	public static Fluid energon;
	public static Block energon_block;
	
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
	}
}
