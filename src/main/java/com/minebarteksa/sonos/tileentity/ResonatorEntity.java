package com.minebarteksa.sonos.tileentity;

import com.minebarteksa.sonos.Sonos;
import com.minebarteksa.sonos.items.SonosItems;
import com.minebarteksa.sonos.items.Sono;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraft.network.NetworkManager;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import com.minebarteksa.sonos.SonosEnergy;
import com.minebarteksa.sonos.items.SonoPrima;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraft.tileentity.TileEntity;

public class ResonatorEntity extends TileEntity implements ITickable
{
  private SonosEnergy energy = new SonosEnergy(1500, 100);
  private ItemStackHandler itemHand = new ItemStackHandler(2);
  public int processTime = 0;
  public static final int totalProcessTime = 100;

  @Override
  public void update()
  {
    if(itemHand.getStackInSlot(0) != ItemStack.EMPTY)
    {
      if(itemHand.getStackInSlot(1).getCount() != 64 && energy.extractEnergy(10, true) == 10 && checkOut())
      {
        energy.extractEnergy(10, false);
        processTime++;
        if(processTime == totalProcessTime)
        {
          processTime = 0;
          ItemStack in = itemHand.extractItem(0, 1, false);
          itemHand.insertItem(1, new ItemStack(SonosItems.getSonoPrimaFormNote(((Sono)in.getItem()).note), 1), false);
        }
        this.markDirty();
      }
    }
    else if(processTime != 0)
      processTime = 0;
  }

  private boolean checkOut()
  {
    if(itemHand.getStackInSlot(1) != ItemStack.EMPTY && ((Sono)itemHand.getStackInSlot(0).getItem()).note == ((SonoPrima)itemHand.getStackInSlot(1).getItem()).note)
      return true;
    else if(itemHand.getStackInSlot(1) == ItemStack.EMPTY)
      return true;
    else
      return false;
  }

  public int getProcess() { return this.processTime; }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound)
  {
    Sonos.log.info("Energy stored get: " + energy.getEnergyStored());
    Sonos.log.info("Energy tag: " + energy.serNBT().toString());
    compound.setTag("energystorage", energy.serNBT());
    compound.setTag("items", itemHand.serializeNBT());
    compound.setInteger("processTime", processTime);
    Sonos.log.info("Full block tag: " + compound.toString());
    return super.writeToNBT(compound);
  }

  @Override
  public void readFromNBT(NBTTagCompound compound)
  {
    Sonos.log.info("Reading Tag: " + compound.toString());
    energy.deNBT(compound.getCompoundTag("energystorage"));
    itemHand.deserializeNBT(compound.getCompoundTag("items"));
    processTime = compound.getInteger("processTime");
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
