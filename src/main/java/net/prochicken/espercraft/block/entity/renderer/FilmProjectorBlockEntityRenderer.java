// --- WARNING: Contains AI-generated code ---

package net.prochicken.espercraft.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.prochicken.espercraft.block.custom.FilmProjectorBlock;
import net.prochicken.espercraft.block.entity.custom.FilmProjectorBlockEntity;
import net.prochicken.espercraft.film.FilmOverlay;
import net.prochicken.espercraft.film.FilmStyle;
import net.prochicken.espercraft.mixin.ItemStackRenderStateAccessorMixin;
import org.jspecify.annotations.Nullable;

public class FilmProjectorBlockEntityRenderer implements BlockEntityRenderer<FilmProjectorBlockEntity, FilmProjectorBlockEntityRenderState> {

    private final ItemModelResolver itemModelResolver;

    private static final int PROJECTION_SLOT = 0;
    private static final int FILM_SLOT = 1;

    public FilmProjectorBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        itemModelResolver = context.itemModelResolver();
    }

    @Override
    public FilmProjectorBlockEntityRenderState createRenderState() {
        return new FilmProjectorBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(FilmProjectorBlockEntity blockEntity, FilmProjectorBlockEntityRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);

        state.level = blockEntity.getLevel();
        state.rotation = (blockEntity.getLevel().getGameTime() + partialTicks * 0.5F) % 360F;
        state.blockRotation = blockEntity.getBlockState().getValue(FilmProjectorBlock.ROTATION);

        ItemStack projected = blockEntity.isProjecting() ? blockEntity.getItems().get(0) : ItemStack.EMPTY;
        itemModelResolver.updateForTopItem(state.itemStackRenderState, projected, ItemDisplayContext.FIXED, blockEntity.getLevel(), null, 0);

        state.style = blockEntity.getStyle();
    }

    @Override
    public void submit(FilmProjectorBlockEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {

        float[][] OFFSETS = {
                {0, 1},
                {-0.71F, 0.71F},
                {-1, 0},
                {-0.71F, -0.71F},
                {0, -1},
                {0.71F, -0.71F},
                {1, 0},
                {0.71F, 0.71F}
        };

        int overlay = state.style == FilmStyle.NATURAL
                ? OverlayTexture.NO_OVERLAY
                : FilmOverlay.encode(state.style);

        poseStack.pushPose();

        poseStack.translate( 0.5F + 1.5F * OFFSETS[state.blockRotation][0], 0.75F, 0.5F + 1.5F * OFFSETS[state.blockRotation][1]);
        poseStack.scale(0.5F, 0.5F, 0.5F);
        poseStack.mulPose(Axis.YP.rotationDegrees(state.rotation));

        state.itemStackRenderState.submit(poseStack, submitNodeCollector, state.lightCoords, overlay, 0);

        poseStack.popPose();
    }
}