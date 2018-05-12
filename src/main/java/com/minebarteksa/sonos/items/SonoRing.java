package com.minebarteksa.sonos.items;

import com.minebarteksa.sonos.sound.SoundEvents.Notes;
import com.minebarteksa.sonos.sound.SoundEvents;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.creativetab.CreativeTabs;
import baubles.api.BaubleType;
import net.minecraft.item.ItemStack;
import baubles.api.IBauble;

public class SonoRing extends ItemBase implements IBauble
{
  private Notes note;

  public SonoRing(String name, Notes n)
  {
    super(name);
    this.setMaxStackSize(1);
    this.setMaxDamage(0);
    note = n;
  }

  @Override
  public BaubleType getBaubleType(ItemStack arg0)
  {
    return BaubleType.RING;
  }

  @Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player)
  {
		if (itemstack.getItemDamage() == 0 && player.ticksExisted % 39 == 0)
    {
			player.addPotionEffect(new PotionEffect(SoundEvents.getNoteEffect(note), 40, 0, true, false));
		}
	}

  @Override
  public SonoRing setCreativeTab(CreativeTabs tab)
  {
    super.setCreativeTab(tab);
    return this;
  }

  @Override
  public void onEquipped(ItemStack itemstack, EntityLivingBase player)
  {
    player.playSound(SoundEvents.getSound(note, "sonar"), .75F, 1.9f);
  }

  @Override
  public void onUnequipped(ItemStack itemstack, EntityLivingBase player)
  {
    player.playSound(SoundEvents.getSound(note, "sonar"), .75F, 2f);
  }
}
