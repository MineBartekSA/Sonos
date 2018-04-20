package com.minebarteksa.sonos.blocks;

import com.minebarteksa.sonos.Sonos;
import com.minebarteksa.sonos.sound.SoundEvents.Notes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class SonosBlocks
{
	//Blocks
	public static SonoOre SO = new SonoOre("sono_ore", Notes.A).setCreativeTab(Sonos.cTab);
	public static Block LitSO = new Block(Material.ROCK).setRegistryName("lit_somo_ore");

	public static void register(IForgeRegistry<Block> registry)
	{
		registry.registerAll(SO);
	}

	public static void registerItemBlock(IForgeRegistry<Item> registry)
	{
		registry.registerAll(SO.getItemBlock());
	}

	public static void registerModels()
	{
		SO.registerItemModel(Item.getItemFromBlock(SO));
	}
}
