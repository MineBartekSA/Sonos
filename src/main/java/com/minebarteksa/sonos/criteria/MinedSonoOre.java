package com.minebarteksa.sonos.criteria;

import com.minebarteksa.orion.criteria.CriterionBase;
import com.minebarteksa.orion.criteria.CriterionInstanceBase;
import com.google.gson.JsonObject;
import com.google.gson.JsonDeserializationContext;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import com.minebarteksa.sonos.Sonos;

public class MinedSonoOre extends CriterionBase<MinedSonoOre.Instance>
{
    public MinedSonoOre() { super(new ResourceLocation(Sonos.ModID, "mined_sono_ore")); }

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
