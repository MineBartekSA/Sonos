package com.minebarteksa.sonos.sound;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.audio.ITickableSound;
import net.minecraft.client.audio.PositionedSound;

public class LoopSound extends PositionedSound implements ITickableSound
{
  protected boolean isDone = false;
  public LoopSound(SoundEvent ev, SoundCategory cat, BlockPos pos)
  {
    this(ev.getSoundName(), cat, pos);
  }

  public LoopSound(ResourceLocation ev, SoundCategory cat, BlockPos pos)
  {
    super(ev, cat);
    this.repeat = true;
    this.xPosF = pos.getX();
    this.yPosF = pos.getY();
    this.zPosF = pos.getZ();
  }

  @Override
  public boolean isDonePlaying()
  {
    return isDone;
  }

  public void stop()
  {
    this.isDone = true;
    this.repeat = false;
  }

  @Override
  public void update() {}
}
