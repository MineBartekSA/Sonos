package com.minebarteksa.sonos.sound;

import com.minebarteksa.sonos.Sonos;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class SoundEvents
{
	public static SoundEvent sonar_c = new SoundEvent(new ResourceLocation(Sonos.ModID, "sonar_c")).setRegistryName("sonar_c");
	public static SoundEvent sonar_cs = new SoundEvent(new ResourceLocation(Sonos.ModID, "sonar_cs")).setRegistryName("sonar_cs");
	public static SoundEvent sonar_d = new SoundEvent(new ResourceLocation(Sonos.ModID, "sonar_d")).setRegistryName("sonar_d");
	public static SoundEvent sonar_ds = new SoundEvent(new ResourceLocation(Sonos.ModID, "sonar_ds")).setRegistryName("sonar_ds");
	public static SoundEvent sonar_e = new SoundEvent(new ResourceLocation(Sonos.ModID, "sonar_e")).setRegistryName("sonar_e");
	public static SoundEvent sonar_f = new SoundEvent(new ResourceLocation(Sonos.ModID, "sonar_f")).setRegistryName("sonar_f");
	public static SoundEvent sonar_fs = new SoundEvent(new ResourceLocation(Sonos.ModID, "sonar_fs")).setRegistryName("sonar_fs");
	public static SoundEvent sonar_g = new SoundEvent(new ResourceLocation(Sonos.ModID, "sonar_g")).setRegistryName("sonar_g");
	public static SoundEvent sonar_gs = new SoundEvent(new ResourceLocation(Sonos.ModID, "sonar_gs")).setRegistryName("sonar_gs");
	public static SoundEvent sonar_a = new SoundEvent(new ResourceLocation(Sonos.ModID, "sonar_a")).setRegistryName("sonar_a");
	public static SoundEvent sonar_as = new SoundEvent(new ResourceLocation(Sonos.ModID, "sonar_as")).setRegistryName("sonar_as");
	public static SoundEvent sonar_b = new SoundEvent(new ResourceLocation(Sonos.ModID, "sonar_b")).setRegistryName("sonar_b");
	public static SoundEvent hum_c = new SoundEvent(new ResourceLocation(Sonos.ModID, "hum_c")).setRegistryName("hum_c");
	public static SoundEvent hum_cs = new SoundEvent(new ResourceLocation(Sonos.ModID, "hum_cs")).setRegistryName("hum_cs");
	public static SoundEvent hum_d = new SoundEvent(new ResourceLocation(Sonos.ModID, "hum_d")).setRegistryName("hum_d");
	public static SoundEvent hum_ds = new SoundEvent(new ResourceLocation(Sonos.ModID, "hum_c")).setRegistryName("hum_ds");
	public static SoundEvent hum_e = new SoundEvent(new ResourceLocation(Sonos.ModID, "hum_e")).setRegistryName("hum_e");
	public static SoundEvent hum_f = new SoundEvent(new ResourceLocation(Sonos.ModID, "hum_f")).setRegistryName("hum_f");
	public static SoundEvent hum_fs = new SoundEvent(new ResourceLocation(Sonos.ModID, "hum_c")).setRegistryName("hum_fs");
	public static SoundEvent hum_g = new SoundEvent(new ResourceLocation(Sonos.ModID, "hum_g")).setRegistryName("hum_g");
	public static SoundEvent hum_gs = new SoundEvent(new ResourceLocation(Sonos.ModID, "hum_c")).setRegistryName("hum_gs");
	public static SoundEvent hum_a = new SoundEvent(new ResourceLocation(Sonos.ModID, "hum_a")).setRegistryName("hum_a");
	public static SoundEvent hum_as = new SoundEvent(new ResourceLocation(Sonos.ModID, "hum_c")).setRegistryName("hum_as");
	public static SoundEvent hum_b = new SoundEvent(new ResourceLocation(Sonos.ModID, "hum_b")).setRegistryName("hum_b");
	public static SoundEvent ring_c = new SoundEvent(new ResourceLocation(Sonos.ModID, "ring_c")).setRegistryName("ring_c");

	public static void registerSounds(IForgeRegistry<SoundEvent> registry)
	{
		registry.registerAll(
		sonar_c, sonar_cs, sonar_d, sonar_ds, sonar_e, sonar_f, sonar_fs, sonar_g, sonar_gs, sonar_a, sonar_as, sonar_b,
		hum_c, hum_cs, hum_d, hum_ds, hum_e, hum_f, hum_fs, hum_g, hum_gs, hum_a, hum_as, hum_b,
		ring_c
		);
	}

	public static SoundEvent getSound(Notes note, String type)
	{
		String n = note.toString();
		n = n.replace("harp", "");
		n = n.toLowerCase();
		Sonos.log.info("Requested Soudn: " + type + "_" + n);
		return SoundEvent.REGISTRY.getObject(new ResourceLocation(Sonos.ModID, type + "_" + n));
	}

	public static enum Notes
	{
		None (0), C (1), CSharp (2), D (3), DSharp (4), E (5), F (6), FSharp (7), G (8), GSharp (9), A (10), ASharp (11), B (12);

		private final int number;

		Notes(int note)
		{
			this.number = note;
		}
		public int Number() { return number; }
		public static Notes getNote(int num)
		{
			switch(num)
			{
				case 1:
					return Notes.C;
				case 2:
					return Notes.CSharp;
				case 3:
					return Notes.D;
				case 4:
					return Notes.DSharp;
				case 5:
					return Notes.E;
				case 6:
					return Notes.F;
				case 7:
					return Notes.FSharp;
				case 8:
					return Notes.G;
				case 9:
					return Notes.GSharp;
				case 10:
					return Notes.A;
				case 11:
					return Notes.ASharp;
				case 12:
					return Notes.B;
				default:
					return Notes.None;
			}
		}
	}

	public static enum Chords
	{
		Root (0), Diminished (1), Minor (2), Major (3), Augmented (4);

		private final int number;

		Chords(int quality)
		{
			this.number = quality;
		}

		public int Number()
		{
			return number;
		}

		public static Chords getChord(int num)
		{
			switch(num)
			{
				case 0:
					return Chords.Root;
				case 1:
					return Chords.Diminished;
				case 2:
					return Chords.Minor;
				case 3:
					return Chords.Major;
				case 4:
					return Chords.Augmented;
				default:
					return Chords.Root;
			}
		}

		public static int[] getChordNotes(int note, int quality)
		{
			int x;
			switch(quality)
			{
				case 1:
					x = 2;
				case 2:
					x = 3;
				case 3:
					x = 4;
				case 4:
					x = 5;
				default:
					x = 4;
			}
			int[] out = {note, note + x, note + 7};
			return out;
		}
	}

	/*public static Dictionary<BlockPos, ISound> playingSounds = new Dictionary<BlockPos, ISound>() {

	};*/
}
