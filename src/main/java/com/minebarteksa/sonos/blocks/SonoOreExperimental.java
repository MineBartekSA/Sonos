package com.minebarteksa.sonos.blocks;

import com.minebarteksa.sonos.items.SonosItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import com.minebarteksa.sonos.items.Sono;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import com.minebarteksa.sonos.titleEntitys.SonoOreEntity;
import java.util.Random;
//import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class SonoOreExperimental extends TitleEntityBlockBase<SonoOreEntity>
{
	@ObjectHolder("sonos:sono")
	public static Sono DropSono;
	public String note;

	public SonoOreExperimental(String name)
	{
		super(Material.ROCK, name);

		setHardness(2f);
		setResistance(5f);
	}

	@Override
	public SonoOreEntity createTileEntity(World world, IBlockState state)
	{
		note = String.valueOf(new Random().nextInt());
		/*SonoOreEntity te = new SonoOreEntity(note);

		IItemHandler iHand = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
		iHand.insertItem(0, new ItemStack(SonosItems.sono, 1), false);
		((Sono)is.getItem()).setNBT(is);
		((Sono)is.getItem()).updateNBT("note", note);*/

		return new SonoOreEntity(note);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		SonoOreEntity tile = getTileEntity(worldIn, pos);
		//IItemHandler iHand = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
		ItemStack item = new ItemStack(SonosItems.sono);
		Sono s = (Sono)item.getItem();
		s.updateNBT("note", tile.getNote(), item);
		if(!item.isEmpty())
		{
			EntityItem eItem = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), item);
			worldIn.spawnEntity(eItem);
		}
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public Class<SonoOreEntity> getTitleEntityClass()
	{
		return SonoOreEntity.class;
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 0;
	}

	/*@Override
	public SoundType getSoundType()
	{
		SoundEvent se = new SoundEvent(new ResourceLocation(note));

		return new SoundType(10f, 1f, se, se, se, se, null);
	}*/

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(!worldIn.isRemote)
		{
			SonoOreEntity te = getTileEntity(worldIn, pos);
			playerIn.sendMessage(new TextComponentString("Note: " + te.getNote()));
		}
		return true;
	}

	@Override
	public SonoOreExperimental setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}
