package com.minebarteksa.sonos.blocks;

import com.minebarteksa.sonos.gui.SonosGUIHandler;
import com.minebarteksa.sonos.Sonos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import com.minebarteksa.sonos.tileentity.ResonatorEntity;

public class Resonator extends TileEntityBlockBase<ResonatorEntity>
{
  public Resonator(String name)
  {
    super(Material.ROCK, name);
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
  {
    if(!worldIn.isRemote)
    {
      if(!playerIn.isSneaking())
        playerIn.openGui(Sonos.instance, SonosGUIHandler.ResonatorID, worldIn, pos.getX(), pos.getY(), pos.getZ());
    }
    return true;
  }

  @Override
  public Class<ResonatorEntity> getTileEntityClass()
  {
    return ResonatorEntity.class;
  }

  @Override
  public ResonatorEntity createTileEntity(World world, IBlockState state)
  {
    return new ResonatorEntity();
  }

  @Override
  public Resonator setCreativeTab(CreativeTabs tab)
  {
    super.setCreativeTab(tab);
    return this;
  }
}
