package cybertron;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cybertron.blocks.BlockCybertronOre;
import cybertron.blocks.BlockCybertronPortal;
import cybertron.blocks.BlockEnergon;
import cybertron.blocks.BlockReinforcedConcrete;
import cybertron.dimension.CybertronBiome;
import cybertron.dimension.WorldProviderCybertron;
import cybertron.items.ItemCybertronCrystal;

@Mod(modid = CybertronMod.MODID, name = CybertronMod.NAME, version = CybertronMod.VERSION)
public class CybertronMod {
	public static final String NAME = "Cybertron";
	public static final String MODID = "cybertron";
	public static final String VERSION = "1.0";
	
	public static Block cybertron_ore;
	public static Item cybertron_crystal;
	
	public static BlockCybertronPortal cybertron_portal;
	public static Block reinforced_concrete;
	
	public static BiomeGenBase cybertron_biome;
	public static final int CYBERTRON_DIMENSION_ID = 10;
	
	public static Fluid energon;
	public static Block energon_block;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		registerBlocks();
		registerItems();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		registerBiomes();
		registerDimensions();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
	private void registerBlocks() {
		cybertron_ore = new BlockCybertronOre();
		GameRegistry.registerBlock(cybertron_ore, "cybertron_ore");
		
		cybertron_portal = new BlockCybertronPortal();
		GameRegistry.registerBlock(cybertron_portal, "cybertron_portal");
		
		reinforced_concrete = new BlockReinforcedConcrete();
		GameRegistry.registerBlock(reinforced_concrete, "reinforced_concrete");
		
		energon = new Fluid("energon");
		FluidRegistry.registerFluid(energon);
		
		energon_block = new BlockEnergon();
		GameRegistry.registerBlock(energon_block, "energon_block");
	}
	
	private void registerItems() {
		cybertron_crystal = new ItemCybertronCrystal();
		GameRegistry.registerItem(cybertron_crystal, "cybertron_crystal");
	}
	
	private void registerBiomes() {
		cybertron_biome = new CybertronBiome();
	}
	
	private void registerDimensions() {
		DimensionManager.registerProviderType(CYBERTRON_DIMENSION_ID, WorldProviderCybertron.class, false);
		DimensionManager.registerDimension(CYBERTRON_DIMENSION_ID, CYBERTRON_DIMENSION_ID);
	}
	
	private void registerShapelessRecipes(){}
	
	private void registerShapedRecipes(){}
	
	private void registerSmeltingRecipes(){}
	
	private void registerWorldGenerators(){}
	
	private void registerTileEntities(){}
}
