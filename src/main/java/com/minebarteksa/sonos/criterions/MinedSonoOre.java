package com.minebarteksa.sonos.criterions;

import net.minecraft.entity.player.EntityPlayerMP;
import java.util.Map;
import com.google.common.collect.Maps;
import com.google.common.collect.Lists;
import java.util.List;
import com.google.common.collect.Sets;
import java.util.Set;
import com.minebarteksa.sonos.blocks.SonosBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.Block;
import net.minecraft.advancements.critereon.AbstractCriterionInstance;
import com.minebarteksa.sonos.Sonos;
import com.google.gson.JsonObject;
import com.google.gson.JsonDeserializationContext;
import net.minecraft.util.ResourceLocation;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.ICriterionTrigger;

public class MinedSonoOre implements ICriterionTrigger<MinedSonoOre.Instance>
{
  private static final ResourceLocation ID = new ResourceLocation(Sonos.ModID, "mined_sono_ore");
  private final Map<PlayerAdvancements, MinedSonoOre.Listeners> listeners = Maps.<PlayerAdvancements, MinedSonoOre.Listeners>newHashMap();

  @Override
  public ResourceLocation getId()
  {
    return ID;
  }

  @Override
  public void removeListener(PlayerAdvancements playerAdvancementsIn, Listener<MinedSonoOre.Instance> listener)
  {
    MinedSonoOre.Listeners consumeitemtrigger$listeners = this.listeners.get(playerAdvancementsIn);

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
  public void addListener(PlayerAdvancements playerAdvancementsIn, Listener<MinedSonoOre.Instance> listener)
  {
    MinedSonoOre.Listeners consumeitemtrigger$listeners = this.listeners.get(playerAdvancementsIn);

		if (consumeitemtrigger$listeners == null)
    {
			consumeitemtrigger$listeners = new MinedSonoOre.Listeners(playerAdvancementsIn);
			this.listeners.put(playerAdvancementsIn, consumeitemtrigger$listeners);
		}

    consumeitemtrigger$listeners.add(listener);
  }

  @Override
  public MinedSonoOre.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context)
  {
    return new MinedSonoOre.Instance();
  }

  public void trigger(EntityPlayerMP parPlayer, IBlockState f)
  {
    Sonos.log.info("MinedSonosOre Triggered!");
    MinedSonoOre.Listeners enterblocktrigger$listeners = this.listeners.get(parPlayer.getAdvancements());

    if (enterblocktrigger$listeners != null)
    {
      enterblocktrigger$listeners.trigger(f);
    }
  }

  public static class Instance extends AbstractCriterionInstance
  {
    private final Block block = SonosBlocks.SO;

    public Instance()
    {
      super(ID);
    }

    public boolean test(IBlockState state)
    {
      Sonos.log.info("Test " + (state.getBlock() == block));
      if(state.getBlock() == block)
        return true;
      return false;
    }
  }

  static class Listeners
  {
    private final PlayerAdvancements playerAdvancements;
		private final Set<ICriterionTrigger.Listener<MinedSonoOre.Instance>> listeners = Sets.<ICriterionTrigger.Listener<MinedSonoOre.Instance>>newHashSet();

    public Listeners(PlayerAdvancements pAdv)
    {
      this.playerAdvancements = pAdv;
    }

    public boolean isEmpty()
    {
			return this.listeners.isEmpty();
		}

		public void add(ICriterionTrigger.Listener<MinedSonoOre.Instance> listener)
    {
			this.listeners.add(listener);
		}

		public void remove(ICriterionTrigger.Listener<MinedSonoOre.Instance> listener)
    {
			this.listeners.remove(listener);
    }

    public void trigger(IBlockState state)
    {
      List<ICriterionTrigger.Listener<MinedSonoOre.Instance>> list = null;

      for (ICriterionTrigger.Listener<MinedSonoOre.Instance> listener : this.listeners)
      {
				if (((MinedSonoOre.Instance) listener.getCriterionInstance()).test(state))
        {
					if (list == null)
          {
						list = Lists.<ICriterionTrigger.Listener<MinedSonoOre.Instance>>newArrayList();
					}

					list.add(listener);
				}
      }

      if (list != null)
      {
				for (ICriterionTrigger.Listener<MinedSonoOre.Instance> listener1 : list)
        {
					listener1.grantCriterion(this.playerAdvancements);
				}
      }
    }
  }
}
