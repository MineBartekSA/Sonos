package com.minebarteksa.sonos.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;

public class LitSonoOre extends BlockBase 
{
	public static final PropertyInteger LitAF = PropertyInteger.create("lit_af", 1, 12);
	
	public LitSonoOre(String name)
	{
		super(Material.ROCK, name);
	}
	
	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, LitAF);
	}

}
