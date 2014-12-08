package com.melanistic.cybertron.client.gui;

import org.lwjgl.opengl.GL11;

import com.melanistic.cybertron.common.entity.EntityDeathLiving;
import com.melanistic.cybertron.lib.CyberTecHandler;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class GuiLooting extends GuiContainer
{

	private ResourceLocation res = new ResourceLocation("cybertron", "textures/gui/GuiLooting.png");
	EntityDeathLiving live;
	
	public GuiLooting(EntityPlayer pl, World w, int x, int y, int z) 
	{
		super(new ContainerLooting(pl, w, x, y, z));
		live = (EntityDeathLiving) w.getEntityByID(x);
		xSize = 176;
		ySize = 222;
	}
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(res);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) 
	{
		fontRendererObj.drawString(live.getInventoryName(), 8, 6, 0x000000);
		fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, 130, 0x000000);
	}
	
	public static class ContainerLooting extends Container
	{
		InventoryPlayer inv;
		EntityDeathLiving death;
		
		public ContainerLooting(EntityPlayer pl, World w, int x, int y, int z) 
		{
			inv = pl.inventory;
			death = (EntityDeathLiving) w.getEntityByID(x);
			
			for (int l = 0; l < 6; ++l)
			{
				for (int i1 = 0; i1 < 3; ++i1)
				{
					this.addSlotToContainer(new SlotLevel(death, i1 + l * 3, 62 + i1 * 18, 18 + l * 18));
				}
			}
			
			for (int l = 0; l < 3; ++l)
			{
				for (int i1 = 0; i1 < 9; ++i1)
				{
					this.addSlotToContainer(new Slot(inv, i1 + l * 9 + 9, 8 + i1 * 18, 140 + l * 18));
				}
			}
			
			for (int l = 0; l < 9; ++l)
			{
				this.addSlotToContainer(new Slot(inv, l, 8 + l * 18, 198));
				
			}
		}
		
		@Override
		public ItemStack transferStackInSlot(EntityPlayer pl, int p_82846_2_)
		{
			
			return null;
		}
		
		@Override
		public boolean canInteractWith(EntityPlayer pl)
		{
			return true;
		}
		
	}
	
	private static class SlotLevel extends Slot
	{

		public SlotLevel(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_)
		{
			super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
		}
		
		@Override
		public boolean canTakeStack(EntityPlayer pl) 
		{
//			ItemStack it = this.getStack();
//			if(it!=null)
//			{
//				return CyberTecHandler.canPlayerUseItem(it, pl);
//			}
			return true;
		}
		
		@Override
		public boolean isItemValid(ItemStack it) 
		{
			return inventory.isItemValidForSlot(this.getSlotIndex(), it);
		}
		
		
	}
}
