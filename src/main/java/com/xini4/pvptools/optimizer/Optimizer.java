package com.xini4.pvptools.optimizer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

public class Optimizer {
    private final MinecraftClient client = MinecraftClient.getInstance();

    public boolean shouldSuppress(EndCrystalEntity crystal, int mode, double optimizeDistance) {
        if (client == null || client.player == null || crystal == null) return false;

        if (mode == 1) return true;
        if (mode == 2) return true;

        if (mode == 3) {
            Vec3d playerPos = new Vec3d(client.player.getX(), client.player.getY(), client.player.getZ());
            Vec3d crystalPos = new Vec3d(crystal.getX(), crystal.getY(), crystal.getZ());
            double distance = playerPos.distanceTo(crystalPos);
            if (distance > optimizeDistance) return true;

            BlockPos below = new BlockPos(crystal.getBlockX(), crystal.getBlockY() - 1, crystal.getBlockZ());
            BlockState bs = client.world.getBlockState(below);
            boolean validBase = isObsidianOrBedrock(bs);
            if (!validBase && distance > Math.min(optimizeDistance, 8.0)) return true;

            if (!Raycast.rayTraceVisible(client.player, crystalPos, client.world) && distance > 10.0) {
                return true;
            }
        }

        return false;
    }

    private boolean isObsidianOrBedrock(BlockState s) {
        return s.getBlock() == Blocks.OBSIDIAN || s.getBlock() == Blocks.BEDROCK;
    }
}
