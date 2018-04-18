package com.minebarteksa.sonos.items;

import com.minebarteksa.sonos.Sonos;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class SonosItems 
{
	//Items
	public static ItemBase TestItem = new ItemBase("TestItem").setCreativeTab(Sonos.cTab);
	
	public static void register(IForgeRegistry<Item> registry)
	{
		registry.registerAll(
			TestItem
		);
	}
	
	public static void registerModels()
	{
		TestItem.registerItemModel();
	}
}
