package cybertron.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cybertron.CybertronMod;

public class ItemCybertronCrystal extends Item {
	public ItemCybertronCrystal() {
		super();
		this.setUnlocalizedName("cybertronCrystal");
		this.setTextureName(CybertronMod.MODID + ":" + "cybertron_crystal");
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}
}