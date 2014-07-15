package cybertron.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cybertron.CybertronMod;

public class CyItem extends Item {
	String texturePNG;
	
	public CyItem( String IconRegStr)
	{
		//setCreativeTab(CybertronMod.TAB);
		texturePNG = CybertronMod.MODID + ":"  + IconRegStr;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg)
	{
		this.itemIcon = reg.registerIcon(texturePNG); 
	}
}