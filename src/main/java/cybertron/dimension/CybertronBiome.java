package cybertron.dimension;

import cybertron.CybertronMod;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class CybertronBiome extends BiomeGenBase {
	public CybertronBiome() {
		super(50);
		this.setBiomeName("Cybertron");
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
	}
}
