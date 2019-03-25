package com.minebarteksa.sonos.items;

import net.minecraft.entity.Entity;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.*;
import com.minebarteksa.sonos.Sonos;
import com.minebarteksa.orion.items.ItemBase;
import net.minecraft.potion.PotionEffect;
import com.minebarteksa.sonos.sound.SoundEvents;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import com.minebarteksa.sonos.sound.SoundEvents.Notes;
import net.minecraft.item.ItemStack;

public class SonoVessel extends ItemBase
{
    public Notes note;
    private int tick;

    public SonoVessel(Notes n)
    {
        super("sonovessel_" + n.getSimpler(), Sonos.ModID);
        this.note = n;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        Potion effect = SoundEvents.getNoteEffect(note);
        if(effect == MobEffects.INSTANT_HEALTH || effect == MobEffects.SATURATION)
        {
            ItemStack thisItem = player.getHeldItem(hand);
            if(!thisItem.hasTagCompound())
            {
                NBTTagCompound nbt = new NBTTagCompound();
                thisItem.setTagCompound(nbt);
            }
            if(!thisItem.getTagCompound().hasKey("cooldown"))
            {
                if(effect == MobEffects.INSTANT_HEALTH)
                    player.heal(2);
                else
                    player.getFoodStats().addStats(1, 1.0F);

                thisItem.getTagCompound().setString("cooldown", tick + ":" + 100);

                world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.getSound(note, "magic"), SoundCategory.PLAYERS, 1.0f, 0.5f);
            }
            return new ActionResult<>(EnumActionResult.FAIL, thisItem);
        }
        else if(effect == MobEffects.NIGHT_VISION)
        {
            world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.getSound(note, "magic"), SoundCategory.PLAYERS, 1.0f, 0.5f);
            player.addPotionEffect(new PotionEffect(effect, 400));
        }
        else
        {
            world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.getSound(note, "magic"), SoundCategory.PLAYERS, 1.0f, 0.5f);
            player.addPotionEffect(new PotionEffect(effect, 40));
        }
        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if(stack.hasTagCompound() && stack.getTagCompound().hasKey("cooldown"))
        {
            String[] cooldown = stack.getTagCompound().getString("cooldown").split(":");
            if (tick >= Integer.parseInt(cooldown[0]) + Integer.parseInt(cooldown[1]))
                stack.getTagCompound().removeTag("cooldown");
            else if(tick == 0)
                stack.getTagCompound().removeTag("cooldown");
        }
        tick++;
    }
}
