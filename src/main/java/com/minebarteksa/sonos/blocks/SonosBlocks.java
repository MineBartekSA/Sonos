package com.minebarteksa.sonos.blocks;

import com.minebarteksa.orion.OrionRegistry;
import com.minebarteksa.sonos.Sonos;
import net.minecraft.block.Block;

public class SonosBlocks
{
    public static Block SO = new SonoOreNew().setCreativeTab(Sonos.cTab);
    public static Block re = new Resonator().setCreativeTab(Sonos.cTab);
    public static Block cm = new ChordManipulator().setCreativeTab(Sonos.cTab);

    public static void register() { OrionRegistry.register(SO, cm, re); }
}
