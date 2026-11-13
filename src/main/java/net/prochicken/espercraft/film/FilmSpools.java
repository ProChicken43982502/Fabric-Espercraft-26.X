// --- WARNING: Contains AI-generated code ---

package net.prochicken.espercraft.film;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.prochicken.espercraft.item.ModItems;

import java.util.Map;

public class FilmSpools {

    private static final Map<Item, FilmStyle> STYLES = Map.of(
            ModItems.FILM_SPOOL, FilmStyle.NATURAL,
            ModItems.OLD_TIMEY_FILM_SPOOL, FilmStyle.GREYSCALE
    );

    /** The style a spool gives. Empty slot or unknown item means Natural. */
    public static FilmStyle styleFor(ItemStack spool) {
        return STYLES.getOrDefault(spool.getItem(), FilmStyle.NATURAL);
    }
}