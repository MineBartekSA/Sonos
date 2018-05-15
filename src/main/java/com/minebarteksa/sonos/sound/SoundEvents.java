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
	public static SoundEvent ring_c = new SoundEvent(new ResourceLocation(Sonos.ModID, "ring_c")).setRegistryName("ring_c");

	public static void registerSounds(IForgeRegistry<SoundEvent> registry)
	{
		registry.registerAll(ring_c);
		registerSoundsForNotes("sonar", registry);
		registerSoundsForNotes("hum", registry);
		registerSoundsForNotes("ghost", registry);
		registerSoundsForNotes("machine", registry);
	}

	public static void registerSoundsForNotes(String name, IForgeRegistry<SoundEvent> reg)
	{
		for(Notes n : Notes.values())
		{
			if(n != Notes.None)
			{
				String note = n.toString().replace("harp", "").toLowerCase();
				Sonos.log.info("Registering " + name + "_" + note + " sound");
				reg.register(new SoundEvent(new ResourceLocation(Sonos.ModID, name + "_" + note)).setRegistryName(name + "_" + note));
			}
		}
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
