package com.minebarteksa.sonos.blocks;

import com.minebarteksa.orion.blocks.TileEntityBlockBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import com.minebarteksa.sonos.gui.SonosGUIHandler;
import com.minebarteksa.sonos.Sonos;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.block.material.Material;
import com.minebarteksa.sonos.tileentity.CMEntity;

public class ChordManipulator extends TileEntityBlockBase<CMEntity>
{
  public ChordManipulator(String name)
  {
    super(Material.IRON, name, Sonos.ModID);
    this.setHardness(3f);
		this.setResistance(5f);
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
  {
    if(!worldIn.isRemote)
    {
      if(!playerIn.isSneaking())
        playerIn.openGui(Sonos.instance, SonosGUIHandler.CMID, worldIn, pos.getX(), pos.getY(), pos.getZ());
      else
        return false;
    }
    return true;
  }

  @Override
  public ChordManipulator setCreativeTab(CreativeTabs tab)
  {
    super.setCreativeTab(tab);
    return this;
  }

  @Override
  public Class<CMEntity> getTileEntityClass() { return CMEntity.class; }

  @Override
  public CMEntity createTileEntity(World world, IBlockState state)
  {
    return new CMEntity();
  }
}
