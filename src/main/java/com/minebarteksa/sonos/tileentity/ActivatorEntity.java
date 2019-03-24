package com.minebarteksa.sonos.tileentity;

import com.minebarteksa.orion.multiblock.MultiBlock;
import com.minebarteksa.sonos.Sonos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.state.IBlockState;

public class ActivatorEntity extends MultiBlock
{
    public ActivatorEntity() { super(new ResourceLocation(Sonos.ModID, "activator")); }

    @Override
    public void onCompleted()
    {
        //
    }

    @Override
    public void update() { }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) { return (oldState.getBlock() != newSate.getBlock()); }
}
