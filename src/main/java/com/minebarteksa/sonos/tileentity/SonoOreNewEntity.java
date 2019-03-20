package com.minebarteksa.sonos.tileentity;

import com.minebarteksa.orion.integrations.IOrionInfoProvider;
import com.minebarteksa.orion.integrations.infoprovider.IPData;
import com.minebarteksa.sonos.blocks.SonoOreNew;
import com.minebarteksa.sonos.sound.SoundEvents;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import java.util.ArrayList;
import java.util.List;

public class SonoOreNewEntity extends TileEntity implements IOrionInfoProvider, ITickable
{
    private final int duration = 14;
    protected SoundEvents.Notes note = SoundEvents.Notes.None;
    private int tick = 0;
    private boolean doTick = false;

    public void init()
    {
        if(note == SoundEvents.Notes.None)
            note = SoundEvents.Notes.getNote(world.rand.nextInt(12));
    }

    @Override
    public void update()
    {
        if(doTick)
        {
            if(tick == (duration * 20) + duration)
            {
                this.world.setBlockState(pos, this.world.getBlockState(pos).getBlock().getDefaultState());
                doTick = false;
            }
            tick++;
        }
    }

    public SoundEvents.Notes getNote()
    {
        if(note == SoundEvents.Notes.None)
            init();
        return note;
    }

    public void setTimeOut()
    {
        this.world.setBlockState(pos, this.world.getBlockState(pos).withProperty(SonoOreNew.LitAF, getNote().Number()));
        doTick = true;
        tick = 0;
        world.playSound(null, pos, SoundEvents.getSound(note, "hum"), SoundCategory.BLOCKS, 1f, 1f);
    }

    @Override
    public List<String> addInfo(IPData data) // ToDo: Delete this! The IOrionInfoProvider on this Block is only for Debug!
    {
        List<String> l = new ArrayList<>();
        l.add("Sono Ore!");
        l.add("Note: " + getNote());
        return l;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        note = SoundEvents.Notes.getNote(compound.getInteger("note"));
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("note", note.Number());
        return compound;
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) { return (oldState.getBlock() != newSate.getBlock()); }
}
