// --- WARNING: Contains AI-generated code ---

package net.prochicken.espercraft.film;

import org.jspecify.annotations.Nullable;

public class FilmGuiPreview {
    private static @Nullable FilmStyle current = null;

    public static void begin(FilmStyle style) {
        current = style == FilmStyle.NATURAL ? null : style;
    }

    public static void end() {
        current = null;
    }

    public static @Nullable FilmStyle current() {
        return current;
    }
}