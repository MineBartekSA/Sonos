package com.minebarteksa.sonos.blocks;

import net.minecraft.item.Item;
import net.minecraft.block.Block;
import com.minebarteksa.sonos.Sonos;
import net.minecraftforge.registries.IForgeRegistry;
import com.minebarteksa.sonos.sound.SoundEvents.Notes;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SonosBlocks
{
	public static SonoOre SO = new SonoOre("sono_ore", Notes.A).setCreativeTab(Sonos.cTab);
	public static Resonator re = new Resonator("resonator").setCreativeTab(Sonos.cTab);
	public static RFGenerator rf = new RFGenerator("generator").setCreativeTab(Sonos.cTab);
	public static ChordManipulator cm = new ChordManipulator("chord_manipulator").setCreativeTab(Sonos.cTab);

	public static void register(IForgeRegistry<Block> registry)
	{
		registry.registerAll(SO, re, rf, cm);
		GameRegistry.registerTileEntity(SO.getTileEntityClass(), SO.getRegistryName().toString());
		GameRegistry.registerTileEntity(re.getTileEntityClass(), re.getRegistryName().toString());
		GameRegistry.registerTileEntity(rf.getTileEntityClass(), rf.getRegistryName().toString());
		GameRegistry.registerTileEntity(cm.getTileEntityClass(), cm.getRegistryName().toString());
	}

	public static void registerItemBlock(IForgeRegistry<Item> registry)
	{
		registry.registerAll(SO.getItemBlock(), re.getItemBlock(), rf.getItemBlock(), cm.getItemBlock());
	}

	public static void registerModels()
	{
		SO.registerItemModel(Item.getItemFromBlock(SO));
		re.registerItemModel(Item.getItemFromBlock(re));
		rf.registerItemModel(Item.getItemFromBlock(rf));
		cm.registerItemModel(Item.getItemFromBlock(cm));
	}
}
