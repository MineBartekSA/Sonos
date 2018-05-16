package com.minebarteksa.sonos.gui;

import com.minebarteksa.sonos.gui.containers.GeneratorContainer;
import com.minebarteksa.sonos.tileentity.GeneratorEntity;
import net.minecraft.inventory.Container;
import com.minebarteksa.sonos.gui.containers.ResonatorContainer;
import com.minebarteksa.sonos.gui.ResonatorGui;
import com.minebarteksa.sonos.tileentity.ResonatorEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class SonosGUIHandler implements IGuiHandler
{
  public static final int ResonatorID = 0;
  public static final int GeneratorID = 1;

  @Override
  public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
  {
    switch(ID)
    {
      case ResonatorID:
        return new ResonatorContainer(player.inventory, (ResonatorEntity)world.getTileEntity(new BlockPos(x, y, z)));
      case GeneratorID:
        return new GeneratorContainer(player.inventory, (GeneratorEntity)world.getTileEntity(new BlockPos(x, y, z)));
      default:
        return null;
    }
  }

  @Override
  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
  {
    switch(ID)
    {
      case ResonatorID:
        return new ResonatorGui((Container)getServerGuiElement(ID, player, world, x, y, z), player.inventory, new BlockPos(x, y, z), world);
      case GeneratorID:
        return new GeneratorGui((Container)getServerGuiElement(ID, player, world, x, y, z), player.inventory, new BlockPos(x, y, z), world);
      default:
        return null;
    }
  }
}
