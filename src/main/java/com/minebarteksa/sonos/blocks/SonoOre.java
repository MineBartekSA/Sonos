package com.minebarteksa.sonos.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class SonoOre extends BlockBase
{
	public String note;

	public SonoOre(String name)
	{
		super(Material.ROCK, name);

		setHardness(2f);
		setResistance(5f);
		
		
	}
	
	@Override
	public SoundType getSoundType() 
	{
		SoundEvent se = new SoundEvent(new ResourceLocation(note));
		
		return new SoundType(10f, 1f, se, se, se, se, null);
	}
	
	@Override
	public SonoOre setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}
