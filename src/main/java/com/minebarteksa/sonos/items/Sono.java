package com.minebarteksa.sonos.items;

import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundCategory;
import com.minebarteksa.sonos.sound.SoundEvents;
import net.minecraft.client.util.ITooltipFlag;
import java.util.List;
import com.minebarteksa.sonos.sound.SoundEvents.Notes;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class Sono extends ItemBase
{
	public Notes note;
	public String info = "";

	public Sono(String name, Notes note, String info)
	{
		super(name);
		this.note = note;
		this.info = info;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		if(worldIn.isRemote)
		{
			SoundEvent e = SoundEvents.getSound(note, "sonar");
			worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, e, SoundCategory.PLAYERS, 1.0f, 1.0f);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		if(info != "")
			tooltip.add(info);
	}

	@Override
	public Sono setCreativeTab(CreativeTabs tab)
	{
		super.setCreativeTab(tab);
		return this;
	}
}
