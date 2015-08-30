package com.melanistic.cybertron.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public abstract class BlockRender implements ISimpleBlockRenderingHandler
{
	protected Tessellator instance = Tessellator.instance;
	
	public int getRenderId()
	{
		return 0;
	}
	
	public boolean shouldRender3DInInventory(int modelId) 
	{
		return true;
	}
	
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		renderWorldBlock(world, x, y, z, block, renderer, modelId);
		return true;
	}
	
	public abstract void renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer, int modelId);

}
