package com.minebarteksa.sonos.items;

import com.minebarteksa.sonos.Sonos;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class SonosItems
{
	//Items
	public static Sono sono = new Sono("sono").setCreativeTab(Sonos.cTab);

	public static void register(IForgeRegistry<Item> registry)
	{
		registry.registerAll(sono);
	}

	public static void registerModels()
	{
		sono.registerItemModel();
	}
}
