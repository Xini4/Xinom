package com.xini4.pvptools.mixin;

import com.xini4.pvptools.PvPToolsClient;
import com.xini4.pvptools.config.PvPToolsConfig;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParticleManager.class)
public abstract class ParticleManagerMixin {
    @Inject(method = "addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V", at = @At("HEAD"), cancellable = true)
    private void pvptools_addParticle(net.minecraft.particle.ParticleEffect effect, double x, double y, double z, double mx, double my, double mz, CallbackInfo ci) {
        try {
            PvPToolsConfig cfg = PvPToolsClient.CONFIG.config;
            if (cfg.antiFireParticles) {
                var type = effect.getType();
                if (type == ParticleTypes.LARGE_SMOKE || type == ParticleTypes.SMOKE || type == ParticleTypes.FLAME || type == ParticleTypes.LAVA) {
                    ci.cancel();
                }
            }
        } catch (Throwable ignored) {}
    }
}
