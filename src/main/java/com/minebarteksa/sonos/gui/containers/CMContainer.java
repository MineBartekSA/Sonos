package com.minebarteksa.sonos.gui.containers;

import com.minebarteksa.sonos.tileentity.ModulatorEntity;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import javax.annotation.Nonnull;

public class CMContainer extends CommonContainer<ModulatorEntity>
{
    public CMContainer(InventoryPlayer playerInv, final ModulatorEntity re)
    {
        super(playerInv, re);
        addSlotToContainer(new SlotItemHandler(itemHandler, 0, 80, 35)
        {
            @Override
            public void onSlotChanged() { re.markDirty(); }

            @Override
            public int getSlotStackLimit() { return 1; }

            @Override
            public int getItemStackLimit(@Nonnull ItemStack stack) { return 1; }

            @Override
            public boolean isItemValid(@Nonnull ItemStack stack)
            {
                for(ItemStack s : OreDictionary.getOres("sonoPrima"))
                    if(s.getItem() == stack.getItem())
                        return true;
                return false;
            }
        });

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 9; j++)
                addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

        for (int k = 0; k < 9; k++)
            addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 142));
    }
}
