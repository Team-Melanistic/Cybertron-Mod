package com.melanistic.cybertron.common.block;

import com.melanistic.cybertron.Cybertron;
import com.melanistic.cybertron.lib.CybertronReference;

import net.minecraft.block.BlockCompressed;
import net.minecraft.block.material.MapColor;
import net.minecraft.world.World;

public class BlockReinforcedConcrete extends BlockCompressed {
	public BlockReinforcedConcrete() {
		super(MapColor.ironColor);
		this.setHardness(5.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypeMetal);
		this.setBlockName("concreteReinforced");
		this.setBlockTextureName(CybertronReference.MODID + ":" + "reinforced_concrete");
	}
}
