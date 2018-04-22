package com.minebarteksa.sonos.blocks;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import com.minebarteksa.sonos.tileEntitys.SonoOreEntity;
import net.minecraft.util.text.TextComponentString;
import com.minebarteksa.sonos.Sonos;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import com.minebarteksa.sonos.sound.SoundEvents.Notes;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class SonoOre extends TileEntityBlockBase<SonoOreEntity>
{
  public static final PropertyInteger LitAF = PropertyInteger.create("lit_af", 0, 12);
  private Notes note;

  public SonoOre(String name, Notes note)
  {
    super(Material.ROCK, name);
    this.note = note;
    setHardness(2f);
		setResistance(5f);
    setDefaultState(this.getBlockState().getBaseState().withProperty(LitAF, 0));
  }

  @Override
  public int tickRate(World worldIn)
  {
    return 30;
  }

  public void activate(World world, BlockPos pos)
  {
    SonoOreEntity soe = (SonoOreEntity)world.getTileEntity(pos);
    world.setBlockState(pos, this.getBlockState().getBaseState().withProperty(LitAF, soe.note));
  }

  @Override
  public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
  {
    Sonos.log.info("SonoOre updateTick!");
    worldIn.setBlockState(pos, this.getDefaultState());
  }

  @Override
  public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn)
  {
    this.activate(worldIn, pos);
    super.onBlockClicked(worldIn, pos, playerIn);
  }

  @Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, LitAF);
	}

  @Override
  public int getMetaFromState(IBlockState state)
  {
    if(state == getDefaultState())
      return 0;
    else
      return 1;
  }

  @Override
  public IBlockState getStateFromMeta(int meta)
  {
    if(meta == 0)
      return getDefaultState();
    else
      return getBlockState().getBaseState().withProperty(LitAF, note.Number());
  }

  @Override
  public Class<SonoOreEntity> getTileEntityClass()
  {
    return SonoOreEntity.class;
  }

  @Override
  public SonoOreEntity createTileEntity(World world, IBlockState state)
  {
    return new SonoOreEntity();
  }

  @Override
  public SonoOre setCreativeTab(CreativeTabs tab)
  {
    super.setCreativeTab(tab);
    return this;
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
  {
    SonoOreEntity soe = (SonoOreEntity)worldIn.getTileEntity(pos);
    Notes n = Notes.getNote(soe.getNote());
    playerIn.sendMessage(new TextComponentString("Note: " + n));
    return true;
  }
}
