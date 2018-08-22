package com.minebarteksa.sonos.items;

import com.minebarteksa.sonos.Sonos;
import com.minebarteksa.orion.items.ItemBase;
import net.minecraft.util.EnumActionResult;
import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.util.ActionResult;
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
    super(name, Sonos.ModID);
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
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
  {
		if(!world.isRemote)
    {
			IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
			for(int i = 0; i < baubles.getSlots(); i++)
				if((baubles.getStackInSlot(i) == null || baubles.getStackInSlot(i).isEmpty()) && baubles.isItemValidForSlot(i, player.getHeldItem(hand), player))
        {
					baubles.setStackInSlot(i, player.getHeldItem(hand).copy());
					if(!player.capabilities.isCreativeMode)
          {
						player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
					}
					onEquipped(player.getHeldItem(hand), player);
					break;
				}
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}

  @Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player)
  {
		if (itemstack.getItemDamage() == 0 && player.ticksExisted % 39 == 0)
    {
			player.addPotionEffect(new PotionEffect(SoundEvents.getNoteEffect(note), 40, 0, true, false)); //Rewrite
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
    player.playSound(SoundEvents.getSound(note, "sonar"), .75F, 2f);
  }

  @Override
  public void onUnequipped(ItemStack itemstack, EntityLivingBase player)
  {
    player.playSound(SoundEvents.getSound(note, "sonar"), .75F, 1.9f);
  }
}
