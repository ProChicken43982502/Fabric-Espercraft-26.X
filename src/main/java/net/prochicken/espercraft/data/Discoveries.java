package net.prochicken.espercraft.data;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;

import java.util.HashSet;
import java.util.Set;

public class Discoveries {

    // Builds an ID like "minecraft:biome/cherry_grove" from a category and a thing's ID.
    public static Identifier id(String category, Identifier thing) {
        return Identifier.fromNamespaceAndPath(thing.getNamespace(), category + "/" + thing.getPath());
    }

    public static Identifier forBlock(Block block) {
        return id("block", BuiltInRegistries.BLOCK.getKey(block));
    }

    public static boolean has(ServerPlayer player, Identifier discovery) {
        return player.getAttachedOrElse(ModAttachments.DISCOVERIES, Set.of()).contains(discovery);
    }

    // Returns true if this was a NEW discovery, false if the player already had it.
    public static boolean discover(ServerPlayer player, Identifier discovery) {
        Set<Identifier> current = player.getAttachedOrElse(ModAttachments.DISCOVERIES, Set.of());
        if (current.contains(discovery)) return false;

        Set<Identifier> updated = new HashSet<>(current);
        updated.add(discovery);
        player.setAttached(ModAttachments.DISCOVERIES, Set.copyOf(updated));
        return true;
    }
}