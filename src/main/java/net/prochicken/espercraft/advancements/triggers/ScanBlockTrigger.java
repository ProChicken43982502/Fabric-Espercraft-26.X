// --- WARNING: Contains AI-generated code ---

package net.prochicken.espercraft.advancements.triggers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.advancements.triggers.SimpleCriterionTrigger;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class ScanBlockTrigger extends SimpleCriterionTrigger<ScanBlockTrigger.TriggerInstance> {

    @Override
    public Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player, BlockState scanned) {
        this.trigger(player, instance -> instance.matches(scanned));
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<HolderSet<Block>> block)
            implements SimpleCriterionTrigger.SimpleInstance {

        public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player),
                RegistryCodecs.homogeneousList(Registries.BLOCK).optionalFieldOf("block").forGetter(TriggerInstance::block)
        ).apply(instance, TriggerInstance::new));

        public boolean matches(BlockState scanned) {
            return this.block.isEmpty() || scanned.is(this.block.get());
        }
    }
}