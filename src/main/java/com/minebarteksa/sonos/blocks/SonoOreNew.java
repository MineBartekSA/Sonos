package com.minebarteksa.sonos.blocks;

import com.minebarteksa.orion.blocks.TileEntityBlockBase;
import com.minebarteksa.sonos.Sonos;
import com.minebarteksa.sonos.criteria.SonosCriteria;
import com.minebarteksa.sonos.sound.SoundEvents;
import com.minebarteksa.sonos.tileentity.SonoOreNewEntity;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import javax.annotation.Nullable;
import java.util.Random;

public class SonoOreNew extends TileEntityBlockBase<SonoOreNewEntity>
{
    public static final PropertyInteger LitAF = PropertyInteger.create("litaf", 0, 12);

    public SonoOreNew()
    {
        super(Material.ROCK, "sono_ore", Sonos.ModID);
        this.setHardness(3f);
        this.setResistance(5f);
        this.setDefaultState(this.getBlockState().getBaseState().withProperty(LitAF, 12));
    }

    @Override
    public Class<SonoOreNewEntity> getTileEntityClass() { return SonoOreNewEntity.class; }

    @Nullable
    @Override
    public SonoOreNewEntity createTileEntity(World world, IBlockState state) { return new SonoOreNewEntity(); }

    @Override
    protected BlockStateContainer createBlockState() { return new BlockStateContainer(this, LitAF); }

    @Override
    public int getMetaFromState(IBlockState state) { return state.getValue(LitAF); }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        if(!worldIn.isRemote)
            ((SonoOreNewEntity)worldIn.getTileEntity(pos)).init();
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        SoundEvents.Notes note = SoundEvents.Notes.getNote(state.getValue(LitAF));
        if (note == SoundEvents.Notes.None)
            note = SoundEvents.Notes.C;
        String n = note.toString().replace("harp", "").toLowerCase();
        return Item.REGISTRY.getObject(new ResourceLocation(Sonos.ModID, "sono_" + n));
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        if(state.getValue(LitAF) != 12)
            return 5;
        else
            return 0;
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        if(!worldIn.isRemote)
            SonosCriteria.MSON.trigger((EntityPlayerMP)player, state);
    }

    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn)
    {
        if(!worldIn.isRemote && worldIn.getBlockState(pos).getValue(LitAF) == 12)
            activate(worldIn, pos);
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
        if(!worldIn.isRemote && worldIn.getBlockState(pos).getValue(LitAF) == 12)
            activate(worldIn, pos);
    }

    private void activate(World world, BlockPos pos) { ((SonoOreNewEntity)world.getTileEntity(pos)).setTimeOut(); }
}
