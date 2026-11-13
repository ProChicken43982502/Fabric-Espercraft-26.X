package net.prochicken.espercraft.menu;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.MenuType;
import net.prochicken.espercraft.Espercraft;
import net.prochicken.espercraft.menu.custom.FilmProjectorMenu;

public class ModMenuTypes {

    public static final MenuType<FilmProjectorMenu> FILM_PROJECTOR_MENU =
            Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(Espercraft.MOD_ID, "film_projector_menu"),
                    new ExtendedMenuType<>(FilmProjectorMenu::new, BlockPos.STREAM_CODEC));

    public static void registerModMenuTypes() {
        Espercraft.LOGGER.info("Loading ModMenuTypes for" + Espercraft.MOD_ID);
    }
}
