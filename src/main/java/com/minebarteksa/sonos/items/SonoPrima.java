package com.minebarteksa.sonos.items;

import com.minebarteksa.sonos.sound.SoundEvents.Chords;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.EnumHand;
import com.minebarteksa.sonos.sound.SoundEvents;
import net.minecraft.util.SoundEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import com.minebarteksa.sonos.sound.SoundEvents.Notes;
import net.minecraft.creativetab.CreativeTabs;

public class SonoPrima extends ItemBase
{
  public Notes note;

  public SonoPrima(String name, Notes note)
  {
    super(name);
    this.note = note;
  }

  @Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		if(worldIn.isRemote)
		{
			SoundEvent e1 = SoundEvents.getSound(Notes.getNote(Chords.getChordNotes(1, 2)[0]), "sonar");
      SoundEvent e2 = SoundEvents.getSound(Notes.getNote(Chords.getChordNotes(1, 2)[1]), "sonar");
      SoundEvent e3 = SoundEvents.getSound(Notes.getNote(Chords.getChordNotes(1, 2)[2]), "sonar");
			worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, e1, SoundCategory.PLAYERS, 1.0f, 1.0f);
      worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, e2, SoundCategory.PLAYERS, 1.0f, 1.0f);
      worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, e3, SoundCategory.PLAYERS, 1.0f, 1.0f);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

  @Override
  public SonoPrima setCreativeTab(CreativeTabs tab) {
    super.setCreativeTab(tab);
    return this;
  }
}
