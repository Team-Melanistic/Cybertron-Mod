package cybertron;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cybertron.blocks.CybertronOre;
import cybertron.blocks.CybertronPortal;
import cybertron.blocks.CybertronPortalFrame;
import cybertron.dimension.CybertronBiome;
import cybertron.dimension.CybertronWorldProvider;
import cybertron.items.CybertronCrystal;

@Mod(modid = CybertronMod.MODID, name = CybertronMod.NAME, version = CybertronMod.VERSION)
public class CybertronMod {
	public static final String NAME = "Cybertron";
	public static final String MODID = "cybertron";
	public static final String VERSION = "1.0";
	
	public static Block cybertron_ore;
	public static Item cybertron_crystal;
	
	public static CybertronPortal cybertron_portal;
	public static Block cybertron_portal_frame;
	
	public static BiomeGenBase cybertron_biome;
	public static final int CYBERTRON_DIMENSION_ID = 10;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		registerBlocks();
		registerItems();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		cybertron_biome = new CybertronBiome();
		registerDimension();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
	private void registerBlocks() {
		cybertron_ore = new CybertronOre();
		GameRegistry.registerBlock(cybertron_ore, "cybertron_ore");
		
		cybertron_portal = new CybertronPortal();
		GameRegistry.registerBlock(cybertron_portal, "cybertron_portal");
		
		cybertron_portal_frame = new CybertronPortalFrame();
		GameRegistry.registerBlock(cybertron_portal_frame, "cybertron_portal_frame");
	}
	
	private void registerItems() {
		cybertron_crystal = new CybertronCrystal();
		GameRegistry.registerItem(cybertron_crystal, "cybertron_crystal");
	}
	
	private void registerDimension() {
		DimensionManager.registerProviderType(CYBERTRON_DIMENSION_ID, CybertronWorldProvider.class, false);
		DimensionManager.registerDimension(CYBERTRON_DIMENSION_ID, CYBERTRON_DIMENSION_ID);
	}
	
	private void registerSmelting(){}
	
	private void registerWorldGen(){}
	
	private void registerTileEntity(){}
	
	private void registerShapelessRecipes(){}
	
	private void registerShapedRecipes(){}
}
