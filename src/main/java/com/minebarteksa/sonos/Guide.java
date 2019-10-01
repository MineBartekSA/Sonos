package com.minebarteksa.sonos;

import amerifrance.guideapi.api.GuideAPI;
import amerifrance.guideapi.api.GuideBook;
import amerifrance.guideapi.api.IGuideBook;
import amerifrance.guideapi.api.IPage;
import amerifrance.guideapi.api.impl.Book;
import amerifrance.guideapi.api.impl.BookBinder;
import amerifrance.guideapi.api.impl.abstraction.EntryAbstract;
import amerifrance.guideapi.category.CategoryItemStack;
import amerifrance.guideapi.entry.EntryItemStack;
import amerifrance.guideapi.page.PageFurnaceRecipe;
import amerifrance.guideapi.page.PageText;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@GuideBook
public class Guide extends Item implements IGuideBook
{
    public static BookBinder guide;

    @Nonnull
    @Override
    public Book buildBook() // TODO: Write this book
    {
        Map<ResourceLocation, EntryAbstract> entries = new LinkedHashMap<ResourceLocation, EntryAbstract>();

        List<IPage> pages1 = new ArrayList<IPage>();
        pages1.add(new PageText("This is a page in my guide!"));
        entries.put(new ResourceLocation("example", "entry_one"), new EntryItemStack(pages1, "Entry One", new ItemStack(Blocks.BEACON)));

        List<IPage> pages2 = new ArrayList<IPage>();
        pages2.add(new PageFurnaceRecipe("oreGold"));
        entries.put(new ResourceLocation("example", "entry_two"), new EntryItemStack(pages2, "Entry Two", new ItemStack(Items.DIAMOND_SWORD)));

        guide = new BookBinder(new ResourceLocation(Sonos.ModID, "guidebook"));
        guide.setAuthor("& Circl3s and MineBartekSA");
        guide.setColor(Color.BLUE);
        guide.setCreativeTab(Sonos.cTab);
        guide.setGuideTitle("Libro de Sonos");
        guide.setItemName("Libro de Sonos");
        guide.setSpawnWithBook();
        guide.addCategory(new CategoryItemStack(entries, "My Category", new ItemStack(Blocks.COMMAND_BLOCK)));

        return guide.build();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void handleModel(ItemStack bookStack)
    {
        GuideAPI.setModel(guide.build());
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        Sonos.log.info("Guide");
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
