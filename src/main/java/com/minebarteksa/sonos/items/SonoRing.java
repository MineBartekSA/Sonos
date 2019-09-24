package com.minebarteksa.sonos.items;

import com.minebarteksa.sonos.Sonos;
import com.minebarteksa.orion.items.ItemBase;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumActionResult;
import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.util.ActionResult;
import com.minebarteksa.sonos.sound.SoundEvents.Notes;
import com.minebarteksa.sonos.sound.SoundEvents;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import baubles.api.BaubleType;
import net.minecraft.item.ItemStack;
import baubles.api.IBauble;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class SonoRing extends ItemBase implements IBauble
{
    private Notes note;

    public SonoRing(Notes n)
    {
        super("sonoring_" + n.getSimpler(), Sonos.ModID);
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
        note = n;
    }

    @Override
    public BaubleType getBaubleType(ItemStack arg) { return BaubleType.RING; }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        if(!world.isRemote)
        {
            IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
            for(int i = 0; i < baubles.getSlots(); i++)
                if((baubles.getStackInSlot(i).isEmpty()) && baubles.isItemValidForSlot(i, player.getHeldItem(hand), player))
                {
                    baubles.setStackInSlot(i, player.getHeldItem(hand).copy());
                    if(!player.capabilities.isCreativeMode)
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
                    onEquipped(player.getHeldItem(hand), player);
                    break;
                }
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) { tooltip.add(TextFormatting.BLUE + Sonos.proxy.localize(note.getEffect().getName())); }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player)
    {
        if (itemstack.getItemDamage() == 0 && player.ticksExisted % 19 == 0)
        {
            Potion effect = note.getEffect();
            if(effect == MobEffects.SATURATION || effect == MobEffects.INSTANT_HEALTH)
            {
                if(!itemstack.hasTagCompound())
                {
                    NBTTagCompound nbt = new NBTTagCompound();
                    nbt.setInteger("i", 0);
                    itemstack.setTagCompound(nbt);
                }
                else if(!itemstack.getTagCompound().hasKey("i"))
                    itemstack.getTagCompound().setInteger("i", 0);
                itemstack.getTagCompound().setInteger("i", itemstack.getTagCompound().getInteger("i") + 1);
            }
            if(effect == MobEffects.INSTANT_HEALTH || effect == MobEffects.SATURATION)
            {
                if (itemstack.getTagCompound().getInteger("i") == 5)
                {
                    if(effect == MobEffects.INSTANT_HEALTH)
                        player.heal(1); // It will maybe get an amplifier
                    else
                        ((EntityPlayer)player).getFoodStats().addStats(/* amp + 1*/ 1, 1.0F);
                    itemstack.getTagCompound().setInteger("i", 0);
                }
            }
            else if(effect == MobEffects.NIGHT_VISION)
                player.addPotionEffect(new PotionEffect(effect, 220, 0, true, false));
            else
                player.addPotionEffect(new PotionEffect(effect, 20, 0, true, false));
        }
    }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) { player.playSound(SoundEvents.getSound(note, "sonar"), .75F, 2f); }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) { player.playSound(SoundEvents.getSound(note, "sonar"), .75F, 1.9f); }
}
