package net.prochicken.espercraft.mixin;

import net.minecraft.world.entity.player.Player;
import net.prochicken.espercraft.item.custom.MagnifierItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin {

	@Inject(method = "isScoping", at = @At("HEAD"), cancellable = true)
	private void espercraft$magnifierScoping(CallbackInfoReturnable<Boolean> cir) {
		Player self = (Player) (Object) this;
		if (self.isUsingItem() && self.getUseItem().getItem() instanceof MagnifierItem) {
			cir.setReturnValue(true);
		}
	}
}