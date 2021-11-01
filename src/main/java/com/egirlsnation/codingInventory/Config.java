package com.egirlsnation.codingInventory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public final class Config {

	private static CodingInventory plugin;
	public static FileConfiguration cfg;
	private static File cfgFile;
	private static boolean debug;
	public static final int ITEM_DEFAULT = -1;
	public static final int ITEM_INFINITE = -2;
	private static int cfgStack;
	private static ConfigurationSection cfgMessagesMap;

	public static void init(CodingInventory ci) {
		plugin = ci;

		cfgFile = new File(plugin.getDataFolder() + File.separator + "config.yml");
		cfg = YamlConfiguration.loadConfiguration(cfgFile);

		loadCfg();

	}

	public static void loadCfg() {

		try {
			cfg.load(cfgFile);
			cfgStack = cfg.getInt("stack-size");
			cfgMessagesMap = cfg.getConfigurationSection("messages");
			debug = cfg.getBoolean("debug-messages");
		} catch (FileNotFoundException e) {
			setupCfg();
		} catch (IOException | InvalidConfigurationException e) {
			plugin.log.warning("IO exception while reading plugin config");
			e.printStackTrace();
		}

	}

	// Sets up the default variables if they don't exist yet.
	public static void setupCfg() {
		// Print debug message
		plugin.log.warning("config.yml not found. Creating a new one");

		// Add sexy ass header with instructions for idiots to follow
		String header = "General options:\n"
				+ "messages section, you can edit the messages if you want custom funny ones\n"
				+ "stack-size, maximum stack size that the plugin will handle before defaulting to vanilla\n";
		cfg.options().header(header);

		cfg.addDefault("debug-messages", false);
		cfg.addDefault("stack-size", 64);

		// messages section keys and values
		Map<String, Object> messages = new HashMap<String, Object>();
		messages.put("no-perm", "BAKA! You don't have permission to use this command!");
		messages.put("no-player", "NANI, Only players can use this command!");
		messages.put("no-item", "BRUH, please hold an item in your hand!");
		messages.put("stacked", "YAY, now your items are stacked :)");
		// janky ass code but should work to add values to message section
		cfg.createSection("messages");
		for (String i : messages.keySet()) {
			cfg.getConfigurationSection("messages").addDefault(i, messages.get(i));
		}

		if (!cfg.isSet("messages") || !cfg.isSet("stack-size") || !cfg.isSet("debug-messages")) {
			cfg.options().copyDefaults(true);
			saveConfig(cfg, cfgFile);
		}
		
		loadCfg();

	}

	public static int getItemMax(Material mat) {

		int max = ITEM_DEFAULT;

		// Force air to keep default value
		if (mat != Material.AIR) {
			// Check player
			// String uuid = player.getUniqueId().toString();

			max = getMax();

			// Handle invalid max
			if (max <= ITEM_DEFAULT && max != ITEM_INFINITE) {
				// Invalid max, count as default
				max = ITEM_DEFAULT;
			}
		}

		return max;
	}

	public static int getMax() {

		// Dont allow more than item stack max defined in config
		if (cfgStack <= 64) {
			return cfgStack;
		}

		return ITEM_DEFAULT;

	}

	public static String getMessage(String key) {
		String result = "";

		try {
			result = cfgMessagesMap.getString(key);
		} catch (Exception ex) {
			plugin.log.warning("Error getting requested message from config.");
			ex.printStackTrace();
		}

		return result;
	}

	public static void saveConfig(FileConfiguration fileConfig, File file) {
		try {
			fileConfig.save(file);
		} catch (IOException e) {
			plugin.log.warning("Error writing config file.");
			e.printStackTrace();
		}
	}
	
	public static boolean isDebugging() {
		return debug;
	}

}
