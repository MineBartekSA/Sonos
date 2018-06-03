package com.minebarteksa.sonos.blocks;

import net.minecraft.entity.player.EntityPlayerMP;
import com.minebarteksa.sonos.criteria.SonosCriteria;
import mcjty.theoneprobe.api.IProbeInfoAccessor;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import com.minebarteksa.sonos.items.SonosItems;
import net.minecraft.item.Item;
import net.minecraft.entity.Entity;
import com.minebarteksa.sonos.tileentity.SonoOreEntity;
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
import net.minecraftforge.fml.common.Optional;

@Optional.Interface(modid="theoneprobe", iface="mcjty.theoneprobe.api.IProbeInfoAccessor")
public class SonoOre extends TileEntityBlockBase<SonoOreEntity> implements IProbeInfoAccessor
{
  public static final PropertyInteger LitAF = PropertyInteger.create("lit_af", 0, 12);

  public SonoOre(String name, Notes note)
  {
    super(Material.ROCK, name);
    this.setHardness(3f);
		this.setResistance(5f);
    this.setTickRandomly(true);
    this.setDefaultState(this.getBlockState().getBaseState().withProperty(LitAF, 0));
  }

  @Override
  public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
  {
    if(!worldIn.isRemote)
    {
      SonoOreEntity soe = (SonoOreEntity)worldIn.getTileEntity(pos);
      int rand = 1;
      do
        rand = worldIn.rand.nextInt(12);
      while (rand >= 12);
      soe.note = rand;
    }
  }

  @Override
  public int tickRate(World worldIn) { return 30; }

  public void activate(World world, BlockPos pos)
  {
    if(!world.isRemote)
    {
      SonoOreEntity soe = (SonoOreEntity)world.getTileEntity(pos);
      if(world.getBlockState(pos).getValue(LitAF) != soe.getNote() + 1)
      {
        world.setBlockState(pos, this.getBlockState().getBaseState().withProperty(LitAF, 1 + soe.getNote()));
        soe.StartPlaying();
      }
    }
  }

  @Override
  public int getLightValue(IBlockState state)
  {
    if(state.getValue(LitAF) != 0)
      return 5;
    else
      return 0;
  }

  @Override
  public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
  {
    if(state.getValue(LitAF) != 0 && !worldIn.isRemote)
    {
      SonoOreEntity soe = (SonoOreEntity)worldIn.getTileEntity(pos);
      Sonos.log.info("SonoOre updateTick! OnSide: " + (worldIn.isRemote ? "Client" : "Server"));
      worldIn.setBlockState(pos, this.getDefaultState());
      soe.StopPlaying();
    }
  }

  @Override
  public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
  {
    if(!worldIn.isRemote)
      SonosCriteria.MSON.trigger((EntityPlayerMP)player, state);
  }

  @Override
  @Optional.Method(modid = "theoneprobe")
  public void addProbeInfo(ProbeMode arg0, IProbeInfo probeInfo, EntityPlayer arg2, World worldIn, IBlockState arg4, IProbeHitData data)
  {
    SonoOreEntity soe = (SonoOreEntity)worldIn.getTileEntity(data.getPos());
    probeInfo.text("Note: " + Notes.getNote(soe.getNote()));
    probeInfo.text("IsPlaying: " + soe.isPlaying());
  }

  @Override
  public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn)
  {
    this.activate(worldIn, pos);
    super.onBlockClicked(worldIn, pos, playerIn);
  }

  @Override
  public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
  {
    this.activate(worldIn, pos);
    super.onEntityWalk(worldIn, pos, entityIn);
  }

  @Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, LitAF);
	}

  @Override
  public int getMetaFromState(IBlockState state)
  {
      return state.getValue(LitAF);
  }

  @Override
  public IBlockState getStateFromMeta(int meta)
  {
      return getBlockState().getBaseState().withProperty(LitAF, meta);
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
  public Item getItemDropped(IBlockState state, Random rand, int fortune)
  {
    switch(Notes.getNote(state.getValue(LitAF) - 1).toString())
    {
      case "C":
        return SonosItems.sono_c;
      case "CSharp":
        return SonosItems.sono_cs;
      case "D":
        return SonosItems.sono_d;
      case "DSharp":
        return SonosItems.sono_ds;
      case "E":
        return SonosItems.sono_e;
      case "F":
        return SonosItems.sono_f;
      case "FSharp":
        return SonosItems.sono_fs;
      case "G":
        return SonosItems.sono_g;
      case "GSharp":
        return SonosItems.sono_gs;
      case "A":
        return SonosItems.sono_a;
      case "ASharp":
        return SonosItems.sono_as;
      case "B":
        return SonosItems.sono_b;
      default:
        return SonosItems.sono_c;
    }
  }
}
