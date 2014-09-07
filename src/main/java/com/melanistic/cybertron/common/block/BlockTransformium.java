package com.melanistic.cybertron.common.block;

import com.melanistic.cybertron.Cybertron;

import net.minecraft.block.BlockCompressed;
import net.minecraft.block.material.MapColor;
import net.minecraft.world.World;

public class BlockTransformium extends BlockCompressed {
	public BlockTransformium() {
		super(MapColor.ironColor);
		this.setHardness(5.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypeMetal);
		this.setBlockName("blockTransformium");
		this.setBlockTextureName("transformium");
	}
	
	public void onBlockAdded(World world, int x, int y, int z)
    {
        if (!CyberBlocks.transformium_portal.tryToCreatePortal(world, x, y, z))
        {
            if (World.doesBlockHaveSolidTopSurface(world, x, y - 1, z))
            {
                world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world) + world.rand.nextInt(10));
            }
        }
    }
}