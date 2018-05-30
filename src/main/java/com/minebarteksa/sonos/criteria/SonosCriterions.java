package com.minebarteksa.sonos.criteria;

import net.minecraft.advancements.CriteriaTriggers;

public class SonosCriterions
{
  //public static MinedSonoOre MSO = new MinedSonoOre();
  public static MinedSonoOreNew MSON = new MinedSonoOreNew();

  public static void registerCriterions()
  {
    //CriteriaTriggers.register(MSO);
    CriteriaTriggers.register(MSON);
  }
}
