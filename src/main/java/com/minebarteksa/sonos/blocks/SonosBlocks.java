package com.minebarteksa.sonos.blocks;

import net.minecraftforge.fml.common.registry.GameRegistry;
import com.minebarteksa.sonos.Sonos;
import com.minebarteksa.sonos.sound.SoundEvents.Notes;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class SonosBlocks
{
	public static SonoOre SO = new SonoOre("sono_ore", Notes.A).setCreativeTab(Sonos.cTab);
	public static Resonator e = new Resonator("resonator").setCreativeTab(Sonos.cTab);
	public static RFGenerator rf = new RFGenerator("generator").setCreativeTab(Sonos.cTab);

	public static void register(IForgeRegistry<Block> registry)
	{
		registry.registerAll(SO, e, rf);
		GameRegistry.registerTileEntity(SO.getTileEntityClass(), SO.getRegistryName().toString());
		GameRegistry.registerTileEntity(e.getTileEntityClass(), e.getRegistryName().toString());
		GameRegistry.registerTileEntity(rf.getTileEntityClass(), rf.getRegistryName().toString());
	}

	public static void registerItemBlock(IForgeRegistry<Item> registry)
	{
		registry.registerAll(SO.getItemBlock(), e.getItemBlock(), rf.getItemBlock());
	}

	public static void registerModels()
	{
		SO.registerItemModel(Item.getItemFromBlock(SO));
		e.registerItemModel(Item.getItemFromBlock(e));
		rf.registerItemModel(Item.getItemFromBlock(rf));
	}
}
