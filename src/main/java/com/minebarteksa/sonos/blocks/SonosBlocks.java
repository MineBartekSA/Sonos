package com.minebarteksa.sonos.blocks;

import net.minecraftforge.fml.common.registry.GameRegistry;
import com.minebarteksa.sonos.Sonos;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class SonosBlocks
{
	//Blocks
	public static SonoOre SO = new SonoOre("sono_ore").setCreativeTab(Sonos.cTab);

	public static void register(IForgeRegistry<Block> registry)
	{
		registry.registerAll(SO);
		GameRegistry.registerTileEntity(SO.getTitleEntityClass(), SO.getRegistryName().toString());
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
