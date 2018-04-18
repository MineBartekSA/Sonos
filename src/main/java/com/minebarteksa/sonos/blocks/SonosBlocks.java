package com.minebarteksa.sonos.blocks;

import com.minebarteksa.sonos.Sonos;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class SonosBlocks 
{
	//Blocks
	public static TestBlock tb = new TestBlock("TestBlock").setCreativeTab(Sonos.cTab);
	
	public static void register(IForgeRegistry<Block> registry)
	{
		registry.registerAll(tb);
	}
	
	public static void registerItemBlock(IForgeRegistry<Item> registry)
	{
		registry.registerAll(tb.getItemBlock());
	}
	
	public static void registerModels()
	{
		tb.registerItemModel(Item.getItemFromBlock(tb));
	}
}
