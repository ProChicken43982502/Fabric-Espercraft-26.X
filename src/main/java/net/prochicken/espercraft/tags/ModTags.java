package net.prochicken.espercraft.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.prochicken.espercraft.Espercraft;

public class ModTags {
    public static class Blocks {
        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Espercraft.MOD_ID, name));
        }
    }
    public static class Items {
        public static final TagKey<Item> CHARGES_RADIO_SCANNER = createTag("charges_radio_scanner");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Espercraft.MOD_ID, name));
        }
    }
}
