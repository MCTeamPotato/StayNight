package me.kall.staynight.mixin;

import net.minecraft.network.protocol.game.ClientboundSetTimePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.DerivedLevelData;
import net.minecraft.world.level.storage.PrimaryLevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class Common {
    @Mixin({PrimaryLevelData.class})
    public static final class PrimaryLevelDataMixin {
        @ModifyVariable(method = "setDayTime", at = @At("HEAD"), argsOnly = true)
        private long stayNight(long dayTime) {
            return 18000L;
        }

        @Inject(method = "getDayTime", at = @At("HEAD"), cancellable = true)
        private void stayNight(CallbackInfoReturnable<Long> cir) {
            cir.setReturnValue(18000L);
        }
    }

    @Mixin({ServerLevel.class})
    public static final class ServerLevelMixin {
        @ModifyVariable(method = "setDayTime", at = @At("HEAD"), argsOnly = true)
        private long stayNight(long dayTime) {
            return 18000L;
        }
    }

    @Mixin({DerivedLevelData.class})
    public static final class DerivedLevelDataMixin {
        @Inject(method = "getDayTime", at = @At("HEAD"), cancellable = true)
        private void stayNight(CallbackInfoReturnable<Long> cir) {
            cir.setReturnValue(18000L);
        }
    }

    @Mixin({ClientboundSetTimePacket.class})
    public static final class ClientboundSetTimePacketMixin {
        @Inject(method = "getDayTime", at = @At("HEAD"), cancellable = true)
        private void stayNight(CallbackInfoReturnable<Long> cir) {
            cir.setReturnValue(18000L);
        }
    }
}
