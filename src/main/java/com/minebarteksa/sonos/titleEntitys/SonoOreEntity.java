package com.minebarteksa.sonos.titleEntitys;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class SonoOreEntity extends TileEntity
{
  private String note;

  public SonoOreEntity(String note)
  {
    this.note = note;
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound)
  {
    compound.setString("note", note);
    return super.writeToNBT(compound);
  }

  @Override
  public void readFromNBT(NBTTagCompound compound)
  {
    note = compound.getString("note");
    super.readFromNBT(compound);
  }

  public String getNote()
  {
    return note;
  }
}
