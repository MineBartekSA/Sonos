package com.minebarteksa.sonos.packets;

import com.minebarteksa.orion.OrionRegistry;
import com.minebarteksa.orion.network.PacketRegister;
import net.minecraftforge.fml.relauncher.Side;

public class SonosPacketHandler
{
    public static void registerPackets()
    {
        OrionRegistry.register(new PacketRegister(SonoOrePacket.SimplePacketHandler.class, SonoOrePacket.class, Side.CLIENT));
    }
}
