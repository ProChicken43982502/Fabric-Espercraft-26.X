package net.prochicken.espercraft.block.entity.renderer;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.level.Level;
import net.prochicken.espercraft.film.FilmStyle;

public class FilmProjectorBlockEntityRenderState extends BlockEntityRenderState {
    public Level level;
    public float rotation;
    public int blockRotation;
    public FilmStyle style = FilmStyle.NATURAL;

    final ItemStackRenderState itemStackRenderState = new ItemStackRenderState();
}
