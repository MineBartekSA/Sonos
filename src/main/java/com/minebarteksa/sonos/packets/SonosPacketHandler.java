package com.minebarteksa.sonos.packets;

import com.minebarteksa.sonos.packets.SoundSSPacket.SoundSSPacketHandler;
import com.minebarteksa.sonos.packets.ProgressUpdatePacket.ProgressUpdatePacketHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class SonosPacketHandler
{
  public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("sonos");

  public static void registerPackets()
  {
    INSTANCE.registerMessage(ProgressUpdatePacketHandler.class, ProgressUpdatePacket.class, 0, Side.CLIENT);
    //INSTANCE.registerMessage(SoundSSPacketHandler.class, SoundSSPacket.class, 1, Side.CLIENT);
  }
}
