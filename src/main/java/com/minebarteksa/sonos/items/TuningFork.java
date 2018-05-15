package com.minebarteksa.sonos.items;

import net.minecraft.creativetab.CreativeTabs;

public class TuningFork extends ItemBase
{
  public TuningFork(String name)
  {
    super(name);
  }

  @Override
  public TuningFork setCreativeTab(CreativeTabs tab)
  {
    super.setCreativeTab(tab);
    return this;
  }
}
