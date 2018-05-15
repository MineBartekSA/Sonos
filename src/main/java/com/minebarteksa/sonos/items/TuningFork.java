package com.minebarteksa.sonos.items;

import net.minecraft.creativetab.CreativeTabs;

public class TuningFork extends ItemBase
{
  public TuningFork(String name)
  {
    super(name);
    this.setMaxStackSize(1);
    this.setMaxDamage(0);
  }

  @Override
  public TuningFork setCreativeTab(CreativeTabs tab)
  {
    super.setCreativeTab(tab);
    return this;
  }
}
