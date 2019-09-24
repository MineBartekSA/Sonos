package com.minebarteksa.sonos.items;

import net.minecraft.client.util.ITooltipFlag;
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
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import com.minebarteksa.sonos.sound.SoundEvents.Notes;
import net.minecraft.item.ItemStack;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

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
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) { tooltip.add(TextFormatting.BLUE + Sonos.proxy.localize(note.getEffect().getName())); }

    @Override
    public boolean showDurabilityBar(ItemStack stack)
    {
        NBTTagCompound nbt = stack.getTagCompound();
        return nbt != null && nbt.hasKey("cooldown");
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack)
    {
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt != null && nbt.hasKey("cooldown"))
        {
            String cd = nbt.getString("cooldown").split(":")[1];
            return (Double.parseDouble(cd)) / (double)note.getCooldown();
        }
        else
            return 0;
    }

    @Override
    public int getRGBDurabilityForDisplay(@Nonnull ItemStack stack) { return SonosItems.colors[note.number()]; }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        Potion effect = note.getEffect();
        ItemStack thisItem = player.getHeldItem(hand);
        NBTTagCompound nbt = new NBTTagCompound();
        if (!thisItem.hasTagCompound())
            thisItem.setTagCompound(nbt);
        else
            nbt = thisItem.getTagCompound();
        if (nbt != null && !nbt.hasKey("cooldown"))
        {
            if(effect == MobEffects.INSTANT_HEALTH || effect == MobEffects.SATURATION)
            {
                if(effect == MobEffects.INSTANT_HEALTH)
                    player.heal(2);
                else
                    player.getFoodStats().addStats(1, 1.0F);
            }
            else
                player.addPotionEffect(new PotionEffect(effect, 20 * note.getEffectDuration()));

            nbt.setString("cooldown", tick + ":" + note.getCooldown());

            world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.getSound(note, "magic"), SoundCategory.PLAYERS, 1.0f, 0.5f);
        }
        thisItem.setTagCompound(nbt);
        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt != null && stack.hasTagCompound() && nbt.hasKey("cooldown"))
        {
            String[] cd = nbt.getString("cooldown").split(":");
            if (0 >= Integer.parseInt(cd[1]))
                nbt.removeTag("cooldown");
            else if (tick >= Integer.parseInt(cd[0]) + 20)
                nbt.setString("cooldown", tick + ":" + (Integer.parseInt(cd[1]) - 1));
        }

        tick++;
    }
}
