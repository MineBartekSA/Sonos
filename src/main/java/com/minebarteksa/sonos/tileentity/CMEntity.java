package com.minebarteksa.sonos.tileentity;

import com.minebarteksa.sonos.Sonos;
import com.minebarteksa.sonos.sound.SoundEvents.Chords;
import com.minebarteksa.sonos.sound.SoundEvents.Notes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
//import com.minebarteksa.sonos.SonosEnergy;
//import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.network.NetworkManager;
import net.minecraft.util.ITickable;
import net.minecraft.tileentity.TileEntity;

public class CMEntity extends TileEntity implements ITickable
{
  //protected SonosEnergy energy = new SonosEnergy(10000, 500);
  private ItemStackHandler itemHand = new ItemStackHandler(2);

  @Override
  public void update()
  {
    if(itemHand.getStackInSlot(0) != ItemStack.EMPTY && itemHand.getStackInSlot(0).hasTagCompound())
    {
      NBTTagCompound sonoTag = itemHand.getStackInSlot(0).getTagCompound();
      Sonos.log.info("Note: " + Notes.getNote(sonoTag.getInteger("note")) + " Quality: " + Chords.getChord(sonoTag.getInteger("quality")) + " Sound type: " + sonoTag.getString("soundType"));
    }
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound)
  {
    //compound.setTag("energystorage", energy.serNBT());
    compound.setTag("items", itemHand.serializeNBT());
    return super.writeToNBT(compound);
  }

  @Override
  public void readFromNBT(NBTTagCompound compound)
  {
    //energy.deNBT(compound.getCompoundTag("energystorage"));
    itemHand.deserializeNBT(compound.getCompoundTag("items"));
    super.readFromNBT(compound);
  }

  @Override
  public boolean hasCapability(Capability<?> capability, EnumFacing facing)
  {
    //if(capability == CapabilityEnergy.ENERGY)
    //  return true;
    if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
      return true;
    return super.hasCapability(capability, facing);
  }

  @Override
  public <T> T getCapability(Capability<T> capability, EnumFacing facing)
  {
    //if(capability == CapabilityEnergy.ENERGY)
    //  return (T)energy;
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
    NBTTagCompound blockNBT = new NBTTagCompound();
    writeToNBT(blockNBT);
    return blockNBT;
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
