package com.minebarteksa.sonos.tileentity;

import com.minebarteksa.sonos.Sonos;
import com.minebarteksa.sonos.blocks.SonoOre;
import com.minebarteksa.sonos.packets.SoundSSPacket;
import com.minebarteksa.sonos.packets.SonosPacketHandler;
import net.minecraft.client.Minecraft;
import com.minebarteksa.sonos.sound.SoundEvents.Notes;
import com.minebarteksa.sonos.sound.SoundEvents;
import com.minebarteksa.sonos.sound.SoundSource;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SonoOreEntity extends TileEntity implements ITickable
{
  protected SoundSource sound;
  private Timer t;
  private Random r = new Random();
  private int deactivateOnTick;
  private int tick = 0;
  public int note = 0;
  protected boolean isPlaying = false;
  public boolean playSound = false;
  public boolean stopSound = false;

    @Override
    public void update()
    {
        if(!world.isRemote)
        {
            if(deactivateOnTick != 0)
            {
                if(tick >= deactivateOnTick)
                {
                    ((SonoOre)world.getBlockState(pos).getBlock()).deactivate(world, pos);
                    tick = 0;
                    deactivateOnTick = 0;
                }
                else
                    tick++;
            }
        }
        else
        {
            if(playSound)
                StartPlaying();
            if(stopSound)
                StopPlaying();
            if(isPlaying && !Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(sound))
                StartPlaying();
        }
    }

    public void StartPlaying()
    {
        playSound = false;
        if(isPlaying)
            return;
        isPlaying = true;
        sound = new SoundSource(this, SoundEvents.getSound(Notes.getNote(note), "hum"), 1.0f, 1.0f, true);
        Minecraft.getMinecraft().getSoundHandler().playSound(sound);
    }

    public void StopPlaying()
    {
        stopSound = false;
        if(sound == null)
            return;
        isPlaying = false;
        sound.stop();
        Minecraft.getMinecraft().getSoundHandler().stopSound(sound);
    }

    public void scheduleDeactivation() { deactivateOnTick = 30 * (r.nextInt(30) + 1); }


  public void SSSound(boolean switchTo)
  {
    SonosPacketHandler.INSTANCE.sendToDimension(new SoundSSPacket(switchTo, pos), world.provider.getDimension());
    isPlaying = switchTo;
  }

  @Override
  public void onLoad()
  {
      if (isPlaying)
      {
          if(world.isRemote)
              playSound = true;
          else
          {
              SSSound(true);
              scheduleDeactivation();
          }
      }
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound)
  {
    if(!compound.hasKey("note"))
      compound.setInteger("note", note);
    compound.setBoolean("isPlaying", isPlaying);
    return super.writeToNBT(compound);
  }

  @Override
  public void readFromNBT(NBTTagCompound compound)
  {
    note = compound.getInteger("note");
    isPlaying = compound.getBoolean("isPlaying");
    super.readFromNBT(compound);
  }

  public int getNote() { return note; }

  public boolean isPlaying() { return isPlaying; }

  @Override
  public SPacketUpdateTileEntity getUpdatePacket()
  {
    NBTTagCompound nbtTagCompound = new NBTTagCompound();
		writeToNBT(nbtTagCompound);
		int metadata = 0;
    return new SPacketUpdateTileEntity(this.pos, metadata, nbtTagCompound);
  }

  @Override
  public NBTTagCompound getUpdateTag()
  {
    NBTTagCompound nbtTagCompound = new NBTTagCompound();
    writeToNBT(nbtTagCompound);
    return nbtTagCompound;
  }

  @Override
  public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) { readFromNBT(pkt.getNbtCompound()); }

  @Override
  public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) { return (oldState.getBlock() != newSate.getBlock()); }
}
