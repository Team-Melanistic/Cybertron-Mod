package cybertron.dimension;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import cybertron.CybertronMod;

public class CybertronWorldProvider extends WorldProvider {
	public String getDimensionName() {
		return "Cybertron";
	}
	
	public void registerWorldChunkManager() {        
		this.worldChunkMgr = new WorldChunkManagerHell(CybertronMod.cybertron_biome, 0.8F);        
		this.dimensionId = CybertronMod.CYBERTRON_DIMENSION_ID; 
	} 
	
	public IChunkProvider createChunkGenerator() { 
		return new ChunkProviderCybertron(worldObj, worldObj.getSeed(), true); 
	}
	
	public boolean canRespawnHere() {
		return new WorldProviderHell().canRespawnHere();
	}
}
