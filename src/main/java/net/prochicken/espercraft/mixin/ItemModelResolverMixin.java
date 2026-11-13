package net.prochicken.espercraft.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.prochicken.espercraft.film.FilmGuiPreview;
import net.prochicken.espercraft.film.FilmOverlay;
import net.prochicken.espercraft.film.FilmStyle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemModelResolver.class)
public class ItemModelResolverMixin {

    @Inject(method = "updateForTopItem", at = @At("RETURN"))
    private void espercraft$tagFilmPreview(CallbackInfo ci, @Local(argsOnly = true) ItemStackRenderState renderState) {
        FilmStyle style = FilmGuiPreview.current();
        if (style == null) return;

        ItemStackRenderStateAccessorMixin accessor = (ItemStackRenderStateAccessorMixin) renderState;
        for (int i = 0; i < accessor.espercraft$getActiveLayerCount(); i++) {
            FilmOverlay.tagTints(accessor.espercraft$getLayers()[i].tintLayers(), style);
        }

        // keeps the GUI's image cache from mixing filtered and unfiltered copies
        renderState.appendModelIdentityElement(style);
    }
}