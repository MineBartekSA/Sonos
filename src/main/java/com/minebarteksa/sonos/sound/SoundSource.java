package com.minebarteksa.sonos.sound;

import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.client.audio.ITickableSound;
import net.minecraft.client.audio.PositionedSound;

public class SoundSource extends PositionedSound implements ITickableSound
{
  boolean donePlaying;
  boolean stop = false;

  public SoundSource(TileEntity te, SoundEvent sound, float volume, float pitch, boolean repeat)
  {
    this(te.getPos().getX(), te.getPos().getY(), te.getPos().getZ(), sound, volume, pitch, repeat);
  }

  public SoundSource(int x, int y, int z, SoundEvent sound, float volume, float pitch, boolean repeat)
  {
    super(sound, SoundCategory.AMBIENT);
    this.xPosF = x;
    this.yPosF = y;
    this.zPosF = z;
    this.volume = volume;
    this.pitch = pitch;
    this.repeat = repeat;
    this.repeatDelay = 0;
  }

  public void stop() { stop = true; }

  @Override
  public void update()
  {
    if(stop)
    {
      donePlaying = true;
      volume = 0;
    }
  }

  @Override
  public boolean isDonePlaying() { return donePlaying; }
}
