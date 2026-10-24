package net.prochicken.espercraft.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;
import net.prochicken.espercraft.Espercraft;

public class OverlayRenderer {
    private static final float MIN = -0.002f;
    private static final float MAX = 1.002f;

    // each face = 4 corners; 0 = min side, 1 = max side
    private static final int[][][] FACES = {
            {{0,0,0},{1,0,0},{1,0,1},{0,0,1}}, // down
            {{0,1,0},{0,1,1},{1,1,1},{1,1,0}}, // up
            {{0,0,0},{0,1,0},{1,1,0},{1,0,0}}, // north
            {{0,0,1},{1,0,1},{1,1,1},{0,1,1}}, // south
            {{0,0,0},{0,0,1},{0,1,1},{0,1,0}}, // west
            {{1,0,0},{1,1,0},{1,1,1},{1,0,1}}  // east
    };

    private static final Identifier TEXTURE = Espercraft.id("textures/misc/scanning_block_overlay.png");
    private static final int FRAMES = 5;
    private static final int TICKS_PER_FRAME = 2;

    private static final float[][] NORMALS = {
            {0,-1,0}, {0,1,0}, {0,0,-1}, {0,0,1}, {-1,0,0}, {1,0,0}
    };

    public static void init() {
        LevelRenderEvents.COLLECT_SUBMITS.register(context -> {
            if (!ScanningOverlay.isScanning()) return;
            BlockPos pos = ScanningOverlay.target;

            Vec3 cam = Minecraft.getInstance().gameRenderer.getMainCamera().position();

            PoseStack pose = new PoseStack();
            pose.translate(pos.getX() - cam.x, pos.getY() - cam.y, pos.getZ() - cam.z);

            int frame = (ScanningOverlay.ticks / TICKS_PER_FRAME) % FRAMES;
            float v0 = frame / (float) FRAMES;
            float v1 = (frame + 1) / (float) FRAMES;

            context.submitNodeCollector().submitCustomGeometry(pose, RenderTypes.entityTranslucent(TEXTURE), (p, buffer) -> {
                for (int f = 0; f < 6; f++) {
                    for (int i = 0; i < 4; i++) {
                        int[] c = FACES[f][i];
                        float u = (i == 1 || i == 2) ? 1f : 0f;
                        float v = (i >= 2) ? v1 : v0;
                        buffer.addVertex(p,
                                        c[0] == 0 ? MIN : MAX,
                                        c[1] == 0 ? MIN : MAX,
                                        c[2] == 0 ? MIN : MAX)
                                .setColor(0xFFFFFFFF)
                                .setUv(u, v)
                                .setOverlay(OverlayTexture.NO_OVERLAY)
                                .setLight(0xF000F0)
                                .setNormal(p, NORMALS[f][0], NORMALS[f][1], NORMALS[f][2]);
                    }
                }
            });
        });
    }
}