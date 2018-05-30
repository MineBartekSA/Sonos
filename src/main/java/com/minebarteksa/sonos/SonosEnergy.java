package com.minebarteksa.sonos;

import net.minecraftforge.energy.EnergyStorage;
import net.minecraft.nbt.NBTTagCompound;

public class SonosEnergy extends EnergyStorage
{
  public SonosEnergy(int capacity)
  {
    this(capacity, capacity, capacity, 0);
  }

  public SonosEnergy(int capacity, int maxTransfer)
  {
    this(capacity, maxTransfer, maxTransfer, 0);
  }

  public SonosEnergy(int capacity, int maxReceive, int maxExtract)
  {
    this(capacity, maxReceive, maxExtract, 0);
  }

  public SonosEnergy(int capacity, int maxReceive, int maxExtract, int energy)
  {
    super(capacity, maxReceive, maxExtract, energy);
  }

  public int insertEnergy(int amount, boolean simulate)
  {
    if(simulate)
    {
      if((energy + amount) - capacity > 0)
        return ((energy + amount) - capacity);
      else
        return 0;
    }
    else
    {
      if((energy + amount) - capacity > 0)
      {
        int over = ((energy + amount) - capacity);
        energy += amount - over;
        return over;
      }
      else
      {
        energy += amount;
        return 0;
      }
    }
  }

  public NBTTagCompound serNBT()
  {
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.setInteger("capacity", capacity);
    nbt.setInteger("receive", maxReceive);
    nbt.setInteger("extract", maxExtract);
    nbt.setInteger("energy", energy);
    return nbt;
  }

  public void deNBT(NBTTagCompound nbt)
  {
    this.capacity = nbt.getInteger("capacity");
    this.maxReceive = nbt.getInteger("receive");
    this.maxExtract = nbt.getInteger("extract");
    this.energy = nbt.getInteger("energy");
  }
}
