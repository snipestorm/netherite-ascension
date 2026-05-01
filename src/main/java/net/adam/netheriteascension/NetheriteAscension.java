package net.adam.netheriteascension;

import net.adam.netheriteascension.item.ModCreativeModeTabs;
import net.adam.netheriteascension.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetheriteAscension implements ModInitializer {
	public static final String MOD_ID = "netherite-ascension";
		public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Welcome to Netherite Ascension!");
		ModItems.registerModItems();
		ModCreativeModeTabs.registerCreativeModeTabs();
	}
}