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

public class SonoOrePacket extends SimplePacket<SonoOrePacket.PacketData>
{
    public SonoOrePacket() {}

    public SonoOrePacket(BlockPos pos, int note, boolean halt) { super(new PacketData(pos, note, halt)); }

    @Override
    public void toBytes(ByteBuf buf) { new PacketBuffer(buf).writeBlockPos(data.pos).writeInt(data.note).writeBoolean(data.halt); }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        PacketBuffer pb = new PacketBuffer(buf);
        data = new PacketData(pb.readBlockPos(), pb.readInt(), pb.readBoolean());
    }

    @Override
    public void onMessage(PacketData data, SimplePacket message, MessageContext context) { ((SonoOreNewEntity)Minecraft.getMinecraft().world.getTileEntity(((SonoOrePacket)message).data.pos)).switchSound(((SonoOrePacket)message).data.note, ((SonoOrePacket)message).data.halt); }

    public static class PacketData
    {
        public PacketData() {}

        public PacketData(BlockPos pos, int note, boolean halt)
        {
            this.pos = pos;
            this.note = note;
            this.halt = halt;
        }

        public BlockPos pos;
        public int note;
        public boolean halt;
    }
}
