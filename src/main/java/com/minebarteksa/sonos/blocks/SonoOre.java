package com.minebarteksa.sonos.blocks;

import java.util.Random;
import com.minebarteksa.sonos.items.SonosItems;
//import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		note = String.valueOf(new Random().nextInt());
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return SonosItems.sono;
	}
	
	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}
	
	/*@Override
	public SoundType getSoundType() 
	{
		SoundEvent se = new SoundEvent(new ResourceLocation(note));
		
		return new SoundType(10f, 1f, se, se, se, se, null);
	}*/
	
	@Override
	public SonoOre setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}
