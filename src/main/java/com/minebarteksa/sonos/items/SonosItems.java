package com.minebarteksa.sonos.items;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.Minecraft;
import net.minecraftforge.oredict.OreDictionary;
import com.minebarteksa.sonos.sound.SoundEvents.Notes;
import com.minebarteksa.sonos.Sonos;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class SonosItems
{
	//Unworked Sonos
	public static Sono sono_c = new Sono("sono_c", Notes.C, "").setCreativeTab(Sonos.cTab);       //Color HEX int value: 01e603
	public static Sono sono_cs = new Sono("sono_cs", Notes.CSharp, "").setCreativeTab(Sonos.cTab);//Color HEX int value: 01e7a9
	public static Sono sono_d = new Sono("sono_d", Notes.D, "").setCreativeTab(Sonos.cTab);       //Color HEX int value: 018ee7
	public static Sono sono_ds = new Sono("sono_ds", Notes.DSharp, "").setCreativeTab(Sonos.cTab);//Color HEX int value: 0111e7
	public static Sono sono_e = new Sono("sono_e", Notes.E, "").setCreativeTab(Sonos.cTab);       //Color HEX int value: 8e01e7
	public static Sono sono_f = new Sono("sono_f", Notes.F, "").setCreativeTab(Sonos.cTab);       //Color HEX int value: e701df
	public static Sono sono_fs = new Sono("sono_fs", Notes.FSharp, "").setCreativeTab(Sonos.cTab);//Color HEX int value: e7017d
	public static Sono sono_g = new Sono("sono_g", Notes.G, "").setCreativeTab(Sonos.cTab);       //Color HEX int value: e70601
	public static Sono sono_gs = new Sono("sono_gs", Notes.GSharp, "").setCreativeTab(Sonos.cTab);//Color HEX int value: e74d01
	public static Sono sono_a = new Sono("sono_a", Notes.A, "").setCreativeTab(Sonos.cTab);       //Color HEX int value: e7a901
	public static Sono sono_as = new Sono("sono_as", Notes.ASharp, "").setCreativeTab(Sonos.cTab);//Color HEX int value: ecef01
	public static Sono sono_b = new Sono("sono_b", Notes.B, "").setCreativeTab(Sonos.cTab);       //Color HEX int value: 9eef01

	//Sono Primas
	public static SonoPrima sono_prima_c = new SonoPrima("sono_prima_c", Notes.C).setCreativeTab(Sonos.cTab);
	public static SonoPrima sono_prima_cs = new SonoPrima("sono_prima_cs", Notes.CSharp).setCreativeTab(Sonos.cTab);
	public static SonoPrima sono_prima_d = new SonoPrima("sono_prima_d", Notes.D).setCreativeTab(Sonos.cTab);
	public static SonoPrima sono_prima_ds = new SonoPrima("sono_prima_ds", Notes.DSharp).setCreativeTab(Sonos.cTab);
	public static SonoPrima sono_prima_e = new SonoPrima("sono_prima_e", Notes.E).setCreativeTab(Sonos.cTab);
	public static SonoPrima sono_prima_f = new SonoPrima("sono_prima_f", Notes.F).setCreativeTab(Sonos.cTab);
	public static SonoPrima sono_prima_fs = new SonoPrima("sono_prima_fs", Notes.FSharp).setCreativeTab(Sonos.cTab);
	public static SonoPrima sono_prima_g = new SonoPrima("sono_prima_g", Notes.G).setCreativeTab(Sonos.cTab);
	public static SonoPrima sono_prima_gs = new SonoPrima("sono_prima_gs", Notes.GSharp).setCreativeTab(Sonos.cTab);
	public static SonoPrima sono_prima_a = new SonoPrima("sono_prima_a", Notes.A).setCreativeTab(Sonos.cTab);
	public static SonoPrima sono_prima_as = new SonoPrima("sono_prima_as", Notes.ASharp).setCreativeTab(Sonos.cTab);
	public static SonoPrima sono_prima_b = new SonoPrima("sono_prima_b", Notes.B).setCreativeTab(Sonos.cTab);

	//SonoRings
	public static SonoRing cRing = new SonoRing("sonoring_c", Notes.C).setCreativeTab(Sonos.cTab);
	public static SonoRing csRing = new SonoRing("sonoring_cs", Notes.CSharp).setCreativeTab(Sonos.cTab);
	public static SonoRing dRing = new SonoRing("sonoring_d", Notes.D).setCreativeTab(Sonos.cTab);
	public static SonoRing dsRing = new SonoRing("sonoring_ds", Notes.DSharp).setCreativeTab(Sonos.cTab);
	public static SonoRing eRing = new SonoRing("sonoring_e", Notes.E).setCreativeTab(Sonos.cTab);
	public static SonoRing fRing = new SonoRing("sonoring_f", Notes.F).setCreativeTab(Sonos.cTab);
	public static SonoRing fsRing = new SonoRing("sonoring_fs", Notes.FSharp).setCreativeTab(Sonos.cTab);
	public static SonoRing gRing = new SonoRing("sonoring_g", Notes.G).setCreativeTab(Sonos.cTab);
	public static SonoRing gsRing = new SonoRing("sonoring_gs", Notes.GSharp).setCreativeTab(Sonos.cTab);
	public static SonoRing aRing = new SonoRing("sonoring_a", Notes.A).setCreativeTab(Sonos.cTab);
	public static SonoRing asRing = new SonoRing("sonoring_as", Notes.ASharp).setCreativeTab(Sonos.cTab);
	public static SonoRing bRing = new SonoRing("sonoring_b", Notes.B).setCreativeTab(Sonos.cTab);

	public static void register(IForgeRegistry<Item> registry)
	{
		registry.registerAll(
		sono_c, sono_cs, sono_d, sono_ds, sono_e, sono_f, sono_fs, sono_g, sono_gs, sono_a, sono_as, sono_b,
		sono_prima_c, sono_prima_cs, sono_prima_d, sono_prima_ds, sono_prima_e, sono_prima_f, sono_prima_fs, sono_prima_g, sono_prima_gs, sono_prima_a, sono_prima_as, sono_prima_b,
		cRing, csRing, dRing, dsRing, eRing, fRing, fsRing, gRing, gsRing, aRing, asRing, bRing
		);
	}

	public static void registerModels()
	{
		sono_c.registerItemModel();
		sono_cs.registerItemModel();
		sono_d.registerItemModel();
		sono_ds.registerItemModel();
		sono_e.registerItemModel();
		sono_f.registerItemModel();
		sono_fs.registerItemModel();
		sono_g.registerItemModel();
		sono_gs.registerItemModel();
		sono_a.registerItemModel();
		sono_as.registerItemModel();
		sono_b.registerItemModel();
		sono_prima_c.registerItemModel();
		sono_prima_cs.registerItemModel();
		sono_prima_d.registerItemModel();
		sono_prima_ds.registerItemModel();
		sono_prima_e.registerItemModel();
		sono_prima_f.registerItemModel();
		sono_prima_fs.registerItemModel();
		sono_prima_g.registerItemModel();
		sono_prima_gs.registerItemModel();
		sono_prima_a.registerItemModel();
		sono_prima_as.registerItemModel();
		sono_prima_b.registerItemModel();
		cRing.registerItemModel();
		csRing.registerItemModel();
		dRing.registerItemModel();
		dsRing.registerItemModel();
		eRing.registerItemModel();
		fRing.registerItemModel();
		fsRing.registerItemModel();
		gRing.registerItemModel();
		gsRing.registerItemModel();
		aRing.registerItemModel();
		asRing.registerItemModel();
		bRing.registerItemModel();
	}

	public static void registerItemColors()
	{
		ItemColors ic = Minecraft.getMinecraft().getItemColors();

		ic.registerItemColorHandler(new IItemColor(){
			public int colorMultiplier(ItemStack stack, int tintIndex)
			{
				return tintIndex == 0 ? -1 : 0x01e603;
			}
		}, cRing);

		ic.registerItemColorHandler(new IItemColor(){
			public int colorMultiplier(ItemStack stack, int tintIndex)
			{
				return tintIndex == 0 ? -1 : 0x01e7a9;
			}
		}, csRing);

		ic.registerItemColorHandler(new IItemColor(){
			public int colorMultiplier(ItemStack stack, int tintIndex)
			{
				return tintIndex == 0 ? -1 : 0x018ee7;
			}
		}, dRing);

		ic.registerItemColorHandler(new IItemColor(){
			public int colorMultiplier(ItemStack stack, int tintIndex)
			{
				return tintIndex == 0 ? -1 : 0x0111e7;
			}
		}, dsRing);

		ic.registerItemColorHandler(new IItemColor(){
			public int colorMultiplier(ItemStack stack, int tintIndex)
			{
				return tintIndex == 0 ? -1 : 0x8e01e7;
			}
		}, eRing);

		ic.registerItemColorHandler(new IItemColor(){
			public int colorMultiplier(ItemStack stack, int tintIndex)
			{
				return tintIndex == 0 ? -1 : 0xe701df;
			}
		}, fRing);

		ic.registerItemColorHandler(new IItemColor(){
			public int colorMultiplier(ItemStack stack, int tintIndex)
			{
				return tintIndex == 0 ? -1 : 0xe7017d;
			}
		}, fsRing);

		ic.registerItemColorHandler(new IItemColor(){
			public int colorMultiplier(ItemStack stack, int tintIndex)
			{
				return tintIndex == 0 ? -1 : 0xe70601;
			}
		}, gRing);

		ic.registerItemColorHandler(new IItemColor(){
			public int colorMultiplier(ItemStack stack, int tintIndex)
			{
				return tintIndex == 0 ? -1 : 0xe74d01;
			}
		}, gsRing);

		ic.registerItemColorHandler(new IItemColor(){
			public int colorMultiplier(ItemStack stack, int tintIndex)
			{
				return tintIndex == 0 ? -1 : 0xe7a901;
			}
		}, aRing);

		ic.registerItemColorHandler(new IItemColor(){
			public int colorMultiplier(ItemStack stack, int tintIndex)
			{
				return tintIndex == 0 ? -1 : 0xecef01;
			}
		}, asRing);

		ic.registerItemColorHandler(new IItemColor(){
			public int colorMultiplier(ItemStack stack, int tintIndex)
			{
				return tintIndex == 0 ? -1 : 0x9eef01;
			}
		}, bRing);
	}

	public static Sono getSonoFormNote(Notes note)
	{
		switch(note.toString())
		{
			case "C":
        return sono_c;
      case "CSharp":
        return sono_cs;
      case "D":
        return sono_d;
      case "DSharp":
        return sono_ds;
      case "E":
        return sono_e;
      case "F":
        return sono_f;
      case "FSharp":
        return sono_fs;
      case "G":
        return sono_g;
      case "GSharp":
        return sono_gs;
      case "A":
        return sono_a;
      case "ASharp":
        return sono_as;
      case "B":
        return sono_b;
			default:
				return null;
		}
	}

	public static SonoPrima getSonoPrimaFormNote(Notes note)
	{
		switch(note.toString())
		{
			case "C":
				return sono_prima_c;
			case "CSharp":
				return sono_prima_cs;
			case "D":
				return sono_prima_d;
			case "DSharp":
				return sono_prima_ds;
			case "E":
				return sono_prima_e;
			case "F":
				return sono_prima_f;
			case "FSharp":
				return sono_prima_fs;
			case "G":
				return sono_prima_g;
			case "GSharp":
				return sono_prima_gs;
			case "A":
				return sono_prima_a;
			case "ASharp":
				return sono_prima_as;
			case "B":
				return sono_prima_b;
			default:
				return null;
		}
	}

	public static void initOreDictionary()
	{
		OreDictionary.registerOre("sono", sono_c);
		OreDictionary.registerOre("sono", sono_cs);
		OreDictionary.registerOre("sono", sono_d);
		OreDictionary.registerOre("sono", sono_ds);
		OreDictionary.registerOre("sono", sono_e);
		OreDictionary.registerOre("sono", sono_f);
		OreDictionary.registerOre("sono", sono_fs);
		OreDictionary.registerOre("sono", sono_g);
		OreDictionary.registerOre("sono", sono_gs);
		OreDictionary.registerOre("sono", sono_a);
		OreDictionary.registerOre("sono", sono_as);
		OreDictionary.registerOre("sono", sono_b);
		OreDictionary.registerOre("sono", sono_prima_c);
		OreDictionary.registerOre("sono", sono_prima_cs);
		OreDictionary.registerOre("sono", sono_prima_d);
		OreDictionary.registerOre("sono", sono_prima_ds);
		OreDictionary.registerOre("sono", sono_prima_e);
		OreDictionary.registerOre("sono", sono_prima_f);
		OreDictionary.registerOre("sono", sono_prima_fs);
		OreDictionary.registerOre("sono", sono_prima_g);
		OreDictionary.registerOre("sono", sono_prima_gs);
		OreDictionary.registerOre("sono", sono_prima_a);
		OreDictionary.registerOre("sono", sono_prima_as);
		OreDictionary.registerOre("sono", sono_prima_b);

		OreDictionary.registerOre("rawSono", sono_c);
		OreDictionary.registerOre("rawSono", sono_cs);
		OreDictionary.registerOre("rawSono", sono_d);
		OreDictionary.registerOre("rawSono", sono_ds);
		OreDictionary.registerOre("rawSono", sono_e);
		OreDictionary.registerOre("rawSono", sono_f);
		OreDictionary.registerOre("rawSono", sono_fs);
		OreDictionary.registerOre("rawSono", sono_g);
		OreDictionary.registerOre("rawSono", sono_gs);
		OreDictionary.registerOre("rawSono", sono_a);
		OreDictionary.registerOre("rawSono", sono_as);
		OreDictionary.registerOre("rawSono", sono_b);

		OreDictionary.registerOre("sonoPrima", sono_prima_c);
		OreDictionary.registerOre("sonoPrima", sono_prima_cs);
		OreDictionary.registerOre("sonoPrima", sono_prima_d);
		OreDictionary.registerOre("sonoPrima", sono_prima_ds);
		OreDictionary.registerOre("sonoPrima", sono_prima_e);
		OreDictionary.registerOre("sonoPrima", sono_prima_f);
		OreDictionary.registerOre("sonoPrima", sono_prima_fs);
		OreDictionary.registerOre("sonoPrima", sono_prima_g);
		OreDictionary.registerOre("sonoPrima", sono_prima_gs);
		OreDictionary.registerOre("sonoPrima", sono_prima_a);
		OreDictionary.registerOre("sonoPrima", sono_prima_as);
		OreDictionary.registerOre("sonoPrima", sono_prima_b);
	}
}
