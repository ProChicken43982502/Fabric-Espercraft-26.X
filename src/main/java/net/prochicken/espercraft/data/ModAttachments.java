package net.prochicken.espercraft.data;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.resources.Identifier;
import net.prochicken.espercraft.Espercraft;

import java.util.List;
import java.util.Set;

public class ModAttachments {
    public static final AttachmentType<Set<Identifier>> DISCOVERIES = AttachmentRegistry.create(
            Espercraft.id("discoveries"),
            builder -> builder
                    .persistent(Identifier.CODEC.listOf().xmap(Set::copyOf, List::copyOf))
                    .copyOnDeath()
    );

    public static void registerAttachments() {
        Espercraft.LOGGER.info("Registering attachments for " + Espercraft.MOD_ID);
    }
}