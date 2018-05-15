package com.minebarteksa.sonos;

import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.state.IBlockState;
import com.minebarteksa.sonos.blocks.SonosBlocks;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.World;
import java.util.Random;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGen implements IWorldGenerator
{
  @Override
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
  {
    if(world.provider.getDimension() == 0)
      generateOre(SonosBlocks.SO.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 1, 16, 1, 6 + random.nextInt(5));
  }

  private void generateOre(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances) {
		int deltaY = maxY - minY;

		for (int i = 0; i < chances; i++) {
			BlockPos pos = new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));

			WorldGenMinable generator = new WorldGenMinable(ore, size);
			generator.generate(world, random, pos);
		}
	}
}
