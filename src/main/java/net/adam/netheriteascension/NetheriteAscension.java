package net.adam.netheriteascension;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetheriteAscension implements ModInitializer {
	public static final String MOD_ID = "netherite-ascension";
		public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Welcome to Netherite Ascension!");
	}
}