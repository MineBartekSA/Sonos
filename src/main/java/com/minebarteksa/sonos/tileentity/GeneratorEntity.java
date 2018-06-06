package com.minebarteksa.sonos.tileentity;

import net.minecraft.init.Items;
import com.minebarteksa.sonos.packets.ProgressUpdatePacket;
import com.minebarteksa.sonos.packets.SonosPacketHandler;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import com.minebarteksa.sonos.SonosEnergy;
import net.minecraft.util.ITickable;
import net.minecraft.tileentity.TileEntity;

public class GeneratorEntity extends TileEntity implements ITickable
{
  protected SonosEnergy energy = new SonosEnergy(50000, 0, 500);
  private ItemStackHandler itemHand = new ItemStackHandler(1);
  private int BurnTime;
  private int bTime;
  private boolean canGenerate = true;

  @Override
  public void update()
  {
    if(!world.isRemote)
    {
      if(itemHand.getStackInSlot(0) != ItemStack.EMPTY && bTime == 0 && canGenerate)
      {
        if(getBurnTime(itemHand.getStackInSlot(0)) != 0)
        {
          BurnTime = getBurnTime(itemHand.getStackInSlot(0));
          if(itemHand.getStackInSlot(0).getItem() == Items.LAVA_BUCKET)
          {
            itemHand.extractItem(0, 1, false);
            itemHand.insertItem(0, new ItemStack(Items.BUCKET), false);
          }
          else
            itemHand.extractItem(0, 1, false);
          bTime++;
          energy.insertEnergy(20, false);
          world.scheduleBlockUpdate(getPos(), getBlockType(), 0, 1);
          this.markDirty();
          this.sendGuiInfo();
        }
      }
      else if(bTime != BurnTime && canGenerate)
      {
        bTime++;
        if(energy.insertEnergy(20, true) > 0)
          canGenerate = false;
        energy.insertEnergy(20, false);
        world.scheduleBlockUpdate(getPos(), getBlockType(), 0, 1);
        this.markDirty();
        this.sendGuiInfo();
      }
      else if(bTime == BurnTime)
      {
        BurnTime = 0;
        bTime = 0;
        world.scheduleBlockUpdate(getPos(), getBlockType(), 0, 1);
        this.markDirty();
        this.sendGuiInfo();
      }
      else if(!canGenerate && energy.getEnergyStored() <= 9500)
      {
        canGenerate = true;
      }
      sendEnergy();
    }
  }

  int getBurnTime(ItemStack is)
  {
    return TileEntityFurnace.getItemBurnTime(is);
  }

  public int getBTime()
  {
    return bTime;
  }

  public int getPercentage(int barWidth)
  {
    if(BurnTime == 0)
      return 0;
    int percentageOfProgress = (bTime * 100) / BurnTime;
    return barWidth - ((percentageOfProgress * barWidth) / 100);
  }

  void sendEnergy()
  {
    if(energy.getEnergyStored() == 0)
      return;
    for(EnumFacing f : EnumFacing.values())
    {
      BlockPos pos = new BlockPos(this.pos.getX() + f.getFrontOffsetX(), this.pos.getY() + f.getFrontOffsetY(), this.pos.getZ() + f.getFrontOffsetZ());
      final TileEntity tile = this.world.getTileEntity(pos);

      if(tile != null && tile.hasCapability(CapabilityEnergy.ENERGY, f.getOpposite()))
      {
        IEnergyStorage es = tile.getCapability(CapabilityEnergy.ENERGY, f.getOpposite());
        if(es.canReceive() && es.getEnergyStored() < es.getMaxEnergyStored())
        {
          if(es.receiveEnergy(500, true) > 0)
          {
            es.receiveEnergy(energy.extractEnergy(500, false), false);
            world.scheduleBlockUpdate(getPos(), getBlockType(), 0, 1);
            this.markDirty();
            this.sendGuiInfo();
          }
        }
      }
    }
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound)
  {
    compound.setTag("energystorage", energy.serNBT());
    compound.setTag("items", itemHand.serializeNBT());
    compound.setInteger("bTiem", bTime);
    compound.setInteger("BurnTime", BurnTime);
    return super.writeToNBT(compound);
  }

  @Override
  public void readFromNBT(NBTTagCompound compound)
  {
    energy.deNBT(compound.getCompoundTag("energystorage"));
    itemHand.deserializeNBT(compound.getCompoundTag("items"));
    BurnTime = compound.getInteger("BurnTime");
    bTime = compound.getInteger("bTime");
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

  void sendGuiInfo() { SonosPacketHandler.INSTANCE.sendToAll(new ProgressUpdatePacket(bTime, BurnTime, 1500, energy.getEnergyStored(), pos)); }

  public void updateGuiInfo(int BurnTime, int bTime, int eCap, int eStor)
  {
    this.BurnTime = BurnTime;
    this.bTime = bTime;
    this.energy = new SonosEnergy(eCap, 0, 500, eStor);
  }
}
