package com.minebarteksa.sonos.blocks;

import com.minebarteksa.orion.OrionRegistry;
import com.minebarteksa.sonos.Sonos;
import net.minecraft.block.Block;

public class SonosBlocks
{
	public static Block SO = new SonoOreNew().setCreativeTab(Sonos.cTab);
	public static Block re = new Resonator("resonator").setCreativeTab(Sonos.cTab);
	public static Block rf = new RFGenerator("generator").setCreativeTab(Sonos.cTab);
	public static Block cm = new ChordManipulator("chord_manipulator").setCreativeTab(Sonos.cTab);

	public static void register() { OrionRegistry.register(SO, rf, cm, re); }
}
