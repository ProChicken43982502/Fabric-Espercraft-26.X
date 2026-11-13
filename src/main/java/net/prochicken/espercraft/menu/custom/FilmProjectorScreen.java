package net.prochicken.espercraft.menu.custom;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.prochicken.espercraft.Espercraft;
import net.prochicken.espercraft.film.FilmGuiPreview;
import net.prochicken.espercraft.film.FilmSpools;
import net.prochicken.espercraft.film.FilmStyle;

public class FilmProjectorScreen extends AbstractContainerScreen<FilmProjectorMenu> {

    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Espercraft.MOD_ID, "textures/gui/film_projector/background.png");
    private static final Identifier FILM_SPOOL_SLOT_SPRITE = Identifier.fromNamespaceAndPath(Espercraft.MOD_ID, "film_projector/empty_slot_film_spool");

    public FilmProjectorScreen(FilmProjectorMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x, y, 0, 0, imageWidth, imageHeight, 256, 256);

        Slot filmSpoolSlot = ((FilmProjectorMenu)this.menu).getFilmSpoolSlot();
        Slot projectionSlot = ((FilmProjectorMenu) this.menu).getProjectionSlot();

        if (!filmSpoolSlot.hasItem()) {
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, FILM_SPOOL_SLOT_SPRITE, x + 51, y + 47, 16, 16);
        } else if (projectionSlot.hasItem()) {
            FilmStyle style = FilmSpools.styleFor(((FilmProjectorMenu) this.menu).getFilmSpoolSlot().getItem());
            FilmGuiPreview.begin(style);
            try {
                float scale = 2.0f;

                graphics.pose().pushMatrix();
                graphics.pose().translate(x + 128, y + 55);   // the centre of the old 16x16 spot
                graphics.pose().scale(scale, scale);
                graphics.item(projectionSlot.getItem(), -8, -8);
                graphics.pose().popMatrix();
            } finally {
                FilmGuiPreview.end();
            }
        }
    }
}