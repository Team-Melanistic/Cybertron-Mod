package cybertron.blocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cybertron.CybertronMod;

public class BlockEvent extends CybertronMod{
	@SubscribeEvent
	public void harvestEvent(HarvestDropsEvent e){
		Block block = e.block;
		int rand = (int)(Math.random() * ((4) + 1));
		int luck = 1 + (int)(Math.random() * ((100 - 1) + 1));
		if(block.equals(Blocks.diamond_ore)){
			if(luck <= 10){
				for(int i = 1; i < rand; i++) e.drops.add(new ItemStack(CybertronMod.cybercrystal));
			}
		}
		if(block.equals(CybertronMod.cyberore)){
			for(int i = 1; i < rand; i++) e.drops.add(new ItemStack(CybertronMod.cybercrystal));
		}
	}
}
