package com.minebarteksa.sonos;

import amerifrance.guideapi.api.GuideAPI;
import com.minebarteksa.sonos.blocks.SonosBlocks;
import com.minebarteksa.sonos.criteria.SonosCriteria;
import com.minebarteksa.sonos.gui.SonosGUIHandler;
import com.minebarteksa.sonos.items.SonoPrima;
import com.minebarteksa.sonos.items.SonosItems;
import com.minebarteksa.sonos.packets.SonosPacketHandler;
import com.minebarteksa.sonos.sonosproxy.SonosCommon;
import com.minebarteksa.sonos.sound.SoundEvents;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.Random;

@Mod(modid = Sonos.ModID, name = Sonos.Name, version = Sonos.Version, dependencies = Sonos.Deps)
public class Sonos
{
    public static final String Name = "Sonos";
    public static final String ModID = "sonos";
    public static final String Version = "1.0.0";
    public static final String Deps = "required-after:baubles; required-after:liborion; before:guideapi;";

    @Mod.Instance(ModID)
    public static Sonos instance;

    public static CreativeTabs cTab = new CreativeTabs("sonos") {
        private int tick;
        private ItemStack current;

        @Override
        public ItemStack getTabIconItem() { return null; }

        @Override
        public ItemStack getIconItemStack()
        {
            if(tick <= 0) {
                current = new ItemStack(SonoPrima.getFromNote(SoundEvents.Notes.getNote(new Random().nextInt(12))));
                tick = 259;
            }
            tick--;
            return current;
        }

        @Override
        public void displayAllRelevantItems(NonNullList<ItemStack> items) {
            super.displayAllRelevantItems(items);
            items.sort(Comparator.comparing(ItemStack::getDisplayName)); // ToDo: Better sorting
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
        SonosItems.register();
        SonosBlocks.register();
        SoundEvents.registerSounds();
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
