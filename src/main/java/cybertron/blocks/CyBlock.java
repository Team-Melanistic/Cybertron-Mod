package cybertron.blocks;

import java.util.Random;

import cybertron.CybertronMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

//CyberOre1.png
public class CyBlock  extends Block {
	
	String texturePNG;

	Item dropItem;
	Block dropBlock;
	boolean dropType; // true = block, false = item
	int dropAmmount;

	public CyBlock(String texture, String IconRegStr, Item drop, int DropNumber) {
		super(Material.rock);
		
		this.dropType = false;
		
		//setCreativeTab(CybertronMod.TAB);
		
		this.setBlockName(IconRegStr);
		this.setHardness(5.0F);
		this.setResistance(10.0F);
		
		this.texturePNG = CybertronMod.MODID + ":"  + IconRegStr;
		this.dropItem = drop;
		this.dropAmmount = DropNumber;
		
		setBlockTextureName(texturePNG);
		
	}
	public CyBlock(String texture, String IconRegStr, Block drop, int DropNumber) {
		super(Material.iron);
		
		this.dropType = true;
		
		//setCreativeTab(CybertronMod.TAB);
		
		this.setBlockName(IconRegStr);
		this.setHardness(5.0F);
		this.setResistance(10.0F);
		
		this.texturePNG = CybertronMod.MODID + ":"  + IconRegStr;
		this.dropBlock = drop;
		this.dropAmmount = DropNumber;
		
		setBlockTextureName(texturePNG);
	}
	
//drops when broken with pickaxe
	@Override
	public Item getItemDropped(int a, Random r, int b)
	{
		if (!dropType){
			return dropItem;
		}else if (dropType) {
			return Item.getItemFromBlock(dropBlock);
		}else{
			return null;
		}
	}

	@Override
	public int quantityDropped(Random random)
	{
		return dropAmmount;
	}

}