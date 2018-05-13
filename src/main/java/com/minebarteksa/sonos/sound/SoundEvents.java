package com.minebarteksa.sonos.sound;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import javax.annotation.Nullable;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.entity.player.EntityPlayer;
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

	public static SoundEvent ghost_c = new SoundEvent(new ResourceLocation(Sonos.ModID, "ghost_c")).setRegistryName("ghost_c");
	public static SoundEvent ghost_cs = new SoundEvent(new ResourceLocation(Sonos.ModID, "ghost_cs")).setRegistryName("ghost_cs");
	public static SoundEvent ghost_d = new SoundEvent(new ResourceLocation(Sonos.ModID, "ghost_d")).setRegistryName("ghost_d");
	public static SoundEvent ghost_ds = new SoundEvent(new ResourceLocation(Sonos.ModID, "ghost_ds")).setRegistryName("ghost_ds");
	public static SoundEvent ghost_e = new SoundEvent(new ResourceLocation(Sonos.ModID, "ghost_e")).setRegistryName("ghost_e");
	public static SoundEvent ghost_f = new SoundEvent(new ResourceLocation(Sonos.ModID, "ghost_f")).setRegistryName("ghost_f");
	public static SoundEvent ghost_fs = new SoundEvent(new ResourceLocation(Sonos.ModID, "ghost_fs")).setRegistryName("ghost_fs");
	public static SoundEvent ghost_g = new SoundEvent(new ResourceLocation(Sonos.ModID, "ghost_g")).setRegistryName("ghost_g");
	public static SoundEvent ghost_gs = new SoundEvent(new ResourceLocation(Sonos.ModID, "ghost_gs")).setRegistryName("ghost_gs");
	public static SoundEvent ghost_a = new SoundEvent(new ResourceLocation(Sonos.ModID, "ghost_a")).setRegistryName("ghost_a");
	public static SoundEvent ghost_as = new SoundEvent(new ResourceLocation(Sonos.ModID, "ghost_as")).setRegistryName("ghost_as");
	public static SoundEvent ghost_b = new SoundEvent(new ResourceLocation(Sonos.ModID, "ghost_b")).setRegistryName("ghost_b");

	public static SoundEvent machine_c = new SoundEvent(new ResourceLocation(Sonos.ModID, "machine_c")).setRegistryName("machine_c");
	public static SoundEvent machine_cs = new SoundEvent(new ResourceLocation(Sonos.ModID, "machine_cs")).setRegistryName("machine_cs");
	public static SoundEvent machine_d = new SoundEvent(new ResourceLocation(Sonos.ModID, "machine_d")).setRegistryName("machine_d");
	public static SoundEvent machine_ds = new SoundEvent(new ResourceLocation(Sonos.ModID, "machine_ds")).setRegistryName("machine_ds");
	public static SoundEvent machine_e = new SoundEvent(new ResourceLocation(Sonos.ModID, "machine_e")).setRegistryName("machine_e");
	public static SoundEvent machine_f = new SoundEvent(new ResourceLocation(Sonos.ModID, "machine_f")).setRegistryName("machine_f");
	public static SoundEvent machine_fs = new SoundEvent(new ResourceLocation(Sonos.ModID, "machine_fs")).setRegistryName("machine_fs");
	public static SoundEvent machine_g = new SoundEvent(new ResourceLocation(Sonos.ModID, "machine_g")).setRegistryName("machine_g");
	public static SoundEvent machine_gs = new SoundEvent(new ResourceLocation(Sonos.ModID, "machine_gs")).setRegistryName("machine_gs");
	public static SoundEvent machine_a = new SoundEvent(new ResourceLocation(Sonos.ModID, "machine_a")).setRegistryName("machine_a");
	public static SoundEvent machine_as = new SoundEvent(new ResourceLocation(Sonos.ModID, "machine_as")).setRegistryName("machine_as");
	public static SoundEvent machine_b = new SoundEvent(new ResourceLocation(Sonos.ModID, "machine_b")).setRegistryName("machine_b");

	public static void registerSounds(IForgeRegistry<SoundEvent> registry)
	{
		registry.registerAll(
		sonar_c, sonar_cs, sonar_d, sonar_ds, sonar_e, sonar_f, sonar_fs, sonar_g, sonar_gs, sonar_a, sonar_as, sonar_b,
		hum_c, hum_cs, hum_d, hum_ds, hum_e, hum_f, hum_fs, hum_g, hum_gs, hum_a, hum_as, hum_b,
		ring_c,
		ghost_c, ghost_cs, ghost_d, ghost_ds, ghost_e, ghost_f, ghost_fs, ghost_g, ghost_gs, ghost_a, ghost_as, ghost_b,
		machine_c, machine_cs, machine_d, machine_ds, machine_e, machine_f, machine_fs, machine_g, machine_gs, machine_a, machine_as, machine_b
		);
	}

	public static SoundEvent getSound(Notes note, String type)
	{
		String n = note.toString();
		n = n.replace("harp", "");
		n = n.toLowerCase();
		Sonos.log.info("Requested Sound: " + type + "_" + n);
		return SoundEvent.REGISTRY.getObject(new ResourceLocation(Sonos.ModID, type + "_" + n));
	}

	public static void playChords(World world, EntityPlayer play, Notes note, String noteType, int quality)
	{
		playChords(world, new BlockPos(play.posX, play.posY, play.posZ), SoundCategory.PLAYERS, note, noteType, quality, play);
	}

	public static void playChords(World world, BlockPos pos, SoundCategory cat, Notes note, String noteType, int quality, @Nullable EntityPlayer play)
	{
		int[] chord = Chords.getChordNotes(note.Number(), quality);
		SoundEvent e1 = SoundEvents.getSound(Notes.getNote(chord[0]), noteType);
		SoundEvent e2 = SoundEvents.getSound(Notes.getNote(chord[1]), noteType);
		SoundEvent e3 = SoundEvents.getSound(Notes.getNote(chord[2]), noteType);
		world.playSound(play, pos.getX(), pos.getY(), pos.getZ(), e1, cat, 1.0f, 1.0f);
		world.playSound(play, pos.getX(), pos.getY(), pos.getZ(), e2, cat, 1.0f, 1.0f);
		world.playSound(play, pos.getX(), pos.getY(), pos.getZ(), e3, cat, 1.0f, 1.0f);
	}

	public static enum Notes
	{
		C (0), CSharp (1), D (2), DSharp (3), E (4), F (5), FSharp (6), G (7), GSharp (8), A (9), ASharp (10), B (11), None (12);

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
				case 0:
					return Notes.C;
				case 1:
					return Notes.CSharp;
				case 2:
					return Notes.D;
				case 3:
					return Notes.DSharp;
				case 4:
					return Notes.E;
				case 5:
					return Notes.F;
				case 6:
					return Notes.FSharp;
				case 7:
					return Notes.G;
				case 8:
					return Notes.GSharp;
				case 9:
					return Notes.A;
				case 10:
					return Notes.ASharp;
				case 11:
					return Notes.B;
				default:
					return Notes.None;
			}
		}
	}

	public static enum Chords
	{
		Root (0), Sus2 (1), Minor (2), Major (3), Sus4 (4);

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
					return Chords.Sus2;
				case 2:
					return Chords.Minor;
				case 3:
					return Chords.Major;
				case 4:
					return Chords.Sus4;
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
			int[] out = {note, (note + x) % 12, (note + 7) % 12};
			return out;
		}
	}

	public static Potion getNoteEffect(Notes note)
	{
		switch(note.toString())
		{
			case "C":
        return MobEffects.SPEED;
      case "CSharp":
        return MobEffects.HASTE;
      case "D":
        return MobEffects.STRENGTH;
      case "DSharp":
        return MobEffects.RESISTANCE;
      case "E":
        return MobEffects.HEALTH_BOOST;
      case "F":
        return MobEffects.REGENERATION;
      case "FSharp":
        return MobEffects.INSTANT_HEALTH;
      case "G":
        return MobEffects.SATURATION;
      case "GSharp":
        return MobEffects.LUCK;
      case "A":
        return MobEffects.FIRE_RESISTANCE;
      case "ASharp":
        return MobEffects.NIGHT_VISION;
      case "B":
        return MobEffects.JUMP_BOOST;
			default:
				return MobEffects.UNLUCK;
		}
	}
}
