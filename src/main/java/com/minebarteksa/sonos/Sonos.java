package com.minebarteksa.sonos;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraft.util.ResourceLocation;
import amerifrance.guideapi.api.GuideAPI;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import com.minebarteksa.sonos.packets.SonosPacketHandler;
import java.util.Random;
import com.minebarteksa.sonos.sound.SoundEvents.Notes;
import net.minecraft.util.SoundEvent;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.Logger;
import com.minebarteksa.sonos.sound.SoundEvents;
import com.minebarteksa.sonos.blocks.SonosBlocks;
import com.minebarteksa.sonos.items.SonosItems;
import com.minebarteksa.sonos.gui.SonosGUIHandler;
import com.minebarteksa.sonos.sonosproxy.SonosCommon;
import com.minebarteksa.sonos.criteria.SonosCriteria;

@Mod(modid = Sonos.ModID, name = Sonos.Name, version = Sonos.Version, dependencies = Sonos.Deps)
public class Sonos
{
  public static final String Name = "Sonos";
  public static final String ModID = "sonos";
  public static final String Version = "1.0.0";
  public static final String Deps = "required-after:baubles; before:guideapi; before:cofhcore;";

  @Mod.Instance(ModID)
  public static Sonos instance;

  public static CreativeTabs cTab = new CreativeTabs("sonos") {
	    @Override
	    public ItemStack getTabIconItem() {
		      Item i = SonosItems.getSonoFormNote(Notes.getNote(new Random().nextInt(12)));
          return new ItemStack(i);
	    }
  };

  @SidedProxy(serverSide = "com.minebarteksa.sonos.sonosproxy.SonosCommon", clientSide = "com.minebarteksa.sonos.sonosproxy.SonosClient")
  public static SonosCommon proxy;

  public static Logger log;

  @Mod.EventHandler
  public void preInit(FMLPreInitializationEvent event)
  {
    log = event.getModLog();
    GameRegistry.registerWorldGenerator(new WorldGen(), 3);
    NetworkRegistry.INSTANCE.registerGuiHandler(this, new SonosGUIHandler());
    SonosPacketHandler.registerPackets();
  }

  @Mod.EventHandler
  public void init(FMLInitializationEvent event)
  {
    log.info("Sonos init!");
    SonosItems.initOreDictionary();
    SonosItems.registerItemColors();
    SonosCriteria.registerCriteria();
  }

  @Mod.EventBusSubscriber
  public static class RegistrationHandler
  {
	  @SubscribeEvent
	  public static void registerItems(RegistryEvent.Register<Item> event)
	  {
		  SonosItems.register(event.getRegistry());
		  SonosBlocks.registerItemBlock(event.getRegistry());
	  }

    @SubscribeEvent
	  public static void registerItems(ModelRegistryEvent event)
	  {
		  SonosItems.registerModels();
		  SonosBlocks.registerModels();
	  }

	  @SubscribeEvent
	  public static void registerBlocks(RegistryEvent.Register<Block> event)
	  {
		  SonosBlocks.register(event.getRegistry());
	  }

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event)
    {
      SoundEvents.registerSounds(event.getRegistry());
    }

    @SubscribeEvent
    public static void playerInteractEvent(PlayerInteractEvent.RightClickItem event)
    {
      if(event.getSide() == Side.SERVER)
        openGuideTrigger(event.getItemStack(), (EntityPlayerMP)event.getEntityPlayer());
    }

    @SubscribeEvent
    public static void playerInteractEvent(PlayerInteractEvent.RightClickBlock event)
    {
      if(event.getSide() == Side.SERVER)
        openGuideTrigger(event.getItemStack(), (EntityPlayerMP)event.getEntityPlayer());
    }

    @SubscribeEvent
    public static void playerInteractEvent(PlayerInteractEvent.EntityInteract event)
    {
      if(event.getSide() == Side.SERVER)
        openGuideTrigger(event.getItemStack(), (EntityPlayerMP)event.getEntityPlayer());
    }

    static void openGuideTrigger(ItemStack item, EntityPlayerMP player)
    {
      if(item.getItem() == GuideAPI.getStackFromBook(GuideAPI.getBooks().get(new ResourceLocation(Sonos.ModID, "guidebook"))).getItem())
        SonosCriteria.OG.trigger(player);
    }
  }
}
