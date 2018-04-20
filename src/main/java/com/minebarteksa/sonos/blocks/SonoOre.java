package com.minebarteksa.sonos.blocks;

import com.minebarteksa.sonos.sound.SoundEvents.Notes;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class SonoOre extends BlockBase
{
  private Notes note;

  public SonoOre(String name, Notes note)
  {
    super(Material.ROCK, name);
    this.note = note;
  }

  public void activate()
  {
	  if(this == SonosBlocks.SO)
		  setDefaultState(SonosBlocks.LitSO.getBlockState().getBaseState().withProperty(LitSonoOre.LitAF, note.Number()));
  }

  @Override
  public SonoOre setCreativeTab(CreativeTabs tab)
  {
    super.setCreativeTab(tab);
    return this;
  }
}
