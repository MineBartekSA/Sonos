package com.minebarteksa.sonos.gui.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import javax.annotation.Nonnull;

public class CommonContainer<T extends TileEntity> extends Container
{
    final IItemHandler itemHandler;
    EnumFacing handlerFacing = EnumFacing.UP;

    CommonContainer(InventoryPlayer playerInv, final T tile) // TODO: Auto add player inventory slots
    {
        itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, handlerFacing);
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) { return true; }

    @Override
    @Nonnull
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack item = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);

        if(slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            item = itemstack1.copy();

            int containerSlots = inventorySlots.size() - playerIn.inventory.mainInventory.size();

            if (index < containerSlots)
                if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true))
                    return ItemStack.EMPTY;
                else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false))
                    return ItemStack.EMPTY;

            if (itemstack1.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == item.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return item;
    }
}
