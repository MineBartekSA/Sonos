package com.minebarteksa.sonos.gui;

import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import com.minebarteksa.sonos.tileentity.GeneratorEntity;
import com.minebarteksa.sonos.blocks.SonosBlocks;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.client.gui.inventory.GuiContainer;

@SideOnly(Side.CLIENT)
public class GeneratorGui extends GuiContainer
{
  private InventoryPlayer playerInv;
  private GeneratorEntity ge;

  public GeneratorGui(Container arg0, InventoryPlayer pl, BlockPos pos, World world)
  {
    super(arg0);
    this.playerInv = pl;
    ge = (GeneratorEntity)world.getTileEntity(pos);
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
  {
    GlStateManager.color(1, 1, 1, 1);
    mc.getTextureManager().bindTexture(SonosGUIHandler.GeneratorBack);
    int x = (width - xSize) / 2;
    int y = (height - ySize) / 2;
    drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    drawTexturedModalRect(x + 80, y + 45 + (14 - ge.getPercentage(14)), 176, (14 - ge.getPercentage(14)), 14, ge.getPercentage(14));
    GlStateManager.pushMatrix();
    GlStateManager.translate(x, y, 0);
    drawEnergyBar(5, 13);
    GlStateManager.popMatrix();
  }

  private void drawEnergyBar(int x, int y)
  {
    int a = getScaledEnergy();
    mc.renderEngine.bindTexture(SonosGUIHandler.EnergyBar);
    drawTexturedModalRect(x, y, 0, 0, 16, 53);
    drawTexturedModalRect(x, y + (53 - a), 16, 53 - a, 16, a);
  }

  private int getScaledEnergy()
  {
    IEnergyStorage e = ge.getEnergyStorage();
    return (((e.getEnergyStored() * 100) / 50000) * 53) / 100;
  }

  @Override
  protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
  {
    String name = I18n.format(SonosBlocks.rf.getUnlocalizedName() + ".name");
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
