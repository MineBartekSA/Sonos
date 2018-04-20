package com.minebarteksa.sonos.sound;

public class SoundEvents 
{
	public static enum Notes
	{
		C (1), CSharp (2), D (3), DSharp (4), E (5), F (6), FSharp (7), G (8), GSharp (9), A (10), ASharp (11), B (12);
		
		private final int number;
		
		Notes(int note)
		{
			this.number = note;
		}
		public int Number() { return number; }
	}
}
