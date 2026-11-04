package net.prochicken.espercraft.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.prochicken.espercraft.item.ModItems;
import net.prochicken.espercraft.item.custom.MagnifierItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin extends Player {

    public AbstractClientPlayerMixin(Level level, GameProfile gameProfile) {
        super(level, gameProfile);
    }

    @Inject(method = "getFieldOfViewModifier", at = @At("HEAD"), cancellable = true)
    private void getFieldOfViewModifierMixin(boolean firstPerson, float effectScale, CallbackInfoReturnable<Float> info) {
        if (this.isUsingItem() && this.getUseItem().is(ModItems.MAGNIFIER) && firstPerson) {
            info.setReturnValue(MagnifierItem.ZOOM_FOV_MODIFIER);
        }
    }
}