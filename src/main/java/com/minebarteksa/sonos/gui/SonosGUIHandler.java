package com.minebarteksa.sonos.gui;

import com.minebarteksa.sonos.gui.containers.CMContainer;
import com.minebarteksa.sonos.tileentity.*;
import com.minebarteksa.sonos.Sonos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.inventory.Container;
import com.minebarteksa.sonos.gui.containers.ResonatorContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class SonosGUIHandler implements IGuiHandler
{
    //Textures locations
    public static final ResourceLocation ResonatorBack = new ResourceLocation(Sonos.ModID, "textures/gui/resonator.png");
    public static final ResourceLocation CMBack = new ResourceLocation(Sonos.ModID, "textures/gui/modulator.png");
    public static final ResourceLocation EnergyBar = new ResourceLocation(Sonos.ModID, "textures/gui/energy.png");

    //Gui ID's
    public static final int ResonatorID = 0;
    public static final int GeneratorID = 1;
    public static final int ModulatorID = 2;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch(ID)
        {
            case ResonatorID:
                return new ResonatorContainer(player.inventory, (ResonatorEntityNew)world.getTileEntity(new BlockPos(x, y, z)));
            case ModulatorID:
                return new CMContainer(player.inventory, (ModulatorEntity)world.getTileEntity(new BlockPos(x, y, z)));
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
            case ModulatorID:
                return new CMGui((Container)getServerGuiElement(ID, player, world, x, y, z), player.inventory, new BlockPos(x, y, z), world);
            default:
                return null;
        }
    }
}
