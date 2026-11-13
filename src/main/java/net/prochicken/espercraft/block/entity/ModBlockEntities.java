package net.prochicken.espercraft.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.prochicken.espercraft.Espercraft;
import net.prochicken.espercraft.block.ModBlocks;
import net.prochicken.espercraft.block.entity.custom.FilmProjectorBlockEntity;

public class ModBlockEntities {

    public static final BlockEntityType<FilmProjectorBlockEntity> FILM_PROJECTOR_BE =
           Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(Espercraft.MOD_ID, "film_projector_be"),
                   FabricBlockEntityTypeBuilder.create(FilmProjectorBlockEntity::new, ModBlocks.FILM_PROJECTOR_BLOCK).build());

    public static void registerBlockEntities() {
        Espercraft.LOGGER.info("Registering ModBlockEntities for " + Espercraft.MOD_ID);
    }
}
