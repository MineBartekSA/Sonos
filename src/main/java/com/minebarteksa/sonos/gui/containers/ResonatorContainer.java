package com.minebarteksa.sonos.gui.containers;

import net.minecraftforge.items.SlotItemHandler;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import com.minebarteksa.sonos.tileEntitys.ResonatorEntity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ResonatorContainer extends Container
{
  public ResonatorContainer(InventoryPlayer playerInv, final ResonatorEntity re)
  {
    IItemHandler iHand = re.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
    addSlotToContainer(new SlotItemHandler(iHand, 0, 80, 35)
    {
      @Override
      public void onSlotChanged() {
        re.markDirty();
      }
    });

    addSlotToContainer(new SlotItemHandler(iHand, 1, 160, 35)
    {
      @Override
      public void onSlotChanged() {
        re.markDirty();
      }
    });

    for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; k++) {
			addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 142));
		}
  }

  @Override
  public boolean canInteractWith(EntityPlayer playerIn)
  {
    return false;
  }

  @Override
  public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
  {
    ItemStack item = ItemStack.EMPTY;
    Slot slot = inventorySlots.get(index);

    if(slot != null && slot.getHasStack())
    {
      ItemStack itemstack1 = slot.getStack();
      item = itemstack1.copy();

			int containerSlots = inventorySlots.size() - playerIn.inventory.mainInventory.size();

			if (index < containerSlots) {
				if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false)) {
				return ItemStack.EMPTY;
			}

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
