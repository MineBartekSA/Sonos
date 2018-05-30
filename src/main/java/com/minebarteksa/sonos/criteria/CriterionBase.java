package com.minebarteksa.sonos.criteria;

import com.minebarteksa.sonos.Sonos;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.util.ResourceLocation;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.ICriterionTrigger;

public class CriterionBase<Instance extends CriterionInstance, Listeners extends CriterionListeners<Instance>> implements ICriterionTrigger<Instance>
{
  protected ResourceLocation ID;
  protected final Map<PlayerAdvancements, Listeners> listeners = Maps.<PlayerAdvancements, Listeners>newHashMap();

  public CriterionBase(ResourceLocation id)
  {
    this.ID = id;
  }

  @Override
  public ResourceLocation getId() { return ID; }

  @Override
  public void removeListener(PlayerAdvancements playerAdvancementsIn, Listener<Instance> listener)
  {
    CriterionListeners<Instance> consumeitemtrigger$listeners = this.listeners.get(playerAdvancementsIn);

		if (consumeitemtrigger$listeners != null)
    {
			consumeitemtrigger$listeners.remove(listener);

			if (consumeitemtrigger$listeners.isEmpty())
      {
				this.listeners.remove(playerAdvancementsIn);
			}
    }
  }

  @Override
  public void removeAllListeners(PlayerAdvancements playerAdvancementsIn)
  {
    this.listeners.remove(playerAdvancementsIn);
  }

  @Override
  public void addListener(PlayerAdvancements playerAdvancementsIn, Listener<Instance> listener)
  {
    Sonos.log.error("No addListener!");
  }

  @Override
  public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context)
  {
    return null;
  }
}
