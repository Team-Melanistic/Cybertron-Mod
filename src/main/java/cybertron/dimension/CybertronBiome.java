package cybertron.dimension;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class CybertronBiome extends BiomeGenBase {
	public CybertronBiome() {
		super(50);
		this.fillerBlock = Blocks.stone;
		this.topBlock = Blocks.grass;
		this.setBiomeName("Cybertron");
		this.spawnableMonsterList.clear();
		this.waterColorMultiplier = 0xE42D17;
	}
}
