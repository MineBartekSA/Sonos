package com.minebarteksa.sonos.tileentity;

import com.minebarteksa.orion.OrionEnergy;
import com.minebarteksa.sonos.items.Sono;
import com.minebarteksa.sonos.items.SonoPrima;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import javax.annotation.Nullable;

public class ResonatorEntityNew extends TileEntity implements ITickable
{
    private OrionEnergy energy = new OrionEnergy(1500, 100);
    private ItemStackHandler iHand = new ItemStackHandler(2);
    private int progressPercentage;
    private int lastEnergy;

    private static final int processingTime = 100;
    private int processTime = 0;

    @Override
    public void update()
    {
        if(!world.isRemote)
        {
            if(iHand.getStackInSlot(0) != ItemStack.EMPTY)
            {
                if(checkOutput() && energy.extractEnergy(10, true) == 10)
                {
                    energy.extractEnergy(10, false);
                    processTime++;
                    if(processTime == processingTime)
                    {
                        processTime = 0;
                        ItemStack sono = iHand.extractItem(0, 1, false);
                        iHand.insertItem(1, SonoPrima.setNBTTags(new ItemStack(SonoPrima.getFromNote(((Sono)sono.getItem()).note))), false);
                    }
                    this.updateBlock();
                }
            }
            else if(processTime != 0)
            {
                processTime = 0;
                this.updateBlock();
            }
            if(lastEnergy != energy.getEnergyStored())
                this.updateBlock();
        }
    }

    private boolean checkOutput()
    {
        if(iHand.getStackInSlot(1).getCount() == 0)
            return true;
        else if(!(iHand.getStackInSlot(1).getItem() instanceof SonoPrima))
            return false;
        return (((Sono)iHand.getStackInSlot(0).getItem()).note == ((SonoPrima)iHand.getStackInSlot(1).getItem()).note);
    }

    private void updateBlock()
    {
        progressPercentage = (processTime * 100) / processingTime;
        lastEnergy = energy.getEnergyStored();
        world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 1);
        this.markDirty();
    }

    public int getProcessPercentage(int barWidth) { return (progressPercentage * barWidth) / 100; }

    public int getEnergyPercentage(int barWidth) { return (this.energy.getEnergyProcentage() * barWidth) / 100; }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound.setTag("energy", energy.serializeNBT());
        compound.setTag("items", iHand.serializeNBT());
        compound.setInteger("progress", progressPercentage);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        energy.deserializeNBT(compound.getCompoundTag("energy"));
        iHand.deserializeNBT(compound.getCompoundTag("items"));
        progressPercentage = compound.getInteger("progress");
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound getUpdateTag() { return this.writeToNBT(new NBTTagCompound()); }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() { return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag()); }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) { readFromNBT(pkt.getNbtCompound()); }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
    {
        if(capability == CapabilityEnergy.ENERGY)
            return true;
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return true;
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        if (capability == CapabilityEnergy.ENERGY)
            return (T)energy;
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return (T)iHand;
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) { return (oldState.getBlock() != newSate.getBlock()); }
}
