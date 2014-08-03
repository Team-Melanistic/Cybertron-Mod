package com.melanistic.cybertron.blocks;

import com.melanistic.cybertron.CybertronMod;

import net.minecraft.block.BlockCompressed;
import net.minecraft.block.material.MapColor;
import net.minecraft.world.World;

public class BlockReinforcedConcrete extends BlockCompressed {
	public BlockReinforcedConcrete() {
		super(MapColor.ironColor);
		this.setHardness(5.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypeMetal);
		this.setBlockName("reinforcedConcrete");
		this.setBlockTextureName(CybertronMod.MODID + ":" + "reinforced_concrete");
	}

	public void onBlockAdded(World world, int x, int y, int z)
    {
        if (!CybertronMod.cybertron_portal.tryToCreatePortal(world, x, y, z))
        {
            if (World.doesBlockHaveSolidTopSurface(world, x, y - 1, z))
            {
                world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world) + world.rand.nextInt(10));
            }
        }
    }
}
