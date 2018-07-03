package com.minebarteksa.sonos.tileentity;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class ActivatorEntity extends TileEntity implements ITickable
{
  @Override
  public void update()
  {
    //
  }

  void checkForMultiBlock()
  {
    //
  }

  @Override
  public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate)
  { return (oldState.getBlock() != newSate.getBlock()); }
}
