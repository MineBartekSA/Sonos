package com.minebarteksa.sonos.criteria;

import com.minebarteksa.orion.criteria.CriterionInstance;
import com.minebarteksa.orion.criteria.CriterionListeners;
import com.minebarteksa.orion.criteria.CriterionBase;
import net.minecraft.entity.player.EntityPlayerMP;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.minebarteksa.sonos.Sonos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.advancements.PlayerAdvancements;

public class OpenGuide extends CriterionBase<OpenGuide.Instance, OpenGuide.Listeners>
{
  public OpenGuide()
  {
    super(new ResourceLocation(Sonos.ModID, "openguide"));
  }

  @Override
  public OpenGuide.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context)
  {
    return new OpenGuide.Instance();
  }

  @Override
  public void addListener(PlayerAdvancements playerAdvancementsIn, Listener<Instance> listener)
  {
    OpenGuide.Listeners consumeitemtrigger$listeners = this.listeners.get(playerAdvancementsIn);

		if (consumeitemtrigger$listeners == null)
    {
			consumeitemtrigger$listeners = new OpenGuide.Listeners(playerAdvancementsIn);
			this.listeners.put(playerAdvancementsIn, consumeitemtrigger$listeners);
		}

    consumeitemtrigger$listeners.add(listener);
  }

  public void trigger(EntityPlayerMP parPlayer)
  {
    CriterionListeners<OpenGuide.Instance> enterblocktrigger$listeners = this.listeners.get(parPlayer.getAdvancements());

    if (enterblocktrigger$listeners != null)
    {
      enterblocktrigger$listeners.trigger(null, null);
    }
  }

  public class Instance extends CriterionInstance
  {
    public Instance()
    {
      super(ID);
    }

    @Override
    public boolean test(Object obj, Class<?> type)
    {
      return true;
    }
  }

  public static class Listeners extends CriterionListeners<Instance>
  {
    public Listeners(PlayerAdvancements pAdv)
    {
      super(pAdv);
    }
  }
}
