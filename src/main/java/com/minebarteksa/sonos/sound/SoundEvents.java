package com.minebarteksa.sonos.sound;

import com.minebarteksa.orion.OrionRegistry;
import com.minebarteksa.sonos.Sonos;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class SoundEvents
{
    public static final int humLength = 14; // Value in seconds!
    public static final int ghostLength = 3;
    public static final int sonarLength = 7;
    public static final int magicLength = 4;
    public static final int machineLength = 12;

    public static SoundEvent ring_c = new SoundEvent(new ResourceLocation(Sonos.ModID, "ring_c")).setRegistryName("ring_c");

    public static void registerSounds()
    {
        OrionRegistry.register(ring_c);
        registerSoundsForNotes("sonar");
        registerSoundsForNotes("hum");
        registerSoundsForNotes("ghost");
        registerSoundsForNotes("machine");
        registerSoundsForNotes("magic");
    }

    public static void registerSoundsForNotes(String name)
    {
        for(Notes n : Notes.values())
        {
            if(n != Notes.None)
            {
                String note = n.getSimpler();
                Sonos.log.info("Registering " + name + "_" + note + " sound");
                OrionRegistry.register(new SoundEvent(new ResourceLocation(Sonos.ModID, name + "_" + note)).setRegistryName(name + "_" + note));
            }
        }
    }

    public static SoundEvent getSound(Notes note, String type)
    {
        String n = note.getSimpler();
        Sonos.log.info("Requested Sound: " + type + "_" + n);
        return SoundEvent.REGISTRY.getObject(new ResourceLocation(Sonos.ModID, type + "_" + n));
    }

    public static void playChords(World world, EntityPlayer play, Notes note, String noteType, int quality)
    {
        playChords(world, new BlockPos(play.posX, play.posY, play.posZ), SoundCategory.PLAYERS, note, noteType, quality, play);
    }

    public static void playChords(World world, BlockPos pos, SoundCategory cat, Notes note, String noteType, int quality, @Nullable EntityPlayer play)
    {
        if(quality == 0)
            world.playSound(play, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.getSound(note, noteType), cat, 1.0f, 1.0f);
        else
        {
            Sonos.log.info("Trying to play " + note + " " + Chords.getChord(quality) + " chord");
            Notes[] chord = Chords.getChordNotes(note.number(), quality);
            SoundEvent e1 = SoundEvents.getSound(chord[0], noteType);
            SoundEvent e2 = SoundEvents.getSound(chord[1], noteType);
            SoundEvent e3 = SoundEvents.getSound(chord[2], noteType);
            world.playSound(play, pos.getX(), pos.getY(), pos.getZ(), e1, cat, 1.0f, 1.0f);
            world.playSound(play, pos.getX(), pos.getY(), pos.getZ(), e2, cat, 1.0f, 1.0f);
            world.playSound(play, pos.getX(), pos.getY(), pos.getZ(), e3, cat, 1.0f, 1.0f);
        }
    }

    public enum Notes
    {
        C (0), CSharp (1), D (2), DSharp (3), E (4), F (5), FSharp (6), G (7), GSharp (8), A (9), ASharp (10), B (11), None (12);

        private final int number;

        Notes(int note)
        {
            this.number = note;
        }
        public int number() { return number; }
        public String getSimpler() { return this.toString().replace("harp", "").toLowerCase(); }
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

        public Potion getEffect()
        {
            switch(number)
            {
                case 0:
                    return MobEffects.SPEED;
                case 1:
                    return MobEffects.HASTE;
                case 2:
                    return MobEffects.STRENGTH;
                case 3:
                    return MobEffects.RESISTANCE;
                case 4:
                    return MobEffects.HEALTH_BOOST;
                case 5:
                    return MobEffects.REGENERATION;
                case 6:
                    return MobEffects.INSTANT_HEALTH; // Note: Single tick in 5 seconds period
                case 7:
                    return MobEffects.SATURATION; // Note: Single tick in 5 seconds period
                case 8:
                    return MobEffects.LUCK;
                case 9:
                    return MobEffects.FIRE_RESISTANCE;
                case 10:
                    return MobEffects.NIGHT_VISION; // Note: 10 seconds
                case 11:
                    return MobEffects.JUMP_BOOST;
                default:
                    return MobEffects.UNLUCK;
            }
        }

        public int getCooldown()
        {
            return number == 10 ? 15 : 5;
        }

        public int getEffectDuration()
        {
            switch (number)
            {
                case 6:
                case 7:
                    return 0;
                case 10:
                    return 10;
                default:
                    return 2;
            }
        }
    }

    public enum Chords
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

        public static Notes[] getChordNotes(int note, int quality)
        {
            int x;
            if(quality == 1)
                x = 2;
            else if(quality == 2)
                x = 3;
            else if(quality == 3)
                x = 4;
            else
                x = 4;
            return new Notes[] {Notes.getNote(note), Notes.getNote((note + x) % 12), Notes.getNote((note + 7) % 12)};
        }
    }
}
