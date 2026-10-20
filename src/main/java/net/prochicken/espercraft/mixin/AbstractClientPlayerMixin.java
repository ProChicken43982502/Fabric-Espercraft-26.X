package net.prochicken.espercraft.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.prochicken.espercraft.item.custom.MagnifierItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayer.class)
public class AbstractClientPlayerMixin {

    @Inject(method = "getFieldOfViewModifier", at = @At("HEAD"), cancellable = true)
    private void espercraft$magnifierZoom(CallbackInfoReturnable<Float> cir) {
        AbstractClientPlayer self = (AbstractClientPlayer) (Object) this;
        if (self.isUsingItem()
                && self.getUseItem().getItem() instanceof MagnifierItem
                && Minecraft.getInstance().options.getCameraType().isFirstPerson()) {
            cir.setReturnValue(MagnifierItem.ZOOM_FOV_MODIFIER);
        }
    }
}