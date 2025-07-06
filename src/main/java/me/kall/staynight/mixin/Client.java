package me.kall.staynight.mixin;

import net.minecraft.client.multiplayer.ClientLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class Client {
    @Mixin({ClientLevel.class})
    public static final class ClientLevelMixin {
        @ModifyVariable(method = "setDayTime", at = @At("HEAD"), argsOnly = true)
        private long stayNight(long dayTime) {
            return 18000L;
        }
    }

    @Mixin({ClientLevel.ClientLevelData.class})
    public static final class ClientLevelDataMixin {
        @ModifyVariable(method = "setDayTime", at = @At("HEAD"), argsOnly = true)
        private long stayNight(long dayTime) {
            return 18000L;
        }

        @Inject(method = "getDayTime", at = @At("HEAD"), cancellable = true)
        private void stayNight(CallbackInfoReturnable<Long> cir) {
            cir.setReturnValue(18000L);
        }
    }
}
