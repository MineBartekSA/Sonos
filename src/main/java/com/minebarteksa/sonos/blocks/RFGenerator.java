package com.minebarteksa.sonos.blocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import com.minebarteksa.sonos.tileentity.GeneratorEntity;
import net.minecraft.block.material.Material;

public class RFGenerator extends TileEntityBlockBase<GeneratorEntity>
{
  public RFGenerator(String name)
  {
    super(Material.IRON, name);
  }

  @Override
  public Class<GeneratorEntity> getTileEntityClass() { return GeneratorEntity.class; }

  @Override
  public GeneratorEntity createTileEntity(World world, IBlockState state)
  {
    return new GeneratorEntity();
  }

  @Override
  public RFGenerator setCreativeTab(CreativeTabs tab)
  {
    super.setCreativeTab(tab);
    return this;
  }
}
