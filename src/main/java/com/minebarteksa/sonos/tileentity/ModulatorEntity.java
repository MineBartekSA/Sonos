package com.minebarteksa.sonos.tileentity;

import com.minebarteksa.orion.tileentity.TileEntityMachine;
import com.minebarteksa.sonos.Sonos;
import com.minebarteksa.sonos.items.SonoPrima;
import com.minebarteksa.sonos.sound.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.ItemStackHandler;

public class ModulatorEntity extends TileEntityMachine implements ITickable
{
    public ModulatorEntity()
    {
        energy = null;
        iHand = new ItemStackHandler(1);
    }

    @Override
    public void update()
    {
        if(!world.isRemote)
        {
            if(iHand.getStackInSlot(0).getItem() instanceof SonoPrima && iHand.getStackInSlot(0).hasTagCompound())
            {
                NBTTagCompound sonoTag = iHand.getStackInSlot(0).getTagCompound();
                Sonos.log.info("Note: " + SoundEvents.Notes.getNote(sonoTag.getInteger("note")) + " Quality: " + SoundEvents.Chords.getChord(sonoTag.getInteger("quality")) + " Sound type: " + sonoTag.getString("soundType"));
            }
        }
    }
}
