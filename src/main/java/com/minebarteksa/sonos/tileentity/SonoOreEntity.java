package com.minebarteksa.sonos.tileentity;

import com.minebarteksa.sonos.packets.SoundSSPacket;
import com.minebarteksa.sonos.packets.SonosPacketHandler;
import net.minecraft.client.Minecraft;
import com.minebarteksa.sonos.sound.SoundEvents.Notes;
import com.minebarteksa.sonos.sound.SoundEvents;
import com.minebarteksa.sonos.sound.SoundSource;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class SonoOreEntity extends TileEntity
{
  protected SoundSource sound;
  public int note = 0;
  protected boolean isPlaying = false;

  public void StartPlaying()
  {
    isPlaying = true;
    sound = new SoundSource(this, SoundEvents.getSound(Notes.getNote(note), "hum"), 1.0f, 1.0f, true);
    Minecraft.getMinecraft().getSoundHandler().playSound(sound);
  }

  public void StopPlaying()
  {
    if(sound == null)
      return;
    isPlaying = false;
    sound.stop();
    Minecraft.getMinecraft().getSoundHandler().stopSound(sound);
  }

  public void SSSound(boolean switchTo)
  {
    SonosPacketHandler.INSTANCE.sendToDimension(new SoundSSPacket(switchTo, pos), world.provider.getDimension());
    isPlaying = switchTo;
  }

  @Override
  public void onLoad()
  {
    if(isPlaying)
      SSSound(true);
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
}
