package com.minebarteksa.sonos.packets;

import com.minebarteksa.sonos.packets.ProgressUpdatePacket.ProgressUpdatePacketHandler;
import net.minecraftforge.fml.relauncher.Side;
import com.minebarteksa.sonos.packets.GamePausedPacket.GamePausedPacketHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class SonosPacketHandler
{
  public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("sonos");

  public static void registerPackets()
  {
    INSTANCE.registerMessage(GamePausedPacketHandler.class, GamePausedPacket.class, 0, Side.SERVER);
    INSTANCE.registerMessage(ProgressUpdatePacketHandler.class, ProgressUpdatePacket.class, 1, Side.CLIENT);
  }
}
