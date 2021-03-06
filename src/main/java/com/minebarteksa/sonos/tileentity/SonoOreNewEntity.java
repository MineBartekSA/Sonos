package com.minebarteksa.sonos.tileentity;

import com.minebarteksa.orion.integrations.IOrionInfoProvider;
import com.minebarteksa.orion.integrations.infoprovider.IPData;
import com.minebarteksa.orion.network.OrionPacketHandler;
import com.minebarteksa.sonos.blocks.SonoOreNew;
import com.minebarteksa.sonos.packets.SonoOrePacket;
import com.minebarteksa.sonos.sound.SoundEvents;
import com.minebarteksa.sonos.sound.SoundSource;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SonoOreNewEntity extends TileEntity implements IOrionInfoProvider, ITickable
{
    protected SoundEvents.Notes note = SoundEvents.Notes.None;
    private int tick = 0;
    private boolean doTick = false;
    private ISound sound;
    private Function<Void, Void> soundStart = (Void) ->  null;

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
            if(tick == (SoundEvents.humLength * 20) + SoundEvents.humLength)
            {
                this.world.setBlockState(pos, this.world.getBlockState(pos).getBlock().getDefaultState());
                OrionPacketHandler.INSTANCE.sendToAll(new SonoOrePacket(pos, note.number(), true));
                doTick = false;
            }
            tick++;
        }
        soundStart.apply(null);
        soundStart = (Void) ->  null;
    }

    public SoundEvents.Notes getNote()
    {
        if(note == SoundEvents.Notes.None)
            init();
        return note;
    }

    public void setTimeOut()
    {
        this.world.setBlockState(pos, this.world.getBlockState(pos).withProperty(SonoOreNew.LitAF, getNote().number()));
        doTick = true;
        tick = 0;
        OrionPacketHandler.INSTANCE.sendToAll(new SonoOrePacket(pos, note.number(), false));
    }

    public void switchSound(int note, boolean halt)
    {
        if(sound == null)
            sound = new SoundSource(this, SoundEvents.getSound(SoundEvents.Notes.getNote(note), "hum"), 1f, 1f, false);
        SoundHandler sh = Minecraft.getMinecraft().getSoundHandler();
        if(sh.isSoundPlaying(sound) && halt)
            sh.stopSound(sound);
        else
            soundStart = (Void) -> {
                sh.playSound(sound);
                return null;
            };
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
        compound.setInteger("note", note.number());
        return compound;
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) { return (oldState.getBlock() != newSate.getBlock()); }
}
