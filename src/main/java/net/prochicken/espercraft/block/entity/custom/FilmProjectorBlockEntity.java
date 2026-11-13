// --- WARNING: Contains AI-generated code ---

package net.prochicken.espercraft.block.entity.custom;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.prochicken.espercraft.block.custom.FilmProjectorBlock;
import net.prochicken.espercraft.block.entity.ImplementedInventory;
import net.prochicken.espercraft.block.entity.ModBlockEntities;
import net.prochicken.espercraft.film.FilmSpools;
import net.prochicken.espercraft.film.FilmStyle;
import net.prochicken.espercraft.menu.custom.FilmProjectorMenu;
import net.prochicken.espercraft.tags.ModTags;
import org.jspecify.annotations.Nullable;

public class FilmProjectorBlockEntity extends BlockEntity implements ExtendedMenuProvider<BlockPos>, ImplementedInventory {

    public NonNullList<ItemStack> inventory = NonNullList.withSize(2, ItemStack.EMPTY);

    private static final int PROJECTION_SLOT = 0;
    private static final int FILM_SLOT = 1;

    private final ContainerData data;
    private FilmStyle style;

    public FilmProjectorBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.FILM_PROJECTOR_BE, worldPosition, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int dataId) {
                return 0;
            }

            @Override
            public void set(int dataId, int value) {

            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public FilmStyle getStyle() {
        return FilmSpools.styleFor(inventory.get(FILM_SLOT));
    }

    public boolean hasFilmSpool() {
        return !inventory.get(FILM_SLOT).isEmpty();
    }

    public boolean isProjecting() {
        return hasFilmSpool() && getBlockState().getValue(FilmProjectorBlock.POWERED);
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public void clearContent() {
        for (int i = 0; i < inventory.size(); i++) {
            inventory.set(i, ItemStack.EMPTY);
        }
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        if (!inventory.get(slot).isEmpty()) {
            return false;   // one item per slot: an occupied slot takes nothing more
        }
        if (slot == FILM_SLOT) {
            return stack.is(ModTags.Items.FILM_SPOOL);
        }
        return true;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.espercraft.film_projector");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new FilmProjectorMenu(containerId, inventory, this, this.data);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayer player) {
        return this.worldPosition;
    }

    // Saving Data

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, this.inventory);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        for (int i = 0; i < inventory.size(); i++) {
            inventory.set(i, ItemStack.EMPTY);
        }
        ContainerHelper.loadAllItems(input, this.inventory);
    }

    public void drops() {
        Containers.dropContents(this.level, this.worldPosition, this.inventory);
    }

    // Block Entity Sync Methods

    @Override
    public void setChanged() {
        super.setChanged();
        if (!level.isClientSide()) {
            level.sendBlockUpdated(worldPosition, this.getBlockState(), this.getBlockState(), 3);
        }
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }
}
