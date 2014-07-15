package cybertron.blocks;

import net.minecraft.block.BlockCompressed;
import net.minecraft.block.material.MapColor;
import net.minecraft.world.World;
import cybertron.CybertronMod;

public class CybertronPortalFrame extends BlockCompressed {

	public CybertronPortalFrame() {
		super(MapColor.ironColor);
		this.setHardness(5.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypeMetal);
		this.setBlockName("cybertronPortalFrame");
		this.setBlockTextureName("iron_block");
	}

	public void onBlockAdded(World world, int x, int y, int z)
    {
        if (!CybertronMod.cybertron_portal.tryToCreatePortal(world, x, y, z))
        {
            if (!World.doesBlockHaveSolidTopSurface(world, x, y - 1, z))
            {
                
            }
            else
            {
                world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world) + world.rand.nextInt(10));
            }
        }
    }
}
