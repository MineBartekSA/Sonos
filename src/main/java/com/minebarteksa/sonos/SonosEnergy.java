package com.minebarteksa.sonos;

import net.minecraftforge.energy.EnergyStorage;
import net.minecraft.nbt.NBTTagCompound;

public class SonosEnergy extends EnergyStorage
{
  protected int energy;
  protected int capacity;
  protected int maxReceive;
  protected int maxExtract;

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
    nbt.setInteger("capacity", this.capacity);
    nbt.setInteger("receive", this.maxReceive);
    nbt.setInteger("extract", this.maxExtract);
    nbt.setInteger("energy", this.energy);
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
