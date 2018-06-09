package com.minebarteksa.sonos.packets;

import com.minebarteksa.sonos.tileentity.GeneratorEntity;
import com.minebarteksa.sonos.tileentity.ResonatorEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ProgressUpdatePacket implements IMessage
{
  public ProgressUpdatePacket() {}

  public ProgressUpdatePacket(int progress, int totalProgress, int eCap, int eStor, BlockPos pos)
  {
    this.prog = progress;
    this.tProg = totalProgress;
    this.energyCapacity = eCap;
    this.energyStored = eStor;
    this.x = pos.getX();
    this.y = pos.getY();
    this.z = pos.getZ();
  }

  private int prog;
  private int tProg;
  private int energyCapacity;
  private int energyStored;
  private int x, y, z;

  @Override
  public void toBytes(ByteBuf buf)
  {
    buf.writeInt(prog);
    buf.writeInt(tProg);
    buf.writeInt(energyCapacity);
    buf.writeInt(energyStored);
    buf.writeInt(x);
    buf.writeInt(y);
    buf.writeInt(z);
  }

  @Override
  public void fromBytes(ByteBuf buf)
  {
    prog = buf.readInt();
    tProg = buf.readInt();
    energyCapacity = buf.readInt();
    energyStored = buf.readInt();
    x = buf.readInt();
    y = buf.readInt();
    z = buf.readInt();
  }

  public static class ProgressUpdatePacketHandler implements IMessageHandler<ProgressUpdatePacket, IMessage>
  {
    public ProgressUpdatePacketHandler() {}

    @Override
    public IMessage onMessage(ProgressUpdatePacket message, MessageContext ctx)
    {
      Minecraft mc = Minecraft.getMinecraft();
      TileEntity te = mc.world.getTileEntity(new BlockPos(message.x, message.y, message.z));

      if(te instanceof ResonatorEntity)
      {
        ((ResonatorEntity)te).updateGuiInfo(message.prog, message.energyCapacity, message.energyStored);
      }
      else if(te instanceof GeneratorEntity)
      {
        ((GeneratorEntity)te).updateGuiInfo(message.tProg, message.prog, message.energyStored);
      }

      return null;
    }
  }
}
