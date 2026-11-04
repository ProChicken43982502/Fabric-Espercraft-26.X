package net.prochicken.espercraft.screen;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.prochicken.espercraft.data.ModDataComponents;
import net.prochicken.espercraft.item.custom.MagnifierItem;

public class ScanningOverlay {
    public static BlockPos target = null;
    public static int ticks = 0;

    public static boolean isScanning() {
        return target != null && ticks < MagnifierItem.SCAN_TICKS;
    }

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            LocalPlayer player = client.player;
            if (player == null || !player.isUsingItem()
                    || !(player.getUseItem().getItem() instanceof MagnifierItem)) {
                reset();
                return;
            }

            BlockPos looked = MagnifierItem.findScannableTarget(player);
            if (looked == null || looked.equals(player.getUseItem().get(ModDataComponents.COORDINATES))) {
                reset();
                return;
            }

            if (looked.equals(target)) {
                if (ticks < MagnifierItem.SCAN_TICKS) ticks++;
            } else {
                target = looked;
                ticks = 1;
            }
        });

        OverlayRenderer.init();
    }

    private static void reset() {
        target = null;
        ticks = 0;
    }
}