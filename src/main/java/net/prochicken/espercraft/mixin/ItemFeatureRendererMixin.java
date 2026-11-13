// --- WARNING: Contains AI-generated code ---

package net.prochicken.espercraft.mixin;

import com.mojang.blaze3d.vertex.QuadInstance;
import net.minecraft.client.renderer.feature.ItemFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.resources.Identifier;
import net.prochicken.espercraft.film.FilmOverlay;
import net.prochicken.espercraft.film.FilmRenderTypes;
import net.prochicken.espercraft.film.FilmStyle;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemFeatureRenderer.class)
public class ItemFeatureRendererMixin {

    private static boolean espercraft$logged = false;

    @Shadow
    @Final
    private QuadInstance quadInstance;

    @ModifyVariable(method = "prepareMainSubmit", at = @At("STORE"), ordinal = 0)
    private RenderType espercraft$swapRenderType(RenderType original, ItemFeatureRenderer.Submit submit) {
        FilmStyle style = null;

        if (FilmOverlay.isFilm(submit.overlayCoords())) {
            style = FilmOverlay.decode(submit.overlayCoords());          // in-world path
        } else if (FilmOverlay.hasTintTag(submit.tintLayers())) {
            style = FilmOverlay.decodeTintTag(submit.tintLayers());      // GUI path
            this.quadInstance.setOverlayCoords(FilmOverlay.encode(style));
        }

        if (style == null) {
            return original;
        }

        Identifier atlas = null;
        for (BakedQuad quad : submit.quads()) {
            if (quad.materialInfo().itemRenderType() == original) {
                atlas = quad.materialInfo().sprite().atlasLocation();
                break;
            }
        }
        if (atlas == null) {
            return original;
        }

        return FilmRenderTypes.get(atlas, original.hasBlending());
    }
}