package com.minebarteksa.sonos.gui;

import org.lwjgl.opengl.GL11;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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

@SideOnly(Side.CLIENT)
public class ResonatorGui extends GuiContainer
{
  private static final ResourceLocation BackGroundImage = new ResourceLocation(Sonos.ModID, "textures/gui/resonator.png");
  private InventoryPlayer playerInv;
  private ResonatorEntity re;
  private static final int ProgressBarWidth = 22;

  public ResonatorGui(Container cont, InventoryPlayer pl, BlockPos pos, World world)
  {
    super(cont);
    this.playerInv = pl;
    re = (ResonatorEntity)world.getTileEntity(pos);
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
  {
    GlStateManager.color(1, 1, 1, 1);
    mc.getTextureManager().bindTexture(BackGroundImage);
    int x = (width - xSize) / 2;
    int y = (height - ySize) / 2;
    drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    drawTexturedModalRect(x + 80, y + 35, 177, 14, re.getProgressPercantage(ProgressBarWidth + 1), 16);

    GL11.glPushMatrix();
    GL11.glColor3f(1.0f, 0.0f, 0.0f);
    GL11.glTranslatef(x + 15, y + 51, 0);
    GL11.glRotatef(180, 0, 0, 1);
    GL11.glBegin(GL11.GL_QUADS);
    {
      GL11.glVertex2f(0, 0);
      GL11.glVertex2f(0, re.getEnergy(31));
      GL11.glVertex2f(10, re.getEnergy(31));
      GL11.glVertex2f(10, 0);
    }
    GL11.glEnd();
    GL11.glPopMatrix();
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
