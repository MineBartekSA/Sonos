package com.minebarteksa.sonos.packets;

import com.minebarteksa.orion.network.SimplePacket;
import com.minebarteksa.sonos.tileentity.SonoOreEntity;
import com.minebarteksa.sonos.tileentity.SonoOreNewEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;

public class SonoOrePacket extends SimplePacket
{
    public SonoOrePacket() {}

    public SonoOrePacket(BlockPos pos, int note)
    {
        this.pos = pos;
        this.note = note;
    }

    private BlockPos pos;
    private int note;

    @Override
    public void toBytes(ByteBuf buf) { new PacketBuffer(buf).writeBlockPos(pos).writeInt(note); }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        PacketBuffer pb = new PacketBuffer(buf);
        pos = pb.readBlockPos();
        note = pb.readInt();
    }

    @Override
    public void onMessage(SimplePacket message, MessageContext context) { ((SonoOreNewEntity)Minecraft.getMinecraft().world.getTileEntity(((SonoOrePacket)message).pos)).switchSound(((SonoOrePacket)message).note); }
}
