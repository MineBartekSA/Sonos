package com.minebarteksa.sonos.items;

import net.minecraft.util.EnumHand;
import com.minebarteksa.sonos.sound.SoundEvents;
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
      SoundEvents.playChords(worldIn, playerIn, note, "sonar", 2);
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

  @Override
  public SonoPrima setCreativeTab(CreativeTabs tab) {
    super.setCreativeTab(tab);
    return this;
  }
}
