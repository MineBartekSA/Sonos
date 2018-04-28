package com.minebarteksa.sonos.gui;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import com.minebarteksa.sonos.tileentity.ResonatorEntity;
import com.minebarteksa.sonos.blocks.SonosBlocks;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.renderer.GlStateManager;
import com.minebarteksa.sonos.Sonos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.client.gui.inventory.GuiContainer;

public class ResonatorGui extends GuiContainer
{
  private static final ResourceLocation BackGroundImage = new ResourceLocation(Sonos.ModID, "textures/gui/resonator.png");
  private InventoryPlayer playerInv;
  private World w;
  private BlockPos pos;
  private static final int ProgressBarWidth = 22;

  public ResonatorGui(Container cont, InventoryPlayer pl, BlockPos pos, World world)
  {
    super(cont);
    this.playerInv = pl;
    this.pos = pos;
    this.w = world;
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
  {
    GlStateManager.color(1, 1, 1, 1);
    mc.getTextureManager().bindTexture(BackGroundImage);
    int x = (width - xSize) / 2;
    int y = (height - ySize) / 2;
    drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    drawTexturedModalRect(x + 80, y + 35, 177, 14, calculateWidth(), 16);
  }

  private int calculateWidth()
  {
    ResonatorEntity re = (ResonatorEntity)w.getTileEntity(pos);
    int percentageOfProgress = (re.getProcess() * 100) / re.totalProcessTime;
    Sonos.log.info("Percentage Of Progress: " + percentageOfProgress + "%");
    return (percentageOfProgress * ProgressBarWidth) / 100;
    //return ProgressBarWidth;
  }

  @Override
  protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
  {
    String name = I18n.format(SonosBlocks.e.getUnlocalizedName() + ".name");
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
