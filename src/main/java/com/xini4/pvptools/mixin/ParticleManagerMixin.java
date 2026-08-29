package com.xini4.pvptools.mixin;

import com.xini4.pvptools.PvPToolsClient;
import com.xini4.pvptools.config.PvPToolsConfig;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.particle.Particle;
import net.minecraft.particle.ParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ParticleManager.class)
public abstract class ParticleManagerMixin {
    @Redirect(method = "addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/ParticleManager;addParticle(Lnet/minecraft/client/particle/Particle;)V"))
    private void pvptools_addParticle(ParticleManager instance, Particle particle) {
        try {
            PvPToolsConfig cfg = PvPToolsClient.CONFIG.config;
            if (cfg.antiFireParticles) {
                var type = particle.getType();
                if (type == ParticleTypes.LARGE_SMOKE || type == ParticleTypes.SMOKE || type == ParticleTypes.FLAME || type == ParticleTypes.LAVA) {
                    return;
                }
            }
        } catch (Throwable ignored) {}
        ((ParticleManager)(Object)instance).addParticle(particle);
    }
}
