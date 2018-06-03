package com.minebarteksa.sonos.packets;

import com.minebarteksa.sonos.tileentity.SonoOreEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;

public class SoundSSPacket implements IMessage
{
  public SoundSSPacket() {}

  public SoundSSPacket(boolean switchTo, BlockPos pos)
  {
    this.switchT = switchTo;
    this.x = pos.getX();
    this.y = pos.getY();
    this.z = pos.getZ();
  }

  boolean switchT;
  int x, y, z;

  @Override
  public void toBytes(ByteBuf buf)
  {
    buf.writeBoolean(switchT);
    buf.writeInt(x);
    buf.writeInt(y);
    buf.writeInt(z);
  }

  @Override
  public void fromBytes(ByteBuf buf)
  {
    switchT = buf.readBoolean();
    x = buf.readInt();
    y = buf.readInt();
    z = buf.readInt();
  }

  public static class SoundSSPacketHandler implements IMessageHandler<SoundSSPacket, IMessage>
  {
    public SoundSSPacketHandler() {}

    @Override
    public IMessage onMessage(SoundSSPacket message, MessageContext ctx)
    {
      TileEntity te = Minecraft.getMinecraft().world.getTileEntity(new BlockPos(message.x, message.y, message.z));

      if(te instanceof SonoOreEntity)
      {
        SonoOreEntity soe = (SonoOreEntity)te;
        if(message.switchT)
          soe.StartPlaying();
        else
          soe.StopPlaying();
      }

      return null;
    }
  }
}
