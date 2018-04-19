package com.minebarteksa.sonos.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
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
		playerIn.sendMessage(new TextComponentString("Sono note: " + nbt.getString("note")));
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public Entity createEntity(World world, Entity location, ItemStack itemstack) 
	{
		if(itemstack.hasTagCompound()) 
			nbt = itemstack.getTagCompound();
		else
			nbt = new NBTTagCompound();
		
		nbt.setString("note", "");
		itemstack.setTagCompound(nbt);
		return super.createEntity(world, location, itemstack);
	}
	
	/*@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) 
	{
		if(stack.hasTagCompound()) 
			nbt = stack.getTagCompound();
		else
			nbt = new NBTTagCompound();
		
		nbt.setString("note", "");
		stack.setTagCompound(nbt);
	}*/
	
	public void updateNBT(String key, String value)
	{
		nbt.setString(key, value);
	}
	
	@Override
	public Sono setCreativeTab(CreativeTabs tab) 
	{
		super.setCreativeTab(tab);
		return this;
	}
}
