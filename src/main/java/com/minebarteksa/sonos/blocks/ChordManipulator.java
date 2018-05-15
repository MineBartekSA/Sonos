package com.minebarteksa.sonos.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.block.material.Material;
import com.minebarteksa.sonos.tileentity.CMEntity;

public class ChordManipulator extends TileEntityBlockBase<CMEntity>
{
  public ChordManipulator(String name)
  {
    super(Material.IRON, name);
  }

  @Override
  public Class<CMEntity> getTileEntityClass() { return CMEntity.class; }

  @Override
  public CMEntity createTileEntity(World world, IBlockState state)
  {
    return new CMEntity();
  }
}
