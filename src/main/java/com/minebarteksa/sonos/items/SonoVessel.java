package com.minebarteksa.sonos.items;

import net.minecraft.potion.PotionEffect;
import com.minebarteksa.sonos.sound.SoundEvents;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import com.minebarteksa.sonos.sound.SoundEvents.Notes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.creativetab.CreativeTabs;

public class SonoVessel extends ItemBase
{
  public Notes note;

  public SonoVessel(String name, Notes n)
  {
    super(name);
    this.note = n;
  }

  @Override
  public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
  {
    player.addPotionEffect(new PotionEffect(SoundEvents.getNoteEffect(note), 40)); //Rewrite
    return super.onItemRightClick(world, player, hand);
  }

  @Override
  public SonoVessel setCreativeTab(CreativeTabs tab)
  {
    super.setCreativeTab(tab);
    return this;
  }
}
