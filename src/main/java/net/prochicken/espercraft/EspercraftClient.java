package net.prochicken.espercraft;

import net.fabricmc.api.ClientModInitializer;
import net.prochicken.espercraft.screen.ScanningOverlay;

public class EspercraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScanningOverlay.init();

        Espercraft.LOGGER.info("Espercraft client loaded");
    }
}