package com.minebarteksa.sonos.blocks;

import javax.annotation.Nullable;
import net.minecraft.world.World;
import net.minecraft.world.IBlockAccess;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;

public abstract class TitleEntityBlockBase<TE extends TileEntity> extends BlockBase
{
  public TitleEntityBlockBase(Material mat, String name)
  {
    super(mat, name);
  }

  public abstract Class<TE> getTitleEntityClass();

  public TE getTileEntity(IBlockAccess world, BlockPos pos)
  {
    return (TE)world.getTileEntity(pos);
  }

  @Override
  public boolean hasTileEntity(IBlockState state)
  {
    return true;
  }

  @Nullable
  @Override
  public abstract TE createTileEntity(World world, IBlockState state);
}
