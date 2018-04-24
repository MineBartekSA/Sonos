package com.minebarteksa.sonos.blocks;

import com.minebarteksa.sonos.Sonos;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block
{
	protected String name;

	public BlockBase(Material mat, String name)
	{
		super(mat);
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
	}

	public void registerItemModel(Item itemBlock)
	{
		Sonos.proxy.registerItemRenderer(itemBlock,  0, name);
	}

	public Item getItemBlock()
	{
		return new ItemBlock(this).setRegistryName(getRegistryName());
	}

	@Override
	public BlockBase setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}
