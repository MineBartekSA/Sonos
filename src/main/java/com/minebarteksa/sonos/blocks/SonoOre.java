package com.minebarteksa.sonos.blocks;

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

public class SonoOre extends TileEntityBlockBase<SonoOreEntity>
{
  public static final PropertyInteger LitAF = PropertyInteger.create("lit_af", 0, 12);

  public SonoOre(String name, Notes note)
  {
    super(Material.ROCK, name);
    setHardness(2f);
		setResistance(5f);
    setTickRandomly(true);
    setDefaultState(this.getBlockState().getBaseState().withProperty(LitAF, 0));
  }

  @Override
  public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
  {
    SonoOreEntity soe = (SonoOreEntity)worldIn.getTileEntity(pos);
    if(!worldIn.isRemote)
    {
      int rand = 1;
      do
        rand = worldIn.rand.nextInt(12);
      while (rand == 0 || rand > 12);
      soe.note = rand;
    }
  }

  @Override
  public int tickRate(World worldIn)
  {
    return 30;
  }

  public void activate(World world, BlockPos pos)
  {
    SonoOreEntity soe = (SonoOreEntity)world.getTileEntity(pos);
    if(world.getBlockState(pos).getValue(LitAF) != soe.getNote())
    {
      world.setBlockState(pos, this.getBlockState().getBaseState().withProperty(LitAF, soe.getNote()));
      if(world.isRemote)
      {
        //LoopSound ls = new LoopSound(SoundEvents.getSound(Notes.getNote(soe.getNote()), "hum"), SoundCategory.BLOCKS, pos);
        //SoundEvents.playingSounds.put(pos, ls);
        //Minecraft.getMinecraft().getSoundHandler().playSound(ls);
      }

    }
  }

  @Override
  public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
  {
    //SonoOreEntity soe = (SonoOreEntity)worldIn.getTileEntity(pos);
    Sonos.log.info("SonoOre updateTick!");
    if(state.getValue(LitAF) != 0)
    {
      worldIn.setBlockState(pos, this.getDefaultState());
      if(worldIn.isRemote)
      {
        //soe.lopped.stop
        //Minecraft.getMinecraft().getSoundHandler().stopSound(SoundEvents.playingSounds.get(pos));
      }
    }
    else if(state.getValue(LitAF) == 0 && worldIn.isRemote)
    {
      //soe.lopped.stop();
      //Minecraft.getMinecraft().getSoundHandler().stopSound(SoundEvents.playingSounds.get(pos));
    }
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
    switch(Notes.getNote(state.getValue(LitAF)).toString())
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
