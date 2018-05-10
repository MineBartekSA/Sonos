package com.minebarteksa.sonos.items;

import net.minecraft.creativetab.CreativeTabs;
import baubles.api.BaubleType;
import net.minecraft.item.ItemStack;
import baubles.api.IBauble;

public class TestRing extends ItemBase implements IBauble
{
  public TestRing()
  {
    super("testring");
    this.setMaxStackSize(1);
    this.setMaxDamage(0);
    }

  @Override
  public BaubleType getBaubleType(ItemStack arg0)
  {
    return BaubleType.RING;
  }

  @Override
  public TestRing setCreativeTab(CreativeTabs tab)
  {
    super.setCreativeTab(tab);
    return this;
  }
}
