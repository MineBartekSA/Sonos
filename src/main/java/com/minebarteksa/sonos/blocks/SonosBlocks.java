package com.minebarteksa.sonos.blocks;

import com.minebarteksa.orion.OrionRegistry;
import com.minebarteksa.sonos.Sonos;
import com.minebarteksa.sonos.sound.SoundEvents.Notes;

public class SonosBlocks
{
	public static SonoOre SO = new SonoOre("sono_ore", Notes.A).setCreativeTab(Sonos.cTab);
	public static Resonator re = new Resonator("resonator").setCreativeTab(Sonos.cTab);
	public static RFGenerator rf = new RFGenerator("generator").setCreativeTab(Sonos.cTab);
	public static ChordManipulator cm = new ChordManipulator("chord_manipulator").setCreativeTab(Sonos.cTab);

	public static void register()
	{
		OrionRegistry.register(SO, rf, cm); // TileEntity
		OrionRegistry.register(re);         // TileEntityWithFacing
	}
}
