package com.minebarteksa.sonos.sonosproxy;

import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@SuppressWarnings("deprecation")
public class SonosCommon
{
  public void PreInit(FMLPreInitializationEvent ev)
  {

  }

  public void Init(FMLInitializationEvent ev)
  {

  }

  public void PostInit(FMLPostInitializationEvent ev)
  {

  }

  public void registerItemRenderer(Item item, int meta, String id)
  {

  }

  public String localize(String unlocalized, Object... args)
  {
    return I18n.translateToLocalFormatted(unlocalized, args);
  }
}
