package com.minebarteksa.sonos.sound;

public class SoundEvents
{
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
}
