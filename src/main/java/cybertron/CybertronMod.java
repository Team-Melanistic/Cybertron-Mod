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
import cybertron.blocks.CyBlock;
import cybertron.blocks.CybertronBlock;
import cybertron.blocks.CybertronPortal;
import cybertron.dimension.CybertronBiome;
import cybertron.dimension.CybertronWorldProvider;
import cybertron.items.CyItem;

@Mod(modid = CybertronMod.MODID, name = CybertronMod.NAME, version = CybertronMod.VERSION)
public class CybertronMod {
	
	public static final String NAME = "Cybertron";
	public static final String MODID = "cybertron";
	public static final String VERSION = "1.0";
	
	public static Block cyberore = new CyBlock("cyberore", "cyber_ore", CybertronMod.cybercrystal,1);
	public static Item cybercrystal = new CyItem("cybercrystal").setUnlocalizedName("cybercrystal");
	
	public static CybertronPortal cybertron_portal;
	public static Block cybertron_block;
	
	public static BiomeGenBase cybertron_biome;
	public static final int CYBERTRON_DIMENSION_ID = 10;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		cybertron_portal = new CybertronPortal();
		
		cybertron_block = new CybertronBlock();
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		cybertron_biome = new CybertronBiome();
		DimensionManager.registerProviderType(CYBERTRON_DIMENSION_ID, CybertronWorldProvider.class, false);
		DimensionManager.registerDimension(CYBERTRON_DIMENSION_ID, CYBERTRON_DIMENSION_ID);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
	private void registerBlocks(){
		GameRegistry.registerBlock(cyberore, "cyberore");
		
		GameRegistry.registerBlock(cybertron_portal, "cybertron_portal");
		GameRegistry.registerBlock(cybertron_block, "cybertron_block");
	}
	
	private void registerItems(){
		GameRegistry.registerItem(cybercrystal, "cybercrystal");
	}
	
	private void registerSmelting(){}
	
	private void registerWorldGen(){}
	
	private void registerTileEntity(){}
	
	private void registerShapelessRecipes(){}
	
	private void registerShapedRecipes(){}
}
