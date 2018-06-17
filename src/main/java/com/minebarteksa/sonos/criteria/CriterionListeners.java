package com.minebarteksa.sonos.criteria;

import com.minebarteksa.sonos.Sonos;
import com.google.common.collect.Lists;
import java.util.List;
import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.ICriterionTrigger;

public class CriterionListeners<Instance extends CriterionInstance>
{
  private final PlayerAdvancements playerAdvancements;
  private final Set<ICriterionTrigger.Listener<Instance>> listeners = Sets.<ICriterionTrigger.Listener<Instance>>newHashSet();

  public CriterionListeners(PlayerAdvancements pAdv)
  {
    this.playerAdvancements = pAdv;
  }

  public boolean isEmpty()
  {
    return this.listeners.isEmpty();
  }

  public void add(ICriterionTrigger.Listener<Instance> listener)
  {
    this.listeners.add(listener);
  }

  public void remove(ICriterionTrigger.Listener<Instance> listener)
  {
    this.listeners.remove(listener);
  }

  public void trigger(Object state, Class<?> type)
  {
    List<ICriterionTrigger.Listener<Instance>> list = null;

    for (ICriterionTrigger.Listener<Instance> listener : this.listeners)
    {
      if (((Instance) listener.getCriterionInstance()).test(state, type))
      {
        if (list == null)
        {
          list = Lists.<ICriterionTrigger.Listener<Instance>>newArrayList();
        }

        list.add(listener);
      }
    }

    if (list != null)
    {
      for (ICriterionTrigger.Listener<Instance> listener1 : list)
      {
        listener1.grantCriterion(this.playerAdvancements);
      }
    }
  }
}
