// --- WARNING: Contains AI-generated code ---

package net.prochicken.espercraft.film;

public enum FilmStyle {
    //          name          tint      saturation  contrast
    NATURAL   ("Natural",   0xFFFFFF, 1.1f,       1.05f),
    GREYSCALE ("Greyscale", 0xFFFFFF, 0.0f, 1.0f),
    MONOTONE  ("Monotone",  0xD9B38C, 0.0f,       1.0f),
    OCEAN     ("Ocean",     0x7FB8FF, 1.1f,       1.05f);

    private final String displayName;
    private final int tint;
    private final float saturation;
    private final float contrast;

    FilmStyle(String displayName, int tint, float saturation, float contrast) {
        this.displayName = displayName;
        this.tint = tint;
        this.saturation = saturation;
        this.contrast = contrast;
    }

    public String getDisplayName() { return displayName; }
    public int getTint() { return tint; }
    public float getSaturation() { return saturation; }
    public float getContrast() { return contrast; }

    public FilmStyle next() {
        FilmStyle[] all = values();
        return all[(this.ordinal() + 1) % all.length];
    }

    public static FilmStyle byName(String name) {
        for (FilmStyle style : values()) {
            if (style.name().equals(name)) return style;
        }
        return NATURAL;
    }
}