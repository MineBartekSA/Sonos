package com.minebarteksa.sonos.blocks;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import com.minebarteksa.sonos.gui.SonosGUIHandler;
import com.minebarteksa.sonos.Sonos;
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
    this.setHardness(3f);
		this.setResistance(5f);
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
  {
    if(!worldIn.isRemote)
    {
      if(!playerIn.isSneaking())
        playerIn.openGui(Sonos.instance, SonosGUIHandler.GeneratorID, worldIn, pos.getX(), pos.getY(), pos.getZ());
      else
        return false;
    }
    return true;
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
