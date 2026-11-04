package net.prochicken.espercraft.stat;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import net.prochicken.espercraft.Espercraft;

public class ModStats {
    public static final Stat<?> FREQUENCIES_SCANNED_WITH_RADIO_SCANNER = makeCustomStat("frequencies_scanned_with_radio_scanner");
    public static final Stat<?> UNIQUE_DISCOVERIES_MADE = makeCustomStat("unique_discoveries_made");
    public static final Stat<?> SCANNED_WITH_MAGNIFIER = makeCustomStat("scanned_with_magnifier");

    private static Stat<?> makeCustomStat(String key) {
        Identifier identifier = Identifier.fromNamespaceAndPath(Espercraft.MOD_ID, key);
        Identifier newStat = Registry.register(BuiltInRegistries.CUSTOM_STAT, key, identifier);

        return Stats.CUSTOM.get(newStat, StatFormatter.DEFAULT);
    }

    public static void registerModStats() {
        Espercraft.LOGGER.info("Registering Mod Stats for " + Espercraft.MOD_ID);
    }
}
