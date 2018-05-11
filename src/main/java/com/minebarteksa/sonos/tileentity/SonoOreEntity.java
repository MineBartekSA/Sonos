package com.minebarteksa.sonos.tileentity;


import com.minebarteksa.sonos.Sonos;
import net.minecraft.client.Minecraft;
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
  public LoopSound ls;
  public int note = 0;

  public void StartPlaying()
  {
    LoopSound ls = new LoopSound(SoundEvents.getSound(Notes.getNote(note), "hum"), SoundCategory.BLOCKS, pos);
    Minecraft.getMinecraft().getSoundHandler().playSound(ls);
    Sonos.log.info(ls.toString());
  }

  public void StopPlaying()
  {
    Sonos.log.info(ls.toString());
    Minecraft.getMinecraft().getSoundHandler().stopSound(ls);
    ls = null;
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound)
  {
    if(!compound.hasKey("note"))
      compound.setInteger("note", note);
    return super.writeToNBT(compound);
  }

  @Override
  public void readFromNBT(NBTTagCompound compound)
  {
    note = compound.getInteger("note");
    super.readFromNBT(compound);
  }

  public int getNote()
  {
    return note;
  }

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