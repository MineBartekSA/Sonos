package com.minebarteksa.sonos.items;

import com.minebarteksa.sonos.sound.SoundEvents.Notes;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class Sono extends ItemBase
{
	public Notes note;

	public Sono(String name, Notes note)
	{
		super(name);
		this.note = note;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		playerIn.sendMessage(new TextComponentString("Note: " + note));
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Override
	public Sono setCreativeTab(CreativeTabs tab)
	{
		super.setCreativeTab(tab);
		return this;
	}
}
