package com.minebarteksa.sonos.sound;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.client.audio.PositionedSound;

public class SoundSource extends PositionedSound
{
    public SoundSource(TileEntity te, SoundEvent sound, float volume, float pitch, boolean repeat) { this(te.getPos(), sound, volume, pitch, repeat); }

    SoundSource(BlockPos pos, SoundEvent sound, float volume, float pitch, boolean repeat)
    {
        super(sound, SoundCategory.BLOCKS);
        this.xPosF = pos.getX();
        this.yPosF = pos.getY();
        this.zPosF = pos.getZ();
        this.volume = volume;
        this.pitch = pitch;
        this.repeat = repeat;
        this.repeatDelay = 0;
    }
}
