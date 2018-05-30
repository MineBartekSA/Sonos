package com.minebarteksa.sonos.tileentity;

import net.minecraft.client.Minecraft;
import scala.actors.threadpool.TimeUnit;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundCategory;
import com.minebarteksa.sonos.sound.SoundEvents.Notes;
import com.minebarteksa.sonos.sound.SoundEvents;
import com.minebarteksa.sonos.sound.LoopSound;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class SonoOreEntity extends TileEntity
{
  protected LoopSound<SonoOreEntity> ls;
  public int note = 0;
  protected boolean isPlaying = false;
  protected boolean isPaused = false;
  private SoundThread thread;

  public void StartPlaying()
  {
    if(thread == null)
      thread = new SoundThread(world, Notes.getNote(note), pos, Minecraft.getMinecraft());
    else
      thread.run();
    isPlaying = true;
  }

  @SuppressWarnings("deprecation")
  public void StopPlaying()
  {
    if(thread != null)
      thread.stop();
    isPlaying = false;
  }

  @Override
  public void onLoad()
  {
    if(isPlaying && thread == null && !world.isRemote)
      StartPlaying();
    /*if(world.isRemote)
      new PauseChecker();*/
  }

  @Override
  @SuppressWarnings("deprecation")
  public void onChunkUnload()
  {
    if(thread != null)
      thread.stop();
    thread = null;
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
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
  {
		readFromNBT(pkt.getNbtCompound());
  }

  @Override
  public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate)
  {
    return (oldState.getBlock() != newSate.getBlock());
  }

  class SoundThread extends Thread
  {
    World wo;
    Notes nn;
    SoundEvent e;
    BlockPos pp;
    Minecraft mm;

    SoundThread(World w, Notes n, BlockPos p, Minecraft m)
    {
      this.wo = w;
      this.nn = n;
      this.pp = p;
      this.mm = m;
      this.start();
    }

    public void run()
    {
      if(true)
      {
        wo.playSound(null, pp, e, SoundCategory.BLOCKS, 1.0f, 1.0f);
        try { TimeUnit.SECONDS.sleep(SoundEvents.humLength); }
        catch(InterruptedException ex) {}
      }
      this.run();
    }
  }

  class PauseChecker extends Thread
  {
    PauseChecker()
    {
      this.start();
    }
    public void run()
    {
      if(Minecraft.getMinecraft().isGamePaused())
        isPaused = true;
      else
        isPaused = false;
      this.run();
    }
  }
}
