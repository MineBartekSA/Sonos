package com.minebarteksa.sonos.tileEntitys;

import net.minecraft.network.NetworkManager;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import com.minebarteksa.sonos.SonosEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraft.tileentity.TileEntity;

public class ResonatorEntity extends TileEntity
{
  private EnergyStorage energy = new SonosEnergy(1500, 500);
  private ItemStackHandler itemHand = new ItemStackHandler(2);

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound)
  {
    compound.setTag("energystorage", ((SonosEnergy)energy).serNBT());
    compound.setTag("items", itemHand.serializeNBT());
    return super.writeToNBT(compound);
  }

  @Override
  public void readFromNBT(NBTTagCompound compound)
  {
    ((SonosEnergy)energy).deNBT(compound.getCompoundTag("energystorage"));
    itemHand.deserializeNBT(compound.getCompoundTag("items"));
    super.readFromNBT(compound);
  }

  @Override
  public boolean hasCapability(Capability<?> capability, EnumFacing facing)
  {
    if(capability == CapabilityEnergy.ENERGY)
      return true;
    if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
      return true;
    return super.hasCapability(capability, facing);
  }

  @Override
  public <T> T getCapability(Capability<T> capability, EnumFacing facing)
  {
    if(capability == CapabilityEnergy.ENERGY)
      return (T)energy;
    if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
      return (T)itemHand;
    return super.getCapability(capability, facing);
  }

  @Override
  public SPacketUpdateTileEntity getUpdatePacket()
  {
    NBTTagCompound nbtTagCompound = new NBTTagCompound();
		writeToNBT(nbtTagCompound);
		int metadata = 0;
    return new SPacketUpdateTileEntity(this.pos, metadata, nbtTagCompound);
  }

  @Override
  public NBTTagCompound getUpdateTag()
  {
    NBTTagCompound nbtTagCompound = new NBTTagCompound();
    writeToNBT(nbtTagCompound);
    return nbtTagCompound;
  }

  @Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
  {
		readFromNBT(pkt.getNbtCompound());
  }

  @Override
  public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate)
  {
    return (oldState.getBlock() != newSate.getBlock());
  }
}
