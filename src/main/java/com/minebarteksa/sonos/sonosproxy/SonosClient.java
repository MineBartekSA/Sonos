package com.minebarteksa.sonos.sonosproxy;

import com.minebarteksa.sonos.Sonos;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class SonosClient extends SonosCommon
{
    @Override
    public void PreInit(FMLPreInitializationEvent ev) { super.PreInit(ev); }

    @Override
    public void Init(FMLInitializationEvent ev) { super.Init(ev); }

    @Override
    public void PostInit(FMLPostInitializationEvent ev) { super.PostInit(ev); }

    @Override
    public void registerItemRenderer(Item item, int meta, String id) { ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Sonos.ModID + ":" + id, "inventory")); }

    @Override
    public String localize(String unlocalized, Object... args) { return I18n.format(unlocalized, args); }
}
