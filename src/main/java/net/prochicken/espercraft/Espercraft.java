package net.prochicken.espercraft;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import net.prochicken.espercraft.creativemodetab.ModCreativeModeTabs;
import net.prochicken.espercraft.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Espercraft implements ModInitializer {
	public static final String MOD_ID = "espercraft";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModCreativeModeTabs.registerModCreativeModeTabs();

		LOGGER.info("Loading Mod " + Espercraft.MOD_ID);
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
