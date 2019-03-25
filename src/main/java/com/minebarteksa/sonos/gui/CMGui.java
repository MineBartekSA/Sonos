package com.minebarteksa.sonos.gui;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.player.InventoryPlayer;
import com.minebarteksa.sonos.blocks.SonosBlocks;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.client.gui.inventory.GuiContainer;

public class CMGui extends GuiContainer
{
    private InventoryPlayer playerInv;

    public CMGui(Container arg0, InventoryPlayer pl, BlockPos pos, World world)
    {
        super(arg0);
        this.playerInv = pl;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(SonosGUIHandler.CMBack);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String name = I18n.format(SonosBlocks.cm.getUnlocalizedName() + ".name");
        fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 0x404040);
        fontRenderer.drawString(playerInv.getDisplayName().getUnformattedText(), 8, ySize - 94, 0x404040);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
}
