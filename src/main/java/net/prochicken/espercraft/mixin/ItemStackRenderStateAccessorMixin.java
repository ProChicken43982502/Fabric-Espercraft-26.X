// --- WARNING: Contains AI-generated code ---

package net.prochicken.espercraft.mixin;

import net.minecraft.client.renderer.item.ItemStackRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemStackRenderState.class)
public interface ItemStackRenderStateAccessorMixin {

    @Accessor("layers")
    ItemStackRenderState.LayerRenderState[] espercraft$getLayers();

    @Accessor("activeLayerCount")
    int espercraft$getActiveLayerCount();
}