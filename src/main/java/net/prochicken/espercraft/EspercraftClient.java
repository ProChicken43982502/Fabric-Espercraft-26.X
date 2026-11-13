package net.prochicken.espercraft;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.prochicken.espercraft.block.entity.ModBlockEntities;
import net.prochicken.espercraft.block.entity.renderer.FilmProjectorBlockEntityRenderer;
import net.prochicken.espercraft.film.FilmRenderTypes;
import net.prochicken.espercraft.menu.ModMenuTypes;
import net.prochicken.espercraft.menu.custom.FilmProjectorMenu;
import net.prochicken.espercraft.menu.custom.FilmProjectorScreen;
import net.prochicken.espercraft.screen.ScanningOverlay;

public class EspercraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScanningOverlay.init();
        FilmRenderTypes.init();

        BlockEntityRenderers.register(ModBlockEntities.FILM_PROJECTOR_BE, FilmProjectorBlockEntityRenderer::new);
        MenuScreens.register(ModMenuTypes.FILM_PROJECTOR_MENU, FilmProjectorScreen::new);

        Espercraft.LOGGER.info("Espercraft client loaded");
    }
}