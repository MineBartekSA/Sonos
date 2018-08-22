package com.minebarteksa.sonos.blocks;

import com.minebarteksa.sonos.Sonos;
import com.minebarteksa.orion.blocks.TileEntityBlockBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.block.material.Material;
import com.minebarteksa.sonos.tileentity.ActivatorEntity;

public class SonoActivator extends TileEntityBlockBase<ActivatorEntity>
{
  public SonoActivator(String name)
  {
    super(Material.IRON, name, Sonos.ModID);
    //Block Properties
  }

  @Override
  public Class<ActivatorEntity> getTileEntityClass() { return ActivatorEntity.class; }

  @Override
  public ActivatorEntity createTileEntity(World world, IBlockState state) { return new ActivatorEntity(); }
}
