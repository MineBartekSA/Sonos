package com.minebarteksa.sonos.criterions;

import net.minecraft.advancements.CriteriaTriggers;

public class SonosCriterions
{
  public static MinedSonoOre MSO = new MinedSonoOre();

  public static void registerCriterions()
  {
    CriteriaTriggers.register(MSO);
  }
}
