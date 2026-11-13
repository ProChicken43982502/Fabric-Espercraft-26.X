// --- WARNING: Contains AI-generated code ---

package net.prochicken.espercraft.film;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.ColorTargetState;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.rendertype.OutputTarget;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.prochicken.espercraft.Espercraft;

import java.util.function.Function;

public class FilmRenderTypes {

    private static final RenderPipeline CUTOUT_PIPELINE = RenderPipelines.register(
            RenderPipeline.builder(RenderPipelines.ITEM_SNIPPET)
                    .withLocation(Espercraft.id("pipeline/film_item_cutout"))
                    .withVertexShader(Espercraft.id("core/film_item"))
                    .withFragmentShader(Espercraft.id("core/film_item"))
                    .withShaderDefine("ALPHA_CUTOUT", 0.1f)
                    .build()
    );

    private static final RenderPipeline TRANSLUCENT_PIPELINE = RenderPipelines.register(
            RenderPipeline.builder(RenderPipelines.ITEM_SNIPPET)
                    .withLocation(Espercraft.id("pipeline/film_item_translucent"))
                    .withVertexShader(Espercraft.id("core/film_item"))
                    .withFragmentShader(Espercraft.id("core/film_item"))
                    .withColorTargetState(new ColorTargetState(BlendFunction.TRANSLUCENT))
                    .build()
    );

    private static final Function<Identifier, RenderType> CUTOUT = Util.memoize(texture -> {
        RenderSetup setup = RenderSetup.builder(CUTOUT_PIPELINE)
                .withTexture("Sampler0", texture)
                .useLightmap()
                .useOverlay()
                .affectsCrumbling()
                .setOutline(RenderSetup.OutlineProperty.AFFECTS_OUTLINE)
                .createRenderSetup();

        return RenderType.create("espercraft_film_cutout", setup);
    });

    private static final Function<Identifier, RenderType> TRANSLUCENT = Util.memoize(texture -> {
        RenderSetup setup = RenderSetup.builder(TRANSLUCENT_PIPELINE)
                .withTexture("Sampler0", texture)
                .setOutputTarget(OutputTarget.ITEM_ENTITY_TARGET)
                .useLightmap()
                .useOverlay()
                .affectsCrumbling()
                .sortOnUpload()
                .setOutline(RenderSetup.OutlineProperty.AFFECTS_OUTLINE)
                .createRenderSetup();

        return RenderType.create("espercraft_film_translucent", setup);
    });

    public static RenderType get(Identifier atlas, boolean translucent) {
        return translucent ? TRANSLUCENT.apply(atlas) : CUTOUT.apply(atlas);
    }

    public static void init() {
    }
}