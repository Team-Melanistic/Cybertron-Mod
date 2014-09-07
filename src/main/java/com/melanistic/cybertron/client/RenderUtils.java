package com.melanistic.cybertron.client;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

import static org.lwjgl.opengl.GL11.*;

public class RenderUtils 
{
	
	public static Tessellator tessellator = Tessellator.instance;
	
	public static void bind(ResourceLocation res)
	{
		ClientUtils.getMC().getTextureManager().bindTexture(res);
	}
	
	public static void enableDepth()
	{
		glDepthMask(true);
		glEnable(GL_DEPTH_TEST);
	}

	public static void disableDepth()
	{
		glDisable(GL_DEPTH_TEST);
		glDepthMask(false);
	}

	public static void setupAlpha2D()
	{
		enableDepth();
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glDisable(GL_ALPHA_TEST);
	}

	public static void closeAlpha2D()
	{
		disableDepth();
		glEnable(GL_ALPHA_TEST);
	}

	public static void setupFogExp(int par1, float par2)
	{
		glFogi(GL_FOG_MODE, par1);
		glFogf(GL_FOG_DENSITY, par2);
	}

	public static void setupFogLinear(float par1)
	{
		glFogi(GL_FOG_MODE, GL_LINEAR);
		glFogf(GL_FOG_START, 0);
		glFogf(GL_FOG_END, par1);
	}

	public static void drawQuads()
	{
		tessellator.startDrawingQuads();
	}

	public static void drawQuadsC(float par1, float par2, float par3)
	{
		tessellator.startDrawingQuads();
		tessellator.setColorRGBA_F(par1, par2, par3, 1);
	}

	public static void drawQuadsCA(float par1, float par2, float par3, float par4)
	{
		tessellator.startDrawingQuads();
		tessellator.setColorRGBA_F(par1, par2, par3, par4);
	}
	
	public static void drawQuadsCA(int i, int i1, int i2, int i3)
	{
		tessellator.startDrawingQuads();
		tessellator.setColorRGBA(i, i1, i2, i3);
	}

	public static void drawQuadsN(float par1, float par2, float par3)
	{
		tessellator.startDrawingQuads();
		tessellator.setNormal(par1, par2, par3);
	}

	public static void drawQuadsNC(float par1, float par2, float par3, float par4, float par5, float par6)
	{
		tessellator.startDrawingQuads();
		tessellator.setNormal(par1, par2, par3);
		tessellator.setColorRGBA_F(par4, par5, par6, 1);
	}

	public static void drawQuadsNCA(float par1, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		tessellator.startDrawingQuads();
		tessellator.setNormal(par1, par2, par3);
		tessellator.setColorRGBA_F(par4, par5, par6, par7);
	}

	public static void addVertex(double ... d)
	{
		tessellator.addVertex(d[0], d[1], d[2]);
		tessellator.addVertex(d[3], d[4], d[5]);
		tessellator.addVertex(d[6], d[7], d[8]);
		tessellator.addVertex(d[9], d[10], d[11]);
	}

	public static void addVertexUV(double ... d)
	{
		tessellator.addVertexWithUV(d[0], d[1], d[2], d[3], d[4]);
		tessellator.addVertexWithUV(d[5], d[6], d[7], d[8], d[9]);
		tessellator.addVertexWithUV(d[10], d[11], d[12], d[13], d[14]);
		tessellator.addVertexWithUV(d[15], d[16], d[17], d[18], d[19]);
	}

	public static void draw()
	{
		tessellator.draw();
	}

	public static void drawRect(float par0, float par1, float par2, float par3)
	{
		addVertex(par0, par3, 0, par2, par3, 0, par2, par1, 0, par0, par1, 0);
	}

	public static void drawRectUV(float par0, float par1, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		addVertexUV(par0, par3, 0, par4, par7, par2, par3, 0, par6, par7, par2, par1, 0, par6, par5, par0, par1, 0, par4, par5);
	}
	
	public static void renderHollowCircle(float x, float y, float x1, float y1, float percent)
	{
		float width = x1 - x;
		float height = y1 - y;
		float mid = width / 2 + x;
		float mid1 = height / 2 + y;
		float f1 = Math.min(percent, .25f) / .25f;
		float f2 = Math.min(f1, .5f);
		float f3 = Math.max(f1 - .5f, 0);
		drawQuads();
		addVertexUV(mid, mid1, 0, 0, 1, mid + (f2 * width), y + (f3 * height), 0, f2 / 2, .75 + (f3 / 2), x1, y, 0, .25, .75, mid, y, 0, 0, .75);
		draw();
		if(percent > .25)
		{
			f1 = Math.min(percent - .25f, .25f) / .25f;
			f2 = Math.max(f1 - .5f, 0);
			f3 = Math.min(f1, .5f);
			drawQuads();
			addVertexUV(mid, mid1, 0, 0, 1, x1 - (f2 * width), mid1 + (f3 * height), 0, f3 / 2, .75 + (f2 / 2), x1, y1, 0, .25, .75, x1, mid1, 0, 0, .75);
			draw();
		}
		if(percent > .5)
		{
			f1 = Math.min(percent - .5f, .25f) / .25f;
			f2 = Math.min(f1, .5f);
			f3 = Math.max(f1 - .5f, 0);
			drawQuads();
			addVertexUV(mid, mid1, 0, 0, 1, mid - (f2 * width), y1 - (f3 * height), 0, f2 / 2, .75 + (f3 / 2), x, y1, 0, .25, .75, mid, y1, 0, 0, .75);
			draw();
		}
		if(percent > .75f)
		{
			f1 = Math.min(percent - .75f, .25f) / .25f;
			f2 = Math.min(f1, .5f);
			f3 = Math.max(f1 - .5f, 0);
			glEnable(GL_CULL_FACE);
			drawQuads();
			addVertexUV(mid, mid1, 0, 0, 1, x + (f3 * width), mid1 - (f2 * height), 0, f2 / 2f, .75 + (f3 / 2f), x, y, 0, .25, .75, x, mid1, 0, 0, .75);
			draw();
			glDisable(GL_CULL_FACE);
		}
	}

}
