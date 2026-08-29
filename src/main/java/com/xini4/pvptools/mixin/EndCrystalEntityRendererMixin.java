package com.xini4.pvptools.mixin;

import com.xini4.pvptools.PvPToolsClient;
import com.xini4.pvptools.config.PvPToolsConfig;
import com.xini4.pvptools.optimizer.Optimizer;
import net.minecraft.client.render.entity.EndCrystalEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndCrystalEntityRenderer.class)
public abstract class EndCrystalEntityRendererMixin extends EntityRenderer<EndCrystalEntity> {
    protected EndCrystalEntityRendererMixin(EntityRendererFactory.Context ctx) { super(ctx); }

    @Inject(method = "render(Lnet/minecraft/entity/decoration/EndCrystalEntity;FFLcom/mojang/blaze3d/vertex/VertexConsumerProvider;Lnet/minecraft/client/util/math/MatrixStack;I)V",
            at = @At("HEAD"), cancellable = true)
    private void pvptools_onRender(EndCrystalEntity entity, float yaw, float tickDelta, VertexConsumerProvider vertexConsumers, MatrixStack matrices, int light, CallbackInfo ci) {
        try {
            PvPToolsConfig cfg = PvPToolsClient.CONFIG.config;
            if (cfg == null) return;
            int mode = cfg.crystalMode;
            if (mode == 0) return;

            Optimizer opt = new Optimizer();
            boolean suppress = opt.shouldSuppress(entity, mode, cfg.crystalOptimizeDistance);

            if (suppress) {
                ci.cancel();
            }
        } catch (Throwable ignored) {}
    }
}
