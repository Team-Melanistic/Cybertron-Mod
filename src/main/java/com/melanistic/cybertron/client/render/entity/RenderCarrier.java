package com.melanistic.cybertron.client.render.entity;

import org.lwjgl.opengl.GL11;

import com.melanistic.cybertron.client.render.ModelCarrier;
import com.melanistic.cybertron.common.entity.EntityCarrier;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.ForgeDirection;

public class RenderCarrier extends RenderLiving
{
	public RenderCarrier() 
	{
		super(new ModelCarrier(), 0.6F);
		
	}
	RenderBlocks blocks = new RenderBlocks();
	//ModelCarrier c = 
	
	
	@Override
	public void doRender(EntityLiving e, double x, double y, double z, float f1, float f2) 
	{
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glTranslated(x, y+0.65, z);
		GL11.glRotatef(-e.renderYawOffset, 0, 1, 0);
		GL11.glScalef(0.75F, 0.75F, 0.75F);
		GL11.glTranslated(-0.5, 0, -0.5);
		Block b = Blocks.sand;
		bindTexture(TextureMap.locationBlocksTexture);	
		Tessellator tessellator = Tessellator.instance;
		blocks.blockAccess= new FaceWorld(b, 1, e.worldObj, MathHelper.floor_double(e.posX), MathHelper.floor_double(e.posY), MathHelper.floor_double(e.posZ));
		
		tessellator.startDrawingQuads();
		blocks.renderBlockAllFaces(b, 0, 0, 0);
		tessellator.draw();
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
		
		((ModelCarrier)mainModel).setSwingProgress(e.getSwingProgress(f2));
		super.doRender(e, x, y-1, z, f1, f2);
		
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity e) 
	{
		return new ResourceLocation("cybertron", "textures/entity/Carrier.png");
	}
	
	private class FaceWorld implements IBlockAccess
	{
		Block block;
		int meta;
		World w;
		int j,k,l;
		
		public FaceWorld(Block b, int metadata, World world, int j, int k ,int l)
		{
			block = b;
			meta = metadata;
			w = world;
			this.j = j;
			this.k = k;
			this.l = l;
		}
		
		@Override
		public Block getBlock(int j, int k, int l) 
		{
			if(j==0 && k==0 && l==0)
			{
				return block;
			}
			return Blocks.air;
		}

		@Override
		public TileEntity getTileEntity(int j, int k, int l)
		{
			TileEntity t = block.createTileEntity(w, meta);
			if(t!=null)
			{
				t.setWorldObj(w);
				t.xCoord=0;
				t.yCoord=0;
				t.zCoord=0;
			}
			return t;
		}

		@Override
		public int getLightBrightnessForSkyBlocks(int j, int k, int l, int p_72802_4_)
		{
			return w.getLightBrightnessForSkyBlocks(this.j, this.l, this.l, p_72802_4_);
		}

		@Override
		public int getBlockMetadata(int j, int k, int l)
		{
			if(j==0 && k==0 && l==0)
			{
				return meta;
			}
			return 0;
		}

		@Override
		public int isBlockProvidingPowerTo(int p_72879_1_, int p_72879_2_, int p_72879_3_, int p_72879_4_) 
		{
			return 0;
		}

		@Override
		public boolean isAirBlock(int j, int k, int l) 
		{
			return getBlock(j, k, l).isAir(this, j, k, l);
		}

		@Override
		public BiomeGenBase getBiomeGenForCoords(int p_72807_1_, int p_72807_2_)
		{
			return w.getBiomeGenForCoords(j, l);
		}

		@Override
		public int getHeight() 
		{
			return 1;
		}

		@Override
		public boolean extendedLevelsInChunkCache() 
		{
			return w.extendedLevelsInChunkCache();
		}

		@Override
		public boolean isSideSolid(int x, int y, int z, ForgeDirection side, boolean _default) 
		{
			return getBlock(x, y, z).isSideSolid(this, x, y, z, side);
		}
		
	}
}
