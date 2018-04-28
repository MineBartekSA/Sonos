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
