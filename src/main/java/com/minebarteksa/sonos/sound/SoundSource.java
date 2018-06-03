package com.minebarteksa.sonos.sound;

import com.minebarteksa.sonos.tileentity.SonoOreEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.client.audio.ITickableSound;
import net.minecraft.client.audio.PositionedSound;

public class SoundSource extends PositionedSound implements ITickableSound
{
  boolean donePlaying;
  boolean stop = false;
  TileEntity te;

  public SoundSource(TileEntity te, SoundEvent sound, float volume, float pitch, boolean repeat)
  {
    this(te.getPos(), sound, volume, pitch, repeat);
    this.te = te;
  }

  SoundSource(BlockPos pos, SoundEvent sound, float volume, float pitch, boolean repeat)
  {
    super(sound, SoundCategory.AMBIENT);
    this.xPosF = pos.getX();
    this.yPosF = pos.getY();
    this.zPosF = pos.getZ();
    this.volume = volume;
    this.pitch = pitch;
    this.repeat = repeat;
    this.repeatDelay = 0;
  }

  public void stop() { stop = true; }

  @Override
  public void update()
  {
    /*if(te instanceof SonoOreEntity)
    {
      if(!(((SonoOreEntity)te).isPlaying()))
      {
        stop = true;
        donePlaying = true;
        volume = 0;
      }
    }*/
    if(stop)
    {
      donePlaying = true;
      volume = 0;
    }
    if(Minecraft.getMinecraft().world.isAirBlock(new BlockPos(this.xPosF, this.yPosF, this.zPosF)))
    {
      stop = true;
      donePlaying = true;
      volume = 0;
    }
  }

  @Override
  public boolean isDonePlaying() { return donePlaying; }
}
