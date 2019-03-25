package com.minebarteksa.sonos.items;

import com.minebarteksa.orion.items.ItemBase;
import com.minebarteksa.sonos.Sonos;
import net.minecraft.entity.Entity;
import net.minecraft.client.util.ITooltipFlag;
import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import com.minebarteksa.sonos.sound.SoundEvents;
import com.minebarteksa.sonos.sound.SoundEvents.Chords;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import com.minebarteksa.sonos.sound.SoundEvents.Notes;

public class SonoPrima extends ItemBase
{
    public Notes note;

    public SonoPrima(Notes note)
    {
        super("sono_prima_" + note.getSimpler(), Sonos.ModID);
        this.note = note;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        setNBTTags(stack);
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if(stack.hasTagCompound())
        {
            tooltip.add("Quality: " + Chords.getChord(stack.getTagCompound().getInteger("quality")));
            tooltip.add("Note: " + Notes.getNote(stack.getTagCompound().getInteger("note")));
            tooltip.add("Sound type: " + stack.getTagCompound().getString("soundType"));
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack item = playerIn.getHeldItem(handIn);
        if(!item.hasTagCompound())
            SoundEvents.playChords(worldIn, playerIn, note, "sonar", 2);
        else
        {
            Sonos.log.info("Playing chord with quality: " + item.getTagCompound().getInteger("quality") + " and soundType: " + item.getTagCompound().getString("soundType").toLowerCase());
            SoundEvents.playChords(worldIn, playerIn, note, item.getTagCompound().getString("soundType").toLowerCase(), item.getTagCompound().getInteger("quality"));
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    public static ItemStack setNBTTags(ItemStack stack)
    {
        if(!stack.hasTagCompound())
        {
            NBTTagCompound tags = new NBTTagCompound();
            tags.setInteger("note", ((SonoPrima)stack.getItem()).note.number());
            tags.setInteger("quality", 0);
            tags.setString("soundType", "Sonar");
            stack.setTagCompound(tags);
        }
        else
        {
            if(!stack.getTagCompound().hasKey("note"))
                stack.getTagCompound().setInteger("note", ((SonoPrima)stack.getItem()).note.number());
            if(!stack.getTagCompound().hasKey("quality"))
                stack.getTagCompound().setInteger("quality", 0);
            if(!stack.getTagCompound().hasKey("soundType"))
                stack.getTagCompound().setString("soundType", "Sonar");
        }
        return stack;
    }

    public static SonoPrima getFromNote(Notes note) { return (SonoPrima) Item.REGISTRY.getObject(new ResourceLocation(Sonos.ModID, "sono_prima_" + note.getSimpler())); }

}
