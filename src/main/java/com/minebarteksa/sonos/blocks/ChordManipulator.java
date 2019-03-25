package com.minebarteksa.sonos.blocks;

import com.minebarteksa.orion.blocks.TileEntityBlockBase;
import com.minebarteksa.sonos.tileentity.ModulatorEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import com.minebarteksa.sonos.gui.SonosGUIHandler;
import com.minebarteksa.sonos.Sonos;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.block.material.Material;

public class ChordManipulator extends TileEntityBlockBase<ModulatorEntity>
{
    public ChordManipulator()
    {
        super(Material.IRON, "chord_manipulator", Sonos.ModID);
        this.setHardness(3f);
        this.setResistance(5f);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!worldIn.isRemote)
        {
            if(!playerIn.isSneaking())
                playerIn.openGui(Sonos.instance, SonosGUIHandler.ModulatorID, worldIn, pos.getX(), pos.getY(), pos.getZ());
            else
                return false;
        }
        return true;
    }

    @Override
    public Class<ModulatorEntity> getTileEntityClass() { return ModulatorEntity.class; }

    @Override
    public ModulatorEntity createTileEntity(World world, IBlockState state)
    {
        return new ModulatorEntity();
    }
}
