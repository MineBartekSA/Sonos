package com.minebarteksa.sonos.criteria;

import com.minebarteksa.orion.criteria.CriterionBase;
import com.minebarteksa.orion.criteria.CriterionInstanceBase;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.minebarteksa.sonos.Sonos;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

public class OpenGuide extends CriterionBase<OpenGuide.Instance>
{
    public OpenGuide() { super(new ResourceLocation(Sonos.ModID, "openguide")); }

    @Override
    public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) { return new Instance(getId()); }

    public void trigger(EntityPlayerMP player) { this.trigger(player, (i) -> true); }

    public class Instance extends CriterionInstanceBase
    {
        public Instance(ResourceLocation id) { super(id); }

        @Override
        public CriterionInstanceBase deserialize(JsonObject jsonObject) { return this; }
    }
}
