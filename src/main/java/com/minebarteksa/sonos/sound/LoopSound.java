package com.minebarteksa.sonos.sound;

import net.minecraft.client.audio.MovingSound;
import com.minebarteksa.sonos.tileentity.SonoOreEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.audio.ITickableSound;
import net.minecraft.client.audio.ISound;

public class LoopSound<TE extends TileEntity> extends MovingSound implements ITickableSound
{
  protected boolean isDone = false;
  protected TE tE;

  public LoopSound(SoundEvent ev, SoundCategory cat, BlockPos pos, TE entity)
  {
    super(ev, cat);
    this.tE = entity;
    this.repeat = true;
    this.repeatDelay = 0;
    this.attenuationType = ISound.AttenuationType.NONE;
    this.xPosF = pos.getX();
    this.yPosF = pos.getY();
    this.zPosF = pos.getZ();
  }

  @Override
  public boolean isDonePlaying()
  {
    return isDone;
  }

  @Override
  public void update()
  {
    if(tE instanceof SonoOreEntity)
    {
      SonoOreEntity so = (SonoOreEntity)tE;
      if(!so.isPlaying())
      {
        this.isDone = true;
        this.donePlaying = true;
      }
    }
  }
}
