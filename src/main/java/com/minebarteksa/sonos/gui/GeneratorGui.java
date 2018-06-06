package com.minebarteksa.sonos.gui;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import com.minebarteksa.sonos.tileentity.GeneratorEntity;
import com.minebarteksa.sonos.blocks.SonosBlocks;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.player.InventoryPlayer;
import com.minebarteksa.sonos.Sonos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.inventory.Container;
import net.minecraft.client.gui.inventory.GuiContainer;

@SideOnly(Side.CLIENT)
public class GeneratorGui extends GuiContainer
{
  private static final ResourceLocation BackGroundImage = new ResourceLocation(Sonos.ModID, "textures/gui/generator.png");
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
    mc.getTextureManager().bindTexture(BackGroundImage);
    int x = (width - xSize) / 2;
    int y = (height - ySize) / 2;
    drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    drawTexturedModalRect(x + 80, y + 45 + (14 - ge.getPercentage(14)), 176, (14 - ge.getPercentage(14)), 14, ge.getPercentage(14));
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
