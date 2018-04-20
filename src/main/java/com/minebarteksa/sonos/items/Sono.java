package com.minebarteksa.sonos.items;

import net.minecraft.creativetab.CreativeTabs;
//import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class Sono extends ItemBase
{
	public NBTTagCompound nbt;

	public Sono(String name)
	{
		super(name);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		if(nbt.hasKey("note"))
			playerIn.sendMessage(new TextComponentString("Sono note: " + nbt.getString("note")));
		else
			playerIn.sendMessage(new TextComponentString("This sono dose not have any note!"));

		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	/*@Override
	public Entity createEntity(World world, Entity location, ItemStack itemstack)
	{
		if(itemstack.hasTagCompound())
			nbt = itemstack.getTagCompound();
		else
			nbt = new NBTTagCompound();

		nbt.setString("note", "");
		itemstack.setTagCompound(nbt);
		return super.createEntity(world, location, itemstack);
	}*/

	public void setNBT(ItemStack is)
	{
		nbt = is.getTagCompound();
	}

	public void updateNBT(String key, String value)
	{
		nbt.setString(key, value);
	}

	public void updateNBT(String key, String value, ItemStack item)
	{
		nbt = item.getTagCompound();
		nbt.setString(key, value);
	}

	@Override
	public Sono setCreativeTab(CreativeTabs tab)
	{
		super.setCreativeTab(tab);
		return this;
	}
}
