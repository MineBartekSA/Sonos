package com.minebarteksa.sonos.tileEntitys;

import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class SonoOreEntity extends TileEntity
{
  public int note = 0;

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound)
  {
    do
      note = new Random().nextInt(12);
    while(note == 0 || note > 12);
    if(!compound.hasKey("note") || note != 0)
      compound.setInteger("note", note);
    return super.writeToNBT(compound);
  }

  @Override
  public void readFromNBT(NBTTagCompound compound)
  {
    note = compound.getInteger("note");
    super.readFromNBT(compound);
  }

  public int getNote()
  {
    return note;
  }
}
