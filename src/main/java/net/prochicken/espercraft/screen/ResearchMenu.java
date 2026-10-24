package net.prochicken.espercraft.screen;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.prochicken.espercraft.block.ModBlocks;
import org.jspecify.annotations.NonNull;

public class ResearchMenu extends CraftingMenu {
    private final ContainerLevelAccess access;

    public ResearchMenu(int containerId, Inventory inventory, ContainerLevelAccess access) {
        super(containerId, inventory, access);
        this.access = access;
    }

    @Override
    public boolean stillValid(@NonNull Player player) {
        return ResearchMenu.stillValid(this.access, player, ModBlocks.RESEARCH_TABLE);
    }
}