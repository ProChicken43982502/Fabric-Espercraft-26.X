// --- WARNING: Contains AI-generated code ---

package net.prochicken.espercraft.film;

import net.minecraft.client.renderer.texture.OverlayTexture;

public class FilmOverlay {
    // Rows 11-15 of the overlay texture look identical to "no overlay".
    // Vanilla only uses rows 3 (hurt) and 10 (normal), so these are free.
    private static final int FIRST_ROW = 11;
    private static final int LAST_ROW = 15;

    /** Packs a style into an overlay value. Natural doesn't need one. */
    public static int encode(FilmStyle style) {
        if (style == FilmStyle.NATURAL) {
            return OverlayTexture.NO_OVERLAY;
        }
        return OverlayTexture.pack(0, 10 + style.ordinal());
    }

    /** True if this overlay value was made by encode(). */
    public static boolean isFilm(int overlay) {
        int u = overlay & 0xFFFF;
        int v = overlay >>> 16;
        return u == 0 && v >= FIRST_ROW && v <= LAST_ROW;
    }

    public static FilmStyle decode(int overlay) {
        FilmStyle[] all = FilmStyle.values();
        int index = (overlay >>> 16) - 10;
        return index >= 0 && index < all.length ? all[index] : FilmStyle.NATURAL;
    }

    private static final int TINT_INDEX = 15;                  // far above any real tint index
    private static final int TINT_MAGIC = 0x7A465400;

    /** Hides a style in the tint list of an item's render state. */
    public static void tagTints(it.unimi.dsi.fastutil.ints.IntList tints, FilmStyle style) {
        while (tints.size() <= TINT_INDEX) {
            tints.add(-1);
        }
        tints.set(TINT_INDEX, TINT_MAGIC | style.ordinal());
    }

    public static boolean hasTintTag(int[] tints) {
        return tints.length > TINT_INDEX && (tints[TINT_INDEX] & 0xFFFFFF00) == TINT_MAGIC;
    }

    public static FilmStyle decodeTintTag(int[] tints) {
        FilmStyle[] all = FilmStyle.values();
        int index = tints[TINT_INDEX] & 0xFF;
        return index < all.length ? all[index] : FilmStyle.NATURAL;
    }
}