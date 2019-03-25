package com.minebarteksa.sonos.items;

import com.minebarteksa.orion.OrionRegistry;
import com.minebarteksa.sonos.blocks.SonosBlocks;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import com.minebarteksa.sonos.sound.SoundEvents.Notes;
import com.minebarteksa.sonos.Sonos;
import net.minecraft.item.Item;

public class SonosItems
{
    //Notes color
    public static final Integer[] colors = new Integer[12];
    static {
        colors[Notes.C.number()] = 0x01ff03;
        colors[Notes.CSharp.number()] = 0x01ffbb;
        colors[Notes.D.number()] = 0x019dff;
        colors[Notes.DSharp.number()] = 0x0113ff;
        colors[Notes.E.number()] = 0x9d01ff;
        colors[Notes.F.number()] = 0xff01ff;
        colors[Notes.FSharp.number()] = 0xff018a;
        colors[Notes.G.number()] = 0xff0701;
        colors[Notes.GSharp.number()] = 0xff5501;
        colors[Notes.A.number()] = 0xffbb01;
        colors[Notes.ASharp.number()] = 0xfcff01;
        colors[Notes.B.number()] = 0xa9ff01;
    }

    //    C         //Color HEX int value: 01e603
    //    CSharp    //Color HEX int value: 01e7a9
    //    D         //Color HEX int value: 018ee7
    //    DSharp    //Color HEX int value: 0111e7
    //    E         //Color HEX int value: 8e01e7
    //    F         //Color HEX int value: e701e7
    //    FSharp    //Color HEX int value: e7017d
    //    G         //Color HEX int value: e70601
    //    GSharp    //Color HEX int value: e74d01
    //    A         //Color HEX int value: e7a901
    //    ASharp    //Color HEX int value: ecef01
    //    B         //Color HEX int value: 9eef01

    public static void register()
    {
        for(Notes n : Notes.values())
        {
            OrionRegistry.register(new Sono(n).setCreativeTab(Sonos.cTab));
            OrionRegistry.register(new SonoPrima(n).setCreativeTab(Sonos.cTab));
            OrionRegistry.register(new SonoRing(n).setCreativeTab(Sonos.cTab));
            OrionRegistry.register(new SonoVessel(n).setCreativeTab(Sonos.cTab));
        }
    }

    public static void registerItemColors()
    {
        ItemColors ic = Minecraft.getMinecraft().getItemColors();

        for(Notes n : Notes.values())
            ic.registerItemColorHandler((stack, tintIndex) -> tintIndex == 0 ? -1 : colors[n.number()], Item.REGISTRY.getObject(new ResourceLocation(Sonos.ModID, "sonoring_" + n.getSimpler())));
    }

    public static void initOreDictionary()
    {
        OreDictionary.registerOre("sonoOre", SonosBlocks.SO);

        for(Notes n : Notes.values()) {
            Item sono = Item.REGISTRY.getObject(new ResourceLocation(Sonos.ModID, "sono_" + n.getSimpler()));
            Item sonoPrima = Item.REGISTRY.getObject(new ResourceLocation(Sonos.ModID, "sono_prima_" + n.getSimpler()));
            Item sonoVessel = Item.REGISTRY.getObject(new ResourceLocation(Sonos.ModID, "sonovessel_" + n.getSimpler()));
            OreDictionary.registerOre("sono", sono);
            OreDictionary.registerOre("sono", sonoPrima);
            OreDictionary.registerOre("rawSono", sono);
            OreDictionary.registerOre("sonoPrima", sonoPrima);
            OreDictionary.registerOre("sonoVessel", sonoVessel);
        }
    }
}
