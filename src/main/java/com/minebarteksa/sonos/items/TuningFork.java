package com.minebarteksa.sonos.items;

import com.minebarteksa.sonos.Sonos;
import com.minebarteksa.orion.items.ItemBase;
import net.minecraft.creativetab.CreativeTabs;

public class TuningFork extends ItemBase
{
  public TuningFork()
  {
    super("fork", Sonos.ModID);
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
