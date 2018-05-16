package com.minebarteksa.sonos.gui.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class CMContainer extends Container
{
  @Override
  public boolean canInteractWith(EntityPlayer playerIn) { return true; }
}
