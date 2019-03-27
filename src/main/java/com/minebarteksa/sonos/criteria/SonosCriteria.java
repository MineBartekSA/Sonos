package com.minebarteksa.sonos.criteria;

import net.minecraft.advancements.CriteriaTriggers;

public class SonosCriteria
{
    public static MinedSonoOre MSON = new MinedSonoOre();
    public static OpenGuide OG = new OpenGuide();

    public static void registerCriteria()
    {
        CriteriaTriggers.register(MSON);
        CriteriaTriggers.register(OG);
    }
}
