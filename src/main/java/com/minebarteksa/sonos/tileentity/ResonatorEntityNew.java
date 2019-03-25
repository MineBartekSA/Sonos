package com.minebarteksa.sonos.tileentity;

import com.minebarteksa.orion.OrionEnergy;
import com.minebarteksa.orion.tileentity.TileEntityMachine;
import com.minebarteksa.sonos.items.Sono;
import com.minebarteksa.sonos.items.SonoPrima;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.ItemStackHandler;

public class ResonatorEntityNew extends TileEntityMachine implements ITickable
{
    private int lastEnergy;

    private static final int processingTime = 100;
    private int processTime = 0;

    public ResonatorEntityNew()
    {
        energy = new OrionEnergy(1500, 100);
        iHand = new ItemStackHandler(2);
    }

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
        progress = (processTime * 100) / processingTime;
        lastEnergy = energy.getEnergyStored();
        world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 1);
        this.markDirty();
    }
}
