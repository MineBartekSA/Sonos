package com.minebarteksa.sonos.criteria;

import com.minebarteksa.orion.criteria.CriterionInstance;
import com.minebarteksa.orion.criteria.CriterionListeners;
import com.minebarteksa.orion.criteria.CriterionBase;
import net.minecraft.advancements.PlayerAdvancements;
import com.google.gson.JsonObject;
import com.google.gson.JsonDeserializationContext;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import com.minebarteksa.sonos.Sonos;
import net.minecraft.block.state.IBlockState;
import com.minebarteksa.sonos.blocks.SonosBlocks;
import net.minecraft.block.Block;

public class MinedSonoOreNew extends CriterionBase<MinedSonoOreNew.Instance, MinedSonoOreNew.Lis>
{
  public MinedSonoOreNew()
  {
    super(new ResourceLocation(Sonos.ModID, "mined_sono_ore"));
  }

  @Override
  public MinedSonoOreNew.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context)
  {
    return new MinedSonoOreNew.Instance();
  }

  @Override
  public void addListener(PlayerAdvancements playerAdvancementsIn, Listener<Instance> listener)
  {
    MinedSonoOreNew.Lis consumeitemtrigger$listeners = this.listeners.get(playerAdvancementsIn);

		if (consumeitemtrigger$listeners == null)
    {
			consumeitemtrigger$listeners = new MinedSonoOreNew.Lis(playerAdvancementsIn);
			this.listeners.put(playerAdvancementsIn, consumeitemtrigger$listeners);
		}

    consumeitemtrigger$listeners.add(listener);
  }

  public void trigger(EntityPlayerMP parPlayer, IBlockState f)
  {
    CriterionListeners<MinedSonoOreNew.Instance> enterblocktrigger$listeners = this.listeners.get(parPlayer.getAdvancements());

    if (enterblocktrigger$listeners != null)
    {
      enterblocktrigger$listeners.trigger(f, IBlockState.class);
    }
  }

  public class Instance extends CriterionInstance
  {
    private final Block block = SonosBlocks.SO;

    public Instance()
    {
      super(ID);
    }

    @Override
    public boolean test(Object obj, Class<?> type)
    {
      if(type != IBlockState.class)
        return false;
      IBlockState state = (IBlockState)obj;
      if(state.getBlock() == block)
        return true;
      return false;
    }
  }

  public static class Lis extends CriterionListeners<Instance>
  {
    public Lis(PlayerAdvancements pAdv)
    {
      super(pAdv);
    }
  }
}
