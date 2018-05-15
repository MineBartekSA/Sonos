package com.minebarteksa.sonos.criterions;

import net.minecraft.util.ResourceLocation;
import net.minecraft.advancements.critereon.AbstractCriterionInstance;

public class CriterionInstance extends AbstractCriterionInstance
{
  public CriterionInstance(ResourceLocation id)
  {
    super(id);
  }

  public boolean test(Object test, Class<?> type)
  {
    return false;
  }
}
