package cybertron.blocks;

import java.util.Random;

import cybertron.CybertronMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class CybertronOre extends Block {
	public CybertronOre() {
		super(Material.rock);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypeStone);
		this.setBlockName("oreCybertron");
		this.setBlockTextureName("diamond_ore");
		this.setHarvestLevel("pickaxe", 3);
	}
	
	@Override
	public Item getItemDropped(int i, Random random, int j) {
		return CybertronMod.cybertron_crystal;
	}
}