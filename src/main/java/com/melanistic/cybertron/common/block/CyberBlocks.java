package com.melanistic.cybertron.common.block;

import net.minecraft.block.Block;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CyberBlocks
{

	// please follow this format of camel naming, _ are really a Bother to do

	/** includes all forms of concrete */
	public static Block concrete = new BlockConcrete();

	/** Planning to remove this later, no need to improve it */
	public static BlockCyberPortal cyberPortal = new BlockCyberPortal();

	/** Fluid object of energon */
	public static Fluid energon = new Fluid("energon").setViscosity(2000);
	
	static
	{
		FluidRegistry.registerFluid(energon);
	}
	
	/** Block object of energon */
	public static Block energonBlock = new BlockEnergon();

	/** includes all ores found in Cybertrnn */
	public static Block ores = new BlockCyberOre();

	/** All Blocks used with the temple */
	public static Block templeBlocks = new BlockTemple();

	private static void initBlock(Block block)
	{
		if (block instanceof BlockItemCyber)
		{
			GameRegistry.registerBlock(block, ItemBlockCyber.class, block.getUnlocalizedName());
		}
		else
		{
			GameRegistry.registerBlock(block, block.getUnlocalizedName());
		}
	}

	private static void initFluid(Fluid fluid, Block block)
	{
		
		GameRegistry.registerBlock(block, block.getUnlocalizedName());
	}

	public static void registerBlocks()
	{
		initBlock(cyberPortal);
		initBlock(concrete);
		initBlock(ores);
		initBlock(templeBlocks);

		initFluid(energon, energonBlock);
	}
}
