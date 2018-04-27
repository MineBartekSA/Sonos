package com.minebarteksa.sonos.items;

import net.minecraftforge.oredict.OreDictionary;
import com.minebarteksa.sonos.sound.SoundEvents.Notes;
import com.minebarteksa.sonos.Sonos;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class SonosItems
{
	//Unworked sono's
	public static Sono sono_c = new Sono("sono_c", Notes.C, "").setCreativeTab(Sonos.cTab);
	public static Sono sono_cs = new Sono("sono_cs", Notes.CSharp, "").setCreativeTab(Sonos.cTab);
	public static Sono sono_d = new Sono("sono_d", Notes.D, "").setCreativeTab(Sonos.cTab);
	public static Sono sono_ds = new Sono("sono_ds", Notes.DSharp, "").setCreativeTab(Sonos.cTab);
	public static Sono sono_e = new Sono("sono_e", Notes.E, "").setCreativeTab(Sonos.cTab);
	public static Sono sono_f = new Sono("sono_f", Notes.F, "").setCreativeTab(Sonos.cTab);
	public static Sono sono_fs = new Sono("sono_fs", Notes.FSharp, "").setCreativeTab(Sonos.cTab);
	public static Sono sono_g = new Sono("sono_g", Notes.G, "").setCreativeTab(Sonos.cTab);
	public static Sono sono_gs = new Sono("sono_gs", Notes.GSharp, "").setCreativeTab(Sonos.cTab);
	public static Sono sono_a = new Sono("sono_a", Notes.A, "").setCreativeTab(Sonos.cTab);
	public static Sono sono_as = new Sono("sono_as", Notes.ASharp, "").setCreativeTab(Sonos.cTab);
	public static Sono sono_b = new Sono("sono_b", Notes.B, "").setCreativeTab(Sonos.cTab);

	//Sono Primas
	public static SonoPrima sono_prima_c = new SonoPrima("sono_prima_c", Notes.C);
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

	public static void register(IForgeRegistry<Item> registry)
	{
		registry.registerAll(
		sono_c, sono_cs, sono_d, sono_ds, sono_e, sono_f, sono_fs, sono_g, sono_gs, sono_a, sono_as, sono_b,
		sono_prima_c, sono_prima_cs, sono_prima_d, sono_prima_ds, sono_prima_e, sono_prima_f, sono_prima_fs, sono_prima_g, sono_prima_gs, sono_prima_a, sono_prima_as, sono_prima_b
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
	}
}
