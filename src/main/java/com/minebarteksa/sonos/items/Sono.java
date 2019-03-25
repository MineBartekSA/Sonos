package com.minebarteksa.sonos.items;

import com.minebarteksa.sonos.Sonos;
import com.minebarteksa.orion.items.ItemBase;
import net.minecraft.item.Item;
import net.minecraft.util.*;
import com.minebarteksa.sonos.sound.SoundEvents;
import com.minebarteksa.sonos.sound.SoundEvents.Notes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Sono extends ItemBase
{
	public Notes note;

	public Sono(Notes note)
	{
		super("sono_" + note.getSimpler(), Sonos.ModID);
		this.note = note;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		SoundEvent e = SoundEvents.getSound(note, "sonar");
		worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, e, SoundCategory.PLAYERS, 1.0f, 1.0f);
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	public static Sono getFromNote(Notes note) { return (Sono) Item.REGISTRY.getObject(new ResourceLocation(Sonos.ModID, "sono_" + note.getSimpler())); }
}
