package com.xini4.pvptools.optimizer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.BlockView;

public class Raycast {
    public static boolean rayTraceVisible(PlayerEntity player, Vec3d target, BlockView world) {
        if (player == null || world == null) return true;
        Vec3d eye = player.getEyePos();
        Vec3d dir = target.subtract(eye);
        double distance = dir.length();
        if (distance <= 0.0001) return true;
        Vec3d end = eye.add(dir.normalize().multiply(distance - 0.01));
        RaycastContext context = new RaycastContext(
                eye,
                end,
                RaycastContext.ShapeType.COLLIDER,
                RaycastContext.FluidHandling.NONE,
                player
        );
        HitResult result = world.raycast(context);
        return result.getType() == HitResult.Type.MISS;
    }
}
