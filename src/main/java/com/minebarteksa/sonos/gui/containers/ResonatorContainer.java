package com.minebarteksa.sonos.gui.containers;

import com.minebarteksa.sonos.tileentity.ResonatorEntityNew;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import javax.annotation.Nonnull;

public class ResonatorContainer extends CommonContainer<ResonatorEntityNew>
{

    public ResonatorContainer(InventoryPlayer playerInv, final ResonatorEntityNew re)
    {
        super(playerInv, re);
        addSlotToContainer(new SlotItemHandler(itemHandler, 0, 56, 35)
        {
            @Override
            public void onSlotChanged() { re.markDirty(); }

            @Override
            public boolean isItemValid(@Nonnull ItemStack stack)
            {
                for(ItemStack s : OreDictionary.getOres("rawSono"))
                    if(s.getItem() == stack.getItem())
                        return true;
                return false;
            }
        });

        addSlotToContainer(new SlotItemHandler(itemHandler, 1, 116, 35)
        {
            @Override
            public void onSlotChanged() { re.markDirty(); }

            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) { return false; }
        });

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 9; j++)
                addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

        for (int k = 0; k < 9; k++)
            addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 142));
    }
}
