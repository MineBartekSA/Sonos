package com.minebarteksa.sonos.items;

import com.minebarteksa.sonos.Sonos;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class SonosItems 
{
	//Items
	public static ItemBase TestItem = new ItemBase("test_item").setCreativeTab(Sonos.cTab);
	public static Sono sono = new Sono("sono").setCreativeTab(Sonos.cTab);
	
	public static void register(IForgeRegistry<Item> registry)
	{
		registry.registerAll(TestItem, sono);
	}
	
	public static void registerModels()
	{
		TestItem.registerItemModel();
		sono.registerItemModel();
	}
}
